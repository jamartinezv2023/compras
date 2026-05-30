package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DebugInvalidFields implements Interaction {

    public static DebugInvalidFields now() {
        return instrumented(DebugInvalidFields.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        Object invalid = ((JavascriptExecutor) driver).executeScript(
                "return Array.from(document.querySelectorAll('input.ng-invalid, select.ng-invalid, textarea.ng-invalid'))" +
                ".map(e => {" +
                " return {" +
                " name:e.name, id:e.id, type:e.type, value:e.value," +
                " classes:e.className, checked:e.checked, disabled:e.disabled" +
                " }" +
                "});"
        );

        Object registerButton = ((JavascriptExecutor) driver).executeScript(
                "var b=document.querySelector('#register_btn');" +
                "if(!b) return 'NO_REGISTER_BUTTON';" +
                "return 'disabled=' + b.disabled + ' classes=' + b.className + ' text=' + b.innerText;"
        );

        System.out.println("REAL INVALID FIELDS => " + invalid);
        System.out.println("REGISTER BUTTON STATE => " + registerButton);
    }
}
