package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickRegisterJs implements Interaction {

    public static ClickRegisterJs now() {
        return instrumented(ClickRegisterJs.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        ((JavascriptExecutor) driver).executeScript(
                "var btn = document.querySelector('#register_btn');" +
                "if (!btn) throw 'REGISTER_BUTTON_NOT_FOUND';" +
                "btn.scrollIntoView({block:'center'});" +
                "btn.focus();" +
                "['mouseover','mousedown','mouseup','click'].forEach(function(type) {" +
                "  btn.dispatchEvent(new MouseEvent(type, {bubbles:true, cancelable:true, view:window}));" +
                "});" +
                "if (window.angular) {" +
                "  var el = angular.element(btn);" +
                "  var scope = el.scope() || el.isolateScope();" +
                "  if (scope) {" +
                "    scope.$applyAsync();" +
                "  }" +
                "}"
        );

        System.out.println("REGISTER BUTTON CLICKED WITH ANGULAR EVENTS");
    }
}
