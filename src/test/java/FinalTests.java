import clients.facade.AdminFacadeTests;
import clients.facade.CompanyFacadeTests;
import clients.facade.CustomerFacadeTests;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Yoav Hachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * FinalTests class combines all facade tests in suitCase test
 */

public class FinalTests {
    @BeforeClass
    public static void message(){
        System.out.println("Starting all tests");
    }


    @Test
    public void customerFacadeTests(){
        new TestSuite(CustomerFacadeTests.class);
    }

    @Test
    public void companyFacadeTests(){
        new TestSuite(CompanyFacadeTests.class);
    }

    @Test
    public void adminFacadeTest(){
        new TestSuite(AdminFacadeTests.class);
    }
}
