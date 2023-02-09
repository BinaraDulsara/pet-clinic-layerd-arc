package lk.ijse.petClinic.dao.custom;

import lk.ijse.petClinic.dao.CrudDAO;
import lk.ijse.petClinic.entity.Appointment;
import lk.ijse.petClinic.entity.PetDetails;

public interface AppointmentDAO extends CrudDAO<Appointment,String> {
    int findAppointmentCount();
}
