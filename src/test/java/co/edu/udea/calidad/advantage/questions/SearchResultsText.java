package co.edu.udea.calidad.advantage.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class SearchResultsText implements Question<String> {

    public static SearchResultsText visible() {
        return new SearchResultsText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor)
                .getDriver()
                .getPageSource();
    }
}
