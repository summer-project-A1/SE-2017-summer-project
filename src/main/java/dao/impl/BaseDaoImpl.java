package dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.mongodb.DB;

import dao.BaseDao;

public class BaseDaoImpl implements BaseDao {
    protected SessionFactory sessionFactory;
    protected MongoTemplate mongoTemplate;

    /* ========================================================= */
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    /* ===================== */
    
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public DB getMongoDb() {
        return this.mongoTemplate.getDb();
    }
    
    /* ============================================================ */
    
    public boolean save(Object obj) {
        getSession().save(obj);
        return true;
    }
    
    public boolean update(Object obj) {
        getSession().update(obj);
        return true;
    }
    
    public boolean delete(Object obj) {
        getSession().delete(obj);
        return true;
    }

}