package clients.dao;

import clients.Exceptions;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface CouponsDAO {

    public void addCoupon (Coupon coupon) throws SQLException, Exceptions;

    public void updateCoupon(Coupon coupon) throws SQLException, Exceptions;

    public void deleteCoupon(int couponID) throws SQLException, Exceptions;

    public ArrayList<Coupon> getCoupons(String sql, Map<Integer, Object> values) throws SQLException, Exceptions;

    public void addCouponPurchase(int customerID, int couponID) throws SQLException, Exceptions;

    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, Exceptions;

}
