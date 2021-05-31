package petstore;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

import interfaces.filePaths;
import interfaces.statusCodes;
import io.restassured.response.Response;
import services.PetStoreApi;

/**
 * Unit test for simple App.
 */
public class UploadImageTest implements filePaths, statusCodes {

    @Test
    public void postUploadImageTest() throws JsonProcessingException, ValidationException {
        PetStoreApi petStoreApi = new PetStoreApi();
        String fileImagePath = System.getProperty("user.dir") + imagePath;
        Response response = petStoreApi.postUploadImage(1234, fileImagePath);
        assertEquals(response.getStatusCode(), status_code_ok);
    }
}
