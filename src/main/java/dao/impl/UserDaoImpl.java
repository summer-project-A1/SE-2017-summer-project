package dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

import common.constants.UserStatus;
import model.FullAddress;
import model.User;
import model.UserProfile;
import dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
	public User getUserById(int userID) {
		String hql = "from User u where u.userID = :userID";
		Query query = getSession().createQuery(hql).setParameter("userID", userID);
		List<User> users = query.list();
		User user = users.size() == 1 ? users.get(0) : null;
		return user;
	}

    @Override
    public User getUserByEmail(String email) {
        String hql = "from User where email = :email";
        Query query = getSession().createQuery(hql).setParameter("email", email);
        List result = query.list();
        if(result.size() == 1) {
            return (User)result.get(0);
        } 
        return null;
    }

    @Override
    public UserProfile getUserProfile(int userID) {
        User user = this.getUserById(userID);
        DBCollection collection = getMongoDb().getCollection("user_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(user.getProfileID()));
        DBObject obj = collection.findOne(query);
        Map userProfileInMongo = (obj!=null) ? (Map)obj : null;
        
        UserProfile userProfile = new UserProfile();
        userProfile.setUserID(user.getUserID());
        userProfile.setNickName(user.getNickName());
        userProfile.setEmail(user.getEmail());
        userProfile.setCredit(user.getCredit());
        userProfile.setRole(user.getRole());
        userProfile.setUserRole(user.getRole().toString());
        userProfile.setImageID(user.getImageID());
        userProfile.setProvince(user.getProvince());
        userProfile.setCity(user.getCity());
        userProfile.setDistrict(user.getDistrict());
        userProfile.setAddress(user.getAddress());
        if(userProfileInMongo != null) {
            userProfile.setName((String)userProfileInMongo.get("name"));
            userProfile.setGender((String)userProfileInMongo.get("gender"));
            userProfile.setMobile((String)userProfileInMongo.get("mobile"));
        }
        else {
            userProfile.setName("");
            userProfile.setGender("");
            userProfile.setMobile("");
        }
        List<FullAddress> deliveryAddress = new ArrayList<FullAddress>();
        List<Map> deliveryAddressListMap = (List<Map>)userProfileInMongo.get("deliveryAddress");
        if(deliveryAddressListMap != null) {
            for(Map tmp1 : deliveryAddressListMap) {
                FullAddress tmp2 = new FullAddress(tmp1);
                deliveryAddress.add(tmp2);
            }
        }
        userProfile.setDeliveryAddress(deliveryAddress);
        return userProfile;
    }
    
    @Override
    public boolean saveUserProfile(UserProfile newUserProfile) {
        // 不涉及用户图片和密码
        DBCollection collection = getMongoDb().getCollection("user_profile");
        Map userProfileInMongo;
        userProfileInMongo = new HashMap();
        
        // 用户在mongodb中的属性
        userProfileInMongo.put("name", newUserProfile.getName());
        userProfileInMongo.put("gender", newUserProfile.getGender());
        userProfileInMongo.put("mobile", newUserProfile.getMobile());
        List<Map> deliveryAddress = new ArrayList<Map>();
        for(FullAddress tmp1 : newUserProfile.getDeliveryAddress()) {
            Map tmp2 = new HashMap();
            tmp2.put("province", tmp1.getProvince());
            tmp2.put("city", tmp1.getCity());
            tmp2.put("district", tmp1.getDistrict());
            tmp2.put("district", tmp1.getAddress());
            tmp2.put("isDefault", tmp1.getIsDefault());
            deliveryAddress.add(tmp2);
        }
        userProfileInMongo.put("deliveryAddress", deliveryAddress);
        
        String profileID;    // mongodb部分的id
        BasicDBObject document = new BasicDBObject(userProfileInMongo);
        collection.insert(document);
        profileID = ((ObjectId)document.get("_id")).toString();
        
        // 用户在mysql中的属性
        User newUser = new User();
        newUser.setNickName(newUserProfile.getNickName());
        newUser.setPassword(newUserProfile.getPassword());   // !!!!!
        newUser.setEmail(newUserProfile.getEmail());
        newUser.setCredit(newUserProfile.getCredit());
        newUser.setRole(newUserProfile.getRole());
        newUser.setProvince(newUserProfile.getProvince());
        newUser.setCity(newUserProfile.getCity());
        newUser.setDistrict(newUserProfile.getDistrict());
        newUser.setAddress(newUserProfile.getAddress());
        
        newUser.setProfileID(profileID);
        newUser.setImageID("");
        
        // 添加或更新
        this.save(newUser);
        return true;
    }
    
    @Override
    public boolean updateUserProfile(UserProfile newUserProfile) {
        // 不涉及用户图片和密码
        DBCollection collection = getMongoDb().getCollection("user_profile");
        Integer userID = newUserProfile.getUserID();
        User oldUser = userID==null? null:this.getUserById(userID);
        Map userProfileInMongo;
            
        DBObject query=new BasicDBObject("_id", new ObjectId(oldUser.getProfileID()));
        DBObject obj = collection.findOne(query);
        userProfileInMongo = (obj!=null) ? (Map)obj : new HashMap();
            
        // 用户在mongodb中的属性
        userProfileInMongo.put("name", newUserProfile.getName());
        userProfileInMongo.put("gender", newUserProfile.getGender());
        userProfileInMongo.put("mobile", newUserProfile.getMobile());
        if(newUserProfile.getDeliveryAddress() != null) {
            List<Map> deliveryAddress = new ArrayList<Map>();
            for(FullAddress tmp1 : newUserProfile.getDeliveryAddress()) {
                deliveryAddress.add(tmp1.toMap());
            }
            userProfileInMongo.put("deliveryAddress", deliveryAddress);
        }
        
        String profileID;    // mongodb部分的id
        BasicDBObject document = new BasicDBObject(userProfileInMongo);
        collection.update(query, document);
        profileID = oldUser.getProfileID();
        
        // 用户在mysql中的属性
        oldUser.setNickName(newUserProfile.getNickName());
        oldUser.setProvince(newUserProfile.getProvince());
        oldUser.setCity(newUserProfile.getCity());
        oldUser.setDistrict(newUserProfile.getDistrict());
        oldUser.setAddress(newUserProfile.getAddress());
        
        this.update(oldUser);
        return true;
    }
    
    @Override
    public boolean deleteUserProfileInMongo(String profileID) {
        DBCollection collection = getMongoDb().getCollection("user_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(profileID));
        collection.remove(query);
        return true;
    }
    
    @Override
    public boolean deleteUser(User user) {
        // 需要删除mongodb中的user_profile、delivery_address、image，再删除mysql中的信息
        if(user == null) {
            return false;
        }
        
        DBCollection profileCollection = getMongoDb().getCollection("user_profile");
        if(user.getProfileID() != null && !user.getProfileID().equals("")) {
            DBObject profileQuery=new BasicDBObject("_id", new ObjectId(user.getProfileID()));
            profileCollection.remove(profileQuery);
        }
        
        DBCollection addressCollection = getMongoDb().getCollection("delivery_address");
        DBObject addressQuery=new BasicDBObject("userID", user.getUserID());
        addressCollection.remove(addressQuery);
        
        DB db = this.getMongoDb();
        GridFS gridFS = new GridFS(db);
        if(user.getImageID() != null && !user.getImageID().equals("")) {
            DBObject query=new BasicDBObject("_id", new ObjectId(user.getImageID()));
            GridFSDBFile gridFSDBFile = gridFS.findOne(query);
            if(gridFSDBFile != null) {
                gridFS.remove(gridFSDBFile);
                return true;
            }
        }
        
        this.delete(user);
        
        return true;
    }

    @Override
	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = getSession().createQuery(hql);
		List<User> users = query.list();
		return users;
	}

    @Override
    public List<User> getAllTimeoutUnactiveUser() {
        String hql = "from User where status = :status and due < :now";
        Query query = this.getSession().createQuery(hql);
        query.setParameter("status", UserStatus.UNACTIVATED.ordinal());
        query.setParameter("now", new Date());
        List<User> users = query.list();
        return users;
    }
    
    @Override
    public Map getUserProfileMapInMongo(int userID) {
        DBCollection collection = getMongoDb().getCollection("user_profile");
        DBObject query=new BasicDBObject("userID", (Integer)userID);
        DBObject obj = collection.findOne(query);
        Map userProfile = (obj!=null) ? (Map)obj : null;
        return userProfile;
    }

    @Override
    public String saveUserProfileInMongo(Map userProfile) {
        // 这里的userProfile只包括在mongodb中的属性！
        /*
        Iterator it = userProfile.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println((String)entry.getKey());
            System.out.println(entry.getValue());
        }
        */
        DBCollection collection = getMongoDb().getCollection("user_profile");
        BasicDBObject document = new BasicDBObject(userProfile);
        collection.insert(document);
        return ((ObjectId)document.get("_id")).toString();
    }

    @Override
    public boolean updateUserProfileInMongo(User user, Map userProfile) {
        // 这里的userProfile只包括在mongodb中的属性！

        DBCollection collection = getMongoDb().getCollection("user_profile");
        DBObject query=new BasicDBObject("_id", new ObjectId(user.getProfileID()));
        DBObject old = collection.findOne(query);

        BasicDBObject document = new BasicDBObject(userProfile);
        if(old!=null) {
            collection.update(query, document);
        }
        else {
            collection.insert(document);
            user.setProfileID(((ObjectId)document.get("_id")).toString());
            this.update(user);
        }
        return true;
    }

    @Override
    public FullAddress getDeliveryAddressByID(int userID, String fullAddressID) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        DBObject query=new BasicDBObject("_id", new ObjectId(fullAddressID));
        Map result = (Map)collection.findOne(query);
        return new FullAddress(result);
    }
    
    @Override
    public FullAddress getDefaultDeliveryAddress(int userID) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        DBObject query=new BasicDBObject();
        query.put("userID", userID);
        query.put("isDefault", true);
        Map result = (Map)collection.findOne(query);
        return result==null? null : new FullAddress(result);
    }
    
    @Override
    public FullAddress addDeliveryAddress(int userID, FullAddress fullAddress) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        Map fullAddressMap = fullAddress.toMap();
        fullAddressMap.put("userID", userID);
        BasicDBObject document = new BasicDBObject(fullAddressMap);
        collection.insert(document);
        return new FullAddress((Map)document);
    }
    
    @Override
    public List<FullAddress> getAllDeliveryAddress(int userID) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        DBObject query=new BasicDBObject("userID", userID);
        List<DBObject> allDeliveryAddress = collection.find(query).toArray();
        List<FullAddress> fullAddressList = new ArrayList<FullAddress>();
        for(DBObject tmp : allDeliveryAddress) {
            fullAddressList.add(new FullAddress((Map)tmp));
        }
        return fullAddressList;
    }
    
    @Override
    public boolean removeDeliveryAddress(int userID, String fullAddressID) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        DBObject query=new BasicDBObject();
        query.put("_id", new ObjectId(fullAddressID));
        query.put("userID", userID);
        collection.remove(query);
        return true;
    }
    
    @Override
    public boolean setDefaultDeliveryAddress(int userID, String fullAddressID) {
        DBCollection collection = getMongoDb().getCollection("delivery_address");
        // 去掉旧的默认地址的默认属性
        DBObject queryDefault=new BasicDBObject();
        queryDefault.put("userID", userID);
        queryDefault.put("isDefault", true);
        DBObject defaultDelivery = collection.findOne(queryDefault);
        if(defaultDelivery != null) {
            defaultDelivery.put("isDefault", false);
            DBObject queryOne = new BasicDBObject("_id", defaultDelivery.get("_id"));
            collection.update(queryOne, defaultDelivery);
        }
        // 新地址添加默认属性
        DBObject query=new BasicDBObject();
        query.put("_id", new ObjectId(fullAddressID));
        query.put("userID", userID);
        DBObject newDefaultDelivery = collection.findOne(query);
        newDefaultDelivery.put("isDefault", true);
        collection.update(query, newDefaultDelivery);
        return true;
    }
}
