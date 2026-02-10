package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String createProductUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
        createProductUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }

    @Test
    void createProductPage_pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(createProductUrl);
        String pageTitle = driver.getTitle();

        // Verify
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProductPage_pageHeading_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(createProductUrl);
        String heading = driver.findElement(By.tagName("h3")).getText();

        // Verify
        assertEquals("Create New Product", heading);
    }

    @Test
    void createProductPage_nameInputField_exists(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(createProductUrl);
        boolean isInputExists = driver.findElements(By.id("nameInput")).size() > 0;

        // Verify
        assertTrue(isInputExists, "Name input field should exist on create product page");
    }

    @Test
    void createProductPage_quantityInputField_exists(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(createProductUrl);
        boolean isInputExists = driver.findElements(By.id("quantityInput")).size() > 0;

        // Verify
        assertTrue(isInputExists, "Quantity input field should exist on create product page");
    }

    @Test
    void createProductPage_submitButton_exists(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(createProductUrl);
        boolean isSubmitButtonExists = driver.findElements(By.cssSelector("button[type='submit']")).size() > 0;

        // Verify
        assertTrue(isSubmitButtonExists, "Submit button should exist on create product page");
    }

    @Test
    void createProduct_withValidData_successful(ChromeDriver driver) throws Exception {
        // Setup
        String productName = "Test Product";
        String productQuantity = "100";

        // Exercise - Navigate to create product page
        driver.get(createProductUrl);

        // Fill in the product name
        driver.findElement(By.id("nameInput")).sendKeys(productName);

        // Fill in the product quantity
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity);

        // Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for redirect to product list page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/product/list"));

        // Verify - Check that we're on the product list page
        assertEquals(baseUrl, driver.getCurrentUrl());
    }

    @Test
    void createProduct_withValidData_productAppearsInList(ChromeDriver driver) throws Exception {
        // Setup
        String productName = "Laptop Test";
        String productQuantity = "5";

        // Exercise - Navigate to create product page
        driver.get(createProductUrl);

        // Fill in the product name
        driver.findElement(By.id("nameInput")).sendKeys(productName);

        // Fill in the product quantity
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity);

        // Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for redirect and page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("table")));

        // Verify - Check that the product name appears in the table
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(productName),
                   "Product name '" + productName + "' should appear in the product list");
    }
}
