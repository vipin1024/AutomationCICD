package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber", // Explicitly point to the feature file location
    glue = "vipinlearns.stepDefinitions", // Package where step definitions are located
    monochrome = true, // Clean console output
    tags= "@Regression",
    plugin = {
        "html:target/cucumber-reports/cucumber.html", // HTML report
        "json:target/cucumber-reports/cucumber.json", // JSON report
        "junit:target/cucumber-reports/cucumber.xml" // JUnit report
    }
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}