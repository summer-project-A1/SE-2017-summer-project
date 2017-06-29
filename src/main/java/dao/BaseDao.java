package dao;

public interface BaseDao {
    
    public boolean save(Object obj);
    
    public boolean update(Object obj);
    
    public boolean delete(Object obj);

}