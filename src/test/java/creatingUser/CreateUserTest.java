package creatingUser;

import FMadmin.users.CreateNewUserForm;
import base.TestBase;
import configs.Credentials;
import configs.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardHomePage;
import pages.LoginPage;
import FMadmin.users.ManageUsersPage;

public class CreateUserTest extends TestBase {

    @DisplayName("Creating a user")
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @CsvFileSource(files = {"src/test/resources/FMuser/users.csv"}, numLinesToSkip = 1)
    public void createUser(String name, String surname, String title, String phone, String email){

        DashboardHomePage dashboardHomePage = authorize(Credentials.fundManagerCreds());
        ManageUsersPage manageUsersPage = dashboardHomePage.openUsersPage();
        Assertions.assertTrue(manageUsersPage.isCreateNewUserButtonDisplayed(), "Users page not opened");

        CreateNewUserForm createNewUserForm = manageUsersPage.clickCreateNewUser();
        Assertions.assertAll(
                () -> Assertions.assertTrue(createNewUserForm.getFirstNameInput(), "First field not displayed"),
                () -> Assertions.assertTrue(createNewUserForm.getLastNameInput(), "Last field not displayed"),
                () -> Assertions.assertTrue(createNewUserForm.getEmailInput(), "Email field not displayed"),
                () -> Assertions.assertTrue(createNewUserForm.getTitleInput(), "Title field not displayed"),
                () -> Assertions.assertTrue(createNewUserForm.getPhoneInput(), "Phone field not displayed"),
                () -> Assertions.assertTrue(createNewUserForm.getCreateUserButton(), "Create button not displayed")
        );

        createNewUserForm.fillRequiredFields(name, surname, title, phone, email);
        Assertions.assertTrue(createNewUserForm.clickSubmitCreation(), "User is not displayed in the table");

        LoginPage loginPage = logout();
        Assertions.assertTrue(loginPage.getEmailInput(), "You didn't log out");
    }
}
