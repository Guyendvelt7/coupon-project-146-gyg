package clients.dbDao;

import clients.CustomExceptions;
import clients.EnumExceptions;
import clients.beans.Coupon;
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
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,email);
        values.put(2,password);
       ResultSet resultSet =  DBTools.runQueryForResult(DBManager.IS_CUSTOMER_EXISTS,values);
        assert resultSet != null;
        try {
            resultSet.next();
            return resultSet.getInt("1") == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public void addCustomer(Customer customer) throws CustomExceptions {
    Map<Integer,Object> values = new HashMap<>();
    values.put(1,customer.getFirstName());
    values.put(2,customer.getLastName());
    values.put(3,customer.getEmail());
    values.put(4,customer.getPassword());
        if(this.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new CustomExceptions(EnumExceptions.EMAIL_EXIST);

        } else {
            DBTools.runQuery(DBManager.ADD_CUSTOMER, values);
        }


        }

    @Override
    public void updateCustomer(Customer customer){
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customer.getFirstName());
        values.put(2,customer.getLastName());
        values.put(3,customer.getEmail());
        values.put(4,customer.getPassword());
        DBTools.runQuery(DBManager.UPDATE_CUSTOMER,values);
    }

    @Override
    public void deleteCustomer(int customerID){
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customerID);
        DBTools.runQuery(DBManager.DELETE_CUSTOMER,values);
    }

    @Override
    public List<Customer> getAllCustomers(){
        ResultSet customerResultSet = DBTools.runQueryForResult(DBManager.GET_ALL_CUSTOMERS);
        List<Customer> allCustomers = new ArrayList<>();
        while(true){
            try {
                assert customerResultSet != null;
                if (!customerResultSet.next()) break;
            } catch (SQLException e) {
                System.out.println(e.getMessage());            }
            try {
                allCustomers.add(new Customer(
                        customerResultSet.getInt("id"),
                        customerResultSet.getString("firstName"),
                        customerResultSet.getString("lastName"),
                        customerResultSet.getString("email"),
                        customerResultSet.getString("password"),
                        couponsDBDAO.getCouponsByCustomerId(customerResultSet.getInt("id"))
                ));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return allCustomers;
    }

    @Override
    public Customer getOneCustomer(int customerID){
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customerID);
       ResultSet resultSet = DBTools.runQueryForResult(DBManager.GET_ONE_CUSTOMER,values);
        assert resultSet != null;
        try {
            resultSet.next();
            return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                     couponsDBDAO.getCouponsByCustomerId(customerID)
            );
        } catch (SQLException e) {
            System.out.println(EnumExceptions.RESULT_SET_DATA_PROBLEM);
            return null;
        }
    }

    public void addCouponToCustomer(int couponId, int customerId){
        Map<Integer,Object> values = new HashMap<>();
        values.put(1,customerId);
        values.put(2,couponId);
        DBTools.runQuery(DBManager.ADD_COUPON_TO_CUSTOMER,values);
    }


}
