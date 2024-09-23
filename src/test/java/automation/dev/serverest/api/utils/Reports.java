package automation.dev.serverest.api.utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

public class Reports {

    public static void attachmentsAllure(Response response) {
        if (response != null) {
            String responseDetails = "Status Code: " + response.getStatusCode() + "\n" +
                    "Headers: " + response.getHeaders().toString() + "\n" +
                    "Body: " + response.getBody().asString();

            Allure.addAttachment("API Response", "text/plain", responseDetails);
        }
    }
}