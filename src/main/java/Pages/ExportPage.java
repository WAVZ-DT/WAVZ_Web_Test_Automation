package Pages;

import Elements.ExportElements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExportPage extends ExportElements {
    public ExportPage(WebDriver driver){super (driver);}

    @Step("Click Export Arrow")
    public ExportPage clickExportArrow(){
        click(ExportArrow);
        return this;
    }

    @Step("Click Export Order Diestribution")
    public ExportPage selectExportDistribution() {
        click(selectExportOrderDistribution);
        return this;
    }
    @Step("Click Export Order Follow Up")
    public ExportPage selectExportFollowUp() {
        click(selectExportOrderFollowUp);
        return this;
    }
    @Step("Select Export Order")
    public ExportPage selectOrderCode(){
        click(selectOrderCode);
        return this;
    }

    @Step("Click On Content")
    public ExportPage clickOnContentsTab() {
        //Thread.sleep(2000);
      click(clickOnContentTab);
        return this;
    }
    @Step("Select Content By CheckBox")
    public ExportPage selectContent(){
        click(clickOnCheckBox);
        return this;
    }
    @Step("Click On Export")
    public ExportPage ClickOnExport(){
        click(clickOnExport);
        return this;
    }


    /*
    @Step("Click Sign In")
    public ExportPage clickTrackingPanel() {
        click(TrackingPanel);
        return this;
    }
    */



}
