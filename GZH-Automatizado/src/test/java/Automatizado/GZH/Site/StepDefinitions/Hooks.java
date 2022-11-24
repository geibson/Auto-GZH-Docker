package Automatizado.GZH.Site.StepDefinitions;

import Automatizado.Config.AutomatedProperties;
import Automatizado.SpringContextConfiguration;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

/**
 * The type Hooks.
 */
@CucumberContextConfiguration
@SpringBootTest(classes = {SpringContextConfiguration.class})
@Primary
public class Hooks {
    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Hooks.class);
    @Autowired
    private AutomatedProperties properties;
    /**
     * The constant driver.
     */
    @Autowired
    public static WebDriver driver;
    //</editor-fold>

    /**
     * Varivel static que busca o ambiente que vai realizar os testes..
     * Obs.: HLG ou PRD
     */
    public static String amb;
    //byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    /**
     * Construtor do class hooks
     *
     * @param driver the driver
     */
    @Autowired
    public Hooks(WebDriver driver) {
        Hooks.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Método para abri o browser
     */
    @Before
    public void openBrowser(Scenario scenario) {
        amb = properties.getAmbiente();
        driver.manage().window().maximize();
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        switch (getScenarioString(scenario)) {

            case "site":
                driver.get(properties.getSite());
                log.info("[STARTED] Scenario: " + scenario.getName());
                scenario.attach(screenshot, "image/png", scenario.getName());
                System.out.println("[STARTED] Scenario: " + scenario.getName());
                break;

            case "edit":
                driver.get(properties.getEdit());
                log.info("[STARTED] Scenario: " + scenario.getName());
                scenario.attach(screenshot, "image/png", scenario.getName());
                System.out.println("[STARTED] Scenario: " + scenario.getName());
                break;

            case "clubedoassinante":
                break;
        }
    }

    @BeforeStep
    public void printBefore(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    @AfterStep
    public void printAfter(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    /**
     * Método que verificar qual string tem no cenario.
     * @param scenario
     * @return
     */
    private static String getScenarioString(Scenario scenario) {
        if (scenario.getUri().getPath().contains("edit")) {
            return "edit";
        } else if (scenario.getUri().getPath().contains("clubedoassinante")) {
            return "clubedoassinante";
        }
        return "site";
    }

    /**
     * Método para fechar o browser
     */
    @After
    public void closeBrowser(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
        driver.quit();
        log.info("[ENDED] Scenario: " + scenario.getName());
        System.out.println("[ENDED] Scenario: " + scenario.getName());
    }

    /**
     * Método para setar o driver para o driver corrente
     */
    public static void setDriver(WebDriver driverCurrent) {
        driver = driverCurrent;
    }
}
