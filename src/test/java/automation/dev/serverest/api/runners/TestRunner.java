package automation.dev.serverest.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@regression",
        features = {"src/test/resources/features"},
        glue = {"automation.dev.serverest.api.stepDefinitions"},
        monochrome = true,
        dryRun = false
)
public class TestRunner {}