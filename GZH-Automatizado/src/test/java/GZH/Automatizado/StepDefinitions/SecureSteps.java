package GZH.spring.selenium.StepDefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import GZH.spring.selenium.Pages.SecurePage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class SecureSteps {

  @Autowired private SecurePage securePage;

  @Then("^I see \"(.*)\" message$")
  public void iSeeMessage(String message) {
    assertThat(this.securePage.getMessage(), is(message));
  }
}

