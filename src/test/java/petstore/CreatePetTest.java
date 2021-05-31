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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(true, schemaToValidate.isSchemaValid());
        assertEquals(petObj.id, response.jsonPath().get("id"));
        assertEquals(petObj.name, response.jsonPath().get("name"));
        assertEquals(petObj.status, response.jsonPath().get("status"));
        assertEquals(petObj.category.categoryAsMap(), response.jsonPath().getMap("category"));
        assertEquals(petObj.photoUrls, response.jsonPath().getList("photoUrls"));
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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(true, schemaToValidate.isSchemaValid());
        assertEquals(petObj.name, response.jsonPath().get("name"));
        assertEquals(petObj.status, response.jsonPath().get("status"));
        assertEquals(petObj.category.categoryAsMap(), response.jsonPath().getMap("category"));
        assertEquals(petObj.photoUrls, response.jsonPath().getList("photoUrls"));
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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(true, schemaToValidate.isSchemaValid());
        assertEquals(petObj.id, response.jsonPath().get("id"));
        assertEquals(petObj.name, response.jsonPath().get("name"));
        assertEquals(petObj.status, response.jsonPath().get("status"));
        assertEquals(null, response.jsonPath().getMap("category"));
        assertEquals(petObj.photoUrls, response.jsonPath().getList("photoUrls"));
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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
        assertEquals(petObj.id, response.jsonPath().get("id"));
        assertEquals(null, response.jsonPath().get("name"));
        assertEquals(petObj.status, response.jsonPath().get("status"));
        assertEquals(petObj.category.categoryAsMap(), response.jsonPath().getMap("category"));
        assertEquals(petObj.photoUrls, response.jsonPath().getList("photoUrls"));
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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(true, schemaToValidate.isSchemaValid());
        assertEquals(petObj.id, response.jsonPath().get("id"));
        assertEquals(petObj.name, response.jsonPath().get("name"));
        assertEquals(petObj.status, response.jsonPath().get("status"));
        assertEquals(petObj.category.categoryAsMap(), response.jsonPath().getMap("category"));
        assertEquals(0, response.jsonPath().getList("photoUrls").size());
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
        assertEquals(status_code_ok, response.getStatusCode());
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(true, schemaToValidate.isSchemaValid());
        assertEquals(petObj.id, response.jsonPath().get("id"));
        assertEquals(petObj.name, response.jsonPath().get("name"));
        assertEquals(null, response.jsonPath().get("status"));
        assertEquals(petObj.category.categoryAsMap(), response.jsonPath().getMap("category"));
        assertEquals(petObj.photoUrls, response.jsonPath().getList("photoUrls"));
    }

    @Test
    public void emptyBodyTest() {
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, "");
        assertEquals("The status code received was: " + String.valueOf(response.getStatusCode()), status_code_bad_request, response.getStatusCode());
        assertEquals(status_code_bad_request, response.jsonPath().get("code"));
        assertEquals("unknown", response.jsonPath().get("type"));
        assertEquals("no data", response.jsonPath().get("message"));
    }

    @Test
    public void emptyArrayBodyTest() {
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, "[]");
        assertEquals("The status code received was: " + String.valueOf(response.getStatusCode()), status_code_bad_request, response.getStatusCode());
        assertEquals(status_code_bad_request, response.jsonPath().get("code"));
        assertEquals("unknown", response.jsonPath().get("type"));
        assertEquals("no data", response.jsonPath().get("message"));
    }

    @Test
    public void mismatchContentType() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/xml");
        Response response = petStoreApi.postPetCreate(header, jsonBody.toString());
        assertEquals(status_code_bad_request, response.getStatusCode());
        assertEquals(status_code_bad_request, response.jsonPath().get("code"));
        assertEquals("unknown", response.jsonPath().get("type"));
    }
}
