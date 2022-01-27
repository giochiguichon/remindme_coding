package remind.me.coding.repository;

import remind.me.coding.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface IUserRepository {
    UserEntity save(UserEntity user);

    UserEntity find(long id);

    List<UserEntity> findAll();

    void delete(long id);

    UserEntity update(UserEntity detachedUser);
}

