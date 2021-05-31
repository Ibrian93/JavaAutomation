package petstore;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.junit.Test;

import interfaces.filePaths;
import io.restassured.response.Response;
import models.PetModel;
import resources.SchemaValidation;
import services.PetStoreApi;

/**
 * Unit test for simple App.
 */
public class CreatePetTest implements filePaths {

    @Test
    public void createPetWithValidData() throws JsonProcessingException, ValidationException {
        PetModel petObj = new PetModel().randomPetModel();
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.postPetCreate(header, petObj.jsonPetBody());
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
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
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
    }

    @Test
    public void createPetWithNullCategory() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.remove("category");
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        Response response = petStoreApi.putPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
    }
}
