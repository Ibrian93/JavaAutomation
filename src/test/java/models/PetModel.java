package models;

import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;

import interfaces.constants;

public class PetModel implements constants {
    public int id;
    public String name;
    public List<String> photoUrls;
    public models.CategoryModel category;
    public List<models.TagsModel> tags;
    public String status;

    public PetModel() {
    }

    public PetModel(CategoryModel category, List<models.TagsModel> tags, String name, List<String> photoUrls,
            String status) {
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PetModel(int id, CategoryModel category, List<models.TagsModel> tags, String name, List<String> photoUrls,
            String status) {
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public String jsonPetBody() throws JsonProcessingException {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(this);
            return json;
        } catch (JsonProcessingException e) {
            return e.toString();
        }
    }

    public PetModel randomPetModel() {
        Faker faker = new Faker();
        this.id = faker.number().numberBetween(min_value, max_value);
        this.name = faker.name().firstName();
        List<String> listOfPhotosUrls = Arrays.asList(faker.internet().url());
        this.photoUrls = listOfPhotosUrls;
        CategoryModel categoryObj = new CategoryModel();
        categoryObj.randomCategoryModel();
        this.category = categoryObj;
        TagsModel tagObj = new TagsModel();
        tagObj.randomTagModel();
        ArrayList<TagsModel> tagList = new ArrayList<TagsModel>();
        tagList.add(tagObj);
        this.tags = tagList;
        this.status = "available";
        return this;
    }
}