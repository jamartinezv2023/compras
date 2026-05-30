package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SearchProduct implements Task {
    private final String product;

    public SearchProduct(String product) {
        this.product = product;
    }

    public static SearchProduct named(String product) {
        return instrumented(SearchProduct.class, product);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                BrowserAction.click("#menuSearch"),
                BrowserAction.type("#autoComplete", product + Keys.ENTER)
        );
    }
}
