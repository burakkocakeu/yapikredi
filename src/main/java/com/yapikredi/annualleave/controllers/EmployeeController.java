package com.yapikredi.annualleave.controllers;

import com.yapikredi.annualleave.entities.Employee;
import com.yapikredi.annualleave.resources.EmployeeDto;
import com.yapikredi.annualleave.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@RestController @Slf4j
@RequestMapping("/employee")
@Tag(name = "Employee", description = "the Employee API")
public class EmployeeController implements CommandLineRunner {

    @Value("${server.port}")
    private int port;

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<EmployeeDto>> getEmployeeList() {
        return new ResponseEntity<>(
                employeeService.toDtoList(employeeService.getEmployeeList()),
                HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.createNewEmployee(employeeDto);
            employeeService.printCurrentTime();
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @Override
    @Order(1)
    public void run(String... args) {

        // fetch all employees
        log.info("Employees found with findAll():");
        log.info("-------------------------------");
        for (Employee employee : employeeService.getEmployeeList()) {
            log.debug(employee.getName());
        }
        log.debug("");

        IntStream.range(1, 101).forEach(i -> {
            EmployeeDto dto = new EmployeeDto();
            dto.setIdentity(Long.valueOf(19585430 + i));
            dto.setStarted(LocalDateTime.now());
            dto.setName("Firstname" + i);
            dto.setSurname("Lastname" + i);

            RequestEntity requestEntity = new RequestEntity(dto, HttpMethod.POST, URI.create("http://localhost:" + port + "/employee"));

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(requestEntity, String.class);
        });

        // fetch all employees
        log.info("Employees found with findAll():");
        log.info("-------------------------------");
        for (Employee employee : employeeService.getEmployeeList()) {
            log.debug(employee.getName());
        }
        log.debug("");

    }

}
