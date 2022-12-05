package Automatizado.GZH.Site.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static Automatizado.Config.AdditionalFunctions.*;
//import static Automatizado.GZH.Site.Pages.OfferPage.validateBlocoDestaque;
//import static Automatizado.GZH.Site.Pages.OfferPage.validateBlocoRecentes;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Editorial page.
 */
@Component
@Scope("cucumber-glue")
public class EditorialPage {

    private static List<String> hotTopics;
    /**
     * Método que criar a lista de URLs para ser usando nos @methods setEditorialUrl e getUrlsList
     */
    public static List<String> urlsList = new ArrayList<>();
    /**
     * Método que criar a lista de Titulos para ser usando nos @methods setEditoriaTitle e gettitleList
     */
    public static List<String> titleList = new ArrayList<>();

    /**
     * Método que busca o elemento m-crd-pt__headline para ser usando nos @methods clickOnTheHighlightedNews e getHighlightedtitle
     */
    static By headline = By.className("m-crd-pt__headline");
    /**
     * Método que busca o  botão "Exibir mais"
     */
    static By btnshowmore = By.className("btn-show-more");
    /**
     * Método que busca o elemento m-crd-pt__date-from-now para ser usando no @method getHighlightedtitle
     */
    static By listClick = By.className("m-crd-pt__date-from-now");
    /**
     * Método que busca o elemento article-calls para ser usando no @method getHighlightedtitle
     */
    static By articlecall = By.className("article-calls");
    /**
     * Método que busca o elemento o elemento header-title para ser usando em varios @methods, pois ele busca o titulo do header
     */
    static By headertitle = By.className("header-title");
    /**
     * Método que busca o elemento o elemento gzh-header-title para ser usando em varios @methods, pois ele busca o titulo do header da pagina
     */
    static By gzhheadertitle = By.className("gzh-header-title");
    /**
     * Método que busca o elemento loadingMore para ser usando no @method clickShowMore
     */
    static By loading = By.className("loadingMore");

    /**
     * Método que clicar na noticia destaque da editoria
     */
    public static void clickOnTheHighlightedNews() {
        waitPage();
        driver.findElement(headline).click();
    }

    /**
     * Método que busca a materia destaque dentro da editoria
     */

    public static String getHighlightedtitle() {
        waitPage();
        scrollToElement(driver.findElement(articlecall));
        return driver.findElement(articlecall).findElement(headline).getText();
    }

    private static int validShowMore() {
        int qtd = 0;
        if (!driver.findElements(btnshowmore).isEmpty()) {
            scrollToElement(driver.findElement(btnshowmore));
            qtd = driver.findElements(listClick).size();
        }
        return qtd;

    }

