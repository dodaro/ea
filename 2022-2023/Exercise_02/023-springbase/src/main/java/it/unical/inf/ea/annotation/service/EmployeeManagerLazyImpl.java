package it.unical.inf.ea.annotation.service;

import it.unical.inf.ea.annotation.bean.Employee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Lazy
@Service
class EmployeeManagerLazyImpl implements EmployeeManagerLazy {
 
    @Override
    public Employee create() {
        Employee emp =  new Employee();
        emp.setId(1);
        emp.setName("Pasticcio");
        return emp;
    }

    //deleted in java 11: see pom.xml
    @PostConstruct
    public void onInit(){
        System.out.println("EmployeeManagerLazyImpl Bean is Created !!");
    }
}