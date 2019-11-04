import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Zuzel 24/05/2019
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = "definitions"
)

public class Runner {

}
