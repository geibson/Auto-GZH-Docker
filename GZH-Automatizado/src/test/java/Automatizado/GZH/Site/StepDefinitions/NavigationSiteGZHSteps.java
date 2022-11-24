package Automatizado.GZH.Site.StepDefinitions;

import Automatizado.GZH.Site.Pages.ArticlePage;
import Automatizado.GZH.Site.Pages.HomePage;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

import static Automatizado.Config.AdditionalFunctions.*;
import static Automatizado.GZH.Site.Pages.ArticlePage.*;

import static Automatizado.GZH.Site.Pages.HomePage.*;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;

/**
 * Classe com os passos de navegação do GZH.
 */
public class NavigationSiteGZHSteps {

    @Autowired
    private ArticlePage articlePage;
    private String highlightedNewsTitle;


    /**
     * Método utilizado para acessar a matéria e validar se realmente acessou a mesma matéria que foi clicada e imprimir o nome da matéria clicada;
     * Nome da matéria que é passada por parâmetro na linha 43 é pego no @method clickOnArticlehighlights()
     */
    @Entao("acesso a materia")
    public void acessArticle() {
        waitPage();
        validateAccessArticle(highlightedNewsTitle);
        System.out.println("Acessei a materia com o titulo " + highlightedNewsTitle.toUpperCase());
    }

    /**
     * Passo que clicar no primeiro hottopics que aparece na tela
     */
    @Quando("Clico no primeiro hottopics")
    public void clickOnFirstHottopics() {
        clickHotTopics();
    }

    /**
     * Passo que clicar no botão volta no top da tela do site
     */
    @Entao("Volto para a home")
    public void backHome() {
        goHome();
        waitPage();
    }
    /**
     * Passo que validar se acessou o JD
     */
    @E("Eu acesso a Edicao Digital")
    public void accessDigitalEdition() {
        //accessDigitalEditing();
    }
    /**
     * Passo para clicar em todas as editorias que existe no menu e validar se acessou cada uma. É necessário ter aberto o menu antes de utilizar esse passo.
     */
    @Entao("Clico e valido cada editoria")
    public void clickAllEditorsOnMenu() {
        ClickAndValidateEachEditorialMenu();
    }

    /**
     * Passo para dar um set no driver, driver novo é passado por @param com onome de driverCurrent
     */
    public void setDriver(WebDriver driverCurrent) {
        driver = driverCurrent;
    }

    /**
     * Passo para realizar o scroll ate o final da pagina
     */
    @Quando("rolo ate o fim da pagina")
    public void scroolAllPage() {
        scrollingToBottomOfPage();
    }

    /**
     * Passo que adiciona um comentario dentro da materia selecionada, existe um exception
     *
     * @throws AWTException
     */
    @E("comento na materia")
    public void adComment() throws AWTException {
        comments();
    }

    /**
     * Passo realiza o click no botao buscar no topo da capa
     */
    @Quando("Clico no botao buscar")
    public void clickTheSearchButtonOnTheCover() {
        waitPage();
        clickSearchTheCover();
    }

    /**
     * Passo para realizar o preenchimento do campo de busca da capa
     *
     * @param searchTerm
     */
    @E("Preencho o campo da busca com o {string}")
    public void fillInTheSearchFieldWithTheTerm(String searchTerm) {
        fillTheSearchField(searchTerm);
    }

    /**
     * Passo para validar o resultado da pesquisa realiza
     */
    @Entao("valido ser realizo a busca")
    public void validPerformedTheSearch() {
        validateSearchResult();
    }

    /**
     * Psso para realizar o click no botao de ultimas noticias no topo da capa
     */
    @Quando("Clico botao de ultimas noticias")
    public void clickButtonDeLatestNews() {
        clickLatestNewsButton();
    }

    /**
     * Passo para realizar o click no botao do especiais no topo da capa
     */
    @Quando("Clico botao de especiais")
    public void clickSpecialsButton() {
        clickSpecialNewsButton();
    }

    /**
     * Passo para realizar o click no incone do CLICTEMPO
     */
    @Quando("Clico no icone do clictempo")
    public void clickOnTheClictempoIcon() {
        clickClicTempoIcon();
    }

    /**
     * Passo para verificar se o site do CLICTEMPO foi aberto
     */
    @Entao("Verifico se abriu o clictempo")
    public void checkIfOpenClicTimpo() {
        validateClictempo();
    }


}
