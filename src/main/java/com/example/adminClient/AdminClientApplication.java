package com.example.adminClient;

import javax.annotation.Resource;

import com.example.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class AdminClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}

	@Value("${server.port}")
	private String port;

	@Resource
	private FeignInterface feignInterface;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String requestMethodName(@RequestParam String name) {
		return name + "访问了 ，端口是：" + port;
	}

	@RequestMapping(value = "/path", method = RequestMethod.GET)
	public String requestMethodName() {
		return restTemplate().getForObject("http://ADMIN-SERVER/path", String.class);
	}

	@RequestMapping("/ft")
	public String feignTest() {
		return feignInterface.path();
	}

	@RequestMapping("/ftp")
	public String getServer(String name) {
		System.out.println("name:" + name);
		return feignInterface.getServer(name);
	}

	@RequestMapping(value = "/getUser")
	public String getUser(@RequestParam String username) {
		User user = new User();
		user.setUsername(username);
		return feignInterface.getUser(user);
	}

}
