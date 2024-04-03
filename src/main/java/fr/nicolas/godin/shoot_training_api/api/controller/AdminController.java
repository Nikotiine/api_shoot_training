package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.service.AdminService;
import fr.nicolas.godin.shoot_training_api.database.UserRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Admin",description = "Admin Controller")
@RequestMapping(value = "/api/admin",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private AdminService adminService;
    @GetMapping("dashboard")
    public AdminDashboardDataInformation getDataForDashboard() {

        return this.adminService.findDataForDashboard();
    }


    @GetMapping("user/all")
    public List<UserProfileDto> getAllUsers() {

        return this.adminService.findAllUsers();
    }

    @PostMapping("user/edit-role")
    public List<UserProfileDto> editUserRole(@Valid @RequestBody UserProfileDto user) {
        return this.adminService.editUserRole(user);
    }

    @DeleteMapping("user/disable")
    public  List<UserProfileDto> disableUser(@RequestParam(name = "id") int id) {
        return this.adminService.disableUser(id);
    }

    @GetMapping("optics/all")
    @ResponseBody
    public List<OpticsDto> getAllOptics(){
        return this.adminService.findAllOptics();
    }

    @GetMapping("weapons/all")
    @ResponseBody
    public List<WeaponDto> getAllWeapons(){
        return this.adminService.findAllWeapons();
    }

    @GetMapping("ammunition/all")
    @ResponseBody
    public List<AmmunitionDto> getAllAmmunition() {
        return this.adminService.findAllAmmunition();
    }
}
