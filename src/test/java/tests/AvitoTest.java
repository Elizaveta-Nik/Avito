package tests;

import jdk.jfr.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;


@Log4j2
public class AvitoTest extends BaseTest {

    @Test(testName = "End-to-End ad test",
            description = "Verify the entire ad creation, search, and editing process.")
    @Description("End-to-End ad test")
    public void endToEndAdTest() {
        SoftAssert softAssert = new SoftAssert();

        String productNameFirst = "Кукла";
        String productImageUrlFirst = "https://rosman.ru/upload/resize_cache/iblock/868/350_350_1/j03zhjbmx7dapg1h5sr2awhuobfqxviu.jpg";
        String productNameSecond = "Машинка";
        String productImageUrlSecond = "https://ir-3.ozone.ru/s3/multimedia-k/c1000/6669551072.jpg";

        startPage.open()
                .isPageOpened()
                .clickCreateButton();

        createAdPage.isModalOpened()
                .createAd(productNameFirst, "50", "Кукла", productImageUrlFirst)
                .clickOnSaveButton();

        startPage.searchAd(productNameFirst);
        String searchResultName = startPage.getFirstSearchResultName(productNameFirst);

        startPage.clickOnAd(productImageUrlFirst);
        String searchResultImageUrl = startPage.getFirstSearchResultImageUrl(productImageUrlFirst);

        adPage.isPageOpened()
                .clickOnEditButton()
                .editAd(productImageUrlSecond, productNameSecond, "200")
                .editAdDescription("Машинка")
                .clickOnEditButton();

        String searchNewProductName = adPage.getAdNameAfterEditing(productNameSecond);

        softAssert.assertEquals(searchResultName, productNameFirst, "Product name does not match!");
        softAssert.assertEquals(searchResultImageUrl, productImageUrlFirst, "Product image URL does not match!");
        softAssert.assertEquals(searchNewProductName, productNameSecond, "Product name after editing does not match!");

        softAssert.assertAll();
    }
}



