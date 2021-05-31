package petstore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

import interfaces.statusCodes;
import io.restassured.response.Response;
import services.PetStoreApi;

/**
 * Unit test for simple App.
 */
public class FindByStatusTest implements statusCodes {

    @Test
    public void retrieveListOfPets() throws JsonProcessingException, ValidationException {
        List<String> listOfStatus = new ArrayList<String>();
        listOfStatus.add("sold");
        PetStoreApi petStoreApi = new PetStoreApi();
        Response response = petStoreApi.getFindByStatus(listOfStatus);
        assertEquals(status_code_ok, response.getStatusCode());
    }
}
