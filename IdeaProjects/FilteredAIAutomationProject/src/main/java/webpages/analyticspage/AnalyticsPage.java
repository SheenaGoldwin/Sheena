package webpages.analyticspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webpages.WebPage;

public class AnalyticsPage extends WebPage {

    public AnalyticsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div#js_mainBody ul.no-margin.font-14 > li:first-child > a")
    protected WebElement overviewButtonElement;

    @FindBy(css = "div > ul > li[data-type='interviewMetrics'] > a")
    protected WebElement assessmentMetricsElement;

    @FindBy(css = "div > ul > li:nth-child(6)[data-type='demographicDistribution'] > a")
    protected WebElement demographicDistributionElement;

    public OverviewPage getOverviewPage() {
        waitForVisibilityAndClick(overviewButtonElement);
        return PageFactory.initElements(driver, OverviewPage.class);
    }

    public AssessmentMetricsPage getAssessmentMetricsPage() {
        waitForVisibilityAndClick(assessmentMetricsElement);
        return PageFactory.initElements(driver, AssessmentMetricsPage.class);
    }

    public DemographicsPage getDemographicPage() {
        waitForVisibilityAndClick(demographicDistributionElement);
        return PageFactory.initElements(driver, DemographicsPage.class);
    }
}