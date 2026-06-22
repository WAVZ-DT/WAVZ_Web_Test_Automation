package Utils;
import Pages.PageBase;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static Pages.PageBase.driver;
public class ListenerTest implements ITestListener {
    @SneakyThrows
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(
                        ".\\TestData\\ScreenShots\\" + getClass().getName() + "\\" + result.getName() + "_" + PageBase.getCurrentDate() + ".png"));
            } catch (Exception ignored) {
            }
        }
//        driver.quit();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        //        if (res == 200) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(
                        ".\\TestData\\ScreenShots\\" + getClass().getName() + "\\" + result.getName() + "_" + PageBase.getCurrentDate() + ".png"));
            } catch (Exception ignored) {
            }
        }
//     driver.quit();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(src, new File(
                        ".\\TestData\\ScreenShots\\" + getClass().getName() + "\\" + result.getName() + "_" + PageBase.getCurrentDate() + ".png"));
            } catch (Exception ignored) {
            }
        }
//     driver.quit();
    }

    @SneakyThrows
    @Override
    public void onStart(ITestContext context) {
//        ScreenshotFailedCases.deleteScreenShots();
        try {
            Runtime.getRuntime().exec("cmd /c allure generate --clean --output .\\allure-results");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Runtime.getRuntime().exec("cmd /c allure generate --clean --output D:\\Web_Automation\\AgentPortal\\TestData\\allureReport");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @SneakyThrows
    @Override
    public void onFinish(ITestContext result) {
        try {
            Runtime.getRuntime().exec("cmd /c allure serve .\\allure-results");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        try {
//            Runtime.getRuntime().exec("cmd /c allure-combine D:\\Web_Automation\\AgentPortal\\allure-results --dest D:\\Web_Automation\\AgentPortal\\TestData\\allureReport --auto-create-folders");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Runtime.getRuntime().exec("cmd /c allure-combine D:\\Web_Automation\\AgentPortal\\TestData\\allureReport --remove-temp-files");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Runtime.getRuntime().exec("cmd /c allure-combine D:\\Web_Automation\\AgentPortal\\TestData\\allureReport --ignore-utf8-errors");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}