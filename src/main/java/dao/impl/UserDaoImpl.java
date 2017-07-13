package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

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
        userProfile.setName((String)userProfileInMongo.get("name"));
        userProfile.setGender((String)userProfileInMongo.get("gender"));
        userProfile.setMobile((String)userProfileInMongo.get("mobile"));
        List<FullAddress> deliveryAddress = new ArrayList<FullAddress>();
        List<Map> deliveryAddressListMap = (List<Map>)userProfileInMongo.get("deliveryAddress");
        for(Map tmp1 : deliveryAddressListMap) {
            FullAddress tmp2 = new FullAddress();
            tmp2.setProvince((String)tmp1.get("province"));
            tmp2.setCity((String)tmp1.get("city"));
            tmp2.setDistrict((String)tmp1.get("district"));
            tmp2.setAddress((String)tmp1.get("address"));
            tmp2.setIsDefault((Boolean)tmp1.get("isDefault"));
            deliveryAddress.add(tmp2);
        }
        userProfile.setDeliveryAddress(deliveryAddress);
        return userProfile;
    }
    
    @Override
    public boolean saveUserProfile(UserProfile newUserProfile) {
        // 不涉及用户图片
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
        // 不涉及用户图片
        DBCollection collection = getMongoDb().getCollection("user_profile");
        Integer userID = newUserProfile.getUserID();
        User oldUser = userID==null? null:this.getUserById(userID);
        User newUser;
        Map userProfileInMongo;
        
        newUser = oldUser;
            
        DBObject query=new BasicDBObject("_id", new ObjectId(oldUser.getProfileID()));
        DBObject obj = collection.findOne(query);
        userProfileInMongo = (obj!=null) ? (Map)obj : null;
            
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
        collection.update(query, document);
        profileID = oldUser.getProfileID();
        
        // 用户在mysql中的属性
        newUser.setNickName(newUserProfile.getNickName());
        newUser.setPassword(newUserProfile.getPassword());   // !!!!!
        newUser.setEmail(newUserProfile.getEmail());
        newUser.setCredit(newUserProfile.getCredit());
        newUser.setRole(newUserProfile.getRole());
        newUser.setProvince(newUserProfile.getProvince());
        newUser.setCity(newUserProfile.getCity());
        newUser.setDistrict(newUserProfile.getDistrict());
        newUser.setAddress(newUserProfile.getAddress());
        
        //newUser.setProfileID(profileID);
        
        this.update(newUser);
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

}
