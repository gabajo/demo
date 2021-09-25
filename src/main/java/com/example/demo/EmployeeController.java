package com.example.demo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}

	@GetMapping("/employees/filter/{from}/{to}")
	List<Employee> ageRange(@PathVariable Long from, @PathVariable Long to) {
		return repository.findAll().stream()
				.filter(emp -> Period.between(emp.getBirthDate(), LocalDate.now()).getYears() >= from
						&& Period.between(emp.getBirthDate(), LocalDate.now()).getYears() <= to)
				.collect(Collectors.toList());

	}

	@GetMapping("/employees/filter/{str}")
	List<Employee> nameAndAddresses(@PathVariable String str) {

		return repository.findAll().stream()
				.filter(emp -> emp.getName().toLowerCase().contains(str.toLowerCase())
						|| emp.getAddresses().stream().anyMatch(addr -> addr.toLowerCase().contains(str.toLowerCase())))
				.collect(Collectors.toList());

	}

	// end::get-aggregate-root[]

	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {

		return repository.save(newEmployee);
	}

	// Single item

	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setSecondName(newEmployee.getSecondName());
			employee.setAddresses(newEmployee.getAddresses());
			employee.setBirthDate(newEmployee.getBirthDate());
			employee.setPhoneNumbers(newEmployee.getPhoneNumbers());
			employee.setPhoto(newEmployee.getPhoto());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}