package Automatizado.GZH.Site.StepDefinitions;

import Automatizado.GZH.Site.Pages.HomePage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import static Automatizado.Config.AdditionalFunctions.scrollingToBottomOfPage;
import static Automatizado.Config.MenuFunctions.clickEditorialMenu;
import static Automatizado.Config.MenuFunctions.clickMenu;
import static Automatizado.GZH.Site.Pages.HomePage.ClickAndValidateRandomEditorialMenu;
import static Automatizado.GZH.Site.Pages.HomePage.getTitlePage;


/**
 * Classe com os métodos da Home do site GZH
 */
public class HomeSiteGZHSteps {

    @Autowired
    private HomePage homePage;

    /**
     * Passo para iniciar os cenários de testes, pois, ele busca o título do síte aberto e imprimir. A url do síte é recebida na @class AutomatedProperties,
     * essa url vem do @file application-NOMEDOAMBIENTE.yml.
     */
    @Dado("Que estou no site GZH")
    public void acessGZHSite() {
        getTitlePage();
    }

    /**
     * Passo para clicar e abri o menu principal do síte GZH WEB. Pode ser usando a qualquer momento na escrita dos cenários.
     */
    @Quando("Clico no menu do site")
    public void acessMenu() {
        clickMenu();
    }

    /**
     * Passo para imprimir msg de fim de navegação e finaliza a navegação
     */
    @E("Finalizo a minha navegacao")
    public void finishNavigation() {
        System.out.println("Navegação finalizada");
    }

    /**
     * Passo para recebe o nome da editoria por paramentro e acessa a mesma
     */

    @E("Seleciono uma {string} para navegar")
    public void selectOneToBrowse(String editorialNameCurrent) {
        if (editorialNameCurrent == null) {
            editorialNameCurrent = "Sem Editoria";
        }
        clickEditorialMenu(editorialNameCurrent);
    }

    /**
     * Passo para navegar até o final da página
     */
    @E("Navego ate o final da pagina")
    public void scrollToFoot() {
        scrollingToBottomOfPage();
    }


    /**
     * Passo para realizar selecao randomica de uma editoria
     */
    @E("Seleciono uma editoria aleatoria para navegar")
    public void selectRandomEditorial() {
        ClickAndValidateRandomEditorialMenu();
    }

}

