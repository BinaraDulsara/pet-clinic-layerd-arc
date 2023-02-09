package lk.ijse.petClinic.dao.custom;

import lk.ijse.petClinic.dao.CrudDAO;
import lk.ijse.petClinic.entity.PetOwnerDetails;

import java.sql.SQLException;

public interface PetOwnerDetailsDAO extends CrudDAO<PetOwnerDetails,String> {
    int findOwnerCount();
}
