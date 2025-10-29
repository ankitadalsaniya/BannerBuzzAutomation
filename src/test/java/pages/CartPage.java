package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;

    By quantityField = By.xpath("//input[contains(@class,'qty') or contains(@name,'quantity')]");
    By updateButton = By.xpath("//button[contains(.,'Update') or contains(.,'Update Cart')]");
    By cartPageIdentifier = By.xpath("//h1[contains(.,'Cart') or contains(.,'Shopping Cart')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnCartPage() {
        try {
            return driver.findElement(cartPageIdentifier).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void updateQuantity(String qty) {
        try {
            System.out.println("Updating cart quantity to: " + qty);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Wait for and find quantity field
            WebElement q = wait.until(ExpectedConditions.elementToBeClickable(quantityField));
            q.clear();
            q.sendKeys(qty);
            
            try {
                // Look for update button
                WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
                updateBtn.click();
                System.out.println("Clicked update button");
            } catch (Exception e) {
                // If there is auto-update, ignore
                System.out.println("No update button found, assuming auto-update");
            }
            
            // Wait for any potential loading indicators to disappear
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Failed to update quantity: " + e.getMessage());
            throw new RuntimeException("Could not update cart quantity", e);
        }
    }
}
