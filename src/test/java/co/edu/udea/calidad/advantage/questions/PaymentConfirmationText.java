package co.edu.udea.calidad.advantage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class PaymentConfirmationText implements Question<String> {

    public static PaymentConfirmationText visible() {
        return new PaymentConfirmationText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getPageSource();
    }
}
