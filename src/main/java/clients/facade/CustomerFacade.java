package clients.facade;

import clients.beans.Category;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.db.DBManager;
import clients.db.DBTools;
import clients.dbDao.CustomersDBDAO;
import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * incorporation of all accessible methods to a customer
 */
public class CustomerFacade extends ClientFacade {
    private int customerID;
    CustomersDBDAO customersDBDAO = new CustomersDBDAO();

    /**
     * Empty Customer constructor
     */
    public CustomerFacade() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
        if (customersDBDAO.isCustomerExist(email, password)) {
            Map<Integer, Object> values = new HashMap<>();
            values.put(1, email);
            values.put(2, password);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_CUSTOMER_ID_BY_EMAIL_AND_PASSWORD, values);
            int id = 0;
            try {
                resultSet.next();
                id = resultSet.getInt("id");
            } catch (SQLException err) {
                System.out.println(err.getMessage());
            }
            setCustomerID(id);
            return true;
        }
        return false;
    }

    public void purchaseCoupon(Coupon coupon) {
        try {
            couponsDBDAO.addCouponPurchase(customerID, coupon.getId());
        } catch (CustomExceptions customException) {
            System.out.println(customException.getMessage());
        }
    }

    public List<Coupon> getCustomerCoupons() throws CustomExceptions {
        return couponsDBDAO.getCouponsByCustomerId(this.customerID);
    }

    public List<Coupon> getCustomerCoupons(Category category) throws CustomExceptions {
        List<Coupon> categoryList = getCustomerCoupons().stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
        if (categoryList.isEmpty()) {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_CATEGORY);
        }
        return categoryList;
    }

    public List<Coupon> getCustomerCoupons(double maxPrice) throws CustomExceptions {
        List<Coupon> priceList = getCustomerCoupons().stream()
                .filter(item -> item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
        if (priceList.isEmpty()) {
            throw new CustomExceptions(EnumExceptions.NO_COUPONS_BY_PRICE);
        }
        return priceList;
    }

    public Customer getCustomerDetails() {
        return customersDBDAO.getOneCustomer(customerID);
    }

}
