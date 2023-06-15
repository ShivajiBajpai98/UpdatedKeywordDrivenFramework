package reusable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Reusable {
    private static final Logger log = Logger.getLogger(Reusable.class);
    private WebDriverWait wait;
    private WebDriver driver;

    public Reusable(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PropertyConfigurator.configure("log4j.properties");
    }

    /**
     * Opens the specified URL in the browser.
     *
     * @param url The URL to be opened.
     */
    public void openURL(String url) {
        log.info("Opening URL: " + url);
        driver.get(url);
    }

    /**
     * Clicks on the specified element.
     *
     * @param locator The locator of the element to be clicked.
     */
    public void clickElement(By locator) {
        log.info("Clicking element: " + locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Enters the specified text into the input field.
     *
     * @param locator The locator of the input field.
     * @param text    The text to be entered.
     */
    public void enterText(By locator, String text) {
        log.info("Entering text: " + text + ", into element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Selects the specified option from a dropdown or multi-select list.
     *
     * @param locator     The locator of the dropdown or multi-select list.
     * @param optionText  The text of the option to be selected.
     */
    public void selectOption(By locator, String optionText) {
        log.info("Selecting option: " + optionText + ", from element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        // Code to select the desired option from the dropdown or multi-select list
    }

    /**
     * Verifies if the specified element is present on the page.
     *
     * @param locator The locator of the element to be verified.
     * @return True if the element is present, false otherwise.
     */
    public boolean verifyElementPresent(By locator) {
        log.info("Verifying element presence: " + locator);
        return driver.findElements(locator).size() > 0;
    }

    /**
     * Verifies if the text of the specified element matches the expected text.
     *
     * @param locator      The locator of the element to verify the text.
     * @param expectedText The expected text.
     * @return True if the text matches, false otherwise.
     */
    public boolean verifyText(By locator, String expectedText) {
        log.info("Verifying text: " + expectedText + ", for element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actualText = element.getText();
        return actualText.equals(expectedText);
    }

    /**
     * Waits until the specified element is visible on the page.
     *
     * @param locator The locator of the element to wait for.
     */
    public void waitForElementVisibility(By locator) {
        log.info("Waiting for element visibility: " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Submits a form using the specified element.
     *
     * @param locator The locator of the element representing the form.
     */
    public void submitForm(By locator) {
        log.info("Submitting form with element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.submit();
    }

    /**
     * Navigates back to the previous page.
     */
    public void navigateBack() {
        log.info("Navigating back");
        driver.navigate().back();
    }

    /**
     * Navigates forward to the next page.
     */
    public void navigateForward() {
        log.info("Navigating forward");
        driver.navigate().forward();
    }

    /**
     * Waits until the page has finished loading.
     */
    public void waitForPageLoad() {
        log.info("Waiting for page to load");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Scrolls to the specified element.
     *
     * @param locator The locator of the element to scroll to.
     */
    public void scrollToElement(By locator) {
        log.info("Scrolling to element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Checks the checkbox if it is not already selected.
     *
     * @param locator The locator of the checkbox element.
     */
    public void checkCheckbox(By locator) {
        log.info("Checking checkbox: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    /**
     * Clears the text in the specified element.
     *
     * @param locator The locator of the element.
     */
    public void clearText(By locator) {
        log.info("Clearing text in element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
    }

    /**
     * Switches to the specified frame.
     *
     * @param locator The locator of the frame element.
     */
    public void switchToFrame(By locator) {
        log.info("Switching to frame: " + locator);
        WebElement frameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.switchTo().frame(frameElement);
    }

    /**
     * Switches to the window with the specified window handle.
     *
     * @param windowHandle The window handle of the window to switch to.
     */
    public void switchToWindow(String windowHandle) {
        log.info("Switching to window: " + windowHandle);
        driver.switchTo().window(windowHandle);
    }

    /**
     * Executes the specified JavaScript code.
     *
     * @param script The JavaScript code to execute.
     */
    public void executeJavaScript(String script) {
        log.info("Executing JavaScript: " + script);
        ((JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * Pauses the execution for the specified number of seconds.
     *
     * @param seconds The number of seconds to wait.
     */
    public void wait(int seconds) {
        log.info("Waiting for " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asserts that the specified condition is true.
     *
     * @param condition The condition to assert.
     */
    public void assertCondition(boolean condition) {
        log.info("Asserting condition: " + condition);
        Assert.assertTrue(condition);
    }

    /**
     * Takes a screenshot and saves it to the specified filename.
     *
     * @param filename The filename to save the screenshot.
     */
    public void takeScreenshot(String filename) {
        log.info("Taking screenshot: " + filename);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the locator type and value into a By object.
     *
     * @param locatorType  The type of locator (e.g., id, xpath, cssSelector).
     * @param locatorValue The value of the locator.
     * @return The By object representing the locator.
     */
    public By getLocator(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "classname":
                return By.className(locatorValue);
            case "tagname":
                return By.tagName(locatorValue);
            case "linktext":
                return By.linkText(locatorValue);
            case "partiallinktext":
                return By.partialLinkText(locatorValue);
            case "cssselector":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    /**
     * Closes the browser.
     */
    public void closeBrowser() {
        log.info("Closing the browser");
        if (driver != null) {
            driver.quit();
        }
    }
}
