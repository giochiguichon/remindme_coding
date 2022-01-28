package remind.me.coding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import remind.me.coding.dto.AddUserDto;
import remind.me.coding.dto.GithubRepositoryMinimal;
import remind.me.coding.dto.UserDto;
import remind.me.coding.protocol.ApiResponse;
import remind.me.coding.service.GitRepositoryInfoProvider;
import remind.me.coding.service.IUserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private GitRepositoryInfoProvider gitRepositoryInfoProvider;

    @PostMapping("/user")
    public ApiResponse saveUser(@RequestBody final AddUserDto userDto){

        var newUserDto = userService.save(userDto);

        return newUserDto;
    }

    @PutMapping("/user")
    public ApiResponse updateUser(@RequestBody final UserDto userDto){

        var updateUserResponse = userService.update(userDto);

        return updateUserResponse;
    }
    @GetMapping("/users")
    public ApiResponse< List<UserDto>> findUsers(){
        var allUsers = userService.findAll();

        return allUsers;
    }
/*
    @GetMapping("/user/{id}")
    public String findUser(@PathVariable long id){

        var userEntity = userService.find(id);

        return userEntity.getFirstname();
    }
*/
    @DeleteMapping("/user/{id}")
    @Transactional
    public ApiResponse<UserDto> deleteUser(@PathVariable long id){
        return userService.delete(id);
    }

    @PostMapping("/user/initData")
    public void InitData()
    {
        var newUser = new AddUserDto();

        newUser.setFirstname("Marvin");
        newUser.setSurname("Chigui");
        newUser.setPosition("dev");
        newUser.setGithubProfileUrl("some url");

        userService.save(newUser);
    }

    @GetMapping("/users/{id}/repositories")
    public ApiResponse findUserRepositories(@PathVariable long id){

        var user = userService.find(id);
        if(user == null)
            return new ApiResponse<>( null, HttpStatus.BAD_REQUEST.value(), "User Not found");

        var response = gitRepositoryInfoProvider.getRepositoriesInfo(user.getGithubProfileUrl());

        return new ApiResponse<>(response);
    }
}
