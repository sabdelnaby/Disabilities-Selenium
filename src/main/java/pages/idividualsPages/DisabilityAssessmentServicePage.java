package pages.idividualsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class DisabilityAssessmentServicePage extends BasePage {

    public DisabilityAssessmentServicePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By startServiceButton = By.cssSelector("dga-button[variant='primary']");

    public void clickOnStartServiceButton(){
        driver.findElement(startServiceButton).click();
        wait.until(ExpectedConditions.urlContains("submit"));
    }
}
