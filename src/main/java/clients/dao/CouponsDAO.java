package clients.dao;

import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {

    public void addCoupon (Coupon coupon) throws SQLException;

    public void updateCoupon(Coupon coupon) throws SQLException;

    public void deleteCoupon(int couponID) throws SQLException;

    public ArrayList<Coupon> getAllCoupons();

    public Coupon getOneCoupon (int couponID);

    public void addCouponPurchase(int customerID, int couponID);

    public void deleteCouponPurchase(int customerID, int couponID);

}
