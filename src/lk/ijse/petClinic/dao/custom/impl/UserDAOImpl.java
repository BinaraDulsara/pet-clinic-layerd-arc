package lk.ijse.petClinic.dao.custom.impl;

import lk.ijse.petClinic.dao.custom.UserDAO;
import lk.ijse.petClinic.dao.util.CrudUtil;
import lk.ijse.petClinic.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) {
        try {
            return CrudUtil.execute("insert into user values (?,?,?)",
            entity.getUserId(),
            entity.getUserName(),
            entity.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User entity) {
        try {
            return CrudUtil.execute("update user set password =?,userName = ? WHERE userId =? ",
                    entity.getPassword(),
                    entity.getUserName(),
                    entity.getUserId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByPk(String pk)  {
        try {
            return CrudUtil.execute("DELETE from user WHERE userId ='"+pk+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            ArrayList<User> userList = new ArrayList<>();
            ResultSet rst = CrudUtil.execute("SELECT * from user");
            while (rst.next()) {
                userList.add(new User(rst.getString(1), rst.getString(2), rst.getString(3)));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * from user WHERE userId ='" + pk + "'");
            if (rst.next()) {
                return Optional.of(new User(rst.getString(1), rst.getString(2), rst.getString(3)));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
