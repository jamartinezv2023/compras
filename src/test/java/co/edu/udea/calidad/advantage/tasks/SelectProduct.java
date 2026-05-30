package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.interactions.Delay;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectProduct implements Task {
    private final String category;

    public SelectProduct(String category) {
        this.category = category;
    }

    public static SelectProduct fromCategory(String category) {
        return instrumented(SelectProduct.class, category);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(TestData.URL);
        actor.attemptsTo(Delay.observable());

        String categorySelector = "#" + category.toLowerCase() + "Img | " +
                "//div[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + category.toLowerCase() + "')]";

        actor.attemptsTo(
                BrowserAction.click(categorySelector),
                Delay.observable(),
                BrowserAction.click(".cell.categoryRight li:first-child | .productName:first-child | .imgProduct:first-child | //ul[contains(@class,'categoryRight')]//li[1] | (//*[contains(@class,'productName')])[1]")
        );
    }
}
