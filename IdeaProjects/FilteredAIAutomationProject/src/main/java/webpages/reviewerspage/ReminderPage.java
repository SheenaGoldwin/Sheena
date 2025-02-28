package webpages.reviewerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class ReminderPage extends WebPage {

    public ReminderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(), 'Remind')]")
    protected WebElement remindButtonElement;

    @FindBy(css = "a#js_confirm_btn")
    protected WebElement reminderConfirmButtonElement;

    @FindBy(css = "div.js_image")
    protected WebElement confirmationMessageElement;

    @FindBy(css = "a[data-cy='btn_close']")
    protected WebElement closeButtonElement;

    public void sendReminder(String emailId) {
        waitClick(reminderConfirmButtonElement);
        waitClick(closeButtonElement);
        remainderEmailStatus();
    }

    public boolean remainderEmailStatus() {
        if(confirmationMessageElement.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
