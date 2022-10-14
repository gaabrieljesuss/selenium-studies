package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.dispose();
    }

    @Test
    public void shouldLoginSuccessfully() {
        String username = "fulano";
        String password = "pass";

        loginPage.fillLoginForm(username, password);
        loginPage.logIn();

        Assertions.assertNotEquals(loginPage.getCurrentUrl(), loginPage.getUrlLogin());
        Assertions.assertEquals(username, loginPage.getUsernameLoggedIn());
    }

    @Test
    public void shouldNotLoginSuccessfully() {
        String expectedErrorMessage = "Usuário e senha inválidos.";
        String username = "invalido";
        String password = "123123";

        loginPage.fillLoginForm(username, password);
        loginPage.logIn();

        Assertions.assertEquals(loginPage.getCurrentUrl(), loginPage.getErrorPageUrl());
        Assertions.assertTrue(loginPage.contains(expectedErrorMessage));
        Assertions.assertNull(loginPage.getUsernameLoggedIn());
    }

    @Test
    public void shouldNotAccessTheRestrictedPageWithoutBeingLoggedIn() {
        String unexpectedMessage = "Dados do Leilão";

        loginPage.navigateToAuctionPage();

        Assertions.assertEquals(loginPage.getCurrentUrl(), loginPage.getUrlLogin());
        Assertions.assertFalse(loginPage.contains(unexpectedMessage));
    }
}
