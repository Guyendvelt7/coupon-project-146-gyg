package clients.dbDao;

import clients.beans.Category;
import clients.beans.Coupon;
import clients.dao.CouponsDAO;
import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {

    //todo: ConnectionPool connectionPool;
    //todo: add exceptions

    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon.getCompanyId());
        values.put(2, coupon.getCategory());
        values.put(3, coupon.getTitle());
        values.put(4, coupon.getDescription());
        values.put(5, coupon.getStartDate());
        values.put(6, coupon.getEndDate());
        values.put(7, coupon.getAmount());
        values.put(8, coupon.getPrice());
        values.put(9, coupon.getImage());
        DBTools.runQuery(DBManager.CREATE_NEW_COUPON, values);

    }

    @Override
    public void updateCoupon(Coupon coupon)  {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon.getCompanyId());
        values.put(2, coupon.getCategory());
        values.put(3, coupon.getTitle());
        values.put(4, coupon.getDescription());
        values.put(5, coupon.getStartDate());
        values.put(6, coupon.getEndDate());
        values.put(7, coupon.getAmount());
        values.put(8, coupon.getPrice());
        values.put(9, coupon.getImage());
        values.put(10, coupon.getId());

        DBTools.runQuery(DBManager.UPDATE_COUPON, values);
    }

    @Override
    public void deleteCoupon(int couponID)  {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponID);
        DBTools.runQuery(DBManager.DELETE_COUPON, values);
    }

    //todo: maybe split two methods, one for allCoupons and another for oneCoupon
    @Override
    public ArrayList<Coupon> getCoupons(String sql, Map<Integer, Object> values) {
    ArrayList<Coupon> coupons = new ArrayList<>();
    ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Coupon coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.valueOf(resultSet.getString("category_id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"));
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return coupons;
    }

    @Override
    public List<Coupon> getAllCoupons() {
       List<Coupon> coupons = new ArrayList<>();
       ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COUPONS);
       while(true){
           try {
               assert resultSet != null;
               if (!resultSet.next()) break;
               coupons.add(new Coupon(
                       resultSet.getInt("id"),
                       resultSet.getInt("company_id"),
                       Category.valueOf(resultSet.getString("category_id")),
                       resultSet.getString("title"),
                       resultSet.getString("description"),
                       resultSet.getDate("start_date"),
                       resultSet.getDate("end_date"),
                       resultSet.getInt("amount"),
                       resultSet.getDouble("price"),
                       resultSet.getString("image")));
           } catch (SQLException e) {
               System.out.println("SQL exception....");
           }

       }
       return coupons;
    }

    @Override
    public Coupon getOneCoupon() {
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COUPONS);
                try {
                    assert resultSet != null;
                    if (!resultSet.next()) {
                   return new Coupon(
                            resultSet.getInt("id"),
                            resultSet.getInt("company_id"),
                            Category.valueOf(resultSet.getString("category_id")),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),
                            resultSet.getInt("amount"),
                            resultSet.getDouble("price"),
                            resultSet.getString("image"));
                }} catch (SQLException e) {
                    System.out.println("SQL exception....");

                }
                return null;
            }


    @Override
    public void addCouponPurchase(int customerID, int couponID) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,customerID);
        values.put(2,couponID);
        DBTools.runQuery(DBManager.ADD_PURCHASED_COUPON, values);

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID){
        Map<Integer, Object> values = new HashMap<>();
        values.put(1,customerID);
        values.put(2,couponID);
        DBTools.runQuery(DBManager.DELETE_PURCHASED_COUPON, values);
    }
    public static ArrayList<Coupon> getCouponsByCompanyId(int companyId) {
        ArrayList<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> value = new HashMap<>();
        value.put(1, companyId);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_SINGLE_COMPANY, value);
        try {
            while (true) {
                assert resultSet != null;
                if (!resultSet.next()) break;
                Coupon coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.valueOf(resultSet.getString("category_id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"));
                coupons.add(coupon);
            }
        } catch (SQLException e) {
            System.out.println("SQL exception...");
        }
        return coupons;
    }
    public static List<Coupon> getCouponsByCustomerId(int customerID) {
        ArrayList<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COUPONS);
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    Coupon coupon = new Coupon(
                            resultSet.getInt("id"),
                            resultSet.getInt("company_id"),
                            Category.valueOf(resultSet.getString("category_id")),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getDate("start_date"),
                            resultSet.getDate("end_date"),
                            resultSet.getInt("amount"),
                            resultSet.getDouble("price"),
                            resultSet.getString("image"));
                    coupons.add(coupon);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        return coupons;
    }


}
