package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.CategoryDao;
import model.Category1;
import model.Category2;

public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    @Override
    public List<Category1> getAllCategory() {
        String hql = "from Category1";
        Query query = getSession().createQuery(hql);
        List<Category1> category = query.list();
        return category;
    }

    @Override
    public Category1 getCategory1ByID(int category1ID) {
        String hql = "from Category1 where category1ID = :category1ID";
        Query query = getSession().createQuery(hql).setParameter("cagetory1ID", category1ID);
        List<Category1> category1List = query.list();
        Category1 category1 = category1List.size()>=1? category1List.get(0) : null;
        return category1;
    }

    @Override
    public Category2 getCategory2ByID(int category2ID) {
        String hql = "from Category2 where category2ID = :category2ID";
        Query query = getSession().createQuery(hql).setParameter("cagetory2ID", category2ID);
        List<Category2> category2List = query.list();
        Category2 category2 = category2List.size()>=1? category2List.get(0) : null;
        return category2;
    }
    
}