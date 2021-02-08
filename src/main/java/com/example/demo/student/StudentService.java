package com.example.demo.student;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	// * Functions
	// ? List all
  public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	// ? Add new
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		// * Email validation
		if (studentOptional.isPresent()) {
			// ? If email already exists
			throw new IllegalStateException("email is already taken");
		}
		
		// ? Saving student
		studentRepository.save(student);
	}

	// ? Delete by id
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);

		// * Id exists validation
		if (!exists) {
			// ? Id doesn't exists
			throw new IllegalStateException("student with id " + studentId + " doesn't exist");
		}

		// ? Deleting student
		studentRepository.deleteById(studentId);
	}

	// * Transactional: Entity goes into a managed state
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		// ? Checking if student exists
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException("student with id " + studentId + " doesn't exist"));

		// ? Validating passed name and saving
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		// ? Validating passed email and saving
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

			// ? Checking if email exists
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("email is already taken");
			}

			student.setEmail(email);
		}
	}
}
