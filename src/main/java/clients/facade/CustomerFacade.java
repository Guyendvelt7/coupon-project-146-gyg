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
import java.util.stream.Collectors;

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

        return email.equals(customersDBDAO.getOneCustomer(customerID).getEmail()) &&
        password.equals(customersDBDAO.getOneCustomer(customerID).getPassword());
    }

    @Override
    public void purchaseCoupon(Coupon coupon){
        if(canPurchaseCoupon(coupon)){
            coupon.setAmount(coupon.getAmount()-1);
            customersDBDAO.addCouponToCustomer(coupon.getId(),customerID);
        } else{
            System.out.println("you cant purchase this coupon");
        }
        }


    @Override
    public List<Coupon> getCustomerCoupons(){
        return couponsDBDAO.getCouponsByCustomerId(customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category){
        return getCustomerCoupons().stream()
                .filter(item->item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return getCustomerCoupons().stream()
                .filter(item->item.getPrice()<=maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerDetails(){
      return customersDBDAO.getOneCustomer(customerID);
    }

    private boolean canPurchaseCoupon(Coupon coupon){
        for (Coupon item:getCustomerCoupons()){
            if(item.getId()==coupon.getId()){
                return false;
            }
        }
        return !coupon.getEndDate().toLocalDate().isBefore(LocalDate.now()) && coupon.getAmount() != 0;
    }

}
