package com.rest.webservices._1_restful_webservices.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties(value = {"arg0", "arg1"})
@JsonFilter("BeanToBeFiltered")
public class BeanToBeFiltered {
    private String arg0;
    private String arg1;

//    @JsonIgnore
    private String arg2;

    public BeanToBeFiltered(String arg0, String arg1, String arg2) {
        super();
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }
}
