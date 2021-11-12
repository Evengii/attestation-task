package creatingUser;

import FMadmin.users.EditUserForm;
import FMadmin.users.ManageUsersPage;
import base.TestBase;
import configs.Storage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DashboardHomePage;


public class EditUserNameTest extends TestBase {
    @DisplayName("Changing any user's name")
    @Test
    public void testEditingUser(){
        DashboardHomePage dashboardHomePage = authorize(Storage.fundManagerAdmin);
        ManageUsersPage manageUsersPage = dashboardHomePage.openUsersPage();
        manageUsersPage.selectAnyUser();
        EditUserForm editUserForm = manageUsersPage.clickEditButton();
    }
}
