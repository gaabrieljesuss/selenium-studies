package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuctionsTest {

    private AuctionsPage auctionsPage;

    @AfterEach
    public void afterEach() {
        this.auctionsPage.dispose();
    }

    @Test
    public void shouldRegisterAuction() {
        String username = "fulano";
        String password = "pass";
        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(username, password);
        this.auctionsPage = loginPage.logIn();
        AuctionRegistrationPage auctionRegistrationPage = auctionsPage.loadForm();

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertTrue(auctionsPage.auctionIsRegistered(auctionName, initialValue, openingDate));

    }
}
