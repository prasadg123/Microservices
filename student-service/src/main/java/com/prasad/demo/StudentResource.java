package com.prasad.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("rest/student")
public class StudentResource {

	private static Map<String,List<Student>> schoolDB=null;
	
	
	@Value("${msg:This is default value}")
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	static {
	    schoolDB = new HashMap<String,List<Student>>();
	    List<Student> lst=new ArrayList<Student>();
	    lst.add(new Student("Prasad","Class 1"));
	    lst.add(new Student("Mahesh","Class 1"));
	    lst.add(new Student("Naresh","Class 1"));
	    
	    schoolDB.put("abcSchool", lst);
	    
	    lst=new ArrayList<Student>();
	    lst.add(new Student("Narendra","Class 2"));
	    lst.add(new Student("Avinash","Class 2"));
	    lst.add(new Student("Akhil","Class 2"));
	    
	    schoolDB.put("xyzSchool", lst);
	}
	
	@GetMapping("/{schoolname}")
	public List<Student> getStudents(@PathVariable("schoolname") final String schoolName){
		System.out.println("Student Service:Getting Student details for "+schoolName);
		List<Student> studentList=schoolDB.get(schoolName);
		if(studentList==null) {
			new ArrayList<Student>().add(new Student("NOT FOUND","N/A"));
		}
		
		return studentList;
	}
}
