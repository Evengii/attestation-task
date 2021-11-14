package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class ManageUsersPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By createNewUserButton = By.id("addNewUserButton");
    private By editButton = By.id("editButton");
    private By userCheckbox = By.cssSelector("tr td input[type='checkbox']");
    private By rowElements = By.cssSelector("tr td.ant-table-cell");

    public ManageUsersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public EditUserForm clickEditButton(){
        if(userIsSelected()) {
            driver.findElement(editButton).click();
        } else
            selectAnyUser();
        return new EditUserForm(driver, wait);
    }

    public CreateNewUserForm clickCreateNewUser(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(createNewUserButton)));
        driver.findElement(createNewUserButton).click();
        return new CreateNewUserForm(driver, wait);
    }



    public void selectAnyUser(){
        Random random = new Random();
        List<WebElement> elements = driver.findElements(userCheckbox);
        WebElement element = elements.get(random.nextInt(elements.size()));
        element.click();
    }

    // Assertions
    public boolean isCreateNewUserButtonDisplayed() {
        return driver.findElement(createNewUserButton).isDisplayed();
    }

    public boolean userIsSelected(){
        return driver.findElement(userCheckbox).isSelected();
    }

    public String getFirstNameOfChosenUser(){
        return driver.findElements(rowElements).get(2).getText();
    }
}
