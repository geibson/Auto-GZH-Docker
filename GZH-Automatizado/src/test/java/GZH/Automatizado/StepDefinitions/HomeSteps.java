package GZH.spring.selenium.StepDefinitions;

import GZH.spring.selenium.Pages.HomePage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class HomeSteps {

  @Autowired
  private HomePage homePage;

  @Given("^I open Login Page$")
  public void iOpenLoginPage() {
    homePage.clickFormAuthentication();
  }
}
