package clients.beans;

import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * Group of methods concerning Category interface
 */
public class CategoryClass {

    /**
     * insert new category type to database
     *
     * @param category input from Category interface
     */
    public static void addCategory(Category category) {
        //todo: add if name== getValue return
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, Category.valueOf(category.name()));
        DBTools.runQuery(DBManager.ADD_CATEGORY, values);
    }

    /**
     * deletes category type from database
     *
     * @param categoryId input category ID
     */
    //todo: check if necessary
    public static void deleteCategory(int categoryId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, categoryId);
        DBTools.runQuery(DBManager.DELETE_CATEGORY, values);
    }

    /**
     * retrieve category id by name
     *
     * @param category input category from interface
     * @return category id integer
     */
    public static int getCategoryId(Category category) {
        int categoryId = 0;
        Map<Integer, Object> value = new HashMap<>();
        try {
            value.put(1, Category.valueOf(category.name()));
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CATEGORY, value);
            assert resultSet != null;
            if (resultSet.next()) {
                categoryId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoryId;
    }

    public String getCategoryName(int categoryId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, categoryId);
        try {
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_CATEGORY_NAME, values);
            assert resultSet != null;
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }
}
