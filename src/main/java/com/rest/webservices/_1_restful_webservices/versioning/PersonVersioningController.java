package com.rest.webservices._1_restful_webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    // versioning our uri using the different mappings
    @GetMapping("/person/v1")
    public PersonV1 personV1() {
        return new PersonV1("Java Developer");
    }

    @GetMapping("/person/v2")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Java", "Developer"));
    }

    // Differentiating the version by the param passed in the request
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Java Developer");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Java", "Developer"));
    }

    // Differentiating the version using the header
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Java Developer");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Java", "Developer"));
    }

    // We can also do the versioning using the produces
    // the messages says what kind result we produce
    // This is also called the MIME type versioning
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Java Developer");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Java", "Developer"));
    }
}
