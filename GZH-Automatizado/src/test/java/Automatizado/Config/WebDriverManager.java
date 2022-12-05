package Automatizado.Config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class WebDriverManager {

  private static String ADDRESS ;
  private static String SELENIUM_PORT;
  private static final String CHROME = "chrome";
  private static final String CONTEXT = "local";

  @Autowired
  private AutomatedProperties properties;

  @Bean
  @Scope("cucumber-glue")
  public WebDriver webDriverFactory() throws IOException {
    return properties.getContext().equalsIgnoreCase(CONTEXT) ? new ChromeDriver()
            : getRemoteWebDriver(properties.getBrowser());
  }

  private WebDriver getRemoteWebDriver(String browser) throws IOException {


    try
    {
      ADDRESS = "http://127.0.0.1";
      SELENIUM_PORT = "4444";
    }catch(Exception e){

      ADDRESS = "http://172.17.0.2";
      SELENIUM_PORT = "4444";
    }
    String remote = String.format("%s:%s/wd/hub", ADDRESS, SELENIUM_PORT);
    if (browser.equalsIgnoreCase(CHROME)) {
      return new RemoteWebDriver(new URL(remote), new ChromeOptions());
    }
    return new RemoteWebDriver(new URL(remote), new FirefoxOptions());
  }
}

