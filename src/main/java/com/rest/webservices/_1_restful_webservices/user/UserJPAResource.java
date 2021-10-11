package com.rest.webservices._1_restful_webservices.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


// @formatter:on
@RestController
public class UserJPAResource {

  private final UserRepository userRepository;
  private final PostRepository postRepository;

  public UserJPAResource(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable Integer id) {
    Optional<User> optional = userRepository.findById(id);
    if (optional.isEmpty()) {
      throw new UserNotFoundException("id - " + id);
    }

    EntityModel<User> model = EntityModel.of(optional.get());

    WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    model.add(linkToUsers.withRel("link-to-retrieve-all-users"));
    return model;
  }

  @PostMapping("/jpa/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

    User savedUser = userRepository.save(user);
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable Integer id) {
    userRepository.deleteById(id);
  }

  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> retrieveAllPostsByUser(@PathVariable Integer id) {
    Optional<User> optional = userRepository.findById(id);
    if (optional.isEmpty()) {
      throw new UserNotFoundException("id - " + id);
    }

    return optional.get().getPosts();
  }

  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity<Object> createPostsByUser(@PathVariable Integer id, @RequestBody Post post) {
    Optional<User> optional = userRepository.findById(id);
    if (optional.isEmpty()) {
      throw new UserNotFoundException("id - " + id);
    }

    User user = optional.get();
    post.setUser(user);

    postRepository.save(post);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(post.getId()).toUri();
    return ResponseEntity.created(location).build();
  }
}
