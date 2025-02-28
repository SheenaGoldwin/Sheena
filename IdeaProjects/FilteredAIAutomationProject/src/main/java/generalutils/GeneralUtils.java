package generalutils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import webpages.dashboardpage.DashboardPage;
import webpages.loginpage.LoginPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GeneralUtils {

    protected WebDriver driver;
    protected DashboardPage dashboardPage;
    protected LoginPage loginPage;

    public void openBrowser() {
        String browser = System.getProperty("browser", "chrome");
        String platformOption = System.getProperty("platformOption", "local");
        driver = initBrowser(platformOption, browser);
    }

    public void setUrl() {
        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/configfiles.properties");
        } catch (FileNotFoundException e) {
            System.out.println("Check file path");
        }
        try {
            properties.load(fis);
        } catch (IOException ex) {
            System.out.println("Error loading property file" + ex.getMessage());
        }
        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
    }

    public void performLogin() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = loginPage.login("madhoo@bandisystems.com", "MontrealIsBest@143");
    }

    public WebDriver initBrowser(String platformOption, String browser) {

        if(platformOption.equalsIgnoreCase("saucelabs")) {
            getCloudDriver(browser);

        } else if(platformOption.equalsIgnoreCase("local")){
            getLocalDriver(browser);
        }
        return driver;
    }

    public void getCloudDriver(String browser) {
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "madhoobhea");
        sauceOptions.put("accessKey", "e3fe0a13-0885-4c15-a567-206ef8e88f0b");
        sauceOptions.put("name", "Analytics Test-1");
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions browserName = new ChromeOptions();
            browserName.setPlatformName("Windows 11");
            browserName.setBrowserVersion("latest");
            browserName.setCapability("sauce:options", sauceOptions);
            saucelabsUrl(browserName);
        } else if(browser.equalsIgnoreCase("edge")) {
            EdgeOptions browserName = new EdgeOptions();
            browserName.setPlatformName("Windows 10");
            browserName.setBrowserVersion("latest");
            browserName.setCapability("sauce:options", sauceOptions);
            saucelabsUrl(browserName);
        } else if(browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions browserName = new FirefoxOptions();
            browserName.setPlatformName("Windows 11");
            browserName.setBrowserVersion("latest");
            browserName.setCapability("sauce:options", sauceOptions);
            saucelabsUrl(browserName);
        }
    }

    public void getLocalDriver(String browser) {
        if(browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("The WebBrowser " + browser + "is not available");
        }
    }

    public void saucelabsUrl(Capabilities browserName) {
        try {
            URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver (url, browserName);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
