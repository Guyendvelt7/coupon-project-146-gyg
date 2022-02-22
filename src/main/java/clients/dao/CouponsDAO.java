package clients.dao;

import clients.CustomExceptions;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * interface implemented by CouponsDBDAO
 */
public interface CouponsDAO {

    /**
     * adds company coupon to database
     * @param coupon coupon object
     */
    public void addCoupon (Coupon coupon) throws CustomExceptions;

    /**
     * update information on database w/o altering coupons ID
     * @param coupon coupon object
     */
    public void updateCoupon(Coupon coupon) throws CustomExceptions;

    /**
     * removes coupon from all linked tables in database
     * @param couponID uses coupons ID for removal
     */
    public void deleteCoupon(int couponID) throws CustomExceptions;

    /**
     * get specific sql query
     * @return list of coupons
     */
    public List<Coupon> getAllCoupons() throws CustomExceptions;

    /**
     * get one coupon info by SQL query by ID
     * @return one coupon object
     */
    public Coupon getOneCoupon() throws CustomExceptions;

    /**
     * insert a purchased coupon to customer table and decrements amount of available coupons in coupon table
     * @param customerID for adding coupon to customer table
     * @param couponID for removing amount from coupon table
     * @throws SQLException
     * @throws CustomExceptions
     */
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

    /**
     * deletes coupon from customer when coupon is used or coupon expiry.
     * @param customerID for removing coupon from customer table
     * @param couponID for removing from coupon table
     * @throws SQLException
     * @throws CustomExceptions
     */
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

}
