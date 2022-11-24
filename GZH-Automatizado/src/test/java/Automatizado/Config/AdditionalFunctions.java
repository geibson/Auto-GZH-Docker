package Automatizado.Config;

import org.loremipsum.LoremIpsum;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static Automatizado.GZH.Site.Pages.HomePage.closeAllAds;
import static Automatizado.GZH.Site.StepDefinitions.Hooks.driver;

;

/**
 * Classe AuxiliarFunctions.
 */
@Component
@Scope("cucumber-glue")
public class AdditionalFunctions {

    /**
     * Método que busca o frame do onesignal
     */
    static By onesignalFrame = By.id("onesignal-slidedown-dialog");

    /**
     * Método que busca o frame do nossa
     */
    static By nossasite = By.id("NOSSASite");
    /**
     * Método que busca o pop-up de aceite de cookie.
     */
    static By moduleAdsAccept = By.className("module-ads__accept");
    /**
     * Método que busca o botão de cancelar notificação.
     */
    static By btnCancelNotification = By.id("onesignal-slidedown-cancel-button");
    /**
     * Método que busca o botão de aceitar notificação.
     */
    static By btnAcceptNotification = By.className("onesignal-slidedown-allow-button");
    /**
     * Método que busca o botão de log in.
     */
    static By btnLogIn = By.className("login-button");
    /**
     * Método que busca o botão de aceitar cookies.
     */
    static By btnAcceptCookies = By.className("module-ads__accept");
    /**
     * Método que busca o botão de fechar da pop-up Ad.
     */
    static By adPop = By.className("root");
    /**
     * Método que busca o botão de fechar pop-up
     */
    static By closePop = By.className("close");

    /**
     * Método construtor
     */
    public AdditionalFunctions(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Método que realizar um scroll ate o elemento passado por @param
     */
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        waitPage(1000);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        waitPage(1000);
        actions.moveToElement(element).perform();
        waitPage();
    }

    /**
     * Método que clicar no botão de aceite do cookies.
     */
    public static void acceptCookies() {
        if(!driver.findElements(btnAcceptCookies).isEmpty()) {
            try {
                driver.findElement(btnAcceptCookies).click();
            }catch(Exception ElementClickInterceptedException) {

            }

        }
        waitPage();
    }

    /**
     * Método que fecha nas pop-up que aparece no GZH.
     */
    public static void closeAdPop() {
        if (!driver.findElements(adPop).isEmpty()) {
            driver.findElement(adPop).getLocation();
            driver.switchTo().frame(driver.findElement(adPop));
            driver.findElement(closePop).click();
        }
    }

    /**
     * Método que clicar no botão de notificacão do GZH
     */
    public static void acceptNotification() {
        waitPage(10000);

        if (!driver.findElements(onesignalFrame).isEmpty()) {
            if (!driver.findElements(btnCancelNotification).isEmpty()) {
                //btnAcceptNotification  // aceito
                //btnCancelNotification // cancelar
                //driver.findElement(btnAcceptNotification).click();
                driver.findElement(btnCancelNotification).click();
            } else {
                System.out.println("Não encontrou o onesignal");
            }
        } else {
            System.out.println("Já clicou ou não apareceu o onesignal");
        }
    }

    /**
     * Método de Wait de 3000 millisegundos como padrão.
     */
    public static void waitPage() {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofMillis(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método de Wait que recebe a quantidade de millisegundo que deve esperar.
     */
    public static void waitPage(int millisegundos) {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofMillis(millisegundos));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método com wait padrão de 3000 milisegundos
     */
    public static void waitGeneral() {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofMillis(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que aguarda 10 segundos pelo elemento estar diponível na página para ser clicado, o elemento é passado via @param
     */
    public static void waitElementToClick(By field) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(field));
        //WebElement element = wait.until(ExpectedConditions.visibilityOf());
        element.click();
    }

    /**
     * Método que realizar um refresh na página.
     */
    public static void refreshPage() {
        driver.navigate().refresh();
        waitGeneral();
    }

    /**
     * Método que realizar um refresh na página para poder apresentar o walls
     */
    public static void refreshWalls() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Refresh "+i);
            driver.navigate().refresh();
            waitGeneral();
        }
    }

