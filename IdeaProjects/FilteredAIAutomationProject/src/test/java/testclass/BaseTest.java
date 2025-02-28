package testclass;

import generalutils.GeneralUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import webpages.dashboardpage.DashboardPage;
import webpages.logoutpage.ProfilePage;

public class BaseTest extends GeneralUtils {

    protected ProfilePage profilePage;

    @BeforeClass
    public void setUp() {
    openBrowser();
    setUrl();
    performLogin();
    }

    @AfterClass
    public void tearDown() {
        profilePage = dashboardPage.getProfilePage();
        profilePage.logoutFunction();
    }
}