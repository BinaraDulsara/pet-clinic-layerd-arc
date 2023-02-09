package lk.ijse.petClinic.service.custom;

import lk.ijse.petClinic.dto.PetDetailsDTO;
import lk.ijse.petClinic.service.SuperService;
import lk.ijse.petClinic.service.exception.DuplicateException;
import lk.ijse.petClinic.view.tm.PetDetailsTM;

import java.sql.SQLException;
import java.util.List;

public interface PetService extends SuperService {
    int getAllOwnersCount();

    int getAllAppointmentCount();

    List<PetDetailsTM> getAllPetDetails();

    boolean savePetDetails(PetDetailsDTO petDetailsDTO) throws DuplicateException;


    boolean deletePetDetails(PetDetailsTM petDetailsTM);

    boolean updatePetDetails(PetDetailsDTO petDetailsDTO);

    PetDetailsDTO getPetDetailsDTO(String petId);
}
