package petstore;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONObject;
import org.junit.Test;

import interfaces.filePaths;
import io.restassured.response.Response;
import models.CategoryModel;
import models.PetModel;
import models.TagsModel;
import resources.SchemaValidation;
import services.PetStoreApi;

/**
 * Unit test for simple App.
 */
public class PutPetTest implements filePaths {

    @Test
    public void putPetIdWithValidData() throws JsonProcessingException, ValidationException {
        PetModel petObj = new PetModel().randomPetModel();
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        petStoreApi.postPetCreate(header, petObj.jsonPetBody());
        JSONObject jsonBody = new JSONObject(petObj.jsonPetBody());
        jsonBody.put("id", 3141528);
        Response response = petStoreApi.putPetCreate(header, jsonBody.toString());
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
    }

    @Test
    public void putPetCategoryWithValidData() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        petStoreApi.postPetCreate(header, petObj.jsonPetBody());
        CategoryModel newCategory = new CategoryModel().randomCategoryModel();
        petObj.category = newCategory;
        Response response = petStoreApi.putPetCreate(header, petObj.jsonPetBody());
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
    }

    @Test
    public void putPetTagWithValidData() throws JsonProcessingException {
        PetModel petObj = new PetModel().randomPetModel();
        PetStoreApi petStoreApi = new PetStoreApi();
        HashMap<String, Object> header = new HashMap<>();
        header.put("Content-type", "application/json");
        petStoreApi.postPetCreate(header, petObj.jsonPetBody());
        TagsModel newTagsModel = new TagsModel().randomTagModel();
        petObj.tags.add(newTagsModel);
        Response response = petStoreApi.putPetCreate(header, petObj.jsonPetBody());
        assertEquals(response.getStatusCode(), 200);
        SchemaValidation schemaToValidate = new SchemaValidation(petBodySchemaJsonPath, response);
        assertEquals(schemaToValidate.isSchemaValid(), true);
    }
}
