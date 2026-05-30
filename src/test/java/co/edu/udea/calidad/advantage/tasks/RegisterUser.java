package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.interactions.ClickRegisterJs;
import co.edu.udea.calidad.advantage.interactions.Delay;
import co.edu.udea.calidad.advantage.interactions.SetRegisterCountry;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterUser implements Task {
    private final String username;

    public RegisterUser(String username) {
        this.username = username;
    }

    public static RegisterUser repeatable(String username) {
        return instrumented(RegisterUser.class, username);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().get(TestData.REGISTER_URL);

        actor.attemptsTo(
                Delay.observable(),
                BrowserAction.type("input[name='usernameRegisterPage'] | #usernameRegisterPage", username),
                BrowserAction.type("input[name='emailRegisterPage'] | #emailRegisterPage", TestData.email(username)),
                BrowserAction.type("input[name='passwordRegisterPage'] | #passwordRegisterPage", TestData.PASSWORD),
                BrowserAction.type("input[name='confirm_passwordRegisterPage'] | #confirm_passwordRegisterPage", TestData.PASSWORD),
                BrowserAction.type("input[name='first_nameRegisterPage'] | #first_nameRegisterPage", "Jose"),
                BrowserAction.type("input[name='last_nameRegisterPage'] | #last_nameRegisterPage", "Martinez"),
                BrowserAction.type("input[name='phone_numberRegisterPage'] | #phone_numberRegisterPage", "3001234567"),
                SetRegisterCountry.toColombia(),
                BrowserAction.type("input[name='cityRegisterPage'] | #cityRegisterPage", "Palmira"),
                BrowserAction.type("input[name='addressRegisterPage'] | #addressRegisterPage", "Calle 1 2 3"),
                BrowserAction.type("input[name='state_/_province_/_regionRegisterPage'] | input[id='state_/_province_/_regionRegisterPage'] | //input[contains(@name,'state') or contains(@id,'state')]", "Valle"),
                BrowserAction.type("input[name='postal_codeRegisterPage'] | #postal_codeRegisterPage", "760001"),
                BrowserAction.click("input[name='i_agree'] | input[type='checkbox']"),
                ClickRegisterJs.now(),
                Delay.observable()
        );

        actor.remember("advantageUser", username);
    }
}
