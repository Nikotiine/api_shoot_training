package fr.nicolas.godin.shoot_training_api.api.service;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
