import clients.db.DBManager;
import clients.db.DBTools;
import clients.facade.AdminFacadeTests;
import clients.facade.CompanyFacadeTests;
import clients.facade.CustomerFacadeTests;
import inProcessTests.TestTablesCreation;
import inProcessTests.TestsCategories;
import junit.framework.TestSuite;
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
    public static void init() {
        System.out.println("Starting all tests");
        new TestSuite(TestTablesCreation.class);
        new TestSuite(TestsCategories.class);
    }

    @Test
    public void adminFacadeTest() {
        new TestSuite(AdminFacadeTests.class);
    }

    @Test
    public void companyFacadeTests() {
        new TestSuite(CompanyFacadeTests.class);
    }

    @Test
    public void customerFacadeTests() {
        new TestSuite(CustomerFacadeTests.class);
    }

}
