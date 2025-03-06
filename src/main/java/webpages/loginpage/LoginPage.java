package webpages.loginpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpages.WebPage;
import webpages.dashboardpage.DashboardPage;

public class LoginPage extends WebPage {

    @FindBy(css = "#js_signin_email")
    protected WebElement userNameElement;

    @FindBy(css = "#js_signin_pw")
    protected WebElement passwordElement;

    @FindBy(css = "div#js_signin_error + button[type='submit']")
    protected WebElement loginButnElement;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String name, String password) {
        userNameElement.sendKeys(name);
        passwordElement.sendKeys(password);
        loginButnElement.click();
        return PageFactory.initElements(driver, DashboardPage.class);
    }
}
