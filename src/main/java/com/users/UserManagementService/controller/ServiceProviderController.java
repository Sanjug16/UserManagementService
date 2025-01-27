package com.users.UserManagementService.controller;

import com.users.UserManagementService.model.ServiceProvider;
import com.users.UserManagementService.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-providers")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping
    public ResponseEntity<ServiceProvider> addServiceProvider(@RequestBody ServiceProvider serviceProvider) {
        return ResponseEntity.ok(serviceProviderService.addServiceProvider(serviceProvider));
    }

    @GetMapping
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        return ResponseEntity.ok(serviceProviderService.getAllServiceProviders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceProvider> getServiceProviderById(@PathVariable Long id) {
        return serviceProviderService.getServiceProviderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceProvider> updateServiceProvider(@PathVariable Long id, @RequestBody ServiceProvider serviceProvider) {
        serviceProvider.setProviderId(id);
        return ResponseEntity.ok(serviceProviderService.updateServiceProvider(serviceProvider));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable Long id) {
        serviceProviderService.deleteServiceProvider(id);
        return ResponseEntity.noContent().build();
    }
}
