package edu.mum.rentalHouse.service;

import edu.mum.rentalHouse.model.Tenant;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TenantService {
    public void createTenant(Tenant tenant);
    public void updateTenant(Tenant tenant);
    public Tenant getTenant(Long id);
    public void deleteTenant(Tenant tenant);
    public List<Tenant> getAllTenant();
	public void delete(Long tenantId);
	boolean createPDF(List<Tenant> tenants, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

}
