package clients.dbDao;

import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Customer;
import clients.dao.CustomersDAO;
import clients.db.DBManager;
import clients.db.DBTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {
    //todo: ConnectionPool connectionPool
    //todo: add exceptions
    private CouponsDBDAO couponsDBDAO;

    @Override
    public boolean isCustomerExist(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.IS_CUSTOMER_EXISTS, values);
        if (resultSet == null) {
            return false;
        }
        try {
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public void addCustomer(Customer customer) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getFirstName());
        values.put(2, customer.getLastName());
        values.put(3, customer.getEmail());
        values.put(4, customer.getPassword());
        if (this.isCustomerExist(customer.getEmail(), customer.getPassword())) {
            throw new CustomExceptions(EnumExceptions.EMAIL_EXIST);
        } else {
            DBTools.runQuery(DBManager.ADD_CUSTOMER, values);
            System.out.println("customer added");
        }


    }

    @Override
    public void updateCustomer(Customer customer) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getFirstName());
        values.put(2, customer.getLastName());
        values.put(3, customer.getEmail());
        values.put(4, customer.getPassword());
        values.put(5, customer.getId());
        if (getOneCustomer(customer.getId()) == null) {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
        DBTools.runQuery(DBManager.UPDATE_CUSTOMER, values);
    }

    @Override
    public void deleteCustomer(int customerID) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        if (getOneCustomer(customerID) == null) {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
        DBTools.runQuery(DBManager.DELETE_CUSTOMER, values);
    }

    @Override
    public List<Customer> getAllCustomers() {
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_CUSTOMERS,new HashMap<>());
        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Customer customer = getOneCustomer(resultSet.getInt("id"));
                customers.add(customer);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return customers;
    }


    @Override
    public Customer getOneCustomer(int customerID) {
        couponsDBDAO = new CouponsDBDAO();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CUSTOMER, values);
        assert resultSet != null;
        try {
            resultSet.next();
            return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    couponsDBDAO.getCouponsByCustomerId(customerID)
            );
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    public void addCouponToCustomer(int couponId, int customerId) {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        values.put(2, couponId);
        DBTools.runQuery(DBManager.ADD_COUPON_TO_CUSTOMER, values);
    }


}
