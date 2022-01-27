package remind.me.coding.repository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import remind.me.coding.error.RemindmeEntityNotFoundException;
import remind.me.coding.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    @PersistenceContext()
    private EntityManager entityManager;



    @Override
    public UserEntity save(UserEntity user){
        entityManager.persist(user);
        return user;
    }

    @Override
    public UserEntity find(long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> findAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery("select u from UserEntity u", UserEntity.class);

        var results = query.getResultList();
        return results;
    }

    @Override
    public void delete(long id) throws RemindmeEntityNotFoundException {
        var entity = entityManager.find(UserEntity.class,id);

        if(entity == null)
            throw new RemindmeEntityNotFoundException("User with id " + id + " does not exists.");

        entityManager.remove(entity);
    }

    @Override
    public UserEntity update(UserEntity detachedUser) throws RemindmeEntityNotFoundException  {

        var userEntity = entityManager.find(UserEntity.class, detachedUser.getId());

        if(userEntity == null)
            throw new RemindmeEntityNotFoundException("User With id " + detachedUser.getId() + " does not exists." );

        userEntity.setFirstname(detachedUser.getFirstname());
        userEntity.setSurname(detachedUser.getSurname());
        userEntity.setPosition(detachedUser.getPosition());
        userEntity.setGithubProfileUrl(detachedUser.getGithubProfileUrl());

        entityManager.persist(userEntity);
        return userEntity;
    }


}
