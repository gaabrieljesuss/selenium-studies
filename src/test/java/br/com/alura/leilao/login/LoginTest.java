package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @Test
    public void shouldLoginSuccessfully() {
        String username = "fulano";
        String password = "pass";

        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals(browser.getCurrentUrl(), URL_LOGIN);
        Assertions.assertEquals(username, browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void shouldNotLoginSuccessfully() {
        String targetUrl = "http://localhost:8080/login?error";
        String expectedErrorMessage = "Usuário e senha inválidos.";
        String username = "invalido";
        String password = "123123";

        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertEquals(browser.getCurrentUrl(), targetUrl);
        Assertions.assertTrue(browser.getPageSource().contains(expectedErrorMessage));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    public void shouldNotAccessTheRestrictedPageWithoutBeingLoggedIn() {
        String initialUrl = "http://localhost:8080/leiloes/2";
        String targetUrl = "http://localhost:8080/login";
        String unexpectedMessage = "Dados do Leilão";

        browser.navigate().to(initialUrl);

        Assertions.assertEquals(browser.getCurrentUrl(), targetUrl);
        Assertions.assertFalse(browser.getPageSource().contains(unexpectedMessage));
    }
}
