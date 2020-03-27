package com.mortazacorp.secretly.databaseUtil;

import com.mortazacorp.secretly.models.SecretMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecretMessageRepository extends CrudRepository<SecretMessage,Long> {
    List<SecretMessage> getAllByToUserNameOrderByTimestampDesc(String toUserName);
}
