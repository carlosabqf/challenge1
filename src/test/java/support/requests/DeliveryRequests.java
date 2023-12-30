package support.requests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import support.EnvironmentHelper;

public class DeliveryRequests {

    public String payloadDelivery(String courierId, int state, double value) {
        String payload = "{\"courierId\":\"" + courierId + "\",\"state\":\"" + state + "\",\"value\":\"" + value + "\"}";
        return payload;
    }

    public Response getDelivery(String deliveryId) {
        EnvironmentHelper env = new EnvironmentHelper();
        RestAssured.baseURI = env.getUrlBase() + ":" + env.getPort();

        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when().get(deliveryId)
                .then().extract().response();
        return response;
    }

    public Response postDelivery(String courierId, int state, double value) {
        EnvironmentHelper env = new EnvironmentHelper();
        RestAssured.baseURI = env.getUrlBase() + ":" + env.getPort();
        String payload = "{\"courierId\":\"" + courierId + "\",\"state\":\"" + state + "\",\"value\":\"" + value + "\"}";


        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(payload)
                .when().post()
                .then().extract().response();
        return response;
    }

}
