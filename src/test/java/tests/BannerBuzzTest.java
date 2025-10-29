package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class BannerBuzzTest extends BaseTest {

    @Test
    public void bannerBuzzFlow() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        try {
            // 1. Open home and handle cookie popup if any
            homePage.openHomePage();
            Thread.sleep(5000); // allow page to load
            homePage.closeButton();
            homePage.closeCookiesIfPresent();

            // 2. Select a product (first product)
        Thread.sleep(5000); // increased wait time

        productPage.selectAnyProduct();        // Step 1: Select any product
        productPage.chooseAvailableOptions();  // Step 2: Choose product options dynamically
        productPage.clickSelectDesignMethod(); // Step 3: Click "Select Design Method"
        productPage.clickUploadArtWorkMethod(); // Step 4: Click "Upload Artwork" method
        productPage.browseFiles();
        productPage.addToCartAfterFileUpload();
        productPage.setQuantity();
        Thread.sleep(3000);
            
         } catch (Exception e) {
             System.out.println("Test failed with error: " + e.getMessage());
             throw e;
         }
    

        
    }
}


