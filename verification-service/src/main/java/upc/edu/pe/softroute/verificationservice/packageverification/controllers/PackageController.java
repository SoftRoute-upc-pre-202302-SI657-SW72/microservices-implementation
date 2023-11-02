package upc.edu.pe.softroute.verificationservice.packageverification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.softroute.verificationservice.packageverification.entities.Package;
import upc.edu.pe.softroute.verificationservice.packageverification.services.PackageService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/validation/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping("/{id}")
    public Optional<Package> getPackageById(@PathVariable Long id) {
        return packageService.getPackageById(id);
    }

    @PostMapping("/{id}/update-status")
    public Package updatePackageStatus(@PathVariable Long id) {
        return packageService.updatePackageStatus(id, "delivered");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPackage(@RequestBody Package request) {
        Optional<Package> existingPackage = packageService.getPackageById(request.getId());
        if(existingPackage.isPresent()) {
            return new ResponseEntity<>("Package with given ID already exists", HttpStatus.CONFLICT);
        }

        Package newPackage = new Package(request.getId(), request.getStatus());
        Package savedPackage = packageService.registerPackage(newPackage);

        return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
    }
}
