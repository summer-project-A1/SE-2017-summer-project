package dao;

import java.util.List;
import model.Category1;
import model.Category2;

public interface CategoryDao extends BaseDao {
    public List<Category1> getAllCategory();
    public Category1 getCategory1ByID(int category1ID);
    public Category2 getCategory2ByID(int category2ID);
}