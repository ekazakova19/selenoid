import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;

import java.util.Random;

public class HabrMainTest {
    private  SelenoidDeployment selenoidDeployment ;
    protected static String remoteWebDriverURL;

    @Before
    public void setUpMain() {
            selenoidDeployment = new SelenoidDeployment();
            selenoidDeployment.deploySelenoid();
            String selenoidListedPort = selenoidDeployment.getSelenoidListedPort();
            System.out.println(selenoidListedPort);
            remoteWebDriverURL= String.format("http://localhost:%s/wd/hub", selenoidListedPort);
            System.out.println(remoteWebDriverURL);
    }


}
