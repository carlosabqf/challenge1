package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import support.requests.CourierRequests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@CucumberContextConfiguration
public class DeliverySteps {
    Response response = null;

    /**
     * Get all deliveries for a specific Courier
     *
     * @param courierId the Courier id
     */
    @When("I get all deliveries to Courier {string}")
    public void performGetAll(final String courierId) {
        this.response = new CourierRequests().getCourier(courierId);
    }

    /**
     * Perform a post call to /courier endpoint for a specific Courier
     *
     * @param courierId the Courier id
     */
    @When("I send a post call to courier with id {string}")
    public void performGetCourierStatement(final String courierId) {
        this.response = new CourierRequests().postCourier(courierId);
    }

    @When("I get statements to courier with id {string}, start date {string} and end date {string}")
    public void performGetCourierStatement(final String courierId, final String startDate, final String endDate) {
        this.response = new CourierRequests().getCourierStatement(courierId, startDate, endDate);
    }

    @When("I send a post call to statements to courier with id {string}, start date {string} and end date {string}")
    public void performPostCourierStatement(final String courierId, final String startDate, final String endDate) {
        this.response = new CourierRequests().postCourierStatement(courierId, startDate, endDate);
    }

    /**
     * Check the response status code
     *
     * @param status Code to assert with the response status code
     */
    @Then("I validate that the code status is {int}")
    public void isCodeStatus(final Integer status) {
        assertThat(this.response.getStatusCode(), equalTo(status));
    }

    /**
     * Check if response contains a specific error message
     *
     * @param message String message to assert with the response error message
     */
    @Then("I validate that the response contains the error message: {string}")
    public void isResponseMessage(final String message) {
        JsonPath responseJson = this.response.jsonPath();
        final String responseMessage = responseJson.get("error");
        Assert.assertTrue(responseMessage.contains(message));
    }

    /**
     * Check if response contains a specific value to totalDeliveries
     *
     * @param value value expected
     */
    @And("I validate that the response contains the totalDeliveries: {int}")
    public void isResponseMessage(final int value) {
        JsonPath responseJson = this.response.jsonPath();
        final int responseValue = responseJson.get("totalDeliveries");
        Assert.assertSame(value, responseValue);
    }

    /**
     * Check if response contains a specific value to totalEarnings
     *
     * @param value value expected
     */
    @And("I validate that the response contains the totalEarnings: {float}")
    public void isResponseMessage(final float value) {
        JsonPath responseJson = this.response.jsonPath();
        final Float responseValue = responseJson.get("totalEarnings");
        Assert.assertTrue(value==responseValue);
    }
}

