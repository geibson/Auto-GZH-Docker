package GZH.spring.selenium.StepDefinitions;

import GZH.spring.selenium.TestConfig;
import GZH.spring.selenium.Config.GZHProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
@Slf4j
public class Hooks {

  @Autowired private GZHProperties properties;

  @Autowired private WebDriver driver;


  @Before
  public void openBrowser(Scenario scenario) {
    driver.get(properties.getHost());
    log.info("[STARTED] Scenario: " + scenario.getName());
  }

  @After
  public void closeBrowser(Scenario scenario) {
    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    scenario.attach(screenshot, "image/png", scenario.getName());
    driver.quit();
    log.info("[ENDED] Scenario: " + scenario.getName());
  }
  public void setDriver(WebDriver driverCurrent) {
    driver = driverCurrent;
  }
}
