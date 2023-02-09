package lk.ijse.petClinic.service.util;

import lk.ijse.petClinic.dto.PetDetailsDTO;
import lk.ijse.petClinic.dto.PetOwnerDetailsDTO;
import lk.ijse.petClinic.entity.PetDetails;
import lk.ijse.petClinic.entity.PetOwnerDetails;

public class Convertor {

    public PetOwnerDetails toPetOwnerDetails(PetOwnerDetailsDTO dto) {
        return new PetOwnerDetails(dto.getOwnerId(), dto.getName(), dto.getAddress(), dto.getTelNumber());
    }

    public PetDetails toPetDetails(PetDetailsDTO dto) {
        return new PetDetails(dto.getPetId(), dto.getOwnerId(), dto.getPetName(), dto.getPetAge(), dto.getPetBreed());
    }
}
