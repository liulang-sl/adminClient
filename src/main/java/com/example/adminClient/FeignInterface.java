package com.example.adminClient;

import com.example.User;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ADMIN-SERVER")
public interface FeignInterface {

  @RequestMapping("/path")
  public String path();

  @RequestMapping("/getServer")
  public String getServer(@RequestParam String name);

  @RequestMapping(value = "/getUser")
  public String getUser(@RequestBody User user);

}