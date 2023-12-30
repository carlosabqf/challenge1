package support;

public class EnvironmentHelper {

    String urlBase = "http://localhost";
    String port = "8081";
    String courierPath = "/v1/delivery/courier/";

    public String getUrlBase() {
        return urlBase;
    }

    public String getPort() {
        return port;
    }

    public String getCourierPath() {
        return courierPath;
    }
}