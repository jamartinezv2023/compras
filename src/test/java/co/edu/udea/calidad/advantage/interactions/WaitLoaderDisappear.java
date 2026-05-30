package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class WaitLoaderDisappear implements Interaction {

    public static WaitLoaderDisappear now() {
        return instrumented(WaitLoaderDisappear.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        wait.until(webDriver -> {
            Object activeLoaders = ((JavascriptExecutor) webDriver).executeScript(
                    "return Array.from(document.querySelectorAll('.loader, .loading, [class*=loader], [class*=Loading]'))" +
                    ".filter(e => getComputedStyle(e).display !== 'none' && " +
                    "getComputedStyle(e).visibility !== 'hidden' && " +
                    "e.offsetParent !== null).length;"
            );
            return Long.valueOf(0).equals(activeLoaders);
        });
    }
}
