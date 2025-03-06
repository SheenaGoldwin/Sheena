package testclass;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webpages.assessmentpage.*;
import webpages.reviewerspage.ArchievePage;

public class AssessmentTest extends BaseTest {

    protected AssessmentPage assessmentPage;
    protected CreateAssessmentPage createAssessmentPage;
    protected ArchiveAssessmentPage archiveAssessmentPage;
    protected CustomizeAssessmentPage customizeAssessmentPage;
    protected FilterPage filterPage;
    protected InviteTalentsPage inviteTalentsPage;

    @BeforeMethod
    public void goToAssessmentPage() {
        assessmentPage = dashboardPage.getAssessmentPage();
    }

    @AfterMethod
    public void returnToAssessmentPage() {
        assessmentPage = dashboardPage.getAssessmentPage();
    }

    @Test(priority = 0)
    public void testUrl() {
        assessmentPage.getUrl();
        Assert.assertTrue(assessmentPage.getUrl());
    }

    @Test(priority = 1)
    public void createAssessment() {
        createAssessmentPage = assessmentPage.goToCreateAssessmentPage();
        createAssessmentPage.createAssessmentForTalents();
        Assert.assertTrue(createAssessmentPage.verifyAssessmentCreation(), "Error");
    }

    @Test(priority = 2)
    public void archiveAssessment() {
        createAssessmentPage = assessmentPage.goToCreateAssessmentPage();
        archiveAssessmentPage.archiveFromList();
        Assert.assertTrue(createAssessmentPage.verifyAssessmentCreation(), "Error");
    }

    @Test(priority = 3)
    public void filterAssessments() {
        filterPage = assessmentPage.goToFilterPage();
        String before = filterPage.countBeforeFilter();
        filterPage.applyFilter();
        String after = filterPage.countAfterFilter();
        Assert.assertTrue(filterPage.verifyFilterFunction(before, after), "Error");
    }

    @Test(priority = 4)
    public void inviteTalents() {
        inviteTalentsPage = assessmentPage.goToInviteTalentsPage();
        String emailId = "elsaephy@gmail.com";
        String billingDetails = inviteTalentsPage.inviteTalentToAssessment(emailId);
        Assert.assertTrue(inviteTalentsPage.verifyInviteSent(billingDetails), "Error");
    }

    @Test(priority = 5)
    public void buildYourOwnAssessment() {
        customizeAssessmentPage = assessmentPage.goToBuildYourOwnAssessmentPage();
        String assessmentCreatedMessage = customizeAssessmentPage.buildOwnAssessment();
        Assert.assertTrue(customizeAssessmentPage.verifyBuildAssessment(assessmentCreatedMessage), "Error");
    }

}

