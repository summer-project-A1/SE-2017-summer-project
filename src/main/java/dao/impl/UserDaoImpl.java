package dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.hibernate.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import model.User;

import dao.BaseDao;
import dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
	public User getUserById(int userID) {
		String hql = "from User u where u.userID = :userID";
		Query query = getSession().createQuery(hql).setParameter("userID", userID);
		List<User> users = query.list();
		User user = users.size() == 0 ? users.get(0) : null;
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

	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = getSession().createQuery(hql);
		List<User> users = query.list();
		return users;
	}

    @Override
    public Map getUserProfileMap(int userID) {
        DBCollection collection = getMongoDb().getCollection("user_profile");
        DBObject query=new BasicDBObject("userID", (Integer)userID);
        DBObject obj = collection.findOne(query);
        Map userProfile = (obj!=null) ? (Map)obj : null;
        return userProfile;
    }

    @Override
    public String saveUserProfile(Map userProfile) {
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
    public boolean updateUserProfile(User user, Map userProfile) {
        // 这里的userProfile只包括在mongodb中的属性！
        Iterator it = userProfile.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println((String)entry.getKey());
            System.out.println(entry.getValue());
        }
        
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
