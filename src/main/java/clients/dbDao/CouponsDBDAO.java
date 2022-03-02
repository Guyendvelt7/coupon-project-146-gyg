package clients.dbDao;

import clients.CustomExceptions;
import clients.EnumExceptions;
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

public class CouponsDBDAO implements CouponsDAO {
    CompaniesDBDAO companiesDBDAO;
    CategoryClass categoryClass = new CategoryClass();

    /**
     * Checks the existence of a coupon in the table
     *
     * @param id use coupon id
     * @return true or false
     */
    public boolean isCouponExists(int id) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, id);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_COUPON, values);
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
     * @param coupon coupon object
     */
    @Override
    public void addCoupon(Coupon coupon) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        if (!isCouponExists(coupon.getId())) {
                values.put(1, coupon.getCompanyId());
                values.put(2, categoryClass.getCategoryId(coupon.getCategory()));
                values.put(3, coupon.getTitle());
                values.put(4, coupon.getDescription());
                values.put(5, coupon.getStartDate());
                values.put(6, coupon.getEndDate());
                values.put(7, coupon.getAmount());
                values.put(8, coupon.getPrice());
                values.put(9, coupon.getImage());
                DBTools.runQuery(DBManager.CREATE_NEW_COUPON, values);
        } else {
            throw new CustomExceptions(EnumExceptions.COUPON_ID_EXIST);
        }

    }

    /**
     * update coupons info in database
     *
     * @param coupon coupon object
     */
    @Override
    public void updateCoupon(Coupon coupon) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        if (isCouponExists(coupon.getId())) {
            values.put(1, coupon.getCompanyId());
            values.put(2, categoryClass.getCategoryId(coupon.getCategory()));
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
     * removes coupon from database
     *
     * @param couponID uses coupons ID for removal
     */
    @Override
    public void deleteCoupon(int couponID) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        if (isCouponExists(couponID)) {
            values.put(1, couponID);
            DBTools.runQuery(DBManager.DELETE_COUPON, values);
        } else {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
    }

    /**
     * gets all coupons from database by specified sql query
     *
     * @return arrayList of said coupons
     */
    @Override
    public List<Coupon> getAllCoupons() throws CustomExceptions {
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer,Object> param = new HashMap<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_COUPONS,param);
        while (true) {
            try {
                assert resultSet != null;
                if (!resultSet.next()) break;
                coupons.add(new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        Category.valueOf(categoryClass.getCategoryName(resultSet.getInt("category_id"))),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("start_date"),
                        resultSet.getInt("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")));
            } catch (SQLException e) {
                System.out.println("SQL exception....");
            }

        }
        return coupons;
    }

    /**
     * gets on coupon from database by ID
     *
     * @return coupon object
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
                        resultSet.getInt("start_date"),
                        resultSet.getInt("end_date"),
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
     * gets purchased coupon from customer and updates customer table and coupon amount in coupon table
     *
     * @param customerID for adding coupon to customer table
     * @param couponID   for removing amount from coupon table
     */
    @Override
    public void addCouponPurchase(int customerID, int couponID) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        values.put(2, couponID);
        DBTools.runQuery(DBManager.ADD_PURCHASED_COUPON, values);
    }

    /**
     * removes expired or used coupon ,updates customer table and coupon amount in coupon table
     *
     * @param customerID for removing coupon from customer table
     * @param couponID   for removing from coupon table
     */
    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        values.put(2, couponID);
        DBTools.runQuery(DBManager.DELETE_PURCHASED_COUPON, values);
    }

    /**
     * gets all existing coupons from one company
     *
     * @param companyId to locate company and said coupons
     * @return arrayLis of companies coupons
     */
    /*public List<Coupon> getCouponsByCompanyId(int companyId) throws CustomExceptions {
        companiesDBDAO = new CompaniesDBDAO();
        List<Coupon> coupons = new ArrayList<>();
        Map<Integer, Object> value = new HashMap<>();
        if (companiesDBDAO.isCompanyExistsById(companyId)) {
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
                System.out.println(e.getMessage());
            }
            return coupons;
        } else {
            throw new CustomExceptions(EnumExceptions.NO_COMPANIES);
        }
    }*/
    public List<Coupon> getCouponsByCompanyId(int companyId) {
        List<Coupon> couponsByCompany = new ArrayList<>();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyId);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_COUPONS_BY_COMPANIES, values);
        if (resultSet == null) {
            return null;
        }
        try {
            while (resultSet.next()) {
                Coupon coupon = getOneCoupon(resultSet.getInt("coupon_id"));
                couponsByCompany.add(coupon);
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return couponsByCompany;
    }

    /**
     * gets all coupons purchased by one specific customer
     *
     * @param customerID to locate said customer and it's coupons
     * @return arrayList of customer purchased coupons
     */
    public List<Coupon> getCouponsByCustomerId(int customerID) {
        List<Coupon> couponsByCustomer = new ArrayList<>();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_COUPONS_BY_CUSTOMER, values);
        if (resultSet == null) {
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



