package clients.facade;

import clients.CustomExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dao.CustomerFacadeDao;
import clients.dbDao.CouponsDBDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends ClientFacade implements CustomerFacadeDao {
    private int customerID;

    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("customer") && password.equals("customer");
    }

    @Override
    public void purchaseCoupon(Coupon coupon){
        if(LocalDate.now().isBefore(coupon.getEndDate().toLocalDate())){
            couponsDBDAO.deleteCoupon(coupon.getId());
        }
    }

    @Override
    public List<Coupon> getCustomerCoupons(){
        return CouponsDBDAO.getCouponsByCustomerId(customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category){
        List<Coupon> couponList = getCustomerCoupons();
        List<Coupon> couponCategoryList = new ArrayList<>();
        for(Coupon item:couponList){
            if(item.getCategory()==category){
                couponCategoryList.add(item);
            }
        }
        return couponCategoryList;
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        List<Coupon> couponList = getCustomerCoupons();
        List<Coupon> couponUnderPriceList = new ArrayList<>();
        for(Coupon item:couponList){
            if(item.getPrice()<=maxPrice){
                couponUnderPriceList.add(item);
            }
        }
        return couponUnderPriceList;
    }

    @Override
    public Customer getCustomerDetails(){
      return customersDBDAO.getOneCustomer(customerID);

    }



}
