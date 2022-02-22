package clients.dao;

import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerFacadeDao {

    void purchaseCoupon(Coupon coupon);

    List<Coupon> getCustomerCoupons() throws CustomExceptions;

    List<Coupon> getCustomerCoupons(Category category) throws CustomExceptions;

    List<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions;

    Customer getCustomerDetails();
}
