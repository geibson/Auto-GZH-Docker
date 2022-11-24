package Automatizado.Config;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static Automatizado.Config.AdditionalFunctions.*;
import static Automatizado.GZH.Site.Pages.HomePage.closeAllAds;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Classe com os métodos do menu
 */
@Component
@Scope("cucumber-glue")
public class MenuFunctions {

    /**
     * Instantiates a new Menu functions.
     *
     * Método Construtor
     */
    public MenuFunctions(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Método que buscar o elemento gzh-header-top-nav-item para ser usado no @method clickMenu
     */
    static By clickMenu = By.className("gzh-header-top-nav-item");
    /**
     * Método que buscar o elemento sidebar-primary-sections para ser usado no @method clickMenu
     */
    static By sideBar = By.className("sidebar-primary-sections");
    /**
     * Método que buscar o elemento section-item para ser usado no @method searchAndSelectAnEditorialMenu
     */
    static By itemSections = By.className("section-item");

    /**
     * Método que clicar no menu e validar se ele abriu, caso não seja aberto o menu vai apresentar mensagem de erro na console.
     */
    public static void clickMenu() {
        // É necessário dar um refresh na página antes de clicar no menu, pois o selenium não encontra o menu do GZH Web se não atualizar a página.
        refreshPage();
        waitPage();
        closeAllAds();
        waitPage();
        // É necessário dar um wait na página antes de clicar no menu, para confirmar que toda a pagina foi carregada, pois, senão pode ocorrer exception.
        //waitAds();
        try {
            driver.findElement(clickMenu).click();
            driver.findElement(sideBar);
            //linha que valida e apresenta erro caso o menu não foi aberto.
            assertThat("Não abriu o menu do GZH", driver.findElement(sideBar).isDisplayed());
            System.out.println("Abriu o menu");
        }
        catch(ElementClickInterceptedException e){
            waitPage();
            closeAllAds();
            driver.findElement(clickMenu).click();
            driver.findElement(sideBar);
            //linha que valida e apresenta erro caso o menu não foi aberto.
            assertThat("Não abriu o menu do GZH", driver.findElement(sideBar).isDisplayed());
            System.out.println("Abriu o menu");

            }
    }

    /**
     * Método que clicar na editoria do menu que esta sendo passada por paramentro.
     * Nesse método ele também faz o scroll caso não esteja visivel na tela
     */
    public static void clickEditorialMenu(String editorialName) {

        if (editorialName.toLowerCase().contains("reportagens especiais".toLowerCase())) {
            waitPage();
            WebElement editorial = searchAndSelectAnEditorialMenu(editorialName);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);
            waitPage();
            System.out.println(editorialName);
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", editorial);
            actions.moveToElement(editorial).perform();
            try {
                editorial.click();
                System.out.println("Cliquei na editoria");
            }catch(Exception e){
                closeAllAds();
                editorial.click();
                System.out.println("No catch cliquei na editoria");
            }
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            waitPage();

        } else {
            waitPage();
            WebElement editorial = searchAndSelectAnEditorialMenu(editorialName);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            System.out.println("Clico na editoria "+editorialName);
            waitPage();
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", editorial);
            scrollToElement(editorial);
            try {
                editorial.click();
                System.out.println("Cliquei na editoria");
            }catch(Exception e){
                closeAllAds();
                editorial.click();
                System.out.println("No catch cliquei na editoria");
            }
            waitPage();
        }
    }

    /**
     * Metodo que fazer a pesquisar da editoria e seleciona a editoria passada por parametro
     */
    private static WebElement searchAndSelectAnEditorialMenu(String editorialTitle) {
        List<WebElement> listWebElements = driver.findElements(itemSections);
        return listWebElements.stream()
                .filter(editorialItem -> checkMenuTitle(editorialItem, editorialTitle))
                .findFirst()
                .orElse(null);
    }

    /**
     *
     * Metodo que validar se a editoria passada por paramentro e a mesma que esta sendo clicada, se devolve um @param boolean
     */
    private static boolean checkMenuTitle(WebElement itemMenu, String editorialTitle) {
        boolean isEditorialboolean;
        isEditorialboolean = removeAccents(itemMenu.getText().toLowerCase()).contains(removeAccents(editorialTitle).toLowerCase());
        return isEditorialboolean;
    }
}
