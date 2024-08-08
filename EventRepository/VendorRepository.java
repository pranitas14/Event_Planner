package com.example.Event.Management.EventRepository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Event.Management.Entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}

