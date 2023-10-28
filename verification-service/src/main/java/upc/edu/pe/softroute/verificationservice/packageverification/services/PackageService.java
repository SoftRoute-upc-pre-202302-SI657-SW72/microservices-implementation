package upc.edu.pe.softroute.verificationservice.packageverification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.softroute.verificationservice.packageverification.entities.Package;
import upc.edu.pe.softroute.verificationservice.packageverification.repository.PackageRepository;

import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public Optional<Package> getPackageById(Long id) {
        return packageRepository.findById(id);
    }

    public Package updatePackageStatus(Long id, String status) {
        Package foundPackage = packageRepository.findById(id).orElse(null);

        if (foundPackage != null) {
            foundPackage.setStatus(status);
            packageRepository.save(foundPackage);
        }

        return foundPackage;
    }

    public Package registerPackage(Package newPackage) {
        return packageRepository.save(newPackage);
    }
}
