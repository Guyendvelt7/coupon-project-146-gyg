package clients.dao;

import clients.exceptions.CustomExceptions;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.List;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 *  * 03.2022
 */

/**
 * interface: implemented by CouponsDBDAO
 */
public interface CouponsDAO {

    /**
     *CRUD
     */

    public void addCoupon (Coupon coupon) throws CustomExceptions;

    public void updateCoupon(Coupon coupon) throws CustomExceptions;

    public void deleteCoupon(int couponID) throws CustomExceptions;

    public List<Coupon> getAllCoupons() throws CustomExceptions;

    public Coupon getOneCoupon(int coupon_id) throws CustomExceptions;

    public void addCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

}
