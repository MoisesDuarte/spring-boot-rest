package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student") // ? Root route mapping
public class StudentController {
	private final StudentService studentService;

	// * StudentService Constructor
	@Autowired // ? Autowired auto-inject resolve and auto injects-bean
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	// * API Routes
  // ? Get all students
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	// ? Register new student
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}

	// ? Delete student by id
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

	// ? Update student name and email
	@PutMapping(path = "{studentId}")
	public void updateStudent(
		@PathVariable("studentId") Long studentId,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String email
	) {
		studentService.updateStudent(studentId, name, email);
	}
}
