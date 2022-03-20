package clients;

import clients.exceptions.CustomExceptions;

import static clients.System.cleanDataBase;
import static clients.System.testAll;
/**
 * @author Yoav Hacmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 *Program class runs System class
 * Main class
 */

public class Program {

    public static void main(String[] args) {
        cleanDataBase();
        try {
            testAll();
        } catch (CustomExceptions customExceptions) {
            java.lang.System.out.println(customExceptions.getMessage());
        }
    }
}
