package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

@Log4j2
public class StartPage extends BasePage {

    private final By CREATE_BUTTON = By.xpath("//button[contains(text(),'Создать')]");
    private final By SEARCH_INPUT = By.xpath("//input[@class='chakra-input css-1owg1hr']");
    private final By SEARCH_BUTTON = By.xpath("//button[contains(text(),'Найти')]");

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage isPageOpened() {
        log.info("Checking if Home page is opened");
        waitUntilAllElementsBeVisible(CREATE_BUTTON);
        return this;
    }

    public StartPage open() {
        log.info("Opening Home page");
        driver.get("http://tech-avito-intern.jumpingcrab.com/");
        waitForPageLoaded(driver);
        log.info("Start page opened successfully");
        return this;
    }

    public StartPage clickCreateButton() {
        log.info("Clicking on 'Create' button");
        click(CREATE_BUTTON);
        log.info("'Create' button clicked");
        return this;
    }

    public StartPage searchAd(String title) {
        log.info("Searching for ad with title: " + title);
        click(SEARCH_INPUT);
        sendKeys(SEARCH_INPUT, title);
        click(SEARCH_BUTTON);
        log.info("Search button clicked");
        return this;
    }

    public String getFirstSearchResultName(String productName) {
        log.info("Getting first search result name for product: " + productName);
        By dynamicNameXPath = By.xpath("//h4[text()='" + productName + "']");
        String result = driver.findElement(dynamicNameXPath).getText();
        log.info("Found search result name: " + result);
        return result;
    }

    public String getFirstSearchResultImageUrl(String productImageUrl) {
        log.info("Getting first search result image URL for product image: " + productImageUrl);
        By dynamicImageXPath = By.xpath("//img[contains(@src,'" + productImageUrl + "')]");
        String result = driver.findElement(dynamicImageXPath).getAttribute("src");
        log.info("Found search result image URL: " + result);
        return result;
    }

    public StartPage clickOnAd(String productImageUrl) {
        log.info("Clicking on ad with image URL: " + productImageUrl);
        By dynamicImageXPath = By.xpath("//img[contains(@src,'" + productImageUrl + "')]");
        driver.findElement(dynamicImageXPath).getAttribute("src");
        click(dynamicImageXPath);
        log.info("Ad clicked");
        return this;
    }
}
