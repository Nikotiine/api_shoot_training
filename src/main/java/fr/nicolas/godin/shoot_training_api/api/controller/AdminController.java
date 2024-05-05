package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.LastEntriesDto;
import fr.nicolas.godin.shoot_training_api.api.dto.TotalCountDto;
import fr.nicolas.godin.shoot_training_api.api.service.AmmunitionService;
import fr.nicolas.godin.shoot_training_api.api.service.UserService;
import fr.nicolas.godin.shoot_training_api.api.service.optics.OpticsService;
import fr.nicolas.godin.shoot_training_api.api.service.weapon.WeaponService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "API_Admin",description = "Admin Controller")
@RequestMapping(value = "/api/admin",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminController {
    private UserService userService;
    private OpticsService opticsService;
    private AmmunitionService ammunitionService;
    private WeaponService weaponService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "last/entries")
    @ResponseBody
    public LastEntriesDto getLastEntries() {
        return new LastEntriesDto(
                this.userService.getLastEntry(),
                this.weaponService.getLastEntry(),
                this.opticsService.getLastEntry(),
                this.ammunitionService.getLastEntry()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "total/counts")
    @ResponseBody
    public TotalCountDto getTotalCount() {
        return new TotalCountDto(
                this.userService.countTotalEntry(),
                this.weaponService.countTotalEntry(),
                this.opticsService.countTotalEntry(),
                this.ammunitionService.countTotalEntry()
        );
    }
}
