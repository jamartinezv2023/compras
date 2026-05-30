package co.edu.udea.calidad.advantage.tasks;

import co.edu.udea.calidad.advantage.interactions.BrowserAction;
import co.edu.udea.calidad.advantage.interactions.Delay;
import co.edu.udea.calidad.advantage.utils.TestData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CheckoutWithSafePay implements Task {
    public static CheckoutWithSafePay now() {
        return instrumented(CheckoutWithSafePay.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String username = actor.recall("advantageUser");

        actor.attemptsTo(
                BrowserAction.click("#shoppingCartLink | //a[contains(@id,'shoppingCart')]"),
                Delay.observable(),
                BrowserAction.click("#checkOutButton | button[name='check_out_btn'] | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'checkout')]"),
                Delay.observable()
        );

        if (BrowseTheWeb.as(actor).getDriver().getCurrentUrl().contains("/login")) {
            actor.attemptsTo(
                    BrowserAction.type("input[name='usernameInOrderPayment'] | input[name='username'] | //input[contains(@name,'username')]", username),
                    BrowserAction.type("input[name='passwordInOrderPayment'] | input[name='password'] | //input[contains(@name,'password')]", TestData.PASSWORD),
                    BrowserAction.click("#login_btnundefined | #sign_in_btn | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'login')] | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign in')]"),
                    Delay.observable()
            );
        }

        actor.attemptsTo(
                BrowserAction.click("#next_btn | button#next_btn | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'next')]"),
                Delay.observable(),
                BrowserAction.type("input[name='safepay_username'] | #safepay_username", username),
                BrowserAction.type("input[name='safepay_password'] | #safepay_password", TestData.PASSWORD),
                BrowserAction.click("#pay_now_btn_SAFEPAY | button#pay_now_btn_SAFEPAY | //button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'pay now')]"),
                Delay.observable()
        );
    }
}
