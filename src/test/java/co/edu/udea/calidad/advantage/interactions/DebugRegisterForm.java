package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DebugRegisterForm implements Interaction {

    public static DebugRegisterForm now() {
        return instrumented(DebugRegisterForm.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        Object result = ((JavascriptExecutor) driver).executeScript(
                "return Array.from(document.querySelectorAll('input, select, textarea, button')).map(e => {" +
                " return {" +
                " tag:e.tagName, id:e.id, name:e.name, type:e.type, value:e.value," +
                " checked:e.checked, disabled:e.disabled, required:e.required," +
                " classes:e.className, text:e.innerText" +
                " }" +
                "});"
        );

        System.out.println("REGISTER FORM DEBUG => " + result);

        Object country = ((JavascriptExecutor) driver).executeScript(
                "var s=document.querySelector(\"select[name='countryListboxRegisterPage']\");" +
                "if(!s) return 'NO_SELECT';" +
                "return 'INDEX=' + s.selectedIndex + ' VALUE=' + s.value + ' TEXT=' + s.options[s.selectedIndex].text;"
        );

        System.out.println("COUNTRY DEBUG => " + country);
    }
}
