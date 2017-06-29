package dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

import model.User;

import dao.BaseDao;
import dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
	public User getUserById(int id) {
		String hql = "from User u where u.id= :id";
		Query query = getSession().createQuery(hql).setParameter("id", id);
		List<User> users = query.list();
		User user = users.size() > 0 ? users.get(0) : null;
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
    public Boolean saveUserProfile(int userID, Map userProfile) {
        // TODO 自动生成的方法存根
        return null;
    }

}
