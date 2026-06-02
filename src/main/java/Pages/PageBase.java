package Pages;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

// import static java.lang.StringTemplate.STR;

public class PageBase {
    public static WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();

    public PageBase(WebDriver driver) {
        PageBase.driver = driver;
    }

    public void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitElementToBeDisplayed(By locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(locator, "src", value));
    }
    public void waitElementToBePresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAllElementsToBeInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        return false;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
            return true;
        });
    }

    @SneakyThrows
    public WebElement find(By locator) {
        waitElement(locator);
        highlightElement(locator);
        scrollElement(locator);
        return driver.findElement(locator);
    }

    @SneakyThrows
    public void click(By locator) {
        waitElement(locator);
        find(locator).click();

    }
    public void clickElementToBePresent(By locator) {
        waitElementToBePresent(locator);
        find(locator).click();
    }


    @SneakyThrows
    public void clickWhenOverlayClosed(By locator, By overlayLocator) {
        waitAllElementsToBeInvisible(overlayLocator);
        waitElement(locator);
        scrollElement(locator);
        highlightElement(locator);
        waitAllElementsToBeInvisible(overlayLocator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public void scrollToP() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("//div[@class='ng-dropdown-panel-items scroll-host'].scrollTop=3000");
    }

    @SneakyThrows
    public void setText(By locator, String data) {
        waitElement(locator);
        find(locator).isEnabled();
        find(locator).clear();
        find(locator).sendKeys(data);
    }

    @SneakyThrows
    public List<WebElement> findElements(By locator) {
        waitElement(locator);
        scrollElement(locator);
        highlightElement(locator);
        return driver.findElements(locator);
    }

    public void setKeys(By locator, Keys key) {
        waitElement(locator);
        find(locator).sendKeys(key);
    }

    public void sendKeys(By locator, Keys key, String val) {
        waitElement(locator);
        find(locator).sendKeys(key + val);
    }

    @SneakyThrows
    public void dbClick(By locator) {
        Actions actions = new Actions(driver);
        waitElement(locator);
        actions.doubleClick(find(locator)).perform();
    }

    public void clickUpload(By locator) {
        waitElement(locator);
        find(locator).click();
    }

    public void selectData(By locator, int index) {
        Select select;
        select = new Select(find(locator));
        select.selectByIndex(index);
    }

    public void ClickElementsByClass(By locator, int index) {
        waitElement(locator);
        WebElement element;
        element = driver.findElements(locator)
                .get(index);
        element.click();
    }

    public boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    public boolean hasClassAttribute(By locator, String attribute) {
        return find(locator).getAttribute("class").contains(attribute);
    }


    public String getContent(By locator) {
        waitElement(locator);
        return find(locator).getText();
    }

    public String getContentByValue(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("value");
    }

    public String getContentBySrc(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("src");
    }
    public String getContentByPlaceholder(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("placeholder");
    }
    public String getAttributeValue(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("style");
    }
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }


    public void scrollDownToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)", "");
    }

    public void scrollUpToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -1000)", "");
    }

    public void hover(By locator) {
        WebElement ele = driver.findElement(locator);
        Actions action = new Actions(driver);
        action.moveToElement(ele)
                .perform();
    }

    public static String getFutureDate() {
        Date dt = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(Calendar.DATE, 364);
        dt = calendar.getTime();
        return new SimpleDateFormat("dd/MM/yyyy").format(dt);
    }

    public String setXpathByDataID(String source) {
        return "//*[@data-axis-test-id='" + source + "']";
    }

    public String setXpathByClass(String cls) {
        return "//*[@class='" + cls + "']";
    }

    public String setXpathById(String id) {
        return "//*[@id='" + id + "']";
    }
    /**
     public String setXpathByHref(String href) {
     // return STR."//*[@href='\{href}']";
     return STR + "//*[@href='" + href + "']";
     }
     */
    public String setXpathByRole(String role) {
        return "//*[@role='" + role + "']";
    }
    public static WebElement setElement(String source) {
        return driver.findElement(By.xpath("//*[@data-axis-test-id='" + source + "']"));
    }

    public String setXpathByText(String source) {
        return "//*[text()='" + source + "']";
    }

    public String setXpathByContains(String source) {
        return "//*[contains(text(),'" + source + "')]";
    }

    public String setXpathByClassText(String source, String text) {
        return "//*[@class='" + source + "' and text()='" + text + "']";
    }

    public String setXpathByValue(By locator) {
        waitElement(locator);
        return find(locator).getAttribute("value");
    }

    public void clickByWebElement(String dataID) {
        WebElement element = driver.findElement(By.xpath("//*[@data-axis-test-id='" + dataID + "']"));
        waitWebElement(element);
        element.click();
    }

    public void clickByWebElementOne(WebElement element, String dataID) {
        waitWebElement(element);
        element = driver.findElement(By.xpath("//*[@data-axis-test-id='" + dataID + "']"));
        waitWebElement(element);
        element.click();
    }


    public boolean isDisabled(By locator) {
        waitElement(locator);
        WebElement element = (WebElement) driver.findElement(locator);
        if (element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    @SneakyThrows
    public void softAssertionEqual(By locator, String expected) {
        waitElement(locator);
        softAssert.assertEquals(getContent(locator), expected);
        if (getContent(locator).equals(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
            String currentMethod = new Object() {
            }.getClass().getSimpleName();
            // String methodName = currentMethod.getName();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src, new File(
                        ".\\TestData\\ScreenShots\\SoftAssertions\\" + getClass().getName() + "\\" + currentMethod + "_" + PageBase.getCurrentDate() + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @SneakyThrows
    public void softAssertionEqualOne(By locator, String expected) {
        waitElement(locator);
        softAssert.assertEquals(getContent(locator), expected);
        if (getContent(locator).equals(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
            Method currentMethod = new Object() {
            }.getClass().getEnclosingMethod();
            String methodName = currentMethod.getName();
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src, new File(
                        ".\\TestData\\ScreenShots\\SoftAssertions\\" + getClass().getName() + "\\" + methodName + "_" + PageBase.getCurrentDate() + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public IAssert<?> softAssertAll() {
        softAssert.assertAll();
        return null;
    }

    public void softAssertionImageSrc(By locator, String expected) {
//        waitElementToBeDisplayed(locator, value);
        waitElement(locator);
        softAssert.assertTrue(getContentBySrc(locator).contains(expected));  // assets/images/logo.png
        if (getContent(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionImageEqual(By locator, String expected, String value) {
        waitElementToBeDisplayed(locator, value);
        softAssert.assertEquals(getContentBySrc(locator), expected);
        if (getContentBySrc(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionTrue(By locator) {
        waitElement(locator);
        softAssert.assertTrue(isDisplayed(locator));
        if (getContent(locator).equals("True")) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }
    public void softAssertionFalse(By locator) {
        waitElement(locator);
        softAssert.assertTrue(isDisabled(locator));
        if (getContent(locator).equals("True")){
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }
    public void errorMsgIsDisplayed(By locator) {
        waitElement(locator);
        softAssert.assertTrue(isDisplayed(locator));
        if (getContent(locator).equals("True")){
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }
    public void assertUiIsDisplayed(By locator) {
        waitElement(locator);
        softAssert.assertTrue(isDisplayed(locator));
        if (isDisplayed(locator) == true){
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }
    public void assertPasswordField(By locator) {
        waitElement(locator);
        softAssert.assertEquals(getAttributeByType(locator), "password");
        softAssert.assertAll();
    }
    public String getAttributeByType(By locator){
        waitElement(locator);
        return find(locator).getAttribute("type");
    }


    public void softAssertionContain(By locator, String expected) {
        waitElement(locator);
        softAssert.assertTrue(getContent(locator).contains(expected));
        if (getContent(locator).contains(expected)) {
            highlightAssertedElements(locator);
        } else {
            highlightUnAssertedElements(locator);
        }
    }

    public void softAssertionNotNull(By locator) {
        waitElement(locator);
        softAssert.assertNotNull(getContent(locator));
        highlightAssertedElements(locator);
    }


    public void uploadFile(By locator, String URL) throws AWTException, InterruptedException {
        clickUpload(locator);
        Robot robot = new Robot();
        StringSelection str = new StringSelection(URL);
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(str, null);
        // Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        // Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        // Thread.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        // Thread.sleep(1500);
    }
    public void uploadFileBySaad(By locator , String filePath){
        WebElement uploadElement = driver.findElement(locator);
        uploadElement.sendKeys(filePath);
    }

    public void scrollElement(By locator) {
        WebElement element = driver.findElement(locator);
        int windowHeight = driver.manage()
                .window()
                .getSize()
                .getHeight();
        int elementLocation = element.getLocation()
                .getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, " + (elementLocation - windowHeight / 2) + ")");
    }
    public void scrollBySaad(By option,By list){
        WebElement dropdownContainer = driver.findElement(list);
        boolean optionFound = false;
        while (!optionFound) {
            try {
                // Try to locate the desired option in the dropdown
                WebElement desiredOption = driver.findElement(option);  // Replace with your option's text
                optionFound = true;
                desiredOption.click();  // Click the desired option
            } catch (Exception e) {
                // Scroll down by 100 pixels each time (adjust if necessary)
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += 100;", dropdownContainer);
            }
        }
    }

    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid fuchsia'", element);
    }

    public void highlightAssertedElements(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid blue'", element);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'green';", element);
    }

    public void highlightUnAssertedElements(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid blue'", element);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = 'red';", element);
    }

    public void uploadByXpath(String source, String path) {
        WebElement uploadField = driver.findElement(By.xpath("//*[@data-axis-test-id='" + source + "']"));
        String dir = System.getProperty("user.dir");
        String filePath = dir + path;
        uploadField.sendKeys(filePath);
    }

    public void xOperation(By locator, String text) {
        WebElement element = driver.findElement(locator);
        waitElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .perform();
        actions.keyDown(Keys.CONTROL)
                .sendKeys("x")
                .keyUp(Keys.CONTROL)
                .perform();
        setText(locator, text);
    }

    public String setXpathForCells(int row, int col) {
        return "//table/tbody/tr[" + row + "]/td[" + col + "]";
    }

    public static String generateArabicFirstName() {
        final String[] firstName = {"فاطمة", "زينب", "مريم", "خديجة", "نور", "لمى", "سارة", "عبدالله"
                , "محمد"
                , "أحمد"
                , "علي"
                , "مصطفى"
                , "يوسف"
                , "خالد"
                , "عمر"
                , "محمود"
                , "سعيد"
                , "حسين"
                , "ناصر"
                , "عبدالرحمن"
                , "طارق"
                , "عبدالعزيز"
                , "عبدالكريم"
                , "فارس"
                , "رامي"
                , "مجدي"
                , "سليمان"
                , "جمال"
                , "عبداللطيف"
                , "محسن"
                , "صالح"
                , "رضا"
                , "زكريا"
                , "نورالدين"
                , "جلال"
                , "عبدالملك"
                , "قاسم"};
        Random random = new Random();
        return firstName[random.nextInt(firstName.length)];
    }

    public static String generateArabicLastName() {
        final String[] MIDDLE_NAMES = {"عزيز", "محمد", "أحمد", "علي", "حسين", "محمود", "عبدالله"
                , "محمد"
                , "أحمد"
                , "علي"
                , "مصطفى"
                , "يوسف"
                , "خالد"
                , "عمر"
                , "محمود"
                , "سعيد"
                , "حسين"
                , "ناصر"
                , "عبدالرحمن"
                , "طارق"
                , "عبدالعزيز"
                , "عبدالكريم"
                , "فارس"
                , "رامي"
                , "مجدي"
                , "سليمان"
                , "جمال"
                , "عبداللطيف"
                , "محسن"
                , "صالح"
                , "رضا"
                , "زكريا"
                , "نورالدين"
                , "جلال"
                , "عبدالملك"
                , "قاسم"
                , "عمر"};

        final String[] LAST_NAMES = {"عبد العال"
                , "عبد الرحيم"
                , "عبد الرحمن"
                , "عبد الجبار"
                , "عبد الصبور"
                , "عبد المهيمن"
                , "عبد العزيز"
                , "عبد الملك"
                , "عبد السلام",
                "عبد المؤمن",
                "عبد الخالق",
                "عبدالله",
                "عبد الرشيد",
                "الملواني"
                , "يسري",
                "جمعة",
                "خميس"

        };
        Random random = new Random();
        String middleNameOne = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String middleNameTwo = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return middleNameOne + " " + middleNameTwo + " " + lastName;
    }

    protected static Faker faker = new Faker();

    public static int generateRNumber() {
        return Integer.parseInt(String.valueOf(faker.number().numberBetween(10, 99)));
    }
    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy hh.mm.ss a").format(new Date());
    }

    public static String getDateFormat(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy | hh:mm a");
        Date date = new Date();
        System.out.println("Current Date : " + dateFormat.format(date));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, -15);
        Date currentDatePlusOne = c.getTime();
        return String.valueOf(dateFormat.format(currentDatePlusOne)
                .toLowerCase());
    }
}
