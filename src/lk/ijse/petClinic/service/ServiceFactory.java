package lk.ijse.petClinic.service;

import lk.ijse.petClinic.service.custom.impl.AppointmentServiceImpl;
import lk.ijse.petClinic.service.custom.impl.PetServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService>T getService(ServiceType serviceType){
        switch (serviceType){
            case PET:
                return (T) new PetServiceImpl();
            case APPOINTMENT:
                return (T) new AppointmentServiceImpl();
            default:
                return null;
        }
    }

    public enum ServiceType{
        PET,APPOINTMENT
    }
}
