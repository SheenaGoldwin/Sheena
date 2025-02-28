package testclass;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import webpages.WebPage;
import webpages.analyticspage.AnalyticsPage;
import webpages.analyticspage.AssessmentMetricsPage;
import webpages.analyticspage.DemographicsPage;
import webpages.analyticspage.OverviewPage;
import webpages.assessmentpage.AssessmentPage;
import webpages.dashboardpage.DashboardPage;

public class AnalyticsTest extends BaseTest {

    protected AnalyticsPage analyticsPage;
    protected AssessmentPage assessmentPage;
    protected AssessmentMetricsPage assessmentMetricsPage;
    protected DemographicsPage demographicsPage;
    protected OverviewPage overviewPage;

    @BeforeMethod
    public void goToAnalyticsPage() {
        analyticsPage = dashboardPage.getAnalyticsPage();
    }

    @AfterMethod
    public void goToAssessmentPage() {
        assessmentPage = dashboardPage.goToAssessmentPageUsingSecKey();
    }

    @Test(priority = 0)
    public void getOverviewReport() {

        overviewPage = analyticsPage.getOverviewPage();
        overviewPage.getOverviewReport();
        overviewPage.downloadReport();
        Assert.assertTrue(overviewPage.verifyReportDownload("C:\\Users\\sheen\\Downloads\\overviewReport.csv"), "Error");
    }

    @Test(priority = 1)
    public void assessmentMetricsReport() {
        assessmentMetricsPage = analyticsPage.getAssessmentMetricsPage();
        assessmentMetricsPage.assessmentMetrics();
        assessmentMetricsPage.downloadReport();
        Assert.assertTrue(assessmentMetricsPage.verifyReportDownload("C:\\Users\\sheen\\Downloads\\overviewReport.csv"), "Error");
    }

    @Test(priority = 2)
    public void demographicDistribution() {
        demographicsPage = analyticsPage.getDemographicPage();
        demographicsPage.demographicDistribution();
        demographicsPage.downloadReport();
        Assert.assertTrue(demographicsPage.verifyReportDownload("C:\\Users\\sheen\\Downloads\\overviewReport.csv"), "Error");
    }
}