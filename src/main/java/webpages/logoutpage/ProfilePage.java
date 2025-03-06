package webpages.logoutpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class ProfilePage extends WebPage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "ul > li[data-cy='menu_signOut']")
    protected WebElement logOutButtonElement;

    public void logoutFunction() {
        waitClick(logOutButtonElement);
        driver.quit();
    }

}