package upc.edu.pe.softroute.verificationservice.packageverification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.softroute.verificationservice.packageverification.entities.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
}
