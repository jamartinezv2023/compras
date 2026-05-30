package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginInvalid implements Task {
    public static LoginInvalid credentials() {
        return instrumented(LoginInvalid.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                BrowserAction.click("#menuUserLink"),
                BrowserAction.type("input[name='username'] | input[placeholder*='Username'] | //input[contains(@name,'username')]", TestData.INVALID_USER),
                BrowserAction.type("input[name='password'] | input[placeholder*='Password'] | //input[contains(@name,'password')]", TestData.INVALID_PASSWORD),
                BrowserAction.click("#sign_in_btn | button[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='sign in'] | //a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')] | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')]")
        );
    }
}
