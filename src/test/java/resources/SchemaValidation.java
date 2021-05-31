package resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.response.Response;

public class SchemaValidation {
    InputStream inputStream;
    String jsonSchemaPath;
    Response bodyResponse;

    public SchemaValidation(String jsonSchemaPath, Response bodyResponse) {
        this.jsonSchemaPath = jsonSchemaPath;
        this.bodyResponse = bodyResponse;
    }

    public Boolean isSchemaValid() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") + this.jsonSchemaPath)) {
            Object obj = parser.parse(reader);
            JSONObject schemaToValidate = new JSONObject(obj);
            Schema schema = SchemaLoader.load(schemaToValidate);
            JSONObject responseBody = new JSONObject(this.bodyResponse.getBody().asString());
            schema.validate(responseBody);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException io) {
            return false;
        } catch (ParseException e) {
            return false;
        }
    }
}
