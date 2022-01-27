package remind.me.coding.service;

import remind.me.coding.dto.AddUserDto;
import remind.me.coding.dto.UserDto;
import remind.me.coding.model.UserEntity;
import remind.me.coding.protocol.ApiResponse;

import java.util.List;

public interface IUserService {

    ApiResponse<UserDto> save(AddUserDto userDto);
    ApiResponse<UserDto> update(UserDto userDto);
    ApiResponse<List<UserDto>> findAll();
    UserEntity find(long id);


    ApiResponse<UserDto> delete(long id);
}
