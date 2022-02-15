package clients.dao;

import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerFacadeDao {
    void purchaseCoupon(Coupon coupon) throws SQLException;
    List<Coupon> getCustomerCoupons() throws SQLException, InterruptedException;
    List<Coupon> getCustomerCoupons(Category category) throws SQLException, InterruptedException;
    List<Coupon> getCustomerCoupons(double maxPrice) throws SQLException, InterruptedException;
    Customer getCustomerDetails() throws SQLException, InterruptedException;
}
