package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;

    // sample locator: first product or category link - you may need to adjust based on live DOM
    By firstProduct = By.cssSelector(".product-item a, .product-link, [data-testid='product-link']");
    By acceptCookies = By.xpath("//button[contains(text(),'Accept') or contains(text(),'ACCEPT') or contains(@id,'cookie')]");
    By menuItem = By.xpath("(//div[@class='productImgBox'])[1]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get("https://www.bannerbuzz.com/");
        // Wait for the page to load
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        System.out.println("HomePage loaded. Current URL: " + driver.getCurrentUrl());
    }

    public void closeButton(){
        try {
            WebElement btn1 = driver.findElement(By.cssSelector(".closebtn"));
            btn1.click();
        } catch (Exception e) {
            // ignore if not present
        }
    }
    

    public void closeCookiesIfPresent() {
        try {
            WebElement btn = driver.findElement(acceptCookies);
            btn.click();
        } catch (Exception e) {
            // ignore if not present
        }
    }

    

    public void clickFirstProduct() {
        try {
            System.out.println("Looking for products on homepage...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // First try to navigate to products page if we're not seeing products
            try {
                WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuItem));
                System.out.println("Found menu item: " + menu.getText());
                menu.click();
                System.out.println("Clicked menu item, waiting for products to load...");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("Menu navigation failed or not needed: " + e.getMessage());
            }
            
            // Now look for products
            try {
                // Print page source for debugging
                System.out.println("Current page source:");
                System.out.println(driver.getPageSource());
                
                // Wait for and find all product links
                java.util.List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(firstProduct));
                System.out.println("Found " + products.size() + " products");
                
                // Find the first visible and clickable product
                for (WebElement product : products) {
                    if (product.isDisplayed() && product.isEnabled()) {
                        // Store the href for verification
                        String href = product.getAttribute("href");
                        System.out.println("Attempting to click product with href: " + href);
                        
                        // Scroll into view
                        ((org.openqa.selenium.JavascriptExecutor) driver)
                            .executeScript("arguments[0].scrollIntoView(true);", product);
                        Thread.sleep(500);
                        
                        // Click using JavaScript
                        ((org.openqa.selenium.JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", product);
                        
                        // Wait for navigation
                        String currentUrl = driver.getCurrentUrl();
                        System.out.println("Current URL after click: " + currentUrl);
                        
                        // Verify we're on a product page
                        if (currentUrl.contains("product") || currentUrl.contains("item") || currentUrl.contains("p/")) {
                            return;
                        }
                        System.out.println("Navigation didn't reach a product page, trying next product...");
                    }
                }
                throw new RuntimeException("No clickable products found");
            } catch (Exception e) {
                System.out.println("Failed to interact with products: " + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            System.out.println("Failed to click first product: " + e.getMessage());
            throw new RuntimeException("Could not click first product. Check locator.", e);
        }
    }
}
