package com.rest.webservices._1_restful_webservices.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    @GetMapping("/filter")
    public MappingJacksonValue retrieveBeanToBeFiltered() {
        BeanToBeFiltered beanToBeFiltered = new BeanToBeFiltered("value-00", "value-01", "value-02");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("arg0", "arg1");

        FilterProvider filters = new SimpleFilterProvider().addFilter("BeanToBeFiltered", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(beanToBeFiltered);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filter-list")
    public MappingJacksonValue retrieveListOfBeanToBeFiltered() {
        List<BeanToBeFiltered> list = List.of(
                new BeanToBeFiltered("value-00", "value-01", "value-02"),
                new BeanToBeFiltered("value-10", "value-11", "value-12")
        );

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("arg1", "arg2");

        FilterProvider filters = new SimpleFilterProvider().addFilter("BeanToBeFiltered", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }
}
