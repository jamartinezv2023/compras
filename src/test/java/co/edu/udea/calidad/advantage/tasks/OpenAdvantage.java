package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.Delay;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenAdvantage implements Task {
    public static OpenAdvantage home() {
        return instrumented(OpenAdvantage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(TestData.URL);
        actor.attemptsTo(Delay.observable());
    }
}
