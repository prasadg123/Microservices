package com.prasad.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest/school")
public class SchoolResource {

	@Autowired
	private SchoolServiceDelegate schoolService;
	
	@HystrixCommand
	@GetMapping("/{schoolname}")
	public List<Object> getStudents(@PathVariable("schoolname") final String schoolName) {
		
		return schoolService.getStudentDataDelegate(schoolName);
	}
}
