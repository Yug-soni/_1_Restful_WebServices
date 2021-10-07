package com.rest.webservices._1_restful_webservices.versioning;

public class PersonV1 {
    private String name;

    public PersonV1() {

    }

    public PersonV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
