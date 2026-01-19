package pages.entitiesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class AssessmentRequestsPage extends BasePage {

    public AssessmentRequestsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    private final By searchButton = By.cssSelector("dga-button[id='button_RequestsPage_Search']");
    private final By firstRequestNumber = By.xpath("//dga-table-row[1]//dga-table-data[@id='value_RequestsPage_RequestNumber']");


    String requestNumberFieldHost = "dga-field[id='input_Uuid']";
    String requestNumberFieldShadowRoot = "input[id='input_Uuid']";

    public String searchByRequestNumber(String requestNumber) throws InterruptedException {
        waitForVisibility(locateElementsInShadowRoot(requestNumberFieldHost,requestNumberFieldShadowRoot));
        sendKeys(locateElementsInShadowRoot(requestNumberFieldHost,requestNumberFieldShadowRoot),requestNumber);
        click(driver.findElement(searchButton));
       return getText(driver.findElement(firstRequestNumber));
    }
}