    /**
     * Método que clicar no botão "exibir mais" na lista de notícias dentro da editoria
     */
    public static void clickShowMore() {

        if (verifyTitle()) {
            if (driver.findElement(headertitle).getText().toLowerCase().replace("-", " ").equalsIgnoreCase("realizar login")) {
                System.out.println("realizar login ");
            } else if (!driver.findElements(btnshowmore).isEmpty()) {
                waitPage();
                scrollingToBottomOfPage();
                int qtdBeforeClick = validShowMore();
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                Actions actions = new Actions(driver);
                HomePage.closeAllAds();
                javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(btnshowmore));
                actions.moveToElement(driver.findElement(btnshowmore)).perform();
                driver.findElement(btnshowmore).click();
                waitPage();
                if (!driver.findElements(loading).isEmpty()) {
                    assertThat("O botão não carregou", driver.findElements(btnshowmore).isEmpty());
                } else {
                    int qtdAfterClick = validShowMore();
                    assertThat("Não funcionou o exibir mais", qtdBeforeClick <= qtdAfterClick);
                }
            } else {
                System.out.println("O botão não e apresentado na tela");
            }
        } else {
            waitPage();
            scrollingToBottomOfPage();
            int qtdBeforeClick = validShowMore();
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);
            HomePage.closeAllAds();
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(btnshowmore));
            actions.moveToElement(driver.findElement(btnshowmore)).perform();
            driver.findElement(btnshowmore).click();
            waitPage();
            if (!driver.findElements(loading).isEmpty()) {
                assertThat("O botão não carregou", driver.findElements(btnshowmore).isEmpty());
            } else {
                int qtdAfterClick = validShowMore();
                assertThat("Não funcionou o exibir mais", qtdBeforeClick <= qtdAfterClick);
            }
        }
        waitPage();
    }

    /**
     * Método que valida se entrou na editoria correta
     */
    public static void validEditorialUrl(String editoriaName) {
        waitPage();
        if (!driver.findElements(headertitle).isEmpty()) {
            System.out.println("gzhheadertitle " + (driver.findElement(headertitle).getAttribute("href")));
            //System.out.println("headertitle " + (driver.findElement(headertitle).getText()).toLowerCase().replace("-", " "));
            //System.out.println("headertitle " + driver.findElement(headertitle).getAccessibleName());
            System.out.println("Url atual do site " + driver.getCurrentUrl());
        } else {
            System.out.println("gzhheadertitle " + (driver.findElement(gzhheadertitle).getAttribute("href")));
            //System.out.println("gzhheadertitle " + (driver.findElement(gzhheadertitle).getAttribute("href")).toLowerCase().replace("-", " "));
            //System.out.println("gzhheadertitle " + driver.findElement(gzhheadertitle).getAccessibleName());
            System.out.println("Url atual do site " + driver.getCurrentUrl());
        }
        if (!driver.findElements(headertitle).isEmpty()) {
            if (removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "))
                    .contains(removeAccents(driver.findElement(headertitle).getText()).toLowerCase().replace("-", " "))) {

                assertThat(removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " ")),
                        containsString(removeAccents((driver.findElement(headertitle).getText()).toLowerCase().replace("-", " "))));
                System.out.println("acessei a editoria " + editoriaName.toUpperCase());

            } else if (removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "))
                    .contains(removeAccents(editoriaName.replace("-", " ").toLowerCase().replace("-", " ")))) {

                assertThat(removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " ")),
                        containsString(removeAccents(editoriaName.replace("-", " ").toLowerCase())));
                System.out.println("acessei a editoria " + editoriaName.toUpperCase());

            } else if (removeAccents(driver.findElement(headertitle).getText()).toLowerCase().replace("-", " ")
                    .contains(removeAccents(editoriaName.replace("-", " ").toLowerCase().replace("-", " ")))) {

                assertThat(removeAccents(driver.findElement(headertitle).getText()).toLowerCase().replace("-", " "),
                        containsString(removeAccents(editoriaName.replace("-", " ").toLowerCase())));
                System.out.println("acessei a editoria " + editoriaName.toUpperCase());
            } else {
                System.out.println("A URL não condiz com o titulo da pagina");
            }

        } else if (driver.findElement(gzhheadertitle).isEnabled()) {
            if (removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "))
                    .contains(removeAccents(driver.findElement(gzhheadertitle).getText()).toLowerCase().replace("-", " "))) {
                assertThat(removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " ")),
                        containsString(removeAccents((driver.findElement(gzhheadertitle).getText()).toLowerCase().replace("-", " "))));
                System.out.println("acessei a editoria " + editoriaName.toUpperCase());

            } else if (removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "))
                    .contains(removeAccents(editoriaName.replace("-", " ").toLowerCase().replace("-", " ")))) {

                assertThat(removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " ")),
                        containsString(removeAccents(editoriaName.replace("-", " ").toLowerCase())));
                System.out.println("acessei a editoria " + editoriaName.toUpperCase());

            } else {
                System.out.println("A URL não condiz com o titulo da pagina");
            }
        } else {
            System.out.println("Não foram encontrados campos para comparação");
        }
    }

    /**
     * Método que adiciona a editoria na lista de URLs
     */
    public static void setEditorialUrl() {
        waitPage();
        urlsList.add(driver.getCurrentUrl().toLowerCase().replace("-", " "));
        String editorialName = driver.getCurrentUrl();
        String editoriaCorrect = removeAccents(editorialName.toLowerCase().replace("-", " "));
        setEditoriaTitle();
        //System.out.println("Acessei a editoria " + siteUrl.toUpperCase());
    }

    /**
     * Método que adiciona os titulos da editoria na lista de editoria
     */
    private static void setEditoriaTitle() {
        waitPage();

        if (!driver.findElements(headertitle).isEmpty()) {
            titleList.add(removeAccents(driver.findElement(headertitle).getText().toLowerCase()).replace("-", " "));

        } else {
            titleList.add(removeAccents(driver.findElement(gzhheadertitle).getText().toLowerCase()));
        }
    }

    /**
     * Método que busca a lista de titulos
     */
    public static List<String> gettitleList() {
        return titleList;
    }

    /**
     * Método que os campos dentro da editoria acessada
     */
    public static void validEditorialFields(String editorialName) {
        waitPage();

        if (editorialName.toLowerCase().contains("especiais") || editorialName.toLowerCase().contains("pioneiro") || editorialName.toLowerCase().contains("donna")) {
            validEditorialWithCover(editorialName);
        } else {
            String header = removeAccents(driver.findElement(headertitle).getText().toLowerCase().replace("-", " "));
            String url = removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "));
            String editoria = removeAccents(editorialName.toLowerCase().replace("-", " "));


            if (!driver.findElements(headertitle).isEmpty()) {
                if (driver.findElement(headertitle).getText().toLowerCase().replace("-", " ").equalsIgnoreCase("realizar login")) {
                    System.out.println("realizar login ");
                } else if (editoria.contains(header)) {
                    assertThat(editoria, containsString(header));
                    System.out.println("acessei a editoria " + editorialName.toUpperCase());
                } else if (url.contains(header)) {
                    assertThat(url, containsString(header));
                    System.out.println("acessei a editoria " + editorialName.toUpperCase());
                } else {
                    assertThat(url, containsString(editoria));
                    System.out.println("acessei a editoria " + editorialName.toUpperCase());
                }

            } else if (driver.findElement(gzhheadertitle).isEnabled()) {
                assertThat(removeAccents(editorialName).toLowerCase(),
                        containsString(removeAccents(driver.findElement(gzhheadertitle).getText()).toLowerCase()));
                System.out.println("acessei a editoria " + editorialName.toUpperCase());
            } else {
                assertThat(driver.getCurrentUrl().toLowerCase().replace("-", " "), containsString(removeAccents(driver.findElement(gzhheadertitle).getText()).toLowerCase()));
                System.out.println("Acessei a editoria " + driver.findElement(gzhheadertitle).getText().toUpperCase());
            }
        }
    }

    /**
     * Método que valida a editoria acessada, recebe por variavel o nome da editoria
     *
     * @param editorialName
     */
    private static void validEditorialWithCover(String editorialName) {

        String header = removeAccents(driver.findElement(By.className("gzh-header-title")).getText().toLowerCase().replace("-", " "));
        String url = removeAccents(driver.getCurrentUrl().toLowerCase().replace("-", " "));
        String editoria = removeAccents(editorialName.toLowerCase().replace("-", " "));

        if (!driver.findElements(By.className("gzh-header-title")).isEmpty()) {
            if (driver.findElement(By.className("gzh-header-title")).getText().toLowerCase().replace("-", " ").equalsIgnoreCase("realizar login")) {
                System.out.println("realizar login ");
            } else if (editoria.contains(header)) {
                assertThat(editoria, containsString(header));
                System.out.println("acessei a editoria " + editorialName.toUpperCase());
            } else if (url.contains(header)) {
                assertThat(url, containsString(header));
                System.out.println("acessei a editoria " + editorialName.toUpperCase());
            } else {
                assertThat(url, containsString(editoria));
                System.out.println("acessei a editoria " + editorialName.toUpperCase());
            }
        } else if (driver.findElement(By.className("gzh-header-title")).isEnabled()) {
            assertThat(removeAccents(editorialName).toLowerCase(),
                    containsString(removeAccents(driver.findElement(By.className("gzh-header-title")).getText()).toLowerCase()));
            System.out.println("acessei a editoria " + editorialName.toUpperCase());
        } else {
            assertThat(driver.getCurrentUrl().toLowerCase().replace("-", " "), containsString(removeAccents(driver.findElement(By.className("gzh-header-title")).getText()).toLowerCase()));
            System.out.println("Acessei a editoria " + driver.findElement(By.className("gzh-header-title")).getText().toUpperCase());
        }
    }

    /**
     * Método que alterar o drive para o driver atual
     */
    public static void setDriver(WebDriver driverCurrent) {
        driver = driverCurrent;
    }

    /**
     * Método que busca a lista de URLs
     */
    public static List<String> getUrlsList() {
        return urlsList;
    }

    public static void validarBlocoDaCapaEditoria(String bloco, String editoria) {

        switch (bloco) {
            case "colunistas":
                validateBlocoColunistaEditoria(bloco, editoria);
                break;

            case "mais lidas":
                validateBlocoMaisLidasEditoria(bloco, editoria);
                break;

            /*case "destaque":
                validateBlocoDestaque(bloco, editoria);
                break;

            case "recentes":
                validateBlocoRecentes(bloco, editoria);
                break;*/
        }
    }




    private static void validateBlocoColunistaEditoria(String bloco, String editoria) {
        int count = 0;
        scrollToElement(driver.findElement(By.className("columnists-area")));
        WebElement cardsColunistas = driver.findElement(By.className("columnist-cards"));
        List<WebElement> listaDeNoticiasColunistas = cardsColunistas.findElements(By.className("card"));
        validateBlocoCapaEditoria(listaDeNoticiasColunistas, bloco, editoria);
    }

    private static void validateBlocoMaisLidasEditoria(String bloco, String editoria) {
        int count = 0;
        scrollToElement(driver.findElement(By.className("m-most-read-widget")));
        WebElement cardsMaisLidas = driver.findElement(By.className("latest-list"));
        List<WebElement> listaDeNoticiasMaisLidas = cardsMaisLidas.findElements(By.className("card"));
        validateBlocoCapaEditoria(listaDeNoticiasMaisLidas, bloco, editoria);
    }

    private static void validateBlocoCapaEditoria(List<WebElement> listElementos, String bloco, String editoria) {
        int count = 0;
        if (listElementos.size() > 0) {
            for (WebElement item : listElementos) {
                WebElement href = item.findElement(By.tagName("a"));
                if (href.getDomAttribute("href").contains(editoria.toLowerCase())) {
                    count++;
                }
            }
        } else {
            System.err.println("Não achou o bloco solicitado");
        }
        if (count == listElementos.size()) {
            System.out.println("Todos as noticias do bloco " + bloco + "é da editoria " + editoria);
        } else {
            System.err.println("Algumas noticias do bloco " + bloco + "é da editoria " + editoria + "." + "\nVerifique se está correto");
        }

    }

}


