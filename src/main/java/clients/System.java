package clients;

import clients.db.ConnectionPool;
import clients.thread.CouponExpirationDailyJob;

/**
 * @author Yoav Chachmon, Guy Endvelt and Gery Glazer
 * 03.2022
 */

/**
 * Program initialization-Test class
 */
public class System {
    private static System instance=null;
    CouponExpirationDailyJob job = null;
    //todo: דף 20

    /**
     * singleton. constructor for thread init
     */
    private System () {
        job = new CouponExpirationDailyJob();
        Thread task = new Thread(job);
        task.start();//start thread
    }

    /**
     * get one single instance of program init
     * @return System Object
     */
    public static synchronized System getInstance(){//throws classNotFound/SQL/Interrupted
        if (instance == null) {
            instance = new System();
        }
        return instance;
    }

    /**
     * ends thread daily job and close connection to database
     */
    public void stopAll(){
        job.stop();
        ConnectionPool.getInstance().closeAllConnections();
    }

    /**
     * Program overall tests
     */
    public static void testAll(){
        //system init
        System system = new System();
        system = System.getInstance();
        //check login for all 3 clients type

        //compile all facade tests here

        //end thread
        //close connection to database
        system.stopAll();
    }
}
