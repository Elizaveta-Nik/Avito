package pages.base;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageLoaded(WebDriver driver) {
        log.info("Waiting for page to load");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Objects.requireNonNull(((JavascriptExecutor) driver)
                                .executeScript("return document.readyState"))
                        .toString().equals("complete");
            }
        });
    }

    protected void click(By by) {
        log.info("Click on - " + by);
        waitUntilElementToBeClickable(by);
        driver.findElement(by).click();
    }

    protected void clear(By by) {
        log.info("Clearing input field - " + by);
        waitUntilAllElementsBeVisible(by);
        driver.findElement(by).clear();
    }

    protected void sendKeys(By by, CharSequence... charSequences) {
        log.info("Enter in -" + by + " next values - " + Arrays.toString(charSequences));
        driver.findElement(by).sendKeys(charSequences);
    }

    protected void waitUntilElementToBeClickable(By by) {
        log.info("Wait until element to be clickable - " + by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilAllElementsBeVisible(By by) {
        log.info("Wait until all elements to visible");
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(by)));
    }
}
