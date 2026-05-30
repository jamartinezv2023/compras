package co.edu.udea.calidad.advantage.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class BrowserAction implements Interaction {
    private final String selector;
    private final String value;
    private final String action;

    public BrowserAction(String selector, String value, String action) {
        this.selector = selector;
        this.value = value;
        this.action = action;
    }

    public static BrowserAction click(String selector) {
        return instrumented(BrowserAction.class, selector, "", "click");
    }

    public static BrowserAction type(String selector, String value) {
        return instrumented(BrowserAction.class, selector, value, "type");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        waitForLoader(driver);

        WebElement element = null;
        long end = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < end && element == null) {
            element = findFirstDisplayed(driver, selector);
            if (element == null) {
                sleep(500);
            }
        }

        if (element == null) {
            throw new NoSuchElementException(
                    "No visible element found using selector group: [" + selector + "] on URL: " + driver.getCurrentUrl()
            );
        }

        ((JavascriptExecutor) driver).executeScript(
                "if(arguments[0] && arguments[0].scrollIntoView){arguments[0].scrollIntoView({block:'center'});}",
                element
        );

        waitForLoader(driver);

        if ("type".equals(action)) {
            element.clear();
            element.sendKeys(value);
        } else {
            try {
                element.click();
            } catch (Exception ignored) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].dispatchEvent(new MouseEvent('click', {bubbles:true, cancelable:true, view:window}));",
                        element
                );
            }
        }

        actor.attemptsTo(Delay.observable());
    }

    private void waitForLoader(WebDriver driver) {
        long end = System.currentTimeMillis() + 20000;

        while (System.currentTimeMillis() < end) {
            try {
                Object activeLoaders = ((JavascriptExecutor) driver).executeScript(
                        "return Array.from(document.querySelectorAll('.loader, .loading, [class*=loader], [class*=Loading]'))" +
                        ".filter(e => getComputedStyle(e).display !== 'none' && getComputedStyle(e).visibility !== 'hidden' && e.offsetParent !== null).length;"
                );

                if (Long.valueOf(0).equals(activeLoaders)) {
                    return;
                }
            } catch (Exception ignored) {
                return;
            }
            sleep(500);
        }
    }

    private WebElement findFirstDisplayed(WebDriver driver, String selectorGroup) {
        String[] selectors = selectorGroup.split("\\|");

        for (String rawSelector : selectors) {
            String selector = rawSelector.trim();
            if (selector.isEmpty()) continue;

            List<WebElement> elements;
            try {
                if (selector.startsWith("//") || selector.startsWith("(")) {
                    elements = driver.findElements(By.xpath(selector));
                } else {
                    elements = driver.findElements(By.cssSelector(selector));
                }
            } catch (Exception ignored) {
                continue;
            }

            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) return element;
                } catch (Exception ignored) { }
            }
        }
        return null;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
