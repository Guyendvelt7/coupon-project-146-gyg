package clients.dbDao;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
import clients.beans.Category;
import clients.beans.CategoryClass;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Yoav Hacmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */
public class CouponsDBDAO implements CouponsDAO {
    CategoryClass categoryClass = new CategoryClass();

    /**
     * Checks the existence of a coupon in database by coupon ID
     *
     * @param id input coupon id
     * @return true or false
     */
    public boolean isCouponExists(int id) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, id);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_COUPON_BY_ID, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Checks if exist coupon with same title  in database
     * @param title input title
     * @return true/false
     */
    public boolean isTitleExist(String title) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, title);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_TITLE_OF_COUPONS, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Checks if customer already has purchased the coupon in database
     *
     * @param customerId input customer ID
     * @param couponId   input coupon ID
     * @return true or false
     */
    public boolean isCouponPurchased(int customerId, int couponId) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, customerId);
            values.put(2, couponId);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.IS_COUPON_PURCHASED, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * insert new coupon info to database
     *
     * @param coupon input coupon object
     * @throws CustomExceptions alerts user if coupon id or title already exist in database
     */
    @Override
    public void addCoupon(Coupon coupon) throws CustomExceptions {
        if (isCouponExists(coupon.getId())) {
            throw new CustomExceptions(EnumExceptions.COUPON_ALREADY_EXIST);
        }else if (isTitleExist(coupon.getTitle())){
            throw new CustomExceptions(EnumExceptions.COUPON_TITLE_EXIST);
        } else {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, coupon.getCompanyId());
            values.put(2, CategoryClass.getCategoryId(coupon.getCategory()));
            values.put(3, coupon.getTitle());
            values.put(4, coupon.getDescription());
            values.put(5, coupon.getStartDate());
            values.put(6, coupon.getEndDate());
            values.put(7, coupon.getAmount());
            values.put(8, coupon.getPrice());
            values.put(9, coupon.getImage());
            DBTools.runQuery(DBManager.CREATE_NEW_COUPON, values);
        }
    }

    /**
     * update coupons info in database
     *
     * @param coupon input coupon object
     * @throws CustomExceptions alerts user if coupon id not found in database
     */
    @Override
    public void updateCoupon(Coupon coupon) throws CustomExceptions {
        if (isCouponExists(coupon.getId())) {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, coupon.getCompanyId());
            values.put(2, CategoryClass.getCategoryId(coupon.getCategory()));
            values.put(3, coupon.getTitle());
            values.put(4, coupon.getDescription());
            values.put(5, coupon.getStartDate());
            values.put(6, coupon.getEndDate());
            values.put(7, coupon.getAmount());
            values.put(8, coupon.getPrice());
            values.put(9, coupon.getImage());
            values.put(10, coupon.getId());
            DBTools.runQuery(DBManager.UPDATE_COUPON, values);
        } else {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
    }

    /**
     * removes coupon from all tables in database.
     *
     * @param couponID input coupons ID for removal
     * @throws CustomExceptions alerts user if coupon id not found in database
     */
    @Override
    public void deleteCoupon(int couponID) throws CustomExceptions {
        if (isCouponExists(couponID)) {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, couponID);
            DBTools.runQuery(DBManager.DELETE_COUPON, values);
        } else {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
    }

    /**
     * retrieve list of all coupons from database
     *
     * @return list of coupons
     */
    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COUPONS);
        Coupon coupon = new Coupon();
        try {
            while (resultSet.next()) {
                coupon = getOneCoupon(resultSet.getInt("id"));
                coupons.add(coupon);
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return coupons;
    }


    /**
     * retrieve one coupon from database by ID
     *
     * @param coupon_id input coupon ID
     * @return coupon object info
     * @throws CustomExceptions alerts user if database is empty
     */
    @Override
    public Coupon getOneCoupon(int coupon_id) throws CustomExceptions {
        if (!isCouponExists(coupon_id)) {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS);
        }
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon_id);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_COUPON, values);
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                return new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.valueOf(categoryClass.getCategoryName(resultSet.getInt("category_id"))),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    /**
     * insert into database purchased coupon from customer and updates coupon amount in coupon table
     *
     * @param customerID input for adding coupon to database
     * @param couponID   input for adding coupon to database
     * @throws CustomExceptions if user tries to purchase a coupon for a second time or coupon is out of stock
     */
    @Override
    public void addCouponPurchase(int customerID, int couponID) throws CustomExceptions {
        Coupon coupon = getOneCoupon(couponID);
        if (coupon.getAmount() == 0) {
            throw new CustomExceptions(EnumExceptions.COUPONS_OUT_OF_STOCK);
        } else if (isCouponPurchased(customerID, couponID)) {
            throw new CustomExceptions(EnumExceptions.COUPON_PURCHASED);
        } else {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, customerID);
            values.put(2, couponID);
            DBTools.runQuery(DBManager.ADD_PURCHASED_COUPON, values);
            coupon.setAmount(coupon.getAmount() - 1);
            updateCoupon(coupon);
            System.out.println("coupon id " + couponID + " successfully purchased by customer " + customerID);
        }
    }

    /**
     * removes used coupon from database
     *
     * @param customerID input for removing coupon from customer
     * @param couponID   input for removing coupon by id
     * @throws CustomExceptions alerts user if database is empty
     */
    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws CustomExceptions {
        if (isCouponExists(couponID)) {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, customerID);
            values.put(2, couponID);
            DBTools.runQuery(DBManager.DELETE_PURCHASED_COUPON, values);
        } else {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
    }

    /**
     * retrieve all existing coupons from one company
     *
     * @param companyId input to locate company
     * @return list of company coupons
     */
    public List<Coupon> getCouponsByCompanyId(int companyId) {
        List<Coupon> couponsByCompany = new ArrayList<>();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_COUPONS_BY_COMPANIES, values);
        if (resultSet ==null){
            return null;
        }
        try {
            while (resultSet.next()) {
                couponsByCompany.add(getOneCoupon(resultSet.getInt("id")));
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return couponsByCompany;
    }

    /**
     * retrieve all coupons purchased by one specific customer
     *
     * @param customerID input to locate customer coupons
     * @return list of customer purchased coupons
     */
    public List<Coupon> getCouponsByCustomerId(int customerID) {
        List<Coupon> couponsByCustomer = new ArrayList<>();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_COUPONS_BY_CUSTOMER, values);
        if (resultSet ==null){
            return null;
        }
        try {
            while (resultSet.next()) {
                Coupon coupon = getOneCoupon(resultSet.getInt("coupon_id"));
                couponsByCustomer.add(coupon);
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return couponsByCustomer;
    }

}



