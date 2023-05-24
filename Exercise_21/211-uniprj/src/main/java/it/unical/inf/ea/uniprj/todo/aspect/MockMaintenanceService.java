package it.unical.inf.ea.uniprj.todo.aspect;

import org.springframework.stereotype.Service;

@Service
public class MockMaintenanceService {
    public boolean isSystemUnderMaintenance() {
        return true; //TODO leggi da db???
    }
}
