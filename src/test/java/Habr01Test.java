import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Habr01Test extends  HabrMainTest{

    private WebDriver driver;
    private MainPage mainPage;
    private  SelenoidDeployment selenoidDeployment ;

    @Before
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo",false);

        try {
            driver = new RemoteWebDriver(
                    new URL(remoteWebDriverURL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mainPage = new MainPage(driver);
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
