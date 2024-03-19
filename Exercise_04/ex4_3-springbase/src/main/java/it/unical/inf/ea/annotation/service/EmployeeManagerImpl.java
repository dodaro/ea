package it.unical.inf.ea.annotation.service;

import it.unical.inf.ea.annotation.bean.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class EmployeeManagerImpl implements EmployeeManager {
 
    @Override
    public Employee create() {
        Employee emp =  new Employee();
        emp.setId(1);
        emp.setName("Ciccio");
        return emp;
    }

    //deleted in java 11: see pom.xml
    @PostConstruct
    public void onInit(){
        System.out.println("EmployeeManagerImpl Bean is Created !!");
    }
}