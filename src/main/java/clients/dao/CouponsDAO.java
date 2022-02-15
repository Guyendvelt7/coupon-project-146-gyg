package clients.dao;

import clients.CustomExceptions;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface CouponsDAO {

    public void addCoupon (Coupon coupon) throws SQLException, CustomExceptions;

    public void updateCoupon(Coupon coupon) throws SQLException, CustomExceptions;

    public void deleteCoupon(int couponID) throws SQLException, CustomExceptions;

    public ArrayList<Coupon> getCoupons(String sql, Map<Integer, Object> values) throws SQLException, CustomExceptions;

    public void addCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

}
