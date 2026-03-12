package it.unical.inf.ea.uniprj.todo.aspect.annotation;

import it.unical.inf.ea.uniprj.todo.aspect.MockMaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MaintenanceCheckerAspect { // test nel main

  private final MockMaintenanceService mockMaintenanceService;

  @Pointcut("@annotation(it.unical.inf.ea.uniprj.todo.aspect.annotation.MaintenanceCheck)")
  public void maintenancePointcut() {

  }

  @Around("maintenancePointcut()")
  public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("In Aspect");
    if (mockMaintenanceService.isSystemUnderMaintenance())
           throw new RuntimeException("System under maintenance");
    return joinPoint.proceed();
  }

}