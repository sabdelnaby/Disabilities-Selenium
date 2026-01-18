package pages.idividualsPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.ArrayList;

public class IndividualLoginPage extends BasePage {
    WebDriver driver;

    public IndividualLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By loginButton = By.cssSelector("dga-button#ad_login_btn[variant='ghost']");


    String languageButtonHost = "dga-navigation-action[aria-label='Menu item']";
    String languageButtonShadowRoot = "button[aria-label='Ar']";
    ArrayList<String> languageButtonRoots = new ArrayList<>();


    String nationalIDFieldHost = "dga-numeric-field[id='national_id']";
    String nationalIDFieldShadowRoot = "input[id='national_id_val']";
    ArrayList<String> nationalIDFieldRoots = new ArrayList<>();


    String nafathVerificationMessagedHost = "dga-modal[class='hydrated']";
    String nafathVerificationMessageShadowRoot = "dga-text[id='modal-title']";
    ArrayList<String> nafathVerificationMessageRoots = new ArrayList<>();



    public void navigateToLoginPage(String URL) {
        driver.get(URL);

    }


    public void changeLanguageToEnglish() throws InterruptedException {
        languageButtonRoots.add(languageButtonHost);
        languageButtonRoots.add(languageButtonShadowRoot);
        try {
            getShadowRootElement(languageButtonRoots).click();
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            getShadowRootElement(languageButtonRoots).click();
        }
    }

    public void enterIndividualNationalID(String nationalID) throws InterruptedException {
        nationalIDFieldRoots.add(nationalIDFieldHost);
        nationalIDFieldRoots.add(nationalIDFieldShadowRoot);
        try {
            getShadowRootElement(nationalIDFieldRoots).sendKeys(nationalID);
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            getShadowRootElement(nationalIDFieldRoots).sendKeys(nationalID);
        }


    }

    public void clickOnLoginMock(){
        waitForClickability(driver.findElement(loginButton));
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.urlContains("landing"));
    }



}
