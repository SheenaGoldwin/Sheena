package webpages.assessmentpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class CreateAssessmentPage extends WebPage {

    public CreateAssessmentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div > div > div[data-cy='Full_Stack']")
    protected WebElement fullStackElement;

    @FindBy(xpath = "//*[contains(text(), 'Front-End Developer / Engineer')]")
    protected WebElement fullStackDeveloperElement;

    @FindBy(css = "div > footer > button[data-cy='btn_next']")
    protected WebElement nextButtonElement;

    @FindBy(css = "div[id=':r6:'] > div > div:nth-child(2) button")
    protected WebElement addAssessmentOptionsElement;

    @FindBy(css = "button[data-cy='btn_next']")
    protected WebElement nextElement;

    @FindBy(css = "button[data-cy='btn_create_role']")
    protected WebElement confirmCreateAssessmentElement;

    @FindBy(css = "div > a[data-cy='nav_pipeline']")
    protected WebElement assessmentAfterCreationElement;

    @FindBy(css = "div[data-cy='title_successful_create_role']")
    protected WebElement assessmentCreatedElement;

    public void returnToAssessments() {
        assessmentAfterCreationElement.click();
    }

    public void createAssessmentForTalents() {
        waitClick(fullStackElement);
        waitClick(fullStackDeveloperElement);
        waitClick(nextButtonElement);
        waitForVisibilityAndClick(addAssessmentOptionsElement);
        waitForVisibilityAndClick(nextElement);
        waitForVisibilityAndClick(confirmCreateAssessmentElement);
    }

    public boolean verifyAssessmentCreation() {
        waitForElementVisibility(assessmentCreatedElement);
        System.out.println(assessmentCreatedElement.getText());
        if (assessmentCreatedElement.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
