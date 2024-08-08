package com.example.Event.Management.Service;

import com.example.Event.Management.Entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    Vendor saveVendor(Vendor vendor);
    List<Vendor> getAllVendors();
    Optional<Vendor> getVendorById(Long id);
    void deleteVendor(Long id);
}
