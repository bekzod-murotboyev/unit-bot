package io.github.reflectframework.unitbot.repositories;

import io.github.reflectframework.unitbot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByChatId(long chatId);

}
