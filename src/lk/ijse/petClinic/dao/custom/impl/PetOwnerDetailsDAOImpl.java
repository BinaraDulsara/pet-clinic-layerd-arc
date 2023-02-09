package lk.ijse.petClinic.dao.custom.impl;

import lk.ijse.petClinic.dao.custom.PetOwnerDetailsDAO;
import lk.ijse.petClinic.dao.util.CrudUtil;
import lk.ijse.petClinic.entity.PetOwnerDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PetOwnerDetailsDAOImpl implements PetOwnerDetailsDAO {
    @Override
    public boolean save(PetOwnerDetails entity) {
        try {
            return CrudUtil.execute("insert into petownerdetails(?,?,?,?)",
            entity.getOwnerId(),
            entity.getName(),
            entity.getAddress(),
            entity.getTelNumber()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(PetOwnerDetails entity) {
        try {
            return CrudUtil.execute("update petownerdetails set name= ?,set address = ?,telNumber Where ownerId = ? ",
            entity.getName(),
            entity.getAddress(),
            entity.getTelNumber(),
            entity.getOwnerId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByPk(String pk) {
        try {
            return CrudUtil.execute("DELETE from petownerdetails WHERE ownerId ='"+pk+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PetOwnerDetails> findAll() {
        try {
            ArrayList<PetOwnerDetails> petOwnerDetailsList = new ArrayList<>();
            ResultSet rst = CrudUtil.execute("SELECT * from petoownerdetails");
            while (rst.next()) {
                petOwnerDetailsList.add(new PetOwnerDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));

            }
            return petOwnerDetailsList;
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PetOwnerDetails> findByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * from petownerdetails WHERE ownerId ='" + pk + "'");
            if (rst.next()) {
                return Optional.of(new PetOwnerDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findOwnerCount() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(ownerId) FROM petownerdetails");
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
