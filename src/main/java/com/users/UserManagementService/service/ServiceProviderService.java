package com.users.UserManagementService.service;


import com.users.UserManagementService.Repository.ServiceProviderRepository;
import com.users.UserManagementService.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProviderService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider addServiceProvider(ServiceProvider serviceProvider) {
        return serviceProviderRepository.save(serviceProvider);
    }

    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    public Optional<ServiceProvider> getServiceProviderById(Long id) {
        return serviceProviderRepository.findById(id);
    }

    public void deleteServiceProvider(Long id) {
        serviceProviderRepository.deleteById(id);
    }

    public ServiceProvider updateServiceProvider(ServiceProvider serviceProvider) {
        return serviceProviderRepository.save(serviceProvider);
    }
}