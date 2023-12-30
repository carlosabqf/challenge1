package support.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import support.EnvironmentHelper;

public class CourierRequests {

    public Response getCourier(String courierId) {
        EnvironmentHelper env = new EnvironmentHelper();
        RestAssured.baseURI = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath();
        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when().get(courierId)
                .then().extract().response();
        return response;
    }

    public Response postCourier(final String courierId) {
        EnvironmentHelper env = new EnvironmentHelper();
        RestAssured.baseURI = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath();
        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when().post(courierId)
                .then().extract().response();
        return response;
    }

    public Response getCourierStatement(final String courierId, final String startDate, final String endDate){
        EnvironmentHelper env = new EnvironmentHelper();
        RestAssured.baseURI = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath()+"/statement";
        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .param("startDate", startDate)
                .param("endDate", endDate)
                .when().post(courierId)
                .then().extract().response();
        return response;
    }

}

