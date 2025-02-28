package webpages.analyticspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class OverviewPage extends WebPage {

    @FindBy(css = "#js_skillsContainer")
    protected WebElement skillsElement;

    @FindBy(css = "div > label > input + span.lever")
    protected WebElement includeArchiveButtonElement;

    @FindBy(css = "div > a#js_report_download")
    protected WebElement downloadReportElement;

    @FindBy(css = "div#js_skillsContainer")
    protected WebElement skillTrendElement;



    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public void getOverviewReport() {
        waitForElementVisibility(skillTrendElement);
        waitForVisibilityAndClick(includeArchiveButtonElement);
        waitForElementVisibility(skillsElement);
    }

    public void downloadReport() {
        waitClick(downloadReportElement);
    }




}
