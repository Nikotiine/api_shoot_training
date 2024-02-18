package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.AdminDashboardDataInformation;
import fr.nicolas.godin.shoot_training_api.api.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Admin",description = "Admin Controller")
@RequestMapping(value = "/api/admin",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    @GetMapping("dashboard")
    public AdminDashboardDataInformation getDataForDashboard(){

        return this.adminService.findDataForDashboard();

    }
}
