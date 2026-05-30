package co.edu.udea.calidad.advantage.stepdefinitions;

import co.edu.udea.calidad.advantage.questions.PageText;
import co.edu.udea.calidad.advantage.tasks.*;
import co.edu.udea.calidad.advantage.utils.TestData;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.*;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class AdvantageStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Jose Alfredo Martinez Valdes").can(BrowseTheWeb.with(driver));
    }

    @Given("the customer opens Advantage Online Shopping")
    public void queElClienteAbreAdvantageOnlineShopping() {
        OnStage.theActorInTheSpotlight().attemptsTo(OpenAdvantage.home());
    }

    @When("the customer registers a new repeatable account")
    public void elClienteRegistraUnaCuentaNuevaYRepetible() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegisterUser.repeatable(TestData.uniqueUser())
        );
    }

    @When("the customer selects an available laptop product")
    public void seleccionaUnProductoDisponibleDeLaCategoriaLaptops() {
        OnStage.theActorInTheSpotlight().attemptsTo(SelectProduct.fromCategory("laptops"));
    }

    @When("the customer selects an available speaker product")
    public void seleccionaUnProductoDisponibleDeLaCategoriaSpeakers() {
        OnStage.theActorInTheSpotlight().attemptsTo(SelectProduct.fromCategory("speakers"));
    }

    @When("the customer adds the product with quantity {int} to the cart")
    public void agregaElProductoConCantidadAlCarrito(Integer quantity) {
        OnStage.theActorInTheSpotlight().attemptsTo(AddProductToCart.withQuantity(quantity));
    }

    @When("the customer completes checkout using SafePay")
    public void realizaElCheckoutConSafePay() {
        OnStage.theActorInTheSpotlight().attemptsTo(CheckoutWithSafePay.now());
    }

    @When("the customer tries to login with invalid credentials")
    public void elClienteIntentaIniciarSesionConCredencialesInvalidas() {
        OnStage.theActorInTheSpotlight().attemptsTo(LoginInvalid.credentials());
    }

    @When("the customer searches for product {string}")
    public void elClienteBuscaElProducto(String product) {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.named(product));
    }

    @Then("the customer should see the successful payment confirmation")
    public void deberiaVisualizarLaOrdenPagadaExitosamente() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(PageText.visible(), anyOf(
                        containsString("Thank you"),
                        containsString("ORDER"),
                        containsString("Tracking number")
                ))
        );
    }

    @Then("the customer should see an invalid authentication message")
    public void deberiaVisualizarUnMensajeDeAutenticacionInvalida() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(PageText.visible(), anyOf(
                        containsString("Incorrect"),
                        containsString("Invalid"),
                        containsString("does not exist")
                ))
        );
    }

    @Then("the cart should display added products")
    public void elCarritoDeberiaMostrarProductosAgregados() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(PageText.visible(), anyOf(
                        containsString("SHOPPING CART"),
                        containsString("CHECKOUT"),
                        containsString("TOTAL")
                ))
        );
    }

    @Then("the customer should see search results")
    public void deberiaVisualizarResultadosAsociadosALaBusqueda() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(PageText.visible(), containsString("HP"))
        );
    }
}
