package clients.facade;

import clients.CustomExceptions;
import clients.EnumExceptions;
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
        try {
            if(canPurchaseCoupon(coupon)){
                coupon.setAmount(coupon.getAmount()-1);
                customersDBDAO.addCouponToCustomer(coupon.getId(),customerID);
            }
        } catch (CustomExceptions customException) {
            System.out.println(customException.getMessage());
        }

    }

    @Override
    public List<Coupon> getCustomerCoupons() throws CustomExceptions {
       if (couponsDBDAO.getCouponsByCustomerId(this.customerID).isEmpty()){
           throw new CustomExceptions(EnumExceptions.NO_COUPONS);
       } else{
           return couponsDBDAO.getCouponsByCustomerId(this.customerID);
       }
        }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) throws CustomExceptions {
        List<Coupon> categoryList =  getCustomerCoupons().stream()
                .filter(item->item.getCategory().equals(category))
                .collect(Collectors.toList());
        if(categoryList.isEmpty()){
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_CATEGORY);
        }
        return categoryList;
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions {
        List<Coupon> priceList =  getCustomerCoupons().stream()
                .filter(item->item.getPrice()<=maxPrice)
                .collect(Collectors.toList());
        if(priceList.isEmpty()){
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_PRICE);
        }
        return priceList;
    }

    @Override
    public Customer getCustomerDetails(){
      return customersDBDAO.getOneCustomer(customerID);
    }

    private boolean canPurchaseCoupon(Coupon coupon) throws CustomExceptions {
             if(getCustomerCoupons().stream()
                    .filter(one->one.getId()==coupon.getId()).count()>0){
                 return false;
             }
        return !coupon.getEndDate().toLocalDate().isBefore(LocalDate.now()) && coupon.getAmount() != 0;
    }

}
