package common.cache;

import java.util.List;
import model.Category1;

public class AllBookCategory {
    private static List<Category1> allBookCategory = null;

    public static List<Category1> getAllBookCategory() {
        return allBookCategory;
    }

    public static void setAllBookCategory(List<Category1> allBookCategory) {
        AllBookCategory.allBookCategory = allBookCategory;
    }
    
}