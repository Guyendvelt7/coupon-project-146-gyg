import clients.CustomExceptions;
import clients.beans.Coupon;
import clients.beans.Customer;
import clients.dbDao.CompaniesDBDAO;
import clients.dbDao.CouponsDBDAO;
import clients.dbDao.CustomersDBDAO;
import clients.facade.ClientFacade;
import clients.facade.ClientType;
import clients.facade.CustomerFacade;
import clients.facade.LoginManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerFacadeTestsWork {
    private static CustomersDBDAO customersDBDAO;
    private static Customer customer;
    private static CouponsDBDAO couponsDBDAO;
    private static CustomerFacade customerFacade;
    private static LoginManager loginManager;
    private static Coupon coupon;



    @BeforeClass
    public static void initializing() {
        System.out.println("starting initialize");
        customersDBDAO = new CustomersDBDAO();
        customer = customersDBDAO.getOneCustomer(5);
        System.out.println(customer);
        loginManager = LoginManager.getInstance();
        couponsDBDAO = new CouponsDBDAO();
        try {
            coupon = couponsDBDAO.getOneCoupon(2);
            System.out.println(coupon);
            customerFacade = (CustomerFacade) loginManager.login(customer.getEmail(),customer.getPassword(),ClientType.CUSTOMER);
        } catch (CustomExceptions e) {
            e.printStackTrace();
        }

    }

    @Test
    public void goodLogin() throws CustomExceptions {
        System.out.println(customer.getEmail());
        System.out.println(customer.getPassword());
        Assert.assertTrue((loginManager.login(customer.getEmail(),customer.getPassword(),ClientType.CUSTOMER) instanceof CustomerFacade));
    }
    @Test
    public void Badlogin(){
        try {
            Assert.assertFalse(loginManager.login("zeev-email","zeev-password",ClientType.CUSTOMER) instanceof  CustomerFacade);
        } catch (CustomExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void purchaseCoupon(){
        //int beforeAmount = coupon.getAmount();
        customerFacade.purchaseCoupon(coupon);
        //int afterAmount = coupon.getAmount();

        //Assert.assertEquals(beforeAmount,afterAmount+1);
    }











}
