package webpages.analyticspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class DemographicsPage extends WebPage {

    @FindBy(css = "div > label > input + span.lever")
    protected WebElement includeArchiveButtonElement;

    @FindBy(xpath = "//*[contains(text(), 'Completed talent')]")
    protected WebElement completedTalentRadioButtonElement;

    @FindBy(css = "div > a#js_report_download")
    protected WebElement downloadReportElement;

    public DemographicsPage(WebDriver driver) {
        super(driver);
    }

    public void demographicDistribution() {
        waitClick(includeArchiveButtonElement);
        waitClick(completedTalentRadioButtonElement);
        completedTalentRadioButtonElement.click();
    }

    public void downloadReport() {
        waitClick(downloadReportElement);
    }
}
