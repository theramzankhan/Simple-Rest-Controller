package com.example.SimpleRestController.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SimpleRestController.Model.GreetingRequest;

//Mark this class as a REST controller
//@RestController marks the class as a REST controller, which means that all methods in this class return data directly as JSON or XML instead of rendering a view. It's a combination of @Controller and @ResponseBody.
@RestController
// @RequestMapping("/api") sets a base URL for all endpoints in this controller. This means every URL in this controller will start with /api.
@RequestMapping("/api")   // Base URL for this controller
public class MainController {
	
	// Endpoint to get a simple greeting
	@GetMapping("/greeting") // Annotation: @GetMapping("/greeting") maps this method to an HTTP GET request at /api/greeting.
	public String getGreeting() {        // Return: The method returns a simple String, "Hello, welcome to the Spring REST API!", which will be sent as a JSON response.
		return "Hello, Welcome to the Spring Boot Rest API!";
	}
	
	@GetMapping("/greeting/{name}")
	public String getPersonalizedGreeting(@PathVariable String name) {
		return "Hello " + name + " ! Welcome to the Rest API!";
	}
//	Parameterized GET Method: getPersonalizedGreeting
//
//	Annotation: @GetMapping("/greeting/{name}") maps this method to /api/greeting/{name}, where {name} is a path variable.
//	Path Variable: @PathVariable String name binds the path variable in the URL to the name parameter.
//	Return: Returns a personalized greeting with the name provided in the URL, e.g., "/api/greeting/John" returns Hello, John! Welcome to the Spring REST API!.
//	In this case, the URL path "/greeting/{name}" expects a path variable (represented by {name}) in the URL. For example, if the request URL is http://localhost:8080/greeting/John, John will be used as the value of the name variable.
//	The @PathVariable annotation tells Spring to extract the value from the URL path ({name}) and pass it to the name parameter in the method.
	
	@PostMapping("/postMethodGreeting")
	public String postGreeting(@RequestBody GreetingRequest request) {
		return "Hello " + request.getName() + " ! Welcome to the Rest API!";
	}
	
//  REST API with Query Parameters
//	@RequestParam is an annotation in Spring Boot used to extract query parameters from the URL in HTTP requests. It helps you access parameters passed in the URL after the ?, allowing you to handle and use them within your methods.
//	How to Access the Endpoint
//	URL: http://localhost:8080/search?keyword=SpringBoot
//	Response: You searched for: SpringBoot
	@GetMapping("/search")
	public String search(@RequestParam String keyword) {
		return "You searched for: " + keyword;
	}
	
//	Optional Parameters with Default Values: You can make parameters optional by setting a default value. URL without parameter: http://localhost:8080/welcome
	@GetMapping("/welcome")
	public String welcome(@RequestParam(defaultValue = "Guest") String name) {
		return "welcome, " + name + "!";
	}
	
//	Example 4: Optional Parameter Without a Default Value
//	You can also make a parameter optional without setting a default value by using required = false.
	@GetMapping("/optional")
	public String optionalParam(@RequestParam(required=false) String keyword) {
		return keyword != null ? "You searched for: " + keyword : "No keyword provided";
	}
	
	//Pagination and Sorting - Scenario: Your application lists data with options for pagination and sorting.
//	URL: http://localhost:8080/items?page=1&size=20&sortBy=price     Use Case: Improves user experience by allowing customized data retrieval.
	
}
