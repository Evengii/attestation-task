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

        CreateNewUserForm newUserForm = manageUsersPage.clickCreateNewUser();

        newUserForm.fillRequiredFields(name, surname, title, phone, email);
        Assertions.assertTrue(newUserForm.clickSubmitCreation(), "User is not displayed in the table");

        LoginPage loginPage = logout();
        Assertions.assertTrue(loginPage.getEmailInput(), "You didn't log out");
    }
}
