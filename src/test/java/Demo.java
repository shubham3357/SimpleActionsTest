

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Demo
{
    public WebDriver driver;
    public static ReadConfig rc = new ReadConfig();
    public static String url = rc.getApplicationURL();

    @BeforeClass
    public void setUp()
    {
        if(rc.getBrowser().equalsIgnoreCase("chrome")){
            System.out.println("Chrome initialized successfully");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            //driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to(url);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }
    @Test
    public void userLogin()
    {
        WebElement searchTxt = driver.findElement(By.name("q"));
        searchTxt.sendKeys("automation");
        WebElement submitBtn = driver.findElement(By.name("btnK"));
        submitBtn.click();
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getTitle().contains("automation - Google Search"));
        System.out.println("Current Title is: " + driver.getTitle());
    }
    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}