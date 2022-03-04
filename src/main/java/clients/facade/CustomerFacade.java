package clients.facade;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dao.CustomerFacadeDao;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to a customer
 */
public class CustomerFacade extends ClientFacade implements CustomerFacadeDao {
    private int customerID;
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    private CustomersDBDAO customersDBDAO = new CustomersDBDAO();

    //todo: check if customer id necessary and also getCustomerID()
    public CustomerFacade(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    /**
     * login verification
     *
     * @param email    input by user
     * @param password input by user
     * @return verification result
     */
    @Override
    public boolean login(String email, String password) {
        try {
            return email.equals(customersDBDAO.getOneCustomer(customerID).getEmail()) &&
                    password.equals(customersDBDAO.getOneCustomer(customerID).getPassword());
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return false;
        }
    }

    @Override
    public void purchaseCoupon(Coupon coupon) {
        try {
            couponsDBDAO.addCouponPurchase(customerID, coupon.getId());
        } catch (CustomExceptions customException) {
            System.out.println(customException.getMessage());
        }
    }

    @Override
    public List<Coupon> getCustomerCoupons() throws CustomExceptions {
        return couponsDBDAO.getCouponsByCustomerId(this.customerID);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) throws CustomExceptions {
        List<Coupon> categoryList = getCustomerCoupons().stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
        if (categoryList.isEmpty()) {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_CATEGORY);
        }
        return categoryList;
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions {
        List<Coupon> priceList = getCustomerCoupons().stream()
                .filter(item -> item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        if (priceList.isEmpty()) {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_PRICE);
        }
        return priceList;
    }

    @Override
    public Customer getCustomerDetails() {
        try {
            return customersDBDAO.getOneCustomer(customerID);
        } catch (CustomExceptions customExceptions) {
            System.out.println(customExceptions.getMessage());
            return null;
        }
    }

}
