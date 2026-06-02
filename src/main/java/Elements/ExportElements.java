package Elements;

import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExportElements extends PageBase {

    // constructor
    public ExportElements(WebDriver driver) {super(driver);}

    // locators
    public By ExportArrow = By.xpath("//mat-icon [@class='mat-icon notranslate ng-tns-c87-1 ng-trigger ng-trigger-ExpandedRTL material-icons mat-icon-no-color ng-star-inserted']");
    public By selectExportClick;
    public By TrackingPanel =By.xpath(setXpathByText("لوحة المتابعة"));
    public By selectExportOrderDistribution=By.xpath(setXpathByText("توزيع طلبات التصدير"));
    public By selectExportOrderFollowUp = By.xpath(setXpathByText("متابعة طلبات التصدير"));


    // Follow UP orders Locators
    public By selectOrderCode=By.xpath("//a[contains(text(),'DCH-REQ00045')]");
    public By clickOnContentTab=By.id("contents");
    public By clickOnCheckBox = By.xpath("//input [@type='checkbox' and @name='chk_11008']");
    public By clickOnExport=By.xpath("//button[contains(text(),'تصدير')]");
    public By loadingOverlay = By.cssSelector(".ngx-overlay");
    //public By clickOnContentTab = By.id("contents");









}
