package webpages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class WebPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;
    protected Actions actions;
    protected Robot robot;
    protected File file;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        jse = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            moveToElementAndClick(element);
        }
    }

    /**
     *
     *
     * @param element
     * Function is to wait until the element is visible and
     * then click the element
     */

    public void waitForVisibilityAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).click();
        } catch (StaleElementReferenceException e) {
            waitForElementVisibility(element);
            element.click();
        }
    }

    public void waitForElementVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            waitForElementVisibility(element);
        }
    }

    /**
     *
     * @param elements
     *
     * The purpose of this function is to wait for the visibility of the
     * list of elements
     *
     */

    public void waitForListOfElementsVisibility(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            waitForListOfElementsVisibility(elements);
        }
    }

    public void moveToElementDown(WebElement moveElement) {
        moveToElementAndClick(moveElement);
        actionPageDown();
    }

    /**
     * To perform scroll down to the page by pressing the PAGE DOWN
     * button on the Keyboard using Action class
     */

    public void actionPageDown() {
        actions.keyDown(Keys.PAGE_DOWN).perform();
        actions.keyUp(Keys.PAGE_DOWN).perform();
    }

    public void moveToElementUp(WebElement moveElement) {
        moveToElementAndClick(moveElement);
        actions.keyDown(Keys.PAGE_UP).perform();
        actions.keyUp(Keys.PAGE_UP).perform();
    }

    /**
     * To scroll to the element using Action class
     * @param element
     */

    public void scrollToElement(WebElement element) {
        actions.scrollToElement(element);
    }


    /**
     * To move to the particular element and to perform the click action using
     * Action class
     * @param element
     */

    public void moveToElementAndClick(WebElement element) {
        actions.moveToElement(element).perform();
        actions.click().perform();
    }

    /**
     * To press the Enter key on the Keyboard using Action class
     */

    public void pressEnterButton() {
        actions.keyDown(Keys.ENTER).perform();
        actions.keyUp(Keys.ENTER).perform();
    }


    /**
     * To upload a document using Robot class
     *
     * @param
     */

    public void uploadFileAction(String filePath) {
        try {
            robot = new Robot();
            StringSelection selectFilePath = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectFilePath, selectFilePath);

            robot.delay(2000);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(500);
        }
    }

    public void searchSelect(WebElement element, String emailId, WebElement element2) {
        searchElement(element, emailId);
        waitForElementVisibility(element2);
    }

    /**
     * Wait for the visibility of the element, click and to send the input values in the
     * field
     * @param element
     * @param emailId
     */
    public void searchElement(WebElement element, String emailId) {
        try {
            waitForVisibilityAndClick(element);

        } catch (TimeoutException e) {
            waitForElementVisibility(element);
            element.click();
            element.clear();
        }
        element.sendKeys(emailId);
    }

    public void searchSelectForList(WebElement element, String emailId, List<WebElement> elements) {
        try {
            waitForVisibilityAndClick(element);

        } catch (StaleElementReferenceException e) {
            waitForElementVisibility(element);
            element.click();
            element.clear();
        }
        element.sendKeys(emailId);
        waitForListOfElementsVisibility(elements);
    }

    public void searchSelectAndClick(WebElement element, String emailId, WebElement element2) {
        searchSelect(element, emailId, element2);
        element2.click();
    }

    public boolean verifyReportDownload(String filePath) {
        try {
            file = new File(filePath);
            if (file.exists()) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void pauseExecution(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Runtime error" + e.getMessage());
        }
    }

    public String dateTime() {
        String pattern = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return pattern;
    }
}
