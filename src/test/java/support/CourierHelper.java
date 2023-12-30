package support;

import io.restassured.response.Response;
import support.requests.CourierRequests;
public class CourierHelper {

    public Response getCourier(final String courierId){
        return new CourierRequests().getCourier(courierId);
    }
}
