package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import wrappers.Input;

@Log4j2
public class CreateAdPage extends BasePage {

    private final By SAVE_BUTTON = By.xpath("//button[@type='submit']");

    public CreateAdPage(WebDriver driver) {
        super(driver);
    }

    public CreateAdPage isModalOpened() {
        log.info("Checking if modal for Creating ad is opened");
        waitUntilAllElementsBeVisible(SAVE_BUTTON);
        log.info("Modal for Creating ad is opened");
        return this;
    }

    public CreateAdPage createAd(String title, String price, String description, String imageUrl) {
        log.info("Creating a new ad");
        log.info("Entering title: " + title);
        new Input(driver, "name").write(title);
        log.info("Entering price: " + price);
        new Input(driver, "price").write(price);
        log.info("Entering description: " + description);
        new Input(driver, "description").write(description);
        log.info("Entering image URL: " + imageUrl);
        new Input(driver, "imageUrl").write(imageUrl);
        log.info("Ad created successfully");
        return this;
    }

    public CreateAdPage clickOnSaveButton() {
        log.info("Clicking on save button");
        click(SAVE_BUTTON);
        log.info("Save button clicked");
        return this;
    }
}
