package clients.dbDao;

import clients.exceptions.CustomExceptions;
import clients.exceptions.EnumExceptions;
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

/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */
public class CustomersDBDAO implements CustomersDAO {
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

    /**
     * Checks the existence of a customer in database by customer ID
     *
     * @param id input customer id
     * @return true or false
     */
    public boolean isCustomerExistsById(int id) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, id);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.COUNT_CUSTOMER_BY_ID, values);
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt(1) == 1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Checks the existence of a customer in database by customer email and password
     *
     * @param email    input e-mail address
     * @param password input password
     * @return true or false
     */
    @Override
    public boolean isCustomerExist(String email, String password) {
        Map<Integer, Object> values = new HashMap<>();
        try {
            values.put(1, email);
            values.put(2, password);
            ResultSet resultSet = DBTools.runQueryForResult(DBManager.IS_CUSTOMER_EXISTS_BY_EMAIL_AND_PASS, values);
            assert resultSet != null;
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * insert new customer info into database
     *
     * @param customer input customer object
     * @throws CustomExceptions alerts user if email already exist in database
     */
    @Override
    public void addCustomer(Customer customer) throws CustomExceptions {
        //todo: check if necessary add a hole method just to check only by email (efficiency) yoavs change
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getFirstName());
        values.put(2, customer.getLastName());
        values.put(3, customer.getEmail());
        values.put(4, customer.getPassword());
        if (this.isCustomerExist(customer.getEmail(), customer.getPassword())) {
            throw new CustomExceptions(EnumExceptions.EMAIL_EXIST);
        } else {
            DBTools.runQuery(DBManager.ADD_CUSTOMER, values);
        }
    }

    /**
     * update customer info into data base
     *
     * @param customer input customer object
     * @throws CustomExceptions alerts user if customer id not found in database
     */
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

    /**
     * removes customer from database
     *
     * @param customerID input customer id for removal
     * @throws CustomExceptions alerts user if customer id not found in database
     */
    @Override
    public void deleteCustomer(int customerID) throws CustomExceptions {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        if (getOneCustomer(customerID) == null) {
            throw new CustomExceptions(EnumExceptions.ID_NOT_EXIST);
        }
        DBTools.runQuery(DBManager.DELETE_CUSTOMER, values);
    }

    /**
     * gets list of all customers in database
     *
     * @return list of customers
     * @throws CustomExceptions alerts user if database is empty
     */
    @Override
    public List<Customer> getAllCustomers() throws CustomExceptions {
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ALL_CUSTOMERS, new HashMap<>());
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        try {
            while (resultSet.next()) {
                customer = getOneCustomer(resultSet.getInt("id"));
                customers.add(customer);
            }
        } catch (SQLException | CustomExceptions err) {
            System.out.println(err.getMessage());
        }
        return customers;
    }

    /**
     * retrieve one customer from database by ID
     *
     * @param customerID input customer ID
     * @return customer object info
     * @throws CustomExceptions alerts user if database is empty
     */
    @Override
    public Customer getOneCustomer(int customerID) throws CustomExceptions {
        couponsDBDAO = new CouponsDBDAO();
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CUSTOMER, values);
        assert resultSet != null;
        if (isCustomerExistsById(customerID)) {
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
        } else {
            throw new CustomExceptions(EnumExceptions.NO_CUSTOMER);
        }
    }
}
