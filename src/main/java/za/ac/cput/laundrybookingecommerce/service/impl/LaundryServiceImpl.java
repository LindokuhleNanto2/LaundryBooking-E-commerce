/**
 * LaundryServiceImpl.java
 * Author: Snalo (230541844)
 * Date: 25 July 2026
 */
package za.ac.cput.laundrybookingecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laundrybookingecommerce.domain.LaundryService;
import za.ac.cput.laundrybookingecommerce.repository.ILaundryServiceRepository;
import za.ac.cput.laundrybookingecommerce.service.ILaundryService;

import java.util.List;

@Service
public class LaundryServiceImpl implements ILaundryService {

    private final ILaundryServiceRepository repository;

    @Autowired
    public LaundryServiceImpl(ILaundryServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public LaundryService create(LaundryService laundryService) {
        return repository.save(laundryService);
    }

    @Override
    public LaundryService read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public LaundryService update(LaundryService laundryService) {
        if (repository.existsById(laundryService.getId())) {
            return repository.save(laundryService);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<LaundryService> getAll() {
        return repository.findAll();
    }

    @Override
    public List<LaundryService> getServicesByName(String name) {
        return repository.findByServiceNameContainingIgnoreCase(name);
    }
}

