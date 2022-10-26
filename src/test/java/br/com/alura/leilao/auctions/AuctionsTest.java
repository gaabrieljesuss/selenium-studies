package br.com.alura.leilao.auctions;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuctionsTest {

    private AuctionsPage auctionsPage;
    private AuctionRegistrationPage auctionRegistrationPage;

    @BeforeEach
    public void beforeEach() {
        String username = "fulano";
        String password = "pass";
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginForm(username, password);
        this.auctionsPage = loginPage.logIn();
        this.auctionRegistrationPage = auctionsPage.loadForm();
    }

    @AfterEach
    public void afterEach() {
        this.auctionsPage.dispose();
    }

    @Test
    public void shouldRegisterAuction() {
        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";
        String expectedSuccessMessage = "Leilão salvo com sucesso";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.auctionIsRegistered(auctionName, initialValue, openingDate));
        Assertions.assertTrue(auctionsPage.containsMessage(expectedSuccessMessage));
    }

    @Test
    public void registrationMustFailBecauseAuctionNameIsEmpty() {
        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "";
        String initialValue = "500.00";
        String expectedErrorMessage = "não deve estar em branco";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void registrationMustFailBecauseAuctionNameIsLessThanThreeCharacters() {
        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "AB";
        String initialValue = "500.00";
        String expectedErrorMessage = "minimo 3 caracteres";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void registrationMustFailBecauseInitialValueIsEmpty() {
        String openingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "";
        String expectedErrorMessage = "deve ser um valor maior de 0.1";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void registrationMustFailBecauseOpeningDateIsEmpty() {
        String openingDate = "";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500";
        String expectedErrorMessage = "deve ser uma data no formato dd/MM/yyyy";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void shouldEditAuction() {
        String openingDate = "25/10/2022";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionRegistrationPage = auctionsPage.loadEditForm();

        String newOpeningDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String newAuctionName = "Leilao do dia " + newOpeningDate;
        String newInitialValue = "400.00";
        String expectedSuccessMessage = "Leilão salvo com sucesso";

        this.auctionRegistrationPage.cleanAuctionRegisterFields();
        this.auctionsPage = auctionRegistrationPage.registerAuction(newAuctionName, newInitialValue, newOpeningDate);
        Assertions.assertTrue(auctionsPage.auctionIsRegistered(newAuctionName, newInitialValue, newOpeningDate));
        Assertions.assertTrue(auctionsPage.containsMessage(expectedSuccessMessage));
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
    }

    @Test
    public void editionMustFailBecauseAuctionNameIsEmpty() {
        String openingDate = "25/10/2022";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionRegistrationPage = auctionsPage.loadEditForm();

        String newOpeningDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String newAuctionName = "";
        String newInitialValue = "400.00";
        String expectedErrorMessage = "não deve estar em branco";

        this.auctionRegistrationPage.cleanAuctionRegisterFields();
        this.auctionsPage = auctionRegistrationPage.registerAuction(newAuctionName, newInitialValue, newOpeningDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void editionMustFailBecauseAuctionNameIsLessThanThreeCharacters() {
        String openingDate = "25/10/2022";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionRegistrationPage = auctionsPage.loadEditForm();

        String newOpeningDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String newAuctionName = "AB";
        String newInitialValue = "400.00";
        String expectedErrorMessage = "minimo 3 caracteres";

        this.auctionRegistrationPage.cleanAuctionRegisterFields();
        this.auctionsPage = auctionRegistrationPage.registerAuction(newAuctionName, newInitialValue, newOpeningDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void editionMustFailBecauseInitialValueIsEmpty() {
        String openingDate = "25/10/2022";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionRegistrationPage = auctionsPage.loadEditForm();

        String newOpeningDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String newAuctionName = "Leilao do dia " + openingDate;
        String newInitialValue = "";
        String expectedErrorMessage = "deve ser um valor maior de 0.1";

        this.auctionRegistrationPage.cleanAuctionRegisterFields();
        this.auctionsPage = auctionRegistrationPage.registerAuction(newAuctionName, newInitialValue, newOpeningDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }

    @Test
    public void editionMustFailBecauseOpeningDateIsEmpty() {
        String openingDate = "25/10/2022";
        String auctionName = "Leilao do dia " + openingDate;
        String initialValue = "500.00";

        this.auctionsPage = auctionRegistrationPage.registerAuction(auctionName, initialValue, openingDate);
        this.auctionRegistrationPage = auctionsPage.loadEditForm();

        String newOpeningDate = "";
        String newAuctionName = "Leilao do dia " + openingDate;
        String newInitialValue = "400.00";
        String expectedErrorMessage = "deve ser uma data no formato dd/MM/yyyy";

        this.auctionRegistrationPage.cleanAuctionRegisterFields();
        this.auctionsPage = auctionRegistrationPage.registerAuction(newAuctionName, newInitialValue, newOpeningDate);
        Assertions.assertFalse(auctionRegistrationPage.isAuctionsRegistrationPage());
        Assertions.assertTrue(auctionsPage.isAuctionsPage());
        Assertions.assertTrue(auctionsPage.containsMessage(expectedErrorMessage));
    }
}
