package com.testsite.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // this annotation is assigning this "testController" as a Controller
public class testController {
	@GetMapping("/testroute") // this annotation mapping the method "test" and the URL "http://~/testroute"
	@ResponseBody
	public String test() {
		return "sample still working? yes yes";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
		// Differences between redirect and forward
		// Redirect requests the new URL
		// Forward requests the URL alongside with the previously requested parameters
	}
}