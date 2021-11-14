package uploadFile;

import PCuser.library.LibraryPage;
import PCuser.library.UploadFileForm;
import base.TestBase;
import configs.Credentials;
import configs.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.DashboardHomePage;
import pages.LoginPage;

public class UploadFileTest extends TestBase {

    @DisplayName("Uploading a file by PC user")
    @ParameterizedTest(name = "Run: {index} - value: {arguments}")
    @CsvFileSource(files = {"src/test/resources/PCuser.library/filesPaths.csv"}, numLinesToSkip = 1)
    public void testUploadingFile(String year, String absolutePath){
        DashboardHomePage dashboardHomePage = authorize(Credentials.companyUser2Creds());
        LibraryPage libraryPage = dashboardHomePage.openLibrary();

        UploadFileForm uploadFileForm = libraryPage.clickUploadFileButton();
        Assertions.assertTrue(uploadFileForm.getDocumentUploadForm().isDisplayed(), "Upload form not appeared");

        uploadFileForm.selectFromFiscalYearDropdown(year);
        Assertions.assertTrue(uploadFileForm.getSelectedFiscalYear(year).equals(year), "Year is not matched to required year");

        uploadFileForm.setChooseFile(absolutePath);
        String timestamp = uploadFileForm.clickUploadButton();
        uploadFileForm.clickPopUpGotItButton();
        Assertions.assertTrue(timestamp.equals(uploadFileForm.getTimeOfFileUploading()), "File is not uploaded");

        LoginPage loginPage = logout();
        Assertions.assertTrue(loginPage.getEmailInput(), "You didn't log out");
    }
}
