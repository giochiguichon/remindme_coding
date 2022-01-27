package remind.me.coding.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import remind.me.coding.dto.AddUserDto;
import remind.me.coding.dto.UserDto;
import remind.me.coding.error.RemindmeEntityNotFoundException;
import remind.me.coding.model.UserEntity;
import remind.me.coding.protocol.ApiResponse;
import remind.me.coding.repository.IUserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserService() {
    }

    @Override
    @Transactional
    public ApiResponse<UserDto> save(AddUserDto userDto){

        var userEntity = mapper.map(userDto, UserEntity.class);

        var newUserEntity = userRepository.save(userEntity);

        var resultDto = mapper.map(newUserEntity, UserDto.class);

        return new ApiResponse<UserDto>(resultDto);
    }

    @Override
    @Transactional
    public ApiResponse<UserDto> update(UserDto userDto) {
        var userEntity = mapper.map(userDto, UserEntity.class);

        try{
            var updatedUserEntity = userRepository.update(userEntity);

            var resultDto = mapper.map(updatedUserEntity, UserDto.class);

            return new ApiResponse<UserDto>(resultDto);

        }catch (RemindmeEntityNotFoundException e){
            return new ApiResponse<>(null, HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @Override
    public UserEntity find(long id){
        return userRepository.find(id);
    }

    @Override
    public ApiResponse<UserDto> delete(long id) {
        try {
            userRepository.delete(id);
            return new ApiResponse<>();
        }catch (EntityNotFoundException e){
               return new ApiResponse<>(null, HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<UserDto>> findAll() {
        var allUserEntities = userRepository.findAll();

        List<UserDto> resultDto = new ArrayList<UserDto>(allUserEntities.size());
        for (var userEntity :
                allUserEntities) {
            resultDto.add(mapper.map(userEntity, UserDto.class));
        }
        return new ApiResponse<>(resultDto);
    }
}
