package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import implementation.StepImplementation;
import net.thucydides.core.annotations.Steps;


import java.io.IOException;

public class MyStepdefs {

    @Steps
    StepImplementation stepImplementation;

    @Given("^The airbnb web site is accessible$")
    public void theAirbnbWebSiteIsAccessible() throws IOException {
        stepImplementation.openAirbBnbPage();

    }
    @And("^The cookies policy is closed$")
    public void theCookiesPolicyIsClosed() {
        stepImplementation.closeCookiesPolicy();
    }


    @When("^The search form is filled up with a location \"([^\"]*)\"$")
    public void theSearchFormIsFilledUpWithALocation(String location) throws Throwable {
        //throw new PendingException();
        stepImplementation.selectLocation(location);
    }

    @And("^The search form is filled up with a checkin \"([^\"]*)\" and a checkout \"([^\"]*)\" date$")
    public void theSearchFormIsFilledUpWithACheckinAndACheckoutDate(String checkinDate, String checkoutDate) throws Throwable {
        stepImplementation.selectCheckInDate(checkinDate);
        stepImplementation.selectCheckOutDate(checkinDate, checkoutDate);
    }


    @And("^The search form is filled up with a number of guests  \"([^\"]*)\" ,  \"([^\"]*)\" , \"([^\"]*)\"$")
    public void theSearchFormIsFilledUpWithANumberOfGuests(String adults, String children, String babies) throws Throwable {
        stepImplementation.selectGuestsNumber(adults,children,babies);
    }

    @Then("^The result page is displayed$")
    public void theResultPageIsDisplayed() {
        stepImplementation.searchResults();
    }

}
