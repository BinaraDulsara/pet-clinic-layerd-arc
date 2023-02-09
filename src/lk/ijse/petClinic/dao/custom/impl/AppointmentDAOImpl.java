package lk.ijse.petClinic.dao.custom.impl;

import lk.ijse.petClinic.dao.custom.AppointmentDAO;
import lk.ijse.petClinic.dao.util.CrudUtil;
import lk.ijse.petClinic.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public boolean save(Appointment entity) {
        try {
            return CrudUtil.execute("insert into appointment values(?,?,?,?)",
                entity.getAppId(),
                entity.getOwnerId(),
                entity.getPetId(),
                entity.getDate()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Appointment entity) {
        try {
            return CrudUtil.execute("update appointment set date =? WHERE appId =?",
            entity.getDate(),
            entity.getAppId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByPk(String pk) {
        try {
            return CrudUtil.execute("delete from appointment WHERE appId ='"+pk+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAll() {
        try {
            ArrayList<Appointment> appointmentList = new ArrayList<>();
            ResultSet rst = CrudUtil.execute("SELECT * FROM appointment");
            while (rst.next()) {
                appointmentList.add(new Appointment(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
            }
            return appointmentList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Appointment> findByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM appointment WHERE appId ='" + pk + "'");
            if (rst.next()) {
                return Optional.of(new Appointment(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4)
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findAppointmentCount() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(appId) FROM appointment");
            return rst.getInt(1);
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }
}

