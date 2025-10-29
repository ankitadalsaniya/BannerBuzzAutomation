package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Step 1: Select any product from homepage or menu
    public void selectAnyProduct() {
        try {
            

        WebElement bannersMenu = driver.findElement(By.xpath("//*[@id=\"headerTop\"]/div[4]/div/div/ul/li[1]/a"));
        bannersMenu.click();

        System.out.println("Navigated to Custom Vinyl Banners page successfully!");
        Thread.sleep(3000);
    
        } catch (Exception e) {
            System.out.println("Error selecting product: " + e.getMessage());
        }
    }

    // Step 2: Choose available product options dynamically
    public void chooseAvailableOptions() {
        try {
            // Example dynamic dropdowns or radio buttons
            // Select first dropdown (Size)
                Thread.sleep(5000);
            List<WebElement> dropdowns = driver.findElements(By.tagName("select"));
            if (!dropdowns.isEmpty()) {
                Select sizeDropdown = new Select(dropdowns.get(0));
                sizeDropdown.selectByIndex(1); // select 2nd option
                System.out.println("Selected size: " + sizeDropdown.getFirstSelectedOption().getText());
            }

            
            List<WebElement> qtyInputs = driver.findElements(By.xpath("//*[@id=\"quantity\"]"));
            if (!qtyInputs.isEmpty()) {
                WebElement qtyBox = qtyInputs.get(0);
                qtyBox.clear();
                qtyBox.sendKeys("2");
                System.out.println("Quantity set to 2.");
            }
            Thread.sleep(3000);
            WebElement addToCartBtn = driver.findElement(By.xpath("//button[contains(text(),'Add To Cart')]"));
            addToCartBtn.click();
        } catch (Exception e) {
            System.out.println("Error choosing options: " + e.getMessage());
        }
    }

    // Step 3: Click on “Select Design Method” dynamically
    public void clickSelectDesignMethod() {
        try {
            WebElement selectDesignBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(),'Select Design Method') or contains(@class,'design-method')]")));
            wait.until(ExpectedConditions.elementToBeClickable(selectDesignBtn));
            selectDesignBtn.click();
            System.out.println("Clicked 'Select Design Method' button.");
        } catch (Exception e) {
            System.out.println("Error clicking Select Design Method: " + e.getMessage());
        }
    }


        // Step 4: Click on Upload Art Work” dynamically
    public void clickUploadArtWorkMethod() {
        try {
            WebElement uploadArtWorkBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[@class='labeltext' and normalize-space()='Upload Artwork']")));
            wait.until(ExpectedConditions.elementToBeClickable(uploadArtWorkBtn));
            uploadArtWorkBtn.click();
            System.out.println("Clicked 'Upload Art Work' button.");


            Thread.sleep(3000);
           

        } catch (Exception e) {
            System.out.println("Error clicking Upload Art Work: " + e.getMessage());
        }
    }

    public void browseFiles(){
        try{
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Locate the input linked to Browse Files
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(text(),'Browse Files')]/preceding::input[@type='file'][1]")
        ));

        // Make sure it’s visible for sendKeys()
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.opacity=1;", fileInput);

        // Upload your file directly from local system
        fileInput.sendKeys("C:\\Users\\RAJ\\Downloads\\sign.PNG");
            System.out.println("File uploaded successfully.");
        } catch (Exception e) {
            System.out.println("Error uploading file: " + e.getMessage());
        }
    }

    public void addToCartAfterFileUpload() {
        try {
            WebElement addToCartAfterFileUpload = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Add to Cart')]")));
            addToCartAfterFileUpload.click();
            System.out.println("Clicked 'Add to Cart' button after file upload.");
        } catch (Exception e) {
            System.out.println("Error adding to cart after file upload: " + e.getMessage());
        }
    }

    public void setQuantity(){
        List<WebElement> setQnty = driver.findElements(By.xpath("//input[contains(@aria-label,'quantity') or @name='quantity']"));
            if (!setQnty.isEmpty()) {
                WebElement quantityBox = setQnty.get(0);
                quantityBox.clear();
                quantityBox.sendKeys("5");
                System.out.println("Quantity set to 5.");
            }
    }
}
