package implementation;


import Pages.AirBnbSearchPage;
import net.thucydides.core.annotations.Step;

public class StepImplementation {

    AirBnbSearchPage searchPage;

    @Step
    public void openAirbBnbPage() {
        searchPage.open();

    }

    @Step
    public void closeCookiesPolicy() {
        searchPage.closeCookiesPolicy();
    }

    @Step
    public void selectLocation(String location) {
        searchPage.selectLocation(location);
    }

    @Step
    public void selectCheckInDate(String checkinDate) {
        searchPage.selectCheckInDate(checkinDate);
    }

    @Step
    public void selectCheckOutDate(String checkinDate, String checkoutDate) {
        searchPage.selectCheckOutDate(checkinDate, checkoutDate);

    }
    @Step
    public void selectGuestsNumber( String adults, String children, String babies) {
        searchPage.selectGuestsNumber(adults, children, babies);
    }

    @Step
    public void searchResults() {
        searchPage.searchResults();
    }
}

