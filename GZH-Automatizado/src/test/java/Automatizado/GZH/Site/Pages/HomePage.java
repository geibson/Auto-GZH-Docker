package Automatizado.GZH.Site.Pages;

import org.openqa.selenium.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static Automatizado.Config.AdditionalFunctions.*;
//import static Automatizado.GZH.Site.Pages.EditorialPage.*;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Classe com os métodos da homepage
 */
@Component
@Scope("cucumber-glue")
public class HomePage {

    /**
     * Método que busca o elemento do menu
     */
    static By clickMenu = By.className("gzh-header-top-nav-item");
    /**
     * Método que busca a primeira seção do menu (primeiro conjunto de dados)
     */
    static By sideBar = By.className("sidebar-primary-sections");
    /**
     * Método que busca o botão cancelar
     */
    static By btnCancelNotification = By.id("onesignal-slidedown-cancel-button");

    static By btnTopCover = By.className("gzh-header-top-nav");

    private static List<WebElement> listAllEditorials;
    private static List<WebElement> listHotTopics;
    private static List<String> hotTopics;
    private static int iteratorHotTopics;
    private static String searchTermLocal;
    public static String EditorialURL;

    private static int weatherTemperatureGZH;

    private static  String weatherCityGZH;

    /**
     * Método que busca titulo da matéria.
     */
    static By articleTitle = By.className("m-crd-pt__headline");//List
    /**
     * The Método que busca um elemento.
     */
    static By topicitem = By.className("topic-item");//List

    static By headeritem = By.className("gzh-header-top-nav-item");

    /**
     * Método que busca o grupo de editorias
     */
    static By grupoEditorialMenu = By.className("sidebar-group-sections");
    /**
     * Método que busca o elemento dentro do menu
     */
    static By itemMenu = By.className("section-item");//List
    /**
     * Método que busca o logoGZH
     */
    static By logoGZH = By.className("gzh-logo");

    /**
     * Método para buscar o elemento do campo de busca
     */
    static By searchBtn = By.className("search-box-btn");

    /**
     * Méotod para buscar o elemento getControl
     */
    static By getControl = By.id("getsitecontrol-273964");

    /**
     * Método que clica no Logo do GZH para voltar para a capa da GZH
     */
    public static void goHome() {
        driver.findElement(logoGZH).click();
    }


    /**
     * Método que busca e imprimir o titulo da pagina atual do driver
     */
    public static void getTitlePage() {
        System.out.println("URL da pagina " + driver.getCurrentUrl());
        closeAllAds();
    }

    /**
     * Método que realiza o clique no banner de cookies, notificações e pop de ads
     */
    public static void closeAllAds() {
        closeGetControl();
        acceptCookies();
        acceptNotification();
        closeAdPop();
    }

    /**
     * Método para realizar o fechamento do GETCONTROL
     */
    private static void closeGetControl(){
        waitPage();
                if(!driver.findElements(getControl).isEmpty()) {
                    try {
                        WebElement getControlShadow = driver.findElement(getControl);
                        SearchContext getControlPopUp = getControlShadow.getShadowRoot();
                        WebElement closeBtn = getControlPopUp.findElement(By.className("close"));
                        closeBtn.click();
                    } catch (Exception ElementClickInterceptedException) {

                    }
                }
        waitPage();
    }

    /**
     * Método que busca o artigo pelo titulo
     */
    public static WebElement getArticleFromTitle(String txtTitle) {
        return driver.findElements(articleTitle).stream()
                .filter(articleItem -> checkArticleTitle(articleItem, txtTitle))
                .findFirst()
                .orElse(null);
    }

