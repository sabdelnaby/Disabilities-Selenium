import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.entitiesPages.*;
import pages.idividualsPages.*;

import java.time.Duration;
import java.util.Objects;

public class TestRunner {

    // WebDriver instance
    WebDriver driver;

    // Individual user pages
    IndividualLoginPage individualLoginPage;
    IndividualLandingPage individualLandingPage;
    IndividualServicesPage individualServicesPage;
    DisabilityAssessmentServicePage disabilityAssessmentServicePage;
    DisabilityAssessmentSubmitPage disabilityAssessmentSubmitPage;
    DisabilityAssessmentRequestsPage disabilityAssessmentRequestsPage;

    // Entity user pages
    EntitiesLoginPage entitiesLoginPage;
    EntitiesInboxPage entitiesInboxPage;
    AssessmentRequestsPage assessmentRequestsPage;

    // Test data
    TestData testData;

    // Runtime request numbers
    String firstRequestNumber;
    String requestNumber;

    // Setup browser and pages
    @BeforeClass
    public void setup() {

        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        individualLoginPage = new IndividualLoginPage(driver);
        individualLandingPage = new IndividualLandingPage(driver);
        individualServicesPage = new IndividualServicesPage(driver);
        disabilityAssessmentServicePage = new DisabilityAssessmentServicePage(driver);
        disabilityAssessmentSubmitPage = new DisabilityAssessmentSubmitPage(driver);
        disabilityAssessmentRequestsPage = new DisabilityAssessmentRequestsPage(driver);

        entitiesLoginPage = new EntitiesLoginPage(driver);
        entitiesInboxPage = new EntitiesInboxPage(driver);
        assessmentRequestsPage = new AssessmentRequestsPage(driver);

        testData = new TestData();
    }

    // Login as individual user
    @Test
    public void individualLogin() throws InterruptedException {

        individualLoginPage.navigateToLoginPage(testData.individualLoginPageURL);
        individualLoginPage.changeLanguageToEnglish();
        individualLoginPage.enterIndividualNationalID(testData.individualNationalID);
        individualLoginPage.clickOnLoginMock();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("landing"),
                "Expected URL to contain 'landing' but found: " + driver.getCurrentUrl()
        );
    }

    // Navigate to services page
    @Test(priority = 1, dependsOnMethods = "individualLogin")
    public void individualLandingPage() throws InterruptedException {

        individualLandingPage.clickOnServices();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("beneficiary-services"),
                "Expected URL to contain 'beneficiary-services' but found: " + driver.getCurrentUrl()
        );
    }

    // Open disability assessment service
    @Test(priority = 2, dependsOnMethods = "individualLandingPage")
    public void servicesPage() throws InterruptedException {

        individualServicesPage.clickOnMedicalAssessmentRequestCard();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("disability-assesment"),
                "Expected URL to contain 'disability-assesment' but found: " + driver.getCurrentUrl()
        );
    }

    // Start assessment service
    @Test(priority = 3, dependsOnMethods = "servicesPage")
    public void disabilityAssessmentServicePage() {

        disabilityAssessmentServicePage.clickOnStartServiceButton();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("submit"),
                "Expected URL to contain 'submit' but found: " + driver.getCurrentUrl()
        );
    }

    // Submit assessment request
    @Test(priority = 4, dependsOnMethods = "disabilityAssessmentServicePage")
    public void disabilityAssessmentSubmitPage() throws InterruptedException {

        disabilityAssessmentSubmitPage.selectBeneficiaryID(testData.individualNationalID);
        disabilityAssessmentSubmitPage.clickOnCheckEligibilityButton();
        disabilityAssessmentSubmitPage.clickOnGoToRequestsButton();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("requests"),
                "Expected URL to contain 'requests' but found: " + driver.getCurrentUrl()
        );
    }

    // Get created request number
    @Test(priority = 5, dependsOnMethods = "disabilityAssessmentSubmitPage")
    public void disabilityAssessmentRequestsPage() {

        firstRequestNumber = disabilityAssessmentRequestsPage.getFirstRequestNumber();

        Assert.assertTrue(
                firstRequestNumber != null && !firstRequestNumber.trim().isEmpty(),
                "Request number is NULL or EMPTY"
        );

        System.out.println(firstRequestNumber);
    }

    // Login as entity user
    @Test(priority = 6, dependsOnMethods = "disabilityAssessmentRequestsPage")
    public void entityLogin() throws InterruptedException {

        entitiesLoginPage.navigateToLoginPage(testData.entityLoginPageURL);
        entitiesLoginPage.changeLanguageToEnglish();
        entitiesLoginPage.enterEntityNationalID(testData.entityNationalID);
        entitiesLoginPage.clickOnLoginMock();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("inbox"),
                "Expected URL to contain 'inbox' but found: " + driver.getCurrentUrl()
        );
    }

    // Open entity inbox
    @Test(priority = 7, dependsOnMethods = "entityLogin")
    public void entityInbox(){

        entitiesInboxPage.clickOnDisabilityAssessmentRequests();

        Assert.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("requests"),
                "Expected URL to contain 'requests' but found: " + driver.getCurrentUrl()
        );
    }

    // Search and validate request
    @Test(priority = 8, dependsOnMethods = "entityInbox")
    public void assessmentRequests() throws InterruptedException {

        assessmentRequestsPage.searchByRequestNumber(firstRequestNumber);
        requestNumber = assessmentRequestsPage.searchByRequestNumber(firstRequestNumber);

        System.out.println("Extracted request number : " + requestNumber);

        Assert.assertEquals(requestNumber, firstRequestNumber);
    }

    // Close browser after all tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
