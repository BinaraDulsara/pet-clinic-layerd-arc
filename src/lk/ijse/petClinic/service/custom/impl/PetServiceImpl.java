package lk.ijse.petClinic.service.custom.impl;

import lk.ijse.petClinic.dao.DAOFactory;
import lk.ijse.petClinic.dao.custom.AppointmentDAO;
import lk.ijse.petClinic.dao.custom.PetDetailsDAO;
import lk.ijse.petClinic.dao.custom.PetOwnerDetailsDAO;
import lk.ijse.petClinic.dao.custom.QueryDAO;
import lk.ijse.petClinic.db.DBConnection;
import lk.ijse.petClinic.dto.PetDetailsDTO;
import lk.ijse.petClinic.dto.PetOwnerDetailsDTO;
import lk.ijse.petClinic.entity.PetDetails;
import lk.ijse.petClinic.entity.PetOwnerDetails;
import lk.ijse.petClinic.service.custom.PetService;
import lk.ijse.petClinic.service.exception.DuplicateException;
import lk.ijse.petClinic.service.util.Convertor;
import lk.ijse.petClinic.view.tm.PetDetailsTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetServiceImpl implements PetService {

    private final PetOwnerDetailsDAO petOwnerDetailsDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PETOWNERDETAILS);
    private final PetDetailsDAO petDetailsDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PETDETAILS);
    private final AppointmentDAO appointmentDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    private final Convertor convertor = new Convertor();
    private final Connection connection= DBConnection.getInstance().getConnection();
    private final QueryDAO queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public int getAllOwnersCount() {
        return petOwnerDetailsDAO.findOwnerCount();
    }

    @Override
    public int getAllAppointmentCount() {
        return appointmentDAO.findAppointmentCount();
    }

    @Override
    public List<PetDetailsTM> getAllPetDetails() {
        List<PetDetailsTM>petDetailsTMS=new ArrayList<>();
        for(PetDetails petDetails:petDetailsDAO.findAll()){
            Optional<PetOwnerDetails> optional = petOwnerDetailsDAO.findByPk(petDetails.getOwnerId());
            if (optional.isPresent()){
                PetOwnerDetails petOwnerDetails=optional.get();
                petDetailsTMS.add(new PetDetailsTM(petDetails.getPetId(),petDetails.getPetName(),petOwnerDetails.getOwnerId(),petOwnerDetails.getName(),petOwnerDetails.getTelNumber()));
            }
        }
        return petDetailsTMS;
    }

    @Override
    public boolean savePetDetails(PetDetailsDTO petDetailsDTO) throws DuplicateException {
        try {
            if (petDetailsDAO.findByPk(petDetailsDTO.getPetId()).isPresent()) {
                throw new DuplicateException("Pet id is dupplicated !");
            }
            connection.setAutoCommit(false);
            if (!petOwnerDetailsDAO.findByPk(petDetailsDTO.getOwnerId()).isPresent()){
                if (!petOwnerDetailsDAO.save(convertor.toPetOwnerDetails(petDetailsDTO.getPetOwnerDetailsDTO()))){
                    throw new SQLException("Fail to save data !");
                }
            }
            if (!petDetailsDAO.save(convertor.toPetDetails(petDetailsDTO))) {
                throw new SQLException("Fail to save data !");
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean deletePetDetails(PetDetailsTM petDetailsTM) {
        try {
            if (petDetailsDAO.findByOwnerId(petDetailsTM.getOwnerId()).size() == 0) {
                return petDetailsDAO.deleteByPk(petDetailsTM.getPetId());
            }
            connection.setAutoCommit(false);
            if (petDetailsDAO.deleteByPk(petDetailsTM.getPetId())){
                if (petOwnerDetailsDAO.deleteByPk(petDetailsTM.getOwnerId())){
                    connection.commit();
                    return true;
                }
            }
            throw new SQLException("Fail to delete !");
        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean updatePetDetails(PetDetailsDTO petDetailsDTO) {
        try {
            connection.setAutoCommit(false);
            if (petOwnerDetailsDAO.update(convertor.toPetOwnerDetails(petDetailsDTO.getPetOwnerDetailsDTO()))){
                if (petDetailsDAO.update(convertor.toPetDetails(petDetailsDTO))){
                    connection.commit();
                    return true;
                }
            }
            throw new SQLException("Fail to update !");
        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public PetDetailsDTO getPetDetailsDTO(String petId) {
        PetDetails pet = petDetailsDAO.findByPk(petId).get();
        PetOwnerDetails owner = petOwnerDetailsDAO.findByPk(pet.getOwnerId()).get();
        PetOwnerDetailsDTO petOwnerDetailsDTO = new PetOwnerDetailsDTO(owner.getOwnerId(),owner.getName(),owner.getAddress(),owner.getTelNumber());
        return new PetDetailsDTO(petId,pet.getOwnerId(),pet.getPetName(),pet.getPetAge(),pet.getPetBreed(),petOwnerDetailsDTO);
    }
}
