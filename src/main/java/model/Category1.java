package model;

import java.util.List;
import java.util.Set;

public class Category1 {
    private int category1ID;
    private String category1Name;
    private List<Category2> category2List;

    /* ================================================= */
    
    public int getCategory1ID() {
        return category1ID;
    }
    public void setCategory1ID(int category1id) {
        category1ID = category1id;
    }
    public String getCategory1Name() {
        return category1Name;
    }
    public void setCategory1Name(String category1Name) {
        this.category1Name = category1Name;
    }
    public List<Category2> getCategory2List() {
        return category2List;
    }
    public void setCategory2List(List<Category2> category2List) {
        this.category2List = category2List;
    }
    
}