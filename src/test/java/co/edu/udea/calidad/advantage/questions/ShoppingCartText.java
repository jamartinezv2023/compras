package co.edu.udea.calidad.advantage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ShoppingCartText implements Question<String> {

    public static ShoppingCartText visible() {
        return new ShoppingCartText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getPageSource();
    }
}
