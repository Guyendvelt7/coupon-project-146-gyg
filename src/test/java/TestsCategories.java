import clients.beans.Category;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static clients.beans.CategoryClass.addCategory;

public class TestsCategories {
    private static List<Category> categories;
    @BeforeClass
    public static void createCategories(){
        categories= Arrays.stream(Category.values()).collect(Collectors.toList());
        for (Category item : categories) {
            addCategory(item);
        }
    }

    @Test
    public void isCategoryCreated(){
        assert categories != null;
    }

}
