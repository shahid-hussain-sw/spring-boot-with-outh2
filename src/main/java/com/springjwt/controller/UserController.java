package com.springjwt.controller;

import com.springjwt.model.User;
import com.springjwt.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping(value = "/user")
  public List<User> users(){
    return service.findAll();
  }

  @PostMapping(value = "/user")
  public User saveUser(@RequestBody User user){
    return service.save(user);
  }

  @DeleteMapping(value = "/user/{id}")
  public void deleteUser(@PathVariable long id){
    service.delete(id);
  }
}
