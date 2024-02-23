package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.tools.ModelMapperTool;
import fr.nicolas.godin.shoot_training_api.database.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private UserService userService;
    private WeaponService weaponService;
    private OpticsService opticsService;
    private AmmunitionService ammunitionService;

    public AdminDashboardDataInformation findDataForDashboard() {
        long totalUser = this.userService.countTotalEntry();
        UserProfileDto lastUser = this.userService.findLastEntry();

        long totalWeapon = this.weaponService.countTotalEntry();
        WeaponDto lastWeapon = this.weaponService.findLastEntry();

        long totalOptics = this.opticsService.countTotalEntry();
        OpticsDto lastOptic = this.opticsService.findLastEntry();

        long totalAmmo = this.ammunitionService.countTotalEntry();
        AmmunitionDto lastAmmunition = this.ammunitionService.findLastEntry();
        return new AdminDashboardDataInformation(totalUser,lastUser,totalWeapon,lastWeapon,totalOptics,lastOptic,totalAmmo,lastAmmunition);
    }

    public List<UserProfileDto> findAllUsers() {

        return this.userService.getAll();
    }

    public List<UserProfileDto> editUserRole(UserProfileDto user) {
        this.userService.updateUserRole(user);
        return this.findAllUsers();
    }

    public List<UserProfileDto> disableUser(UserProfileDto user) {
        this.userService.disableUser(user);
        return this.findAllUsers();
    }

    public List<OpticsDto> findAllOptics() {
        return this.opticsService.getAll();
    }

    public List<WeaponDto> findAllWeapons() {
        return this.weaponService.getAll();
    }

    public List<AmmunitionDto> findAllAmmunition() {
        return this.ammunitionService.getAll();
    }
}
