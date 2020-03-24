import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    @FindBy(xpath = ".//h3[contains(text(), 'Хабы')]")
    private WebElement HUBS_LINK;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public MainPage openMainPage(){
        driver.get("https://habr.com/ru/");
        return this;
    }

    public  MainPage cliclElHubs(){
        HUBS_LINK.click();
        return this;
    }

    public void assertThatHubsPageOpened(){
        Assert.assertTrue( driver.getCurrentUrl().equalsIgnoreCase("https://habr.com/ru/hubs/"));
    }
}
