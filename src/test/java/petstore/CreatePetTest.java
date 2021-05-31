package petstore;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.junit.Test;

import interfaces.filePaths;
import interfaces.statusCodes;
import io.restassured.response.Response;
import models.PetModel;
import resources.SchemaValidation;
import services.PetStoreApi;

/**
 * Unit test for simple App.
 */
public class CreatePetTest implements filePaths, statusCodes {

    @Test
    public void createPetWithValidData() throws JsonProcessingException, ValidationException {
        PetModel petObj = new PetModel().randomPetModel();
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, petObj.jsonPetBody());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("id"), petObj.id);
        assertEquals(response.jsonPath().get("name"), petObj.name);
        assertEquals(response.jsonPath().get("status"), petObj.status);
        assertEquals(response.jsonPath().getMap("category"), petObj.category.categoryAsMap());
        assertEquals(response.jsonPath().getList("photoUrls"), petObj.photoUrls);
    }

    @Test
    public void createPetWithNullId() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("id");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("name"), petObj.name);
        assertEquals(response.jsonPath().get("status"), petObj.status);
        assertEquals(response.jsonPath().getMap("category"), petObj.category.categoryAsMap());
        assertEquals(response.jsonPath().getList("photoUrls"), petObj.photoUrls);
    }

    @Test
    public void createPetWithNullCategory() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("category");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("id"), petObj.id);
        assertEquals(response.jsonPath().get("name"), petObj.name);
        assertEquals(response.jsonPath().get("status"), petObj.status);
        assertEquals(response.jsonPath().getMap("category"), null);
        assertEquals(response.jsonPath().getList("photoUrls"), petObj.photoUrls);
    }

    @Test
    public void createPetWithNullName() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("name");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("id"), petObj.id);
        assertEquals(response.jsonPath().get("name"), null);
        assertEquals(response.jsonPath().get("status"), petObj.status);
        assertEquals(response.jsonPath().getMap("category"), petObj.category.categoryAsMap());
        assertEquals(response.jsonPath().getList("photoUrls"), petObj.photoUrls);
    }

    @Test
    public void createPetWithNullPhotoUrls() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("photoUrls");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("id"), petObj.id);
        assertEquals(response.jsonPath().get("name"), petObj.name);
        assertEquals(response.jsonPath().get("status"), petObj.status);
        assertEquals(response.jsonPath().getMap("category"), petObj.category.categoryAsMap());
        assertEquals(response.jsonPath().getList("photoUrls").size(), 0);
    }

    @Test
    public void createPetWithNullStatus() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("status");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_ok);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(response.jsonPath().get("id"), petObj.id);
        assertEquals(response.jsonPath().get("name"), petObj.name);
        assertEquals(response.jsonPath().get("status"), null);
        assertEquals(response.jsonPath().getMap("category"), petObj.category.categoryAsMap());
        assertEquals(response.jsonPath().getList("photoUrls"), petObj.photoUrls);
    }

    @Test
    public void emptyBodyTest() {
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, "");
        assertEquals("The status code received was: " + String.valueOf(response.getStatusCode()), status_code_bad_request);
        assertEquals(response.jsonPath().get("code"), status_code_bad_request);
        assertEquals(response.jsonPath().get("type"), "unknown");
        assertEquals(response.jsonPath().get("message"), "no data");
    }

    @Test
    public void emptyArrayBodyTest() {
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, "[]");
        assertEquals("The status code received was: " + String.valueOf(response.getStatusCode()), response.getStatusCode(), status_code_bad_request);
        assertEquals(response.jsonPath().get("code"), status_code_bad_request);
        assertEquals(response.jsonPath().get("type"), "unknown");
        assertEquals(response.jsonPath().get("message"), "no data");
    }

    @Test
    public void mismatchContentType() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/xml");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), status_code_bad_request);
        assertEquals(response.jsonPath().get("code"), status_code_bad_request);
        assertEquals(response.jsonPath().get("type"), "unknown");
    }
}
