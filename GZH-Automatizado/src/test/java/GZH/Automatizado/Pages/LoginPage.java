package GZH.spring.selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage extends HomePage{

  @FindBy(id = "username")
  WebElement usernameInput;

  @FindBy(id = "password")
  WebElement passwordInput;

  @FindBy(className = "radius")
  WebElement loginButton;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void typeUsername(String username) {
    this.usernameInput.sendKeys(username);
  }

  public void typePassword(String password) {
    this.passwordInput.sendKeys(password);
  }

  public void clickLogin() {
    loginButton.click();
  }
}
