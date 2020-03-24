import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class HabrTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
//        DesiredCapabilities capabilities = new DesiredCapabilities(); capabilities.setBrowserName("chrome"); 
//        capabilities.setVersion("80.0"); capabilities.setCapability("enableVNC", true); 
//        capabilities.setCapability("enableVideo", false); 
        //driver = new RemoteWebDriver(     new URL("http://0.0.0.0:4444/wd/hub"));

    }

    @Test
    public void test() {
        mainPage.openMainPage();
        mainPage.cliclElHubs();
        mainPage.assertThatHubsPageOpened();
        System.out.println("Open hub page test id= " + new Random().nextInt());
    }

    @After
    public void closeDriver(){
        if(driver!=null){
            driver.quit();
        }
    }
}
