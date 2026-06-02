package Pages;

import Elements.LoginElements;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.codehaus.groovy.runtime.ResourceGroovyMethods.setText;

/**
 * LoginPage — all actions that can be performed on the Login page.
 * TODO: Update method bodies once you have the real locators in LoginElements.
 */
public class LoginPage extends LoginElements {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Set Email")
    public LoginPage setEmail(String email) {
        setText(emailField, email);
        return this;
    }

    @Step("Set Password")
    public LoginPage setPassword(String password) {
        setText(passwordField, password);
        return this;
    }
    @Step("Click Sign In")
    public LoginPage clickSignIn() {
        click(loginButton);
        return this;
    }
    @Step("Assert Invalid Login Error Message")
    public LoginPage assertInvalidLoginError(String msg) {
        softAssertionEqual(invalidLoginErrorMes, msg);
        return this;
    }


//    private final LoginElements elements = new LoginElements();
//
//    public void enterEmail(String email) {
//        type(elements.emailField, email);
//    }
//
//    public void enterPassword(String password) {
//        type(elements.passwordField, password);
//    }
//
//    public void clickLogin() {
//        click(elements.loginButton);
//    }
//
//    /**
//     * Full login flow in one call.
//     */
//    public void login(String email, String password) {
//        enterEmail(email);
//        enterPassword(password);
//        clickLogin();
//    }
//
//    public String getErrorMessage() {
//        return getText(elements.errorMessage);
//    }
//
//    public boolean isErrorMessageDisplayed() {
//        return isDisplayed(elements.errorMessage);
//    }
}
