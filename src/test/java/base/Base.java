package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base {
    // Logger for logging messages
    protected static final Logger logger = Logger.getLogger(Base.class);

    // WebDriver instance to interact with the browser
    protected WebDriver driver;

    // Properties to store configuration settings
    protected Properties properties;

    public Base() {
        // Initialize properties and logger
        initializeProperties("src/test/resources/config.properties");
        initializeLogger();
    }

    public WebDriver initializeBrowser() {
        // Get the browser name from properties
        String browserName = properties.getProperty("browser");

        // Check if headless mode is enabled
        boolean headlessMode = Boolean.parseBoolean(properties.getProperty("headless"));

        if (browserName.equalsIgnoreCase("chrome")) {
            // Setup ChromeDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();

            // Configure ChromeOptions for additional settings
            ChromeOptions options = new ChromeOptions();

            // Set headless mode if enabled
            if (headlessMode) {
                options.addArguments("--headless");
            }

            // Create a new ChromeDriver with the provided options
            driver = new ChromeDriver(options);
        } else {
            // Throw an exception for unsupported browser names
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        // Maximize the browser window
        driver.manage().window().maximize();

        // Return the initialized WebDriver instance
        return driver;
    }

    private void initializeProperties(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            // Load the properties file into the properties object
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Log an error message if properties initialization fails
            logger.error("Error initializing properties: " + e.getMessage());

            // Throw a runtime exception to indicate the failure
            throw new RuntimeException("Error initializing properties: " + e.getMessage());
        }
    }

    private void initializeLogger() {
        // Configure log4j using the provided log4j.properties file
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    public Properties getProperties() {
        // Return the properties object
        return properties;
    }
}
