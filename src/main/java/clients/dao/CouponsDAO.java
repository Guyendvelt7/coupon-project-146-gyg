package clients.dao;

import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface CouponsDAO {

    public void addCoupon (Coupon coupon) throws SQLException;

    public void updateCoupon(Coupon coupon) throws SQLException;

    public void deleteCoupon(int couponID) throws SQLException;

    public ArrayList<Coupon> getCoupons(String sql, Map<Integer, Object> values) throws SQLException;

    public void addCouponPurchase(int customerID, int couponID) throws SQLException;

    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException;

}
