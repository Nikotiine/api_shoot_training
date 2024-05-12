package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.UserEditDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserProfileDto;
import fr.nicolas.godin.shoot_training_api.api.service.UserService;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "API_User",description = "User Controller")
@RequestMapping(value = "/api/user",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;

    @PostMapping(value ="edit")
    @ResponseBody

    public UserProfileDto editProfile(@RequestBody UserEditDto userEditDto) {

            User user = this.userService.update(userEditDto);
            return this.modelMapper.map(user, UserProfileDto.class);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "all")
    @ResponseBody
    public List<UserProfileDto> allUsers() {

        return this.userService.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @PostMapping(value ="admin/edit-role")
    public UserProfileDto editUserRole(@Valid @RequestBody UserProfileDto user) {

        return this.userService.updateUserRole(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @DeleteMapping(value ="admin/disable")
    public  List<UserProfileDto> disableUser(@RequestParam(name = "id") int id) {
        return this.userService.disableUser(id);
    }
}
