package upc.edu.pe.softroute.verificationservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import upc.edu.pe.softroute.verificationservice.packageverification.controllers.PackageController;
import upc.edu.pe.softroute.verificationservice.packageverification.entities.Package;
import upc.edu.pe.softroute.verificationservice.packageverification.services.PackageService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PackageControllerTest {
    @InjectMocks
    private PackageController packageController;

    @Mock
    private PackageService packageService;

    @Test
    public void testRegisterPackage() {
        Package newPackage = new Package(1L, "travelling");

        when(packageService.registerPackage(any(Package.class))).thenReturn(newPackage);

        ResponseEntity<?> response = packageController.registerPackage(newPackage);

        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(newPackage, response.getBody());
    }

    @Test
    public void testUpdatePackageStatus() {
        Long packageId = 1L;
        String newStatus = "delivered";

        Package existingPackage = new Package(packageId, "travelling");
        Package updatedPackage = new Package(packageId, newStatus);

        when(packageService.getPackageById(packageId)).thenReturn(Optional.of(existingPackage));
        when(packageService.updatePackageStatus(packageId, newStatus)).thenReturn(updatedPackage);

        Package response = packageController.updatePackageStatus(packageId);

        Assertions.assertEquals(updatedPackage, response);
    }
}
