package Pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.TestAirbBnbSearchForm;

@DefaultUrl("https://www.airbnb.es/")
public class AirBnbSearchPage extends PageObject {

    //Page Fields in the AirbBnbHomePage

    // "Vale" Button to accept the cookies
    @FindBy(xpath = "/html/body/div[1]/div[2]/div[4]/div[2]/div/button")
    private WebElementFacade cookiesPopup;

    // input text field to select the location the user want to go
    @FindBy(xpath = "//*[@id=\"Koan-magic-carpet-koan-search-bar__input\"]")
    private WebElementFacade locationBox;

    //input text field to select the check in date
    @FindBy(xpath = "//*[@id=\"checkin_input\"]")
    private WebElementFacade checkinBox;

    //input text field to select the check in date
    @FindBy(xpath = "//*[@id=\"checkout_input\"]")
    private WebElementFacade checkoutBox;

    //right button to pass to the next month
    @FindBy(css = "div[aria-label='Haz clic en la flecha de la derecha para cambiar al mes siguiente.']")
    private WebElementFacade nextMonthButton;

    //Div that contains the calendar
    @FindBy(css = "div[data-visible='true']")
    private WebElementFacade calendarCheckIn;

    @FindBy(css = "div[data-visible='true']")
    private WebElement monthSelectedInCalendar;

    //Button to pick up the guest's number
    @FindBy(xpath = "//*[@id=\"lp-guestpicker\"]")
    private WebElementFacade guestsNumberButton;

    //Button to add the number of adults
    @FindBy(xpath = "//*[@id=\"FMP-target\"]/div/div[1]/div/div/div/div/div/div[2]/div/form/div[3]/div/div[2]/div/div/div[1]/div[1]/div/div/div/div[2]/div/div[3]/button")
    private WebElementFacade adultsNumberButton;

    //Button to add the number of children
    @FindBy(xpath = "//*[@id=\"FMP-target\"]/div[2]/div/div/form/div[3]/div/div[2]/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]/button")
    private WebElementFacade childrenNumberButton;

    //Button to add the number of babies
    @FindBy(xpath = "//*[@id=\"FMP-target\"]/div[2]/div/div/form/div[3]/div/div[2]/div/div/div[1]/div[3]/div/div/div/div[2]/div/div[3]/button")
    private WebElementFacade babiesNumberButton;


    @FindBy(xpath = "//*[@id=\"FMP-target\"]/div/div[1]/div/div/div/div/div/div[2]/div/form/div[4]/div/button")
    private WebElementFacade buscarButton;

    //constructor
    public AirBnbSearchPage(WebDriver driver){
        super(driver);
        max(driver);

    }
    public static void max(WebDriver driver){
        driver.manage().window().maximize();

    }


    public void closeCookiesPolicy() {
        Assert.assertTrue("Fail while click in cookies", TestAirbBnbSearchForm.closeCookiesPolicy(cookiesPopup, "click"));
    }

    public void selectLocation(String location) {
        Assert.assertTrue("Fail in location selection", TestAirbBnbSearchForm.selectLocation(locationBox, location));
    }

    public void selectCheckInDate(String checkinDate) {
        Assert.assertTrue("Fail while typing checkin date", TestAirbBnbSearchForm.selectCheckInDate(checkinBox, nextMonthButton, this.getDriver(), calendarCheckIn, checkinDate));
    }

    public void selectCheckOutDate(String checkinDate, String checkoutDate) {
        Assert.assertTrue("Fail while typing checkout date", TestAirbBnbSearchForm.selectCheckOutDate(checkoutBox, nextMonthButton, calendarCheckIn, checkinDate, checkoutDate));
    }

    public void selectGuestsNumber(String adults, String children, String babies) {
        Assert.assertTrue("Fail while selecting the number of guests", TestAirbBnbSearchForm.selectGuestsNumber(guestsNumberButton, adultsNumberButton, childrenNumberButton, babiesNumberButton, adults, children, babies));
    }

    public void searchResults() {
        Assert.assertTrue("Fail while pressing the 'Buscar' button", TestAirbBnbSearchForm.searchResults(buscarButton));
    }
}
