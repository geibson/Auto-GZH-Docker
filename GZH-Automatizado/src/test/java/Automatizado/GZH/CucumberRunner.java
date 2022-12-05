package Automatizado.GZH;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
    glue = {"Automatizado/GZH/Site/StepDefinitions","Automatizado/EDIT/StepDefinitions"},
    //tags = "@b2c @tdc",
    features = "src/test/resources/features",
        plugin = {
        "pretty",
        "html:build/test-results/html-report.html",
        "json:build/test-results/json-report.json"
    }

)

public class CucumberRunner extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}

