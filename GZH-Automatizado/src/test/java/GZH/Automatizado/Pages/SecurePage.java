package GZH.spring.selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class SecurePage extends HomePage{

  @FindBy(id = "flash")
  WebElement messageDiv;

  public SecurePage(WebDriver driver) {
    super(driver);
  }

  public String getMessage() {
    return this.messageDiv.getText().split("\n")[0];
  }
}

