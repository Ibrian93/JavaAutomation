package models;

import com.github.javafaker.Faker;

import interfaces.constants;


public class TagsModel implements constants {
    public int id;
    public String name;
 
    public TagsModel() {}

    public TagsModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagsModel randomTagModel() {
        Faker faker = new Faker();
        this.id = faker.number().numberBetween(max_value, min_value);
        this.name = faker.name().firstName();
        return this;
    }
}