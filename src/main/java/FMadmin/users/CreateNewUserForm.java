package FMadmin.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CreateNewUserForm {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("name");
    private By lastNameInput = By.id("surname");
    private By titleInput = By.id("title");
    private By phoneInput = By.xpath("//input[@placeholder='Enter phone']");
    private By emailInput = By.id("email");
    private By createUserButton = By.xpath("//button[@form='userEditForm']");
    private By userRow = By.xpath("//tr[@class='ant-table-row ant-table-row-level-0']");
    private By userRowFields = By.xpath("//td[@class='ant-table-cell']");

    public CreateNewUserForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillRequiredFields(String name, String surname, String title, String phone, String email){
        if(checkIfAlreadyUserExist(email)){
            email = replaceSymbolInEmail(email);
        }
        driver.findElement(firstNameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(surname);
        driver.findElement(titleInput).sendKeys(title);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(emailInput).sendKeys(email);
    }

    // Each user must have own unique email. That is checking for uniqueness a new user's email and old ones
    private boolean checkIfAlreadyUserExist(String email){
        boolean check = false;
        for(String element : getExistedUsersEmails()){
            check = element.equalsIgnoreCase(email);
            if(check){
                break;
            }
        }
        return check;
    }

    // In case of email coincidence that function replace one symbol at new email by random one
    private String replaceSymbolInEmail(String email) {
        Random random = new Random();
        String newEmail = email.replace(email.charAt(0), (char) (random.nextInt(26 + 'a')));
        if (newEmail.equalsIgnoreCase(email)) {
            replaceSymbolInEmail(email);
        }
        return newEmail;
    }

    private List<String> getExistedUsersEmails(){
        List<WebElement> elements = driver.findElements(userRowFields);
        return elements.stream().map(e->e.getText()).collect(Collectors.toList());
    }

    // Function get num of users before creating new one and after that. So here we assert that new user actually created
    public boolean clickSubmitCreation(){
        int usersBefore = driver.findElements(userRow).size();
        driver.findElement(createUserButton).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(userRow, usersBefore + 1));
        int usersAfter = driver.findElements(userRow).size();
        if(usersBefore != usersAfter){
            return true;
        } else
            return false;
    }

    //Assertions

    public boolean getFirstNameInput() {
        return driver.findElement(firstNameInput).isDisplayed();
    }

    public boolean getLastNameInput() {
        return driver.findElement(lastNameInput).isDisplayed();
    }

    public boolean getTitleInput() {
        return driver.findElement(titleInput).isDisplayed();
    }

    public boolean getPhoneInput() {
        return driver.findElement(phoneInput).isDisplayed();
    }

    public boolean getEmailInput() {
        return driver.findElement(emailInput).isDisplayed();
    }

    public boolean getCreateUserButton() { return driver.findElement(createUserButton).isDisplayed(); }
}
