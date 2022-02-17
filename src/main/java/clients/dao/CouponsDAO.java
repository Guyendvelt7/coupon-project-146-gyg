package clients.dao;

import clients.CustomExceptions;
import clients.beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CouponsDAO {

    public void addCoupon (Coupon coupon);

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(int couponID);

    public List<Coupon> getCoupons(String sql, Map<Integer, Object> values);





    public void addCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, CustomExceptions;

}
