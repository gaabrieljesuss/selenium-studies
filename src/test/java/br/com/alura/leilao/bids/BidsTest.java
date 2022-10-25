package br.com.alura.leilao.bids;

import br.com.alura.leilao.auctions.AuctionRegistrationPage;
import br.com.alura.leilao.auctions.AuctionsPage;
import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BidsTest {

    private AuctionsPage auctionsPage;
    private AuctionRegistrationPage auctionRegistrationPage;
    private BidsPage bidsPage;

    @BeforeEach
    public void beforeEach() {
        String username = "fulano";
        String password = "pass";
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(username, password);
        this.auctionsPage = loginPage.logIn();
        this.auctionRegistrationPage = auctionsPage.loadForm();

        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionsPage.logout();
        loginPage = this.auctionsPage.logIn();

        username = "beltrano";
        password = "pass";
        loginPage.fillLoginForm(username, password);
        this.auctionsPage = loginPage.logIn();
    }

    @AfterEach
    public void afterEach() {
        this.bidsPage.dispose();
    }

    @Test
    public void shouldRegisterBid() {
        String expectedMessage = "Lance adicionado com sucesso!";
        String dateExpected = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String userExpected = "beltrano";
        String valueExpected = "R$ 600,00";
        String bidValue = "600.00";


        this.bidsPage = this.auctionsPage.loadBids();
        this.bidsPage.registerBid(bidValue);

        Assertions.assertTrue(this.bidsPage.contains(expectedMessage));
        Assertions.assertTrue(this.bidsPage.BidIsRegistered(dateExpected, userExpected, valueExpected));
    }
}
