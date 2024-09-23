package automation.dev.serverest.api.utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.io.ByteArrayInputStream;

public class Reports {

    public static void attachmentsAllure(Response response) {
        if (response != null) {
            String responseDetails = String.format(
                    "Status Code: %d\nHeaders: %s\nBody: %s",
                    response.getStatusCode(),
                    response.getHeaders().toString(),
                    response.getBody().asString()
            );

            ByteArrayInputStream inputStream = new ByteArrayInputStream(responseDetails.getBytes());
            Allure.addAttachment("API Response", "application/json", inputStream, "response.txt");
        }
    }
}
