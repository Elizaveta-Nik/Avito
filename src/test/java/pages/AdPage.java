package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import wrappers.Input;

@Log4j2
public class AdPage extends BasePage {

    private final By ADD_TO_CART_BUTTON = By.xpath("//button[contains(text(),'Добавить в корзину')]");
    private final By EDIT_BUTTON = By.xpath("//div[@class='css-nb383z']//*[name()='svg']");
    private final By DESCRIPTION_AREA_INPUT = By.xpath("//textarea[@name='description']");

    public AdPage(WebDriver driver) {
        super(driver);
    }

    public AdPage isPageOpened() {
        log.info("Checking if ad page is open");
        waitUntilAllElementsBeVisible(ADD_TO_CART_BUTTON);
        log.info("Ad page is opened");
        return this;
    }

    public AdPage clickOnEditButton() {
        log.info("Clicking on edit button");
        click(EDIT_BUTTON);
        log.info("Edit button clicked");
        return this;
    }

    public AdPage editAd(String imageUrl, String title, String price) {
        log.info("Editing an ad");

        log.info("Entering image URL: " + imageUrl);
        Input imageUrlInput = new Input(driver, "imageUrl");
        imageUrlInput.clear();
        imageUrlInput.write(imageUrl);

        log.info("Entering title: " + title);
        Input titleInput = new Input(driver, "name");
        titleInput.clear();
        titleInput.write(title);

        log.info("Entering price: " + price);
        Input priceInput = new Input(driver, "price");
        priceInput.clear();
        priceInput.write(price);

        log.info("Ad edited successfully");
        return this;
    }

    public AdPage editAdDescription(String description) {
        log.info("Editing an ad");

        log.info("Entering description: " + description);
        click(DESCRIPTION_AREA_INPUT);
        clear(DESCRIPTION_AREA_INPUT);
        sendKeys((DESCRIPTION_AREA_INPUT), description);

        log.info("Description edited successfully");
        return this;
    }

    public String getAdNameAfterEditing(String newProductName) {
        log.info("Getting ad name after editing for product: " + newProductName);
        By dynamicNameXPath = By.xpath("//h2[text()='" + newProductName + "']");
        String result = driver.findElement(dynamicNameXPath).getText();
        log.info("Found ad name after editing: " + result);
        return result;
    }
}
