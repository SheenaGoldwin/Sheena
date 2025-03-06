package testclass;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import webpages.assessmentpage.AssessmentPage;
import webpages.reviewerspage.*;

public class ReviewersTest extends BaseTest {

    protected AssessmentPage assessmentPage;
    protected ReviewersPage reviewersPage;
    protected CreateReviewersPage createReviewersPage;
    protected ReminderPage reminderPage;
    protected ArchievePage archievePage;
    protected AutoAssignPage autoAssignPage;

    @BeforeMethod
    public void goToReviewersPage() {
        reviewersPage = dashboardPage.getReviewersPage();
    }

    @AfterMethod
    public void goToAssessmentPage() {
        assessmentPage = dashboardPage.goToAssessmentPageUsingSecKey();
    }

    @Test(priority = 0)
    public void addReviewers() {
        createReviewersPage = reviewersPage.getCreateReviewerPage();
        String emailId = "rachel" + dashboardPage.dateTime() + "@gmail.com";
        createReviewersPage.createReviewers(emailId);
        Assert.assertTrue(createReviewersPage.verifyAddedReviewer(emailId), "Error");
    }

    @Test(priority = 1)
    public void sendReminder() {
        String emailId = "jose" + dashboardPage.dateTime()  + "@gmail.com";
        createReviewersPage = reviewersPage.getCreateReviewerPage();
        createReviewersPage.createReviewers(emailId);
        reminderPage = reviewersPage.getReminderPage();
        reminderPage.sendReminder(emailId);
        Assert.assertTrue(reminderPage.remainderEmailStatus(), "Error");
    }

    @Test(priority = 2)
    public void archiveReviewers() {
        String emailId = "keerth" + dashboardPage.dateTime()  + "@gmail.com";
        createReviewersPage = reviewersPage.getCreateReviewerPage();
        createReviewersPage.createReviewers(emailId);
        archievePage = reviewersPage.getArchivePage();
        archievePage.archive();
        Assert.assertTrue(archievePage.verifyArchievedReviewer(emailId), "Error");
    }

    @Test(priority = 3, dataProvider = "test3")
    public void autoAssignReviewers(String emailId) {
        createReviewersPage = reviewersPage.getCreateReviewerPage();
        createReviewersPage.createReviewers(emailId);
        autoAssignPage = reviewersPage.getAutoAssignPage();
        String talent1 = autoAssignPage.autoAssign(emailId);
        Assert.assertTrue(autoAssignPage.verifyAutoAssign(talent1, emailId));
    }

    @DataProvider(name = "test3")
    public Object[][] createData3() {
        return new Object[][] {
                { "merlin1@gmail.com" }
        };
    }
}