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

public class LibraryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By uploadFileButton = By.cssSelector("td button");

    public LibraryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public UploadFileForm clickUploadFileButton(){
        driver.findElement(uploadFileButton).click();
        return new UploadFileForm(driver, wait);
    }
}
