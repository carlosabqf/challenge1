package support.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import support.EnvironmentHelper;

public class CourierRequests {

    public Response getCourier(String courierId) {
        EnvironmentHelper env = new EnvironmentHelper();
        final String path = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath() + courierId;

        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when().get(path)
                .then().extract().response();
        return response;
    }

    public Response postCourier(final String courierId) {
        EnvironmentHelper env = new EnvironmentHelper();
        final String path = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath() + courierId;

        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when().post(path)
                .then().extract().response();
        return response;
    }

    public Response getCourierStatement(final String courierId, final String startDate, final String endDate) {
        EnvironmentHelper env = new EnvironmentHelper();
        final String path = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath() + courierId + "/statement";
        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when().get(path)
                .then().extract().response();
        return response;
    }

    public Response postCourierStatement(final String courierId, final String startDate, final String endDate) {
        EnvironmentHelper env = new EnvironmentHelper();
        final String path = env.getUrlBase() + ":" + env.getPort() + env.getCourierPath() + courierId + "/statement";
        Response response = RestAssured.given().header("accept", "application/json")
                .header("Content-Type", "application/json")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when().post(path)
                .then().extract().response();
        return response;
    }

}

