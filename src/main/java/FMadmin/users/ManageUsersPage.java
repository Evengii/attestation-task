package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class ManageUsersPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By createNewUserButton = By.id("addNewUserButton");
    private By editButton = By.id("editButton");
    private By userCheckbox = By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']");

    public ManageUsersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public EditUserForm clickEditButton(){
        driver.findElement(editButton).click();
        return new EditUserForm(driver, wait);
    }

    public CreateNewUserForm clickCreateNewUser(){
        driver.findElement(createNewUserButton).click();
        return new CreateNewUserForm(driver, wait);
    }

    public WebElement selectAnyUser(){
        Random random = new Random();
        List<WebElement> elements = driver.findElements(userCheckbox);
        WebElement element = elements.get(random.nextInt(elements.size()-1));
        element.click();
        return element;
    }

    // Assertions
    public boolean isCreateNewUserButtonDisplayed() {
        return driver.findElement(createNewUserButton).isDisplayed();
    }

    public boolean userIsSelected(WebElement element){
        return element.isSelected();
    }
}
