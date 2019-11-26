package test;


import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;



public class TestAirbBnbSearchForm {

    //Constructor
    public  TestAirbBnbSearchForm(){
        super();
    }

    private static final String ROW_OF_GIVEN_MONTH = "//td[contains(.,'%s')]";

    //method to accept cookies policy (when we accept the cookies policy, the popups at the buttom is closed)
    public static boolean closeCookiesPolicy(WebElementFacade cookiesPopUp, String action){
        try {
            cookiesPopUp.waitUntilEnabled();
            if (action.equals("click")) { cookiesPopUp.click(); return true;}
        } catch (Exception e) {
            System.out.println("The error is: "+e.toString());
            return false;}
        return false;
    }

    //method to select the test location
    public static boolean selectLocation(WebElementFacade locationBox, String location) {
        try {

            locationBox.clear();
            locationBox.typeAndEnter(location);
            return true;
        } catch (Exception e) {
            System.out.println("*** The error is ****"+e.toString());
            return false;
        }
    }

    // method to select the test checkInDate in the left calendar
    public static boolean selectCheckInDate(WebElementFacade checkinBox, WebElementFacade nextMonthButton, WebDriver driver, WebElementFacade calendarCheckIn, String checkinDate) {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,300)");

        LocalDate date=null;
        try {
            checkinBox.click();
            nextMonthButton.waitUntilEnabled();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            date = LocalDate.parse(checkinDate, formatter);
            LocalDate dateNow=LocalDate.now();
            int monthsBetweenDates= numberOfMonthBetweenDates(dateNow, date);
            if(monthsBetweenDates>0){
                moveToYearAndMonth(monthsBetweenDates, nextMonthButton);
            }
            movetoDay(calendarCheckIn,date);

            return true;
        } catch (Exception e) {
            System.out.println("*** The error in the selectCheckInDate method is:  "+e.toString());
            return false;
        }
    }

    // method to select the test checkoutDate in the right calendar
    public static boolean selectCheckOutDate(WebElementFacade checkoutBox, WebElementFacade nextMonthButton, WebElementFacade calendarCheckIn, String checkinDate, String checkoutDate) {

        LocalDate dateCheckOut=null;
        LocalDate dateCheckIn=null;
        try {
            checkoutBox.click();
            nextMonthButton.waitUntilEnabled();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            dateCheckOut = LocalDate.parse(checkoutDate, formatter);
            dateCheckIn=LocalDate.parse(checkinDate, formatter);

            int monthsBetweenDates= numberOfMonthBetweenDates(dateCheckIn, dateCheckOut);
            if(monthsBetweenDates>0){
                moveToYearAndMonth(monthsBetweenDates, nextMonthButton);

            }
            movetoDay(calendarCheckIn,dateCheckOut);
            return true;
        } catch (Exception e) {
            System.out.println("*** The error in the selectCheckOutDate method is "+e.toString());
            return false;
        }
    }

    private static String getRowOfTheDay(String selectedDay) {
        return String.format(ROW_OF_GIVEN_MONTH, selectedDay);
    }

    //method that return the months of difference between the current date, and the date we want to test and locate in the calendar
    private static int numberOfMonthBetweenDates(LocalDate dateNow, LocalDate date) {
        Period diff=Period.between(dateNow.withDayOfMonth(1), date.withDayOfMonth(1));
        int months=diff.getMonths();
        return months;

    }

    //method to move to the month and year we want to test
    private static void moveToYearAndMonth(int monthsBetweenDates, WebElementFacade nextMonthButton) throws InterruptedException {
        for(int i=0; i<=(monthsBetweenDates+1); i++){
            System.out.println("El valor de i es: "+i);
            //nextMonthButton.waitUntilEnabled();
            nextMonthButton.waitUntilClickable();
            nextMonthButton.click();
        }
    }

    // method to select the day in the calendar
    private static void movetoDay(WebElementFacade calendarCheckIn, LocalDate date) {
        //wait until the calendar is present and enabled
        calendarCheckIn.waitUntilPresent();
        calendarCheckIn.waitUntilEnabled();

        // Get the day of the date we want to select
        String day = Integer.toString(date.getDayOfMonth());

        // Get the date's month we want to select
        Month monthSelected=date.getMonth();

        //Get the month's name in Spanish
        String nombreMes=monthSelected.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        //Get the year of the date passed as parameter
        String year = Integer.toString(date.getYear());

        // Construct the string concatenating the day, month and year to create a date with spanish format
        String ariaLabelDaySelectedCalendar=day+" de "+nombreMes+" de "+year;

        System.out.println("La fecha a buscar en el calendario es: "+ariaLabelDaySelectedCalendar);

        /*In calendar table each td has an 'aria label' that contains the date in Spanish format
        we locate the element dynamically through this date*/
        WebElementFacade daySelected=calendarCheckIn.findBy(By.xpath("//table[@class='_p5jgym']//td[contains(@aria-label,'"+ariaLabelDaySelectedCalendar+"')]"));
        daySelected.waitUntilEnabled();
        daySelected.waitUntilClickable();
        daySelected.click();


    }

    //method to select the number of guests (adults, children and babies)
    public static boolean selectGuestsNumber(WebElementFacade guestsNumberButton, WebElementFacade adultsNumberButton, WebElementFacade childrenNumberButton, WebElementFacade babiesNumberButton, String adults, String children, String babies) {
        int numberOfAdults=Integer.parseInt(adults);
        int counterAdults=0;
        int numberOfChildren=Integer.parseInt(children);
        int counterChildren=0;
        int numberOfBabies=Integer.parseInt(children);
        int counterBabies=0;

        try{
            guestsNumberButton.waitUntilClickable();
            guestsNumberButton.click();
            adultsNumberButton.waitUntilEnabled();

            while(counterAdults<numberOfAdults) {
                adultsNumberButton.waitUntilClickable();
                adultsNumberButton.click();
                counterAdults++;
            }
            /*childrenNumberButton.waitUntilEnabled();

            while(counterChildren<numberOfChildren) {
                childrenNumberButton.waitUntilClickable();
                childrenNumberButton.click();
                counterChildren++;
            }

           babiesNumberButton.waitUntilEnabled();
            while(counterBabies<numberOfBabies) {
                babiesNumberButton.waitUntilClickable();
                babiesNumberButton.click();
                counterBabies++;
            }*/

            guestsNumberButton.waitUntilClickable();
            guestsNumberButton.click();
            return true;
        }
        catch (Exception e) {
            System.out.println("*** The error selecting the guests' number is ****"+e.toString());
            return false;
        }
    }

    // method to click on the 'Buscar' button in the search form
    public static boolean searchResults(WebElementFacade buscarButton) {
        try{

            buscarButton.waitUntilClickable();
            buscarButton.click();
            return true;
        }

        catch (Exception e) {
            System.out.println("*** The error while pressing buscar button was: "+e.toString());
            return false;
        }
    }


}
