package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private List<Student> studentList = new ArrayList<>();

	// Accept JSON instead of RequestParams
	@PostMapping("/addStudents")
	public String addCandidate(@RequestBody Student student) {

		// Auto-increment ID
		if (!studentList.isEmpty()) {
			int lastId = studentList.get(studentList.size() - 1).getId();
			student.setId(lastId + 1);
		} else {
			student.setId(1);
		}

		studentList.add(student);
		return "Candidate added successfully with ID: " + student.getId();
	}

	@GetMapping("/getStudents")
	public List<Student> getAllStudents() {
		return studentList;
	}
}
