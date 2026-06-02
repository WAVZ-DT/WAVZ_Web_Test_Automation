package Elements;

import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * LoginElements — all locators for the Login page.
 */
public class LoginElements extends PageBase {
    // Constructor
    public LoginElements(WebDriver driver) {
        super(driver);
    }


    public By emailField      = By.xpath("// input [@name='email' and @type='text']");
    public By passwordField   = By.xpath("// input [@name='password' and @type='password']");
    public By loginButton = By.xpath("// button[@type='submit' ]");
    public By invalidLoginErrorMes = By.xpath(setXpathByContains("اسم المستخدم او كلمة السر غير صحيح"));
    //public By errorMessage    = By.cssSelector(".error-message");
    //public By forgotPassword  = By.linkText("Forgot Password?");
}
