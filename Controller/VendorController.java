package com.example.Event.Management.Controller;

import com.example.Event.Management.Entity.Vendor;
import com.example.Event.Management.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // Create a new vendor
    @PostMapping
    public ResponseEntity<Vendor> saveVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorService.saveVendor(vendor);
        return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
    }

    // Get all vendors
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        if (vendors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    // Get a vendor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long id) {
        Optional<Vendor> vendor = vendorService.getVendorById(id);
        if (vendor.isPresent()) {
            return new ResponseEntity<>(vendor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update an existing vendor
    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("id") Long id, @RequestBody Vendor vendor) {
        Optional<Vendor> existingVendor = vendorService.getVendorById(id);
        if (existingVendor.isPresent()) {
            vendor.setId(id); // Ensure the ID is set for updating
            Vendor updatedVendor = vendorService.saveVendor(vendor);
            return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a vendor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") Long id) {
        if (vendorService.getVendorById(id).isPresent()) {
            vendorService.deleteVendor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
