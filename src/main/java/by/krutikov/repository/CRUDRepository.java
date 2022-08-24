package by.krutikov.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CRUDRepository<K, T> {
    int DEFAULT_FIND_ALL_LIMIT = 10;
    int DEFAULT_FIND_ALL_OFFSET = 0;

    // TODO: 10.08.22 add exceptions to methods
    T findById(Long id);

    Optional<T> findOne(K id);

    List<T> findAll();

    List<T> findAll(int offset, int limit);

    T create(T object);

    T update(T object);

    K delete(K id);
}
