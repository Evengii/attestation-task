package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EditUserForm {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("name");
    private By lastNameInput = By.id("surname");
    private By titleInput = By.id("title");
    private By phoneInput = By.xpath("//input[@placeholder='Enter phone']");
    private By emailInput = By.id("email");
    private By titleEditForm = By.id("rcDialogTitle12");

    public EditUserForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


}
