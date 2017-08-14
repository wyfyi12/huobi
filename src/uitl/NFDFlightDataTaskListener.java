package uitl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
 
public class NFDFlightDataTaskListener implements  ServletContextListener {
 
    public void contextInitialized(ServletContextEvent sce) {
         new TimerManager();
         //new TimerManager2();
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         
    }
 
}
