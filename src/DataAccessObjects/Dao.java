package DataAccessObjects;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface Dao<T> {
    Optional<T> get(Long id);
    Set<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(T t);

}

