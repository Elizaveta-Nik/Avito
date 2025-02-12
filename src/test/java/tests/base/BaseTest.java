package tests.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AdPage;
import pages.CreateAdPage;
import pages.StartPage;
import utils.testngUtils.TestListener;

import java.time.Duration;

@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected StartPage startPage;
    protected CreateAdPage createAdPage;
    protected AdPage adPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        log.info("Setting up browser: {}", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("start-maximized");
            driver = new EdgeDriver(options);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("start-maximized");
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        startPage = new StartPage(driver);
        createAdPage = new CreateAdPage(driver);
        adPage = new AdPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        log.info("Browser setup completed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        log.info("Tearing down browser");
        driver.quit();
        log.info("Browser closed");
    }
}
