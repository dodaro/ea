package it.unical.inf.ea.annotation.controller;

import it.unical.inf.ea.annotation.service.EmployeeManager;
import it.unical.inf.ea.annotation.service.EmployeeManagerLazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

  @Lazy
  @Autowired
  EmployeeManagerLazy employeeManagerLazy;

  @Autowired
  EmployeeManager employeeManager;

  public EmployeeManagerLazy getLazyManagerInstance() {
		return employeeManagerLazy;
  }

  public EmployeeManager getManagerInstance() {
    return employeeManager;
  }
}