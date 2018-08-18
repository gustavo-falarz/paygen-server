package com.gfbdev.session;

import com.gfbdev.Messages;
import com.gfbdev.entity.Employee;
import com.gfbdev.entity.Response;
import com.gfbdev.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSession {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSession(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Response findEmployeeByEmail(String email) {
        try {
            Employee employee = employeeRepository.findByEmail(email);
            if (employee == null) {
                return Response.error(Messages.getInstance()
                        .getString("message.error.employee-not-found"));
            } else {
                return Response.ok(email);
            }
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
