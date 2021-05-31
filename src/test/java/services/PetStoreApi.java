package services;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import interfaces.filePaths;
import interfaces.petEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PetStoreApi implements petEndpoints, filePaths {
    private String BaseUri = "https://petstore.swagger.io/v2";

    public PetStoreApi() {
        RestAssured.baseURI = BaseUri;
    }

    public Response postPetCreate(HashMap<String, Object> headers, String body) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(body);
        Response response = request.post(petEndpoint);
        return response;
    }

    public Response putPetCreate(HashMap<String, Object> headers, String body) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(body);
        Response response = request.put(petEndpoint);
        return response;
    }

    public Response getFindByStatus(List<String> statusList) {
        RequestSpecification request = RestAssured.given();
        HashMap<String, String> paramsQuery = new HashMap<>();
        for (String element : statusList) {
            paramsQuery.put("status", element);
        }
        request.params(paramsQuery);
        Response response = request.get(findByStatusEndpoint);
        return response;
    }

    public Response postUploadImage(Integer petId, String filePath) {
        File myImage = new File(filePath);
        RequestSpecification request = RestAssured.given();
        request.multiPart(myImage);
        Response response = request.post(String.format(uploadImageEndpoint, petId));
        return response;
    }
}
