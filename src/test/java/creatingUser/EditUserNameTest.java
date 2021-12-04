package creatingUser;

import FMadmin.users.EditUserForm;
import FMadmin.users.ManageUsersPage;
import base.TestBase;
import configs.Credentials;
import configs.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardHomePage;


public class EditUserNameTest extends TestBase {
    @DisplayName("Changing any user's name")
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @CsvFileSource(files = {"src/test/resources/FMuser/editUser/userNameEdit.csv"}, numLinesToSkip = 1)
    public void testEditingUser(String name){
        DashboardHomePage dashboardHomePage = authorize(Credentials.fundManagerCreds());
        ManageUsersPage manageUsersPage = dashboardHomePage.openUsersPage();
        manageUsersPage.selectAnyUser();
        EditUserForm editUserForm = manageUsersPage.clickEditButton();

        String actualFirstNameField = editUserForm.clearFirstNameField();
        Assertions.assertEquals("", actualFirstNameField, "First name field is not clear");

        editUserForm.setTextIntoFirstNameField(name);
        editUserForm.clickSubmitUpdateButton();
        Assertions.assertEquals(name, manageUsersPage.getFirstNameOfChosenUser(), "Expected and actual names don't match");
    }
}
