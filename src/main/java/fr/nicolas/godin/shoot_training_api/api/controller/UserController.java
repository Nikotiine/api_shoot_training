package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.UserEditDto;
import fr.nicolas.godin.shoot_training_api.api.dto.UserProfileDto;
import fr.nicolas.godin.shoot_training_api.api.service.UserService;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User",description = "User Controller")
@RequestMapping(value = "/api/user",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;

    @PostMapping("edit")
    @ResponseBody
    public UserProfileDto edit(@RequestBody UserEditDto userEditDto) {

            User user = this.userService.update(userEditDto);
            return this.modelMapper.map(user, UserProfileDto.class);

    }
}
