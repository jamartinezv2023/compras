package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.time.Duration;

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

        long end = System.currentTimeMillis() + millis;

        while (System.currentTimeMillis() < end) {
            Thread.onSpinWait();
        }
    }
}
