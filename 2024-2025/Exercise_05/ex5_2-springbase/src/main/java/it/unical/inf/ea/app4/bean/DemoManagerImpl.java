package it.unical.inf.ea.app4.bean;

public class DemoManagerImpl implements DemoManager {
    @Override
    public String getServiceName() {
        return "My first service with Spring";
    }
}