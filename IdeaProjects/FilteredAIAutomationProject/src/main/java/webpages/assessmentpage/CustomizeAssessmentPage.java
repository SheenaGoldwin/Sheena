package webpages.assessmentpage;

import generalutils.UsedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class CustomizeAssessmentPage extends WebPage {

    public CustomizeAssessmentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div > div > div[data-cy='Full_Stack']")
    protected WebElement targetElement;

    @FindBy(css = "div > div > div[data-cy='Build_Your_Own']")
    protected WebElement buildYourOwnElement;

    @FindBy(css = "button[data-cy='btn_upload_job_detail']")
    protected WebElement uploadCloudButtonElement;

    @FindBy(xpath = "//*[contains(text(), 'Choose a local file')]")
    protected WebElement choosePathSelector;

    @FindBy(css = "footer > button[data-cy='btn_next']")
    protected WebElement nextTabButtonElement;

    @FindBy(css = "div[id=':r6:'] > div > div:nth-child(2)")
    protected WebElement chooseQuestionsElement;

    @FindBy(css = "div > div:last-child > span >  button > svg")
    protected WebElement questionPatternElement;

    @FindBy(css = "button > svg[data-testid='EditOutlinedIcon']")
    protected WebElement penEditElement;

    @FindBy(css = "div > input[data-cy='input_role_title']")
    protected WebElement editBarElement;

    @FindBy(css = "button[data-cy='btn_next']")
    protected WebElement nextButtonInQuestionsElement;

    @FindBy(css = "button[data-cy='btn_create_role']")
    protected WebElement createAssessmentButtonElement;

    @FindBy(css = "div > div:nth-child(3)[data-cy='title_successful_create_role']")
    protected WebElement assessmentCreatedElement;

    public String buildOwnAssessment() {
        pauseExecution(2);
        moveToElementDown(targetElement);
        System.out.println(targetElement.getLocation());
        waitForElementVisibility(buildYourOwnElement);
        scrollToElement(buildYourOwnElement);
        actions.click(buildYourOwnElement).perform();
        scrollToElement(uploadCloudButtonElement);
        waitForElementVisibility(uploadCloudButtonElement);
        waitClick(uploadCloudButtonElement);
        waitClick(choosePathSelector);
        String filePath = UsedData.customizedAssessmentPath;
        pauseExecution(2);
        uploadFileAction(filePath);
        pauseExecution(2);
        waitClick(nextTabButtonElement);
        waitClick(chooseQuestionsElement);
        robot.mouseWheel(150);
        waitClick(questionPatternElement);
        waitClick(penEditElement);
        waitForElementVisibility(editBarElement);
        editBarElement.sendKeys("3");
        editBarElement.click();
        waitClick(nextButtonInQuestionsElement);
        waitClick(createAssessmentButtonElement);
        waitForElementVisibility(assessmentCreatedElement);
        System.out.println(assessmentCreatedElement.getText());
        String assessmentText = assessmentCreatedElement.getText();
        return assessmentText;
    }

    public boolean verifyBuildAssessment(String assessmentCreatedMessage) {
        if(assessmentCreatedMessage.contains(UsedData.verifyAssessment)){
            return true;
        }
        return false;
    }
}
