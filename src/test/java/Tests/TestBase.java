package Tests;

import Pages.*;


import java.io.File;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.ArrayList;

@Listeners({Utils.ListenerTest.class})
public class TestBase {
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public LoginTests loginTests;
    public ExportPage exportPage;



    @BeforeSuite
    void setEnvironment() {

    }

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        String url = "http://10.109.45.130/dch-test/login";
        driver.navigate().to(url);
        loginPage = new LoginPage(driver);
        loginTests = new LoginTests();
        homePage = new HomePage(driver);

    }

    @AfterTest
    public void tearDown() {
 //        driver.quit();
    }

    @SneakyThrows
    public void generateReport() {
        FileUtils.deleteDirectory(new File("target/allure-results"));
    }

    public void switchTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void refresh() {
        driver.navigate().refresh();

    }


}