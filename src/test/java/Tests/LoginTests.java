package Tests;

import Pages.ExportPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static Utils.JsonReader.TestJson.getJson;

/*
*/

@Epic("Authentication")
@Feature("Login")
public class LoginTests extends TestBase {
    String loginTestData = "TestData/Data/login.json";
    SoftAssert softAssert = new SoftAssert();
    LoginPage loginPage = new LoginPage(driver);


    @Test(priority = 1, enabled = true)
    public void LoginWithValidCredentials() {
        loginPage.
                setEmail((getJson(loginTestData, "email")))
                .setPassword((getJson(loginTestData, "password")))
                .clickSignIn();

    }
    @Test(priority = 1, enabled = true)
    public void LoginWithInValidCredentials() {
        loginPage.
                setEmail((getJson(loginTestData, "invalidEmail")))
                .setPassword((getJson(loginTestData, "invalidpassword")))
                .clickSignIn()
                .assertInvalidLoginError("اسم المستخدم او كلمة السر غير صحيح");
        softAssert.assertAll();


    }
}