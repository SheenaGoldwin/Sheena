package webpages.assessmentpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class ArchiveAssessmentPage extends WebPage {

    public ArchiveAssessmentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul.Jobs-jobList > li:first-Child > a")
    protected WebElement selectAssessment;

    @FindBy(css = "div > button > h5")
    protected WebElement archiveDropDown;

    @FindBy(css = "div.JobActions-jobActionContent > div:last-child")
    protected WebElement archiveButton;

    @FindBy(css = "div[role='dialog'] > div:nth-child(4) > button:nth-child(2)")
    protected WebElement confirmArchiveButton;


    public void archiveFromList() {
        waitClick(selectAssessment);
        waitClick(archiveDropDown);
        waitClick(archiveButton);
        waitClick(confirmArchiveButton);
    }









}