    /**
     * Método para remover os acentos das palavras, ele recebe por @param a palavra e @return a palavra sem acento
     */
    public static String removeAccents(String text) {
        return text == null ? null : Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Método que remover somente o hifen(-), ele recebe por @param o texto e @return o texto sem hifen(-)
     */
    public static String removehifen(String stringCurrent) {
        stringCurrent = stringCurrent.replaceAll("-", " ");
        return stringCurrent;
    }

    /**
     * Método que realizar o voltar para a capa do síte e dar um wait de 2000 milisegundos
     */
    public static void backHome() {
        try {
            driver.findElement(By.className("home-link")).click();
            waitPage();
        }
        catch(ElementClickInterceptedException e){
            closeAllAds();
            driver.findElement(By.className("home-link")).click();
            waitPage();
        }
    }

    /**
     * Método utilizado se pegou o titulo da pagina title boolean. @return the boolean
     */
    public static boolean verifyTitle(){
        if(!driver.findElements(By.className("header-title")).isEmpty()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método que realiza um scroll na página.
     */
    public static void scrollingToBottomOfPage() {

        long finalHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.documentElement.clientHeight");
        System.out.println("altura total "+finalHeight);
        for (int i = 0; i<finalHeight;i=i+100){
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, "+i+")");
            waitPage();
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
    }

    /**
     * Método que pega o timestamp @return o timestamp em formato de string
     */
    public static String defineTime() {
        return new SimpleDateFormat("dd-MM-HH-mm").format(new java.util.Date());
    }

    /**
     * Método que seleciona um item aleatorio no menu
     */
    public static int randomMenu(List<WebElement> listEditorials){
        SecureRandom rand = new SecureRandom();
        int randomEditorial = rand.nextInt(listEditorials.size());
        return randomEditorial;
    }

    /**
     * Método que gera o nome randomico para os testes e @return o nome+timestamp (timestamp vem do @method definetime)
     */
    public static String generateName() {
        return "Automatizado " + defineTime();
    }

    /**
     * Método que gera um e-mail randomico para os testes e @return o e-mail + timestamp (timestamp vem do @method definetime) + o sufixo @mailinator.com
     */
    public static String generateEmail() {
        return "Automatizado" + defineTime() + "@mailinator.com";
    }

    /**
     * Método que gera um CPF randomico para os testes @return o cpf em formato de string
     */
    public static String generateCPF() {

        StringBuilder iniciais = new StringBuilder();// contém os 9 primeiros números do cpf
        int number;// número gerado randomicamente
        int firstDigit = 0, secodDig;// recebem o primeiro e o segundo digitos calculados
        int sum = 0, effort = 10; // utilizados nos calculos dos digitos
// verificadores
        String num; // receberá o valor contido em iniciais

// gera randomicamente os 9 primeiros números do cpf
        for (int i = 0; i < 9; i++) {

            number = (int) (Math.random() * 10);
            if (number > 9)// pois o número deve ser de 0 a 9
                number = 9;
            iniciais.append(number);
        }

// armazena em num o valor de iniciais
        num = iniciais.toString();

        for (int i = 0; i < iniciais.length(); i++) {
            sum += Integer.parseInt(num.substring(i, i + 1)) * effort--;
        }

        if (sum % 11 == 0 | sum % 11 == 1)
            firstDigit = 0;
        else
            secodDig = 11 - (sum % 11);

        sum = 0;
        effort = 11;

        for (int i = 0; i < num.length(); i++) {
            sum += Integer.parseInt(num.substring(i, i + 1)) * effort--;
        }

        sum += firstDigit * 2;
        if (sum % 11 == 0 | sum % 11 == 1)
            secodDig = 0;
        else
            secodDig = 11 - (sum % 11);
        //System.out.println("CPF "+iniciais.toString() + primDig + segDig);
// retorna o cpf gerado
        return iniciais.toString() + firstDigit + secodDig;
    }

    /**
     * Método que gerar um CNPJ randomico para os testes @return ele em formato de string
     */
    public static String generateCNPJ() {
        StringBuilder iniciais = new StringBuilder();// contém os 8 primeiros números do cnpj
        int number;// número gerado randomicamente
        int firstDigit, secodDig = 0;// recebem o primeiro e o segundo digitos calculados
        String num; // receberá o valor contido em iniciais
        int integerRestDivision; // recebe o resto de uma divisão

// gera randomicamente os 8 primeiros números do cnpj
        for (int i = 0; i < 8; i++) {
            number = (int) (Math.random() * 10);
            if (number > 9)// pois o número deve ser de 0 a 9
                number = 9;
            iniciais.append(number);
        }
        iniciais.append("0001");
// armazena em num o valor de iniciais
        num = iniciais.toString();

// 5 4 3 2 9 8 7 6 5 4 3 2
// calculando o primeiro dígito:
        int sum = 0;
        sum += 5 * Integer.parseInt((num.substring(0, 1)));
        sum += 4 * Integer.parseInt((num.substring(1, 2)));
        sum += 3 * Integer.parseInt((num.substring(2, 3)));
        sum += 2 * Integer.parseInt((num.substring(3, 4)));
        sum += 9 * Integer.parseInt((num.substring(4, 5)));
        sum += 8 * Integer.parseInt((num.substring(5, 6)));
        sum += 7 * Integer.parseInt((num.substring(6, 7)));
        sum += 6 * Integer.parseInt((num.substring(7, 8)));
        sum += 5 * Integer.parseInt((num.substring(8, 9)));
        sum += 4 * Integer.parseInt((num.substring(9, 10)));
        sum += 3 * Integer.parseInt((num.substring(10, 11)));
        sum += 2 * Integer.parseInt((num.substring(11, 12)));

        integerRestDivision = sum % 11;
// Caso o resto da divisão seja menor que 2,
// o nosso primeiro dígito verificador se torna 0 (zero),
// caso contrário subtrai-se o valor obtido de 11
        if (integerRestDivision < 2) {
            firstDigit = 0;
        } else {
            firstDigit = 11 - integerRestDivision;
        }

// 6 5 4 3 2 9 8 7 6 5 4 3 2
// calculando o segundo dígito:
        sum = 0;
        sum += 6 * Integer.parseInt((num.substring(0, 1)));
        sum += 5 * Integer.parseInt((num.substring(1, 2)));
        sum += 4 * Integer.parseInt((num.substring(2, 3)));
        sum += 3 * Integer.parseInt((num.substring(3, 4)));
        sum += 2 * Integer.parseInt((num.substring(4, 5)));
        sum += 9 * Integer.parseInt((num.substring(5, 6)));
        sum += 8 * Integer.parseInt((num.substring(6, 7)));
        sum += 7 * Integer.parseInt((num.substring(7, 8)));
        sum += 6 * Integer.parseInt((num.substring(8, 9)));
        sum += 5 * Integer.parseInt((num.substring(9, 10)));
        sum += 4 * Integer.parseInt((num.substring(10, 11)));
        sum += 3 * Integer.parseInt((num.substring(11, 12)));
        sum += 2 * secodDig;

        integerRestDivision = sum % 11;
// Caso o resto da divisão seja menor que 2,
// o nosso primeiro dígito verificador se torna 0 (zero),
// caso contrário subtrai-se o valor obtido de 11
        if (integerRestDivision < 2) {
            secodDig = 0;
        } else {
            secodDig = 11 - integerRestDivision;
        }
        //System.out.println("CNPJ "+iniciais.toString() + primDig + segDig);
// retorna o cnpj gerado
        return iniciais.toString() + firstDigit + secodDig;

    }

    public static void addCookies(){
        Cookie cookie1 = new Cookie.Builder("GCP_IAAP_AUTH_TOKEN_F7B60A1F1A5B9894", "eyJhbGciOiJSUzI1NiIsImtpZCI6IjYzMWZhZTliNTk0MGEyZDFmYmZmYjAwNDAzZDRjZjgwYTIxYmUwNGUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4MTgyOTcyNTcxMDMtODc0OGJmMnZnczRua2d0c3J1dnNzc3A5aWY1NGV0ZWsuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4MTgyOTcyNTcxMDMtODc0OGJmMnZnczRua2d0c3J1dnNzc3A5aWY1NGV0ZWsuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDIwNDYyNjkwODgzNzczMDMyMTEiLCJoZCI6ImdydXBvcmJzLmNvbS5iciIsImVtYWlsIjoidGMuZ2VpYnNvbmJhcmJvc2FAZ3J1cG9yYnMuY29tLmJyIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiIzSVVCUkliMzdxSkxwLWJraFAtcE5RIiwiZ29vZ2xlIjp7ImdpYyI6IkFGbU9VdHFzZmdGZkxwMkFQaUJQNFk2UnVJeUhDWmlmeUIwOF8ycVA3dlpqMmV4UFFMVEVCYWVHZkw2T0tCWXZnTGVyVUNJcGR0Z1BNQjNIZERxdkZKZG1NQUFJRjdKZ0VmS2ZfbXQ0NGJ3U09aYWxZSGNnNExySzBtVGdwc01mRzE4WEdabHhsRFRxZXliODJCUGdkdkl2NW8zZ0xtcWYxY0laNEFoQVdhYVNPWXo3VW12QVdGcnJtaG5NUEw5VTRUZUhfa1F4cE1HRHlwVmxlVTZGdmQ5a3VqZlZNUkVHWnM3Y1VBIn0sImlhdCI6MTY1ODIyODEzMiwiZXhwIjoxNjU4MjMxNzMyfQ.l0TdW5ihncZ1JyyJhuN1v81mk6JUeYaTYLDujcBMl1GfvJS86s9KiRBkgKMl6REld25DU0sGOotmDJy9lYGfoOBStalL0bROaV4TKZHSB1h7ZVqczn6YMUnfxIArVY9fu_mDwZwmk4odgeNzonlCaXHVao94EgXKUbSWpBtVEdCLqC27tEpiBFrMOaz4LcBwsXmdEDVZgJ4JmVFD30FSlBZUjEHnxgxr8kizcyIZGkHaeQHrYRG0VTxw1xmw8CNkS2toPU8PmI92RFIPYI0C0cd0n9yuHRMFLS9oFYOMNUVBX8jEX1XszcXIaoS5eKLk_ZVSAkjl8_R6Ghg38Ml1yQ")
                .domain("edit-hlg.rbs.com.br")
                //.expiresOn(new Date(2015, 10, 28))
                .expiresOn(new Date(Calendar.DATE))
                .isHttpOnly(true)
                .isSecure(true)
                .path("/")
                .build();


        driver.manage().addCookie(cookie1);


    }

    public static int whereImNow() {
        String actualURL = driver.getCurrentUrl();
        String here = "";
        int place = 1;
        char someChar = '/';
        int count = 0;
        waitPage();
        for (int i = 0; i < actualURL.length(); i++) {
            if (actualURL.charAt(i) == someChar) {
                count++;
            }
        }
        if (count <= 3) {
            here = "HOME";
        } else if (count == 5) {
            here = "EDITORIA";
        } else if (count >= 7) {
            here = "MATERIA";
        }


        switch (here.toUpperCase()) {
            case "HOME":
                place = 1;
                System.out.println("Acessei a Home");
                break;
            case "EDITORIA":
                    place = 2;
                System.out.println("Acessei uma editoria");
                break;
            case "MATERIA":
                place = 3;
                System.out.println("Acessei uma materia");
                break;
        }
        return place;
    }


    public static void comments() throws AWTException {
        String text = LoremIpsum.createParagraph();
        System.out.println(text);
        scrollingToBottomOfPage();
        waitPage();
        scrollToElement(driver.findElement(By.className("m-comments")));

        Point coordinates = driver.findElement(By.className("m-comments")).getLocation();
        System.out.println(coordinates);

        int x = coordinates.getX()+10;
        int y = coordinates.getY()+10;

        System.out.println(" X "+x+" Y "+y);

        Actions actions = new Actions(driver);
        actions.moveByOffset(300,120).click().perform();
        //actions.getActiveKeyboard();
        actions.sendKeys("teste comentario");
    }

    public static void switchTab(){
        waitPage(4000);
        List<String> tab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(tab.size()-1));
        waitPage();
    }
}
