package com.rest.webservices._1_restful_webservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

  private final MessageSource messageSource;

  public HelloWorldController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  // GET
  // URI - "/hello-world"
  // method - "Hello World"
  @GetMapping(path = "/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  // Internationalized
  @GetMapping(path = "/hello-world-internationalized")
  public String helloWorldInternationalized() {
    return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
  }

  // hello-world-bean
  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World Bean");
  }


  @GetMapping(path = "/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World to %s", name));
  }
}
