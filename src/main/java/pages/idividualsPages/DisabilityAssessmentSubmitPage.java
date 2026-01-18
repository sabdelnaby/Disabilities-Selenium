package pages.idividualsPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.ArrayList;

public class DisabilityAssessmentSubmitPage extends BasePage {

    public DisabilityAssessmentSubmitPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

//    private By beneficiaryDropDown = By.cssSelector("input[id='beneficiary']");
    private final By selectHostLocator = By.id("beneficiary");
    private final By beneficiaryID = By.cssSelector("dga-select-item[id='1000000008']");
    private final By checkEligibilityButton = By.cssSelector("dga-button[id='check_btn']");
    private final By goToRequestsButton = By.cssSelector("dga-button[variant='dark']");
    private final By popupMessage = By.cssSelector("dga-modal[is-opened]");
    private final By blanket = By.cssSelector("dga-blanket[class='is-opened']");

    String beneficiaryDropDownHost = "dga-select-item[id='beneficiary']";
    String beneficiaryDropDownShadowRoot = "input[id='beneficiary']";
    ArrayList<String> beneficiaryDropDownRoots = new ArrayList<>();

//    public void clickOnBeneficiaryDropDown(){
//        waitForClickability(driver.findElement(beneficiaryDropDown));
//        driver.findElement(beneficiaryDropDown).click();
//
//    }


//    public void clickOnBeneficiaryDropDown() throws InterruptedException {
//        beneficiaryDropDownRoots.add(beneficiaryDropDownHost);
//        beneficiaryDropDownRoots.add(beneficiaryDropDownShadowRoot);
//        WebElement dropdown = getShadowRootElement(beneficiaryDropDownRoots);
//        waitForClickability(dropdown);
//        try {
//            getShadowRootElement(beneficiaryDropDownRoots).click();
//        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
//            getShadowRootElement(beneficiaryDropDownRoots).click();
//        }
//
//
//
//    }

    public void clickOnBeneficiaryID(){

        waitForClickability(driver.findElement(beneficiaryID));
        driver.findElement(beneficiaryID).click();

    }

    public void selectBeneficiaryID(String individualID) throws InterruptedException {
        WebElement selectHost = waitForVisibility(driver.findElement(selectHostLocator));
        click(selectHost);

        WebElement BeneficiaryID = waitForElementVisibleWithRetry("dga-select-item[id='"+individualID+"']");
        click(BeneficiaryID);
    }

    public void clickOnCheckEligibilityButton() {
        WebElement button = waitForVisibility(driver.findElement(checkEligibilityButton));
        click(button);
    }

    public void clickOnGoToRequestsButton() {
//        waitForVisibility(driver.findElement(blanket));
        waitForVisibility(driver.findElement(popupMessage));
        WebElement button = waitForVisibility(driver.findElement(goToRequestsButton));
        click(button);
    }


}
