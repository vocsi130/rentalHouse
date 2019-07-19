package edu.mum.rentalHouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import edu.mum.rentalHouse.serviceImpl.ResidenceServiceImpl;
import edu.mum.rentalHouse.serviceImpl.TenantServiceImpl;
import edu.mum.rentalHouse.model.*;
@RestController
public class RestTenantController {

    @Autowired
    private TenantServiceImpl tenantService;

    @Autowired
    private ResidenceServiceImpl residenceService;

    @GetMapping(value = "/allTenant/")
    public List<Tenant> getAll() {
        return tenantService.getAllTenant();
    }

    
    @GetMapping(value = "/tenant/{id}")
    public Tenant get(@PathVariable long id) {
        return tenantService.getTenant(id);
    }

    @PostMapping(value = "/tenant/")
    public Long save(@RequestBody Tenant tenant) {
       tenantService.createTenant(tenant);
       return tenant.getId();
    }

    @PostMapping(value = "/tenant/redirect/")
    public RedirectView post(@RequestBody Tenant tenant) {
       tenantService.createTenant(tenant);
        return new RedirectView("/tenant/" + tenant.getId());
    }

    @PutMapping(value = "/tenant/{id}")
    public void put(@PathVariable long id, @RequestBody Tenant tenant) {
        if (id != tenant.getId()) {
            throw new IllegalArgumentException();
        }
        tenantService.updateTenant(tenant);
    }

    @DeleteMapping("/tenant/{id}")
    public void delete(@PathVariable long id) {
    	tenantService.delete(id);
    }
    
    
    @GetMapping(value = "/allApartements/")
    public List<Residence> getAllApartement() {
        return residenceService.getApartments();
    }

    @GetMapping(value = "/allHouses/")
    public List<Residence> getAllHouses() {
        return residenceService.getHouses();
    }

}
