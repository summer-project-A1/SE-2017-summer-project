package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

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
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public boolean saveUserProfile(int userID, Map userProfile) {
        // TODO 自动生成的方法存根
        return false;
    }

}