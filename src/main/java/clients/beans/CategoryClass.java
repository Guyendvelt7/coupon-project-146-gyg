package clients.beans;

import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryClass {


    public static void addCategory(Category category) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, Category.valueOf(category.name()));
        DBTools.runQuery(DBManager.ADD_CATEGORY, values);

    }
    public static void deleteCategory(int categoryId){
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, categoryId);
        DBTools.runQuery(DBManager.DELETE_CATEGORY, values);
    }
    public static int getCategoryId(Category category) throws IllegalArgumentException{
        int categoryId=0;
        Map<Integer, Object> map = new HashMap<>();
            try {

                map.put(1, Category.valueOf(category.name()));
                ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CATEGORY, map);
                assert resultSet != null;
                if (resultSet.next()) {
                    categoryId = resultSet.getInt("id");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        return categoryId;
    }
    public String getCategoryName(int categoryId){
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,categoryId);
        try {
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_CATEGORY_NAME,values);
            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("name");
            }} catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }
}
