package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import model.User;

import dao.BaseDao;
import dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {

	public Integer save(User user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public void update(User user) {
		getHibernateTemplate().merge(user);
	}

	public User getUserById(int id) {
		String hql = "from User u where u.id= :id";
		Query query = getSession().createQuery(hql).setParameter("id", id);
		List<User> users = query.list();
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

    public User getUserByUsername(String username) {
        String hql = "from User where username = :username";
        Query query = getSession().createQuery(hql).setParameter("username", username);
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

}
