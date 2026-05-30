package co.edu.udea.calidad.advantage.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/advantage",
        glue = "co.edu.udea.calidad.advantage.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty"},
        tags = "not @manual"
)
public class AdvantageRunner { }
