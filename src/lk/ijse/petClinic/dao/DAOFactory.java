package lk.ijse.petClinic.dao;

import lk.ijse.petClinic.dao.custom.impl.*;
import org.apache.bcel.generic.SWITCH;
import org.apache.poi.hssf.record.formula.functions.T;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory ==null){
            daoFactory =new DAOFactory();
        }
        return daoFactory;
    }
    public <T extends SuperDAO>T getDAO(DAOTypes types){
        switch (types){
            case APPOINTMENT:
                return (T) new AppointmentDAOImpl();
            case PETDETAILS:
                return (T) new PetDetailsDAOImpl();
            case PETOWNERDETAILS:
                return (T) new  PetOwnerDetailsDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return  null;
        }
    }

    public enum DAOTypes{
        APPOINTMENT,PETDETAILS,PETOWNERDETAILS,USER,QUERY
    }
}
