package PCuser.library;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UploadFileForm {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fiscalYearDropdown = By.id("fiscalYear");
    private By listOfFiscalYears = By.xpath("//div[@class='ant-select-item ant-select-item-option']");
    private By documentUploadForm = By.id("documentUploadForm");
    private By uploadFileArea = By.id("file");
    private By removeButton = By.xpath("//button[@title='Remove file']");
    private By uploadButton = By.xpath("//span[text()='Upload']");
    private By popUpGotItButton = By.id("gotItBtn");
    private By popUpFailingSign = By.xpath("//h1");

    public UploadFileForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void findFiscalYearDropdown(){
        driver.findElement(fiscalYearDropdown).sendKeys(Keys.DOWN);
    }

    public void selectFromFiscalYearDropdown(String year){
        findFiscalYearDropdown();
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(listOfFiscalYears)));
        driver.findElement(By.xpath("//div[@title='" + year + "']")).click(); // xpath of title in list with fiscal years
    }

    public void setChooseFile(String absolutePath){
        driver.findElement(uploadFileArea).sendKeys(absolutePath);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(removeButton)));
    }

    public String clickUploadButton(){
        driver.findElement(uploadButton).click();
        return getSystemTimestamp();
    }

    public void clickPopUpGotItButton(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(popUpGotItButton)));
        driver.findElement(popUpGotItButton).click();
    }

    public String getSystemTimestamp(){
        TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
        return new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date());
    }

    //Assertions
    public String getPopUpSign(){
        return driver.findElement(popUpFailingSign).getText();
    }

    public String getSelectedFiscalYear(String year){
        return driver.findElement(By.xpath("//span[@title='" + year + "']")).getText(); // xpath of text in fiscal year field
    }

    public String getTimeOfFileUploading(){
        return driver.findElement(By.xpath("//td[text()='" + getSystemTimestamp() + "']")).getText();
    }

    public WebElement getDocumentUploadForm(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(documentUploadForm)));
        return driver.findElement(documentUploadForm);
    }
}
