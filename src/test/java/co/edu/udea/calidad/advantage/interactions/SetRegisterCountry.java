package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SetRegisterCountry implements Interaction {

    public static SetRegisterCountry toColombia() {
        return instrumented(SetRegisterCountry.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        ((JavascriptExecutor) driver).executeScript(
                "const selects = Array.from(document.querySelectorAll('select'));" +
                "const country = selects.find(s => (s.name || s.id || '').toLowerCase().includes('country')) || selects[0];" +
                "if (country) {" +
                "  const option = Array.from(country.options).find(o => /colombia/i.test(o.text)) || Array.from(country.options).find(o => o.value);" +
                "  if (option) {" +
                "    country.value = option.value;" +
                "    country.dispatchEvent(new Event('change', {bubbles:true}));" +
                "    country.dispatchEvent(new Event('input', {bubbles:true}));" +
                "  }" +
                "}"
        );

        actor.attemptsTo(Delay.observable());
    }
}
