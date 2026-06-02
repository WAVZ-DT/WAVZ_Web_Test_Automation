package Pages;

import Elements.HomeElements;
import org.openqa.selenium.WebDriver;

/**
 * HomePage — all actions that can be performed on the Home / Dashboard page.
 * TODO: Add more methods as you discover the features of your new application.
 */
public class HomePage extends PageBase {

    private final HomeElements elements = new HomeElements();

    public HomePage(WebDriver driver) {
        super(driver);
    }
//
//    public String getPageTitle() {
//        return getText(elements.pageTitle);
//    }
//
//    public void logout() {
//        click(elements.userAvatar);
//        click(elements.logoutButton);
//    }
//
//    public boolean isLoaded() {
//        return isDisplayed(elements.pageTitle);
//    }
}
