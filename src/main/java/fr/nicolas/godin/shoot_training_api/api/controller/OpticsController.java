package fr.nicolas.godin.shoot_training_api.api.controller;

import fr.nicolas.godin.shoot_training_api.api.dto.*;
import fr.nicolas.godin.shoot_training_api.api.enums.FactoryType;
import fr.nicolas.godin.shoot_training_api.api.service.FactoryService;
import fr.nicolas.godin.shoot_training_api.api.service.optics.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Optics",description = "Optics Controller")
@RequestMapping(value = "/api/optics",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OpticsController {

    private OpticsService opticsService;
    private OpticsBodyDiameterService opticsBodyDiameterService;
    private OpticsOutletDiameterService opticsOutletDiameterService;
    private FactoryService factoryService;
    private OpticsUnitService opticsUnitService;
    private OpticsFocalPlaneService opticsFocalPlaneService;

    @GetMapping("all")
    @ResponseBody
    public List<OpticsDto> getAllOptics() {

        return this.opticsService.getAll();
    }
    @GetMapping("actives")
    @ResponseBody
    public List<OpticsDto> getAllActiveOptics() {

        return this.opticsService.getAllActive();
    }


    @GetMapping(value = "data-collection")
    @ResponseBody
    public OpticsDataCollection getOpticsDataCollection() {

        return new OpticsDataCollection(
                this.factoryService.getAllByType(FactoryType.OPTICS),
                this.opticsBodyDiameterService.getAll(),
                this.opticsUnitService.getAll(),
                this.opticsFocalPlaneService.getAll(),
                this.opticsOutletDiameterService.getAll()
        );
    }

    @PostMapping("save")
    @ResponseBody
    public OpticsDto newOptics(@Valid @RequestBody OpticsCreateDto newOptics) {

        return this.opticsService.create(newOptics);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("delete")
    @ResponseBody
    public List<OpticsDto> disableOptics(@RequestParam(name = "id") int id) {

        return this.opticsService.delete(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("edit")
    @ResponseBody
    public OpticsDto editOptics(@Valid @RequestBody OpticsDto opticsDto) {

        return this.opticsService.update(opticsDto);
    }
 }
