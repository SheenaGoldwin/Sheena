package webpages.analyticspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webpages.WebPage;

public class AssessmentMetricsPage extends WebPage {

    public AssessmentMetricsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div#js_leaderboardSelect > div:nth-child(3)")
    protected WebElement assessmentRadioButtonElement;

    @FindBy(css = "div > a#js_report_download")
    protected WebElement downloadReportElement;

    public void assessmentMetrics() {
        scrollToElement(assessmentRadioButtonElement);
        waitClick(assessmentRadioButtonElement);
    }

    public void downloadReport() {
        waitClick(downloadReportElement);
    }
}