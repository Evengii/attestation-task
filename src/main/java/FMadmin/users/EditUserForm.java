package FMadmin.users;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EditUserForm {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("name");
    private By submitUpdateButton = By.xpath("//span[text()='Update']");
    private By titleEditForm = By.id("rcDialogTitle12");
    private By submitPopUpButton = By.id("dialogOkButton");

    public EditUserForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSubmitPopUpButton(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(submitUpdateButton)));
        driver.findElement(submitUpdateButton).click();
    }
    public void clickSubmitUpdateButton(){
        driver.findElement(submitUpdateButton).click();
    }

    public void setTextIntoFirstNameField(String text){
        driver.findElement(firstNameInput).sendKeys(text);
    }

    public String clearFirstNameField(){
        driver.findElement(firstNameInput).clear();
        return driver.findElement(firstNameInput).getText();
    }

}
