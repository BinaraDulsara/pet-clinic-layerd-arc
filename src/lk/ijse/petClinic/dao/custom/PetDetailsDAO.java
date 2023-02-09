package lk.ijse.petClinic.dao.custom;

import lk.ijse.petClinic.dao.CrudDAO;
import lk.ijse.petClinic.entity.PetDetails;

import java.util.Collection;
import java.util.List;

public interface PetDetailsDAO extends CrudDAO<PetDetails,String> {
    List<PetDetails> findByOwnerId(String ownerId);
}
