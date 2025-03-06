package it.unical.inf.ea.uniprj.todo.aspect.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestMaintenanceService {

    @MaintenanceCheck
    public void shipStuff(){
        log.info("In Service");
    }
}
