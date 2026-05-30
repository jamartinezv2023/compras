package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.interactions.Delay;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginWithValidUser implements Task {

    public static LoginWithValidUser now() {
        return instrumented(LoginWithValidUser.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.remember("advantageUser", TestData.VALID_USER);

        actor.attemptsTo(
                BrowserAction.click("#menuUserLink | //a[contains(@id,'menuUser')]"),
                BrowserAction.type("input[name='username'] | //input[contains(@name,'username')]", TestData.VALID_USER),
                BrowserAction.type("input[name='password'] | //input[contains(@name,'password')]", TestData.VALID_PASSWORD),
                BrowserAction.click("#sign_in_btn | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')]"),
                Delay.observable()
        );
    }
}
