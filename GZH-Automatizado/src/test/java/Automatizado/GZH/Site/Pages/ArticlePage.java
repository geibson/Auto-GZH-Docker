package Automatizado.GZH.Site.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static Automatizado.Config.AdditionalFunctions.*;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Classe com todos os métodos utilizados na pagina de uma noticia
 */
@Component
@Scope("cucumber-glue")
public class ArticlePage {

    /**
     * Método para buscar o elemento w-div para ser usando no @method skipAdvertisement
     */
    static By adDivElement = By.tagName("w-div");
    /**
     * Método para buscar o elemento span para ser usando no @method skipAdvertisement
     */
    static By adSpanElement = By.tagName("span");
    /**
     * Método para buscar o elemento top-login-button para ser usando no @method clickToRegister
     */
    static By btnSignwall = By.className("top-login-button");
    /**
     * Método para buscar o elemento articleTitle para ser usando no @method getArticleTitle
     */
    static By articleTitle = By.className("m-headline");
    /**
     * Método para buscar o elemento NOSSASITE para ser usando no @method clickToRegister
     */
    static By nossasite = By.id("NOSSASite");

    /**
     * Método construtor
     */
    public ArticlePage(WebDriver driver) {
    }

    /**
     * Método para dar refresh ate aparece o signwal ou paywall
     */
    public static void showSingWall(ArticlePage driver) {
        skipAdvertisement();
        System.out.println("Refresh para mostrar o signwall ou paywall");
        refreshPage();
        //driver.navigate().refresh();
        skipAdvertisement();
    }

    /**
     * Método para pular a publicidades
     */
    public static void skipAdvertisement() {
        try {
            if (driver.findElements(adDivElement).size() > 0)
                driver.findElements(adDivElement).get(0).findElements(adSpanElement).get(0).click();
            System.out.println("ad found");
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("ad not found");
        }
    }

    /**
     * Método que clicar no botão do nossa para realizar o login
     */
    public static void clickToRegister() {
        waitPage();
        if(!driver.findElement(btnSignwall).isDisplayed()){
            refreshPage();
        }else{
            waitPage();
            scrollToElement(driver.findElement(btnSignwall));
            skipAdvertisement();
            driver.findElement(btnSignwall).click();
            driver.findElement(nossasite).getLocation();
            driver.switchTo().frame(driver.findElement(nossasite));
        }
    }

    /**
     * Método que busca o titulo da materia que esta sendo acessada
     */
    public static String getArticleTitle() {
        waitPage();
        return driver.findElement(articleTitle).getText();
    }

    /**
     * Método que validar se realmente acesso a materia clicada
     */
    public static void validateAccessArticle(String highlightedNewsTitle) {
        assertThat(highlightedNewsTitle, containsString(getArticleTitle()));
    }
}
