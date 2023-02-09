package lk.ijse.petClinic.dao.custom.impl;

import lk.ijse.petClinic.dao.custom.PetDetailsDAO;
import lk.ijse.petClinic.dao.util.CrudUtil;
import lk.ijse.petClinic.entity.PetDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetDetailsDAOImpl implements PetDetailsDAO {
    @Override
    public boolean save(PetDetails entity) {
        try {
            return CrudUtil.execute("insert into petdetails values(?,?,?,?,?)",
                entity.getPetId(),
                entity.getOwnerId(),
                entity.getPetName(),
                entity.getPetAge(),
                entity.getPetBreed()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(PetDetails entity) {
        try {
            return CrudUtil.execute("update petdetails set ownerId = ?,petName =?,petAge = ?,petBreed = ? WHERE petId =? ",
                    entity.getOwnerId(),
                    entity.getPetName(),
                    entity.getPetAge(),
                    entity.getPetBreed(),
                    entity.getPetId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByPk(String pk) {
        try {
            return CrudUtil.execute("DELETE from petdetails WHERE petId ='"+pk+"' ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PetDetails> findAll() {
        try {
            ArrayList<PetDetails> petDetailsList = new ArrayList<>();
            ResultSet rst = CrudUtil.execute("SELECT * from petdetails");
            while (rst.next()) {
                petDetailsList.add(new PetDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5)));
            }
            return petDetailsList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public Optional<PetDetails> findByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM petdetails WHERE petId ='" + pk + "'");
            if (rst.next()) {
                return Optional.of(new PetDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5)));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PetDetails> findByOwnerId(String ownerId) {
        try {
            List<PetDetails> petDetailsList = new ArrayList<>();
            ResultSet rst = CrudUtil.execute("SELECT * from petdetails WHERE ownerId=?",ownerId);
            while (rst.next()) {
                petDetailsList.add(new PetDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5)));
            }
            return petDetailsList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
