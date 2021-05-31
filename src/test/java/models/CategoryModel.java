package models;

import com.github.javafaker.Faker;

import interfaces.constants;

public class CategoryModel implements constants {
    public int id;
    public String name;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel randomCategoryModel() {
        Faker faker = new Faker();
        this.id = faker.number().numberBetween(min_value, max_value);
        this.name = faker.name().firstName();
        return this;
    }
}