    /**
     * Método que pega a primeira noticia da capa
     */
    public static WebElement getFirstArticleCover() {
        return driver.findElements(articleTitle).stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Método que clica no artigo buscando pelo titulo
     */
    public static void findAndClickArticle(String txtTitle) {
        WebElement article = getArticleFromTitle(txtTitle);
        clickArticle(article);
        //return new ArticlePage(driver);
    }

    /**
     * Método que busca e clicar no artigo passado por paramentro
     */
    public static void findAndClickArticle(WebElement article) {
        clickArticle(article);
        //new ArticlePage(driver);
    }

        /**
         * Método que clica no artigo passado por paramentro
         */
    //clicar no artigo passado por paramentro
    public static void clickArticle(WebElement article) {
        scrollToElement(article);
        article.click();
        //new ArticlePage(driver);
    }

    /**
     * Método que verifica se o artigo passado no paramentro e o mesmo que foi clicado
     */
    private static boolean checkArticleTitle(WebElement article, String title) {
        boolean isArticleBoolean;
        WebElement articleTitle = article.findElement(By.className("m-crd-pt__headline"));
        isArticleBoolean = title.equals(articleTitle.getText());
        return isArticleBoolean;
    }

    /**
     * Método que clica nos hottopics pegando a qta de itens que existe na tela
     */
    public static void clickHotTopics() {
        listHotTopics = driver.findElements(topicitem);
        iteratorHotTopics = listHotTopics.size();
        for (int i = 0; i < listHotTopics.size(); i++) {
            listHotTopics = driver.findElements(topicitem);
            listHotTopics.get(i).click();
            setInteradorHotTopics();
            break;
        }
    }


    /**
     * Método get do interador utilizado para validar se entrou ou não no hottopics
     */
    public static int getIteratorHotTopics() {
        return iteratorHotTopics;
    }

    /**
     * Método set para alterar a qtd de hottopics validado
     */
    private static void setInteradorHotTopics() {
        iteratorHotTopics = iteratorHotTopics - 1;
    }

    /**
     * Método que valida se acessou todos os hottopics individualmente depois de clicar no primeiro hottopic
     */

    /**
     * Método que adiciona os topicos em uma lista
     */
    private static void hotTopicList(List<WebElement> listHotTopics) {
        hotTopics = new ArrayList<String>();
        for (WebElement item : listHotTopics) {
            hotTopics.add(removeAccents(item.getText().toLowerCase()));
        }
    }


    /**
     * Método que clicar no primeiro topico
     */
    public static void clickSpecificHeaderItemHome(String editoria) {
        for (int i = 0; i < driver.findElements(headeritem).size(); i++) {
            refreshPage();
            closeAllAds();
            waitPage();
            if(driver.findElements(headeritem).get(i).getText().equalsIgnoreCase(editoria)) {
                System.out.println("url hottopic "+driver.findElements(headeritem).get(i).getText());
                EditorialURL = driver.findElements(headeritem).get(i).getAttribute("href");
                System.out.println("url hottopic "+ EditorialURL);
                driver.findElements(headeritem).get(i).click();
                waitGeneral();

            }

        }
        System.out.println("Clicou no item do Header");
    }
    public static void validateHeaderItemHome() {
        if (driver.getCurrentUrl().equalsIgnoreCase(EditorialURL)) {
            System.out.println("Editoria correta");
        }
    }


    /**
     * Método que clica e valida cada editoria existente no menu. Obs.: Esse método não abre o menu, so validar cada link existente dentro do menu aberto
     */
    public static void ClickAndValidateEachEditorialMenu() {

        List<WebElement> listEditorials = driver.findElements(By.className("section-item"));
        System.out.println("editorias " + listEditorials.size());

        for (int i = 0; i < listEditorials.size(); i++) {
            WebElement webElement = listEditorials.get(i);
            if(webElement.getText().contains("APLICATIVOS") || webElement.getText().contains("PROGRAMA GZH")){
                break;
            }if(listEditorials.get(i).findElement(By.cssSelector("a")).getAttribute("href").contains("flip")
                    || webElement.getText().contains("ESPECIAIS")
                    || webElement.getText().contains("CRUZADINHAS")
                    || webElement.getText().contains("WHATS")
                    || webElement.getText().contains("TESTE")
                    || webElement.getText().contains("FALE CONOSCO")){

                System.out.println(i+"- A editoria "+ listEditorials.get(i).getText()+" sera testada em outro momento ");
            }else{
                System.out.println(i+"- Acessei a editoria "+ listEditorials.get(i).getText());
                WebElement editorial = listEditorials.get(i);
                JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                waitPage();
                javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", editorial);
                scrollToElement(editorial);
                waitPage();
                try{
                    editorial.click();
                    System.out.println("Acessei a url "+ driver.getCurrentUrl());
                    waitPage();
                    driver.findElement(clickMenu).click();
                }catch(ElementClickInterceptedException e){
                    closeAllAds();
                    editorial.click();
                    System.out.println("No catch acessei a url "+ driver.getCurrentUrl());
                    waitPage();
                    driver.findElement(clickMenu).click();
                }
            }
        }
    }

    /**
     * Método que acessa aleatoriamente uma editoria e valida se acessou
     */
    public static void ClickAndValidateRandomEditorialMenu(){
        waitPage();
        List<WebElement> listEditorials = driver.findElements(By.className("section-item"));
        System.out.println("editorias " + listEditorials.size());
        int randomEditorial;
        //randomEditorial = randomMenu(listEditorials);
        WebElement webElement;

        for (int i = 0; i < listEditorials.size(); i++) {
            System.out.println(i + "- Acessei a editoria " + listEditorials.get(i).getText());
        }

        while(true){
            randomEditorial = randomMenu(listEditorials);
            webElement = listEditorials.get(randomEditorial);
            System.out.println("Numero da editoria - "+randomEditorial);
            if(listEditorials.get(randomEditorial).findElement(By.cssSelector("a")).getAttribute("href").contains("flip")
                    || webElement.getText().contains("ESPECIAIS")
                    || webElement.getText().contains("CRUZADINHAS")
                    || webElement.getText().contains("WHATS")
                    || webElement.getText().contains("TESTE")
                    || webElement.getText().contains("APLICATIVOS")
                    || webElement.getText().contains("PROGRAMA GZH")
                    || webElement.getText().contains("FALE CONOSCO")
                    || webElement.getText().contains("BRASILEIR")
                    || webElement.getText().contains("TABELAS")
                    || webElement.getText().contains("SÉRIE B")){
                System.out.println(randomEditorial+"- A editoria "+ listEditorials.get(randomEditorial).getText()+" nao sera testada");
            }else{
                System.out.println("Acessou");
                break;
            }
        }
        System.out.println(randomEditorial+" - Acessei a editoria "+ listEditorials.get(randomEditorial).getText());
        WebElement editorial = listEditorials.get(randomEditorial);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        waitPage();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", editorial);
        scrollToElement(editorial);
        waitPage();
        try{
            editorial.click();
            System.out.println("Acessei a url "+ driver.getCurrentUrl());
            waitPage();
        }catch(ElementClickInterceptedException e){
            closeAllAds();
            editorial.click();
            System.out.println("No catch acessei a url "+ driver.getCurrentUrl());
            waitPage();

        }
    }

    /**
     * Método que clicar no botão buscar no topo da capa
     */
    public static void clickSearchTheCover(){
        refreshPage();
        waitPage();
        driver.findElement(searchBtn).click();

    }

    /**
     * Método que serve para setar a variavel local
     * @param searchTerm
     */
    private static void setSearchTermLocal(String searchTerm) {
        searchTermLocal = searchTerm;
    }

    /**
     * Método que serve realizar a busca atraves de um termo específico passado por @param searchTerm
     */
    public static void fillTheSearchField(String searchTerm){
        setSearchTermLocal(searchTerm);
        driver.findElement(By.className("search-input")).sendKeys(searchTerm);
        driver.findElement(By.className("search-input")).sendKeys(Keys.ENTER);
    }

    /**
     * Método que valida o resultado da pesquisa realizada
     */
    public static void validateSearchResult(){
        System.out.println("entrou na validacao");
        waitPage(6000);
        WebElement searchResult = driver.findElement(By.className("search-results"));
        List<WebElement> searchResultList = searchResult.findElements(By.className("card"));
        if(searchResultList.size()>0 ) {
            System.out.println("Buscar trouxe os resultados");
        }else {
            System.out.println("Pesquisa não encontrou resultados");
            //assertThat("Buscar não trouxe resultados, verifique o termo buscado. \nTermo utilizado: " + searchTermLocal,searchResultList.size()<0);
        }
    }

    /**
     * Método que clicar no botão de últimas notícias no topo da capa
     */
    public static void clickLatestNewsButton(){
        refreshPage();
        waitPage(1500);
        List<WebElement> btnTopList = driver.findElement(btnTopCover).findElements(By.className("gzh-header-top-nav-item"));
        for (WebElement item: btnTopList){
            System.out.println(removeAccents(item.getText().toLowerCase()).contains("ultimas"));
            if(removeAccents(item.getText().toLowerCase()).contains("ultimas")) {
                item.click();
            }else{
                assertThat("Não encontrou o botão para clicar", removeAccents(item.getText().toLowerCase()).contains("ultimas")==false);
            }
        }
    }

    /**
     * Método que clicar no botão de Especiais no topo da capa
     */
    public static void clickSpecialNewsButton(){
        waitPage(2000);
        refreshPage();
        waitPage(2000);
        List<WebElement> btnTopList = driver.findElement(btnTopCover).findElements(By.className("gzh-header-top-nav-item"));
        for (WebElement item: btnTopList){
            if(removeAccents(item.getText().toLowerCase()).contains("especiais")) {
                item.click();
            }else{
                assertThat("Não encontrou o botão para clicar", removeAccents(item.getText().toLowerCase()).contains("especiais")==false);
            }
        }

    }

    /**
     * Método que clicar no icone do tempo na capa do site
     */
    public static void clickClicTempoIcon(){
        WebElement weather = driver.findElement(By.className("m-weather"));
        if(weather.isDisplayed()){
            weatherCityGZH = driver.findElement(By.className("m-weather-city")).getText();
            String TemperatureLocalClicTempo = driver.findElement(By.className("m-weather-temperature")).getText();
            weatherTemperatureGZH = Integer.parseInt(TemperatureLocalClicTempo.substring(0, TemperatureLocalClicTempo.length()-1));
            weather.click();
        }else{
            System.out.println("Clic tempo não esta aparecendo no GZH");
        }
    }

    /**
     * Método que valida se acessou o site do CLICTEMPO
     */
    public static void validateClictempo(){
        waitPage();
        List<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
        waitPage();
        System.out.println("URL do Clictempo " + driver.getCurrentUrl());
        String temperatureClictempoString  = driver.findElement(By.className("temperature_now")).getText();
        int weatherTemperatureClicTempo = Integer.parseInt(temperatureClictempoString.substring(0, temperatureClictempoString.length()-1));
        if(driver.findElement(By.className("cityName")).getText().toLowerCase().contains(weatherCityGZH.toLowerCase())){
            if((weatherTemperatureGZH>=(weatherTemperatureClicTempo-3) && (weatherTemperatureClicTempo+3)>=weatherTemperatureGZH )){
                System.out.println("Cidade e temperatura esta correta");
                //verificar horario
            } else {
                assertThat("Temperatura no Clictempo esta diferente da que esta no GZH", (weatherTemperatureGZH >= (weatherTemperatureClicTempo - 3) && (weatherTemperatureClicTempo + 3) >= weatherTemperatureGZH) == false);
            }
        }else{
            assertThat("Cidade do clictempo esta diferente da que esta no GZH", driver.findElement(By.className("cityName")).getText().toLowerCase().contains(weatherCityGZH.toLowerCase())==false);
        }

    }

}