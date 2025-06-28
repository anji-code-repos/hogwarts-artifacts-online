package com.practice.hard.hogwarts_artifacts_online.hogwartsUser;

import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Converter.UserToUserDtoConverter;
import com.practice.hard.hogwarts_artifacts_online.hogwartsUser.Dto.UserDto;
import com.practice.hard.hogwarts_artifacts_online.system.Result;
import com.practice.hard.hogwarts_artifacts_online.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint.baseurl}/users")
public class UserController implements Serializable {

    private final UserService userService;

    private final UserToUserDtoConverter userToUserDtoConverter;

    public UserController(UserService userService, UserToUserDtoConverter userToUserDtoConverter) {
        this.userService = userService;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Integer userId){

        UserDto hogwartsUser= this.userToUserDtoConverter.convert(this.userService.getUserById(userId));
        return new Result(true, StatusCode.SUCCESS, "Find User Success", hogwartsUser);
    }


    @GetMapping
    public Result findAllUser() {

        List<HogwartsUser> userList = this.userService.getAllUsers();
        List<UserDto> userDtoList= userList.stream()
                .map(this.userToUserDtoConverter::convert).toList();
        return new Result(true, StatusCode.SUCCESS, "Find All Users Success", userDtoList);

    }

    @PostMapping()
    public Result createUser(@RequestBody HogwartsUser hogwartsUser){
        UserDto userDto= this.userToUserDtoConverter.convert(this.userService.saveUser(hogwartsUser));
        return new Result(true, StatusCode.SUCCESS, "Create User Successfull", userDto);
    }

    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable Integer userId,
                             @RequestBody HogwartsUser hogwartsUser){
        UserDto userDto=  this.userToUserDtoConverter.convert(this.userService.updateUser(userId, hogwartsUser));
        return new Result(true, StatusCode.SUCCESS, "Update User Success", userDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete User Success", null);
    }
}

