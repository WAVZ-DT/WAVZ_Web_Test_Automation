package Tests;

import Pages.ExportPage;
import Pages.LoginPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.awt.*;

public class ExportTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    String desData = "TestData/Data/Des.json";
    ExportPage exportPage = new ExportPage(driver);

    @Test(priority = 1, enabled = true)
    public void DistributionOfExportOrders() throws InterruptedException, AWTException {
        loginTests.LoginWithValidCredentials();
        exportPage
                .clickExportArrow()
                .selectExportDistribution();
                //.clickTrackingPanel()
               //.ExportDistribution("توزيع طلبات التصدير");
    }
    @Test(priority = 2, enabled = true)
    public void FollowUpOfExportOrders() throws InterruptedException, AWTException {
        loginTests.LoginWithValidCredentials();
        exportPage
                .clickExportArrow()
                .selectExportFollowUp()
                .selectOrderCode()
                .clickOnContentsTab()
                .selectContent()
                .ClickOnExport();
        //.clickTrackingPanel()
        //.ExportDistribution("توزيع طلبات التصدير");
    }
}
