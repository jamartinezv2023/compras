package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Delay implements Interaction {
    private final int millis;

    public Delay(int millis) {
        this.millis = millis;
    }

    public static Delay observable() {
        return instrumented(Delay.class, 800);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
