package com.mortazacorp.secretly.databaseUtil;

import com.mortazacorp.secretly.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String username);

    boolean existsByUserName(String username);
}
