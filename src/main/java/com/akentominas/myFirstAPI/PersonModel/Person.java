package com.akentominas.myFirstAPI.PersonModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private UUID id;
    @NotBlank   //name field cannot be empty
    private String name;

    //the @JsonProperty lets spring recognise the database fields and convert them into a class
    //@JsonProperty("id") = (id field from json data database)
    //@JsonProperty("name") = (name field from json data database)
    public Person(@JsonProperty("id") UUID id,@JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
