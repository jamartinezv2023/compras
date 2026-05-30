package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.interactions.Delay;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddProductToCart implements Task {
    private final int quantity;

    public AddProductToCart(int quantity) {
        this.quantity = quantity;
    }

    public static AddProductToCart withQuantity(int quantity) {
        return instrumented(AddProductToCart.class, quantity);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int i = 1; i < quantity; i++) {
            actor.attemptsTo(BrowserAction.click(".plus, .plusQuantity"));
        }

        actor.attemptsTo(
                BrowserAction.click("button[name='save_to_cart'], #productProperties button"),
                Delay.observable()
        );
    }
}
