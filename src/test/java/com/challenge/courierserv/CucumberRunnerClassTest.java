package com.challenge.courierserv;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"pretty",
                "json:target/cucumber_json_reports/home-page.json",
                "html:target/home-page-html"},
        glue = {"support", "stepDefinitions"},
        publish = true)
public class CucumberRunnerClassTest {
}
