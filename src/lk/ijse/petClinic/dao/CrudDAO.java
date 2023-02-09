package lk.ijse.petClinic.dao;

import lk.ijse.petClinic.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO {

    boolean save(T entity) ;

    boolean update(T entity);

    boolean deleteByPk(ID pk) ;

    List<T> findAll();

    Optional<T> findByPk(ID pk);
}
