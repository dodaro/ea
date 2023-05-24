package it.unical.inf.ea.uniprj.todo.aspect.method;

import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.todo.aspect.MockMaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class PrankerMethodAspect { // test nel main

  private final MockMaintenanceService mockMaintenanceService;
  @Pointcut("execution(public java.util.List<it.unical.inf.ea.uniprj.data.entities.Teacher> it.unical.inf.ea.uniprj.data.service.TeacherService.getAllTeacher())")
  public void prankerGetAllTeacherPointcut() {
  }

  @Before("prankerGetAllTeacherPointcut()")
  public void beforeGetAllTeacherService() {
    log.info("In Aspect");
     throw new RuntimeException("Prank");
  }

  //
  @Pointcut("execution(* it.unical.inf.ea.uniprj.data.service.StudentService.*(..))")
  public void prankerStudentServicePointcut() {
  }

  @After("prankerStudentServicePointcut()")
  public void afterStudentService() throws Throwable {
    log.info("In Aspect");
    throw new RuntimeException("Prank");
  }

  //

  //
  @Pointcut("execution(* it.unical.inf.ea.uniprj.data.service.CourseService.*(Long))")
  public void prankerCourseServicePointcut() {
  }

  @After("prankerCourseServicePointcut()")
  public void afterCourseService() throws Throwable {
    log.info("In Aspect");
    throw new RuntimeException("Prank");
  }

  //
  @Pointcut("within(it.unical.inf.ea.uniprj.core.services.*)")
  public void prankerCorePointcut() {}

  @Around("prankerCorePointcut()")
  public Object aroundCoreService(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("In Aspect");
    if (true)
      throw new RuntimeException("System under maintenance");
    return joinPoint.proceed();
  }

  //TODO: @annotazione vedi MaintenanceChekerAspect

  //
  @Pointcut("within(it.unical.inf.ea.uniprj.data.service.TeacherService) && execution(public String it.unical.inf.ea.uniprj.data.service.TeacherService.*(..))")
  public void logPointcutWithLogicalOperator(){}

  @Before("logPointcutWithLogicalOperator()")
  public void logPointcutWithLogicalOperatorAdvice(){
    log.info("In Aspect");
    throw new RuntimeException("Prank");
  }
}