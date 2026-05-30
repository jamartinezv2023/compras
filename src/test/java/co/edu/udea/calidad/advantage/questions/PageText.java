package co.edu.udea.calidad.advantage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class PageText implements Question<String> {
    public static PageText visible() {
        return new PageText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getPageSource();
    }
}
