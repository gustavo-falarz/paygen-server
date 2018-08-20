package com.gfbdev.controller;

import com.gfbdev.entity.Employee;
import com.gfbdev.entity.Response;
import com.gfbdev.session.EmployeeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final
    EmployeeSession employeeSession;

    @Autowired
    public EmployeeController(EmployeeSession employeeSession) {
        this.employeeSession = employeeSession;
    }

    @RequestMapping("/add")
    public Response addEmployee(@RequestBody Employee employee) {
        return employeeSession.addEmployee(employee);
    }
}
