package edu.mum.rentalHouse.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.rentalHouse.model.Apartment;
import edu.mum.rentalHouse.model.Contract;
import edu.mum.rentalHouse.model.House;
import edu.mum.rentalHouse.model.Payment;
import edu.mum.rentalHouse.model.Residence;
import edu.mum.rentalHouse.model.Task;
import edu.mum.rentalHouse.model.Tenant;
import edu.mum.rentalHouse.model.User;
import edu.mum.rentalHouse.serviceImpl.ContractServiceImpl;
import edu.mum.rentalHouse.serviceImpl.PaymentServiceImpl;
import edu.mum.rentalHouse.serviceImpl.ResidenceServiceImpl;
import edu.mum.rentalHouse.serviceImpl.RoleServiceImpl;
import edu.mum.rentalHouse.serviceImpl.TaskServiceImpl;
import edu.mum.rentalHouse.serviceImpl.TenantServiceImpl;
import edu.mum.rentalHouse.serviceImpl.UserServiceImpl;

@Controller
public class RentalController {

@Autowired
private ContractServiceImpl contractService;

@Autowired
private PaymentServiceImpl paymentService;

@Autowired
private ServletContext context;

@Autowired
private TenantServiceImpl tenantService;

@Autowired
private ResidenceServiceImpl residenceService;


@Autowired
private RoleServiceImpl roleService;


@Autowired
private TaskServiceImpl taskService;

@Autowired
private UserServiceImpl userService;


@GetMapping(value = "/")
public String getAll(Model model) {
    model.addAttribute("contracts", contractService.getAll());
    return "header";
}
@RequestMapping("/login-error")
public String loginError(Model model) {
    System.out.println("loginError.....");
    model.addAttribute("loginError", true);
    return "login";
}


@GetMapping(value="/login")
public String loginPage(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

	  String errorMessge = null;
    if(error != null) {
        errorMessge = "Username or Password is incorrect !!";
    }
    if(logout != null) {
        errorMessge = "You have been successfully logged out !!";
    }
    model.addAttribute("errorMessge", errorMessge);
    return "loginForm";
}

@GetMapping(value="/logout")
public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null){   
       new SecurityContextLogoutHandler().logout(request, response, auth);
  }
    return "redirect:/login?logout=true";
}

@GetMapping("/profile")
public String showProfilePage(Model model, Principal principale) {
	
	String email=principale.getName();
	User user=userService.findByEmail(email);
	model.addAttribute("tasks",taskService.findUserTask(user));
	return "profile";
}

@GetMapping("/users")
public String listUsers(Model model, @RequestParam(defaultValue="") String name) {
	model.addAttribute("users", userService.findByName(name));
	return "listUsers";

}
@GetMapping("/register")
public String registerForm(Model model) {
 	model.addAttribute("user", new User());
	return "registerForm";
}


@PostMapping("/register")
public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
if(bindingResult.hasErrors()) {
	return "registerForm";	
}
if(userService.isUserPresent(user.getEmail())) {
	model.addAttribute("exist",true);
	return "registerForm";	
	}
userService.createUser(user);
return "loginForm";
}

@GetMapping("/addTask")
public String taskForm(String email, Model model, HttpSession session) {
	session.setAttribute("email", email);
	model.addAttribute("task",new Task());
	return "taskForm";
}

@PostMapping("/addTask")
public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
	if(bindingResult.hasErrors()) {
		return "taskForm";	
	}
	String email=(String) session.getAttribute("email");
	taskService.addTask(task,userService.findByEmail(email));
return "redirect:/users";
}
// add contract //
@GetMapping("/addContract")
public String addContract(@ModelAttribute("contract") Contract contract, Model model) {
   	model.addAttribute("tenants", tenantService.getAllTenant());
   	model.addAttribute("residences", residenceService.getAll());
      	return "addContracts";
}

@PostMapping(value = "/addContract")
public String saveContract(@Valid Contract contract, BindingResult result,
        RedirectAttributes attributes) {
	    if (result.hasErrors()) {
        attributes.addFlashAttribute("org.springframework.validation.BindingResult.contract", result);
        attributes.addFlashAttribute("contract", contract);
        return "addContract";
    } else {
    	Long tenantId=contract.getTenant().getId();
    	Long residenceId=contract.getResidence().getId();
    	contractService.createContract(contract, tenantId, residenceId);
        return "redirect:/contracts";
    }
}

@GetMapping(value = "/contracts")
public String getAllContracts(Model model) {
	   	model.addAttribute("contracts", contractService.getAll());
    return "contractList";
}


@PostMapping(value = "/contracts/{id}")
public String updateContracts(@Valid Contract contract, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
        return "contractDetail";
    } else {
    	contractService.createContract(contract, contract.getTenant().getId(), contract.getResidence().getId());
        return "redirect:/contracts";
    }
}

@GetMapping(value = "/contracts/{id}")
public String getContracts(@PathVariable Long id, Model model) {
	model.addAttribute("contract", contractService.getContract(id));
    return "contractDetail";
}

@GetMapping(value="/contracts/delete/{id}")
public String deleteContract(@PathVariable Long id, Model model){		
	contractService.deleteContract(id);
	return "redirect:/contracts";
}	


@GetMapping("/contractActive")
public String getAllPaymentLate(Model model) {
    List<Contract> contracts = contractService.getContractInactive();
    model.addAttribute("contracts",contracts);
    return "contractActive";
}

// add Tenant
@GetMapping("/addTenant")
public String viewTenant(@ModelAttribute("tenant") Tenant tenant, Principal p) {
return "addTenant";
}
@PostMapping("/addTenant")
public String addTenant(@Valid Tenant tenant, BindingResult result, RedirectAttributes attributes) {

    if (result.hasErrors()) {
        attributes.addFlashAttribute("org.springframework.validation.BindingResult.tenant", result);
        attributes.addFlashAttribute("tenant", tenant);
        return "addTenant";
    } else {
         tenantService.createTenant(tenant);
        return "redirect:/tenants";
    }
}

@GetMapping("/tenants")
public String getAllTenants(Model model){
    model.addAttribute("tenants" , tenantService.getAllTenant());
    return "tenantList";
}


@PostMapping(value = "/tenants/{id}")
public String update(@Valid Tenant tenant, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
        return "tenantDetail";
    } else {
    	tenantService.createTenant(tenant);
        return "redirect:/tenants";
    }
}

@GetMapping(value = "/tenants/{id}")
public String get(@PathVariable Long id, Model model) {
	model.addAttribute("tenant", tenantService.getTenant(id));
    return "tenantDetail";

}

@GetMapping(value="/tenants/delete/{id}")
public String delete(@PathVariable Long id, Model model){		
	tenantService.delete(id);
	return "redirect:/tenants";
}	


//add Payment
@GetMapping("/addPayment")
public String viewPayment(@ModelAttribute("payment") Payment payment, Model model) {
    model.addAttribute("tenants" , tenantService.getAllTenant());
	return "addPayment";
}
@PostMapping("/addPayment")
public String savePayment(@Valid Payment payment, BindingResult result, RedirectAttributes attributes) {

    if (result.hasErrors()) {
        attributes.addFlashAttribute("org.springframework.validation.BindingResult.payment", result);
        attributes.addFlashAttribute("payment", payment);
        return "addPayment";
    } else {
         paymentService.addPayment(payment);
        return "redirect:/payments";
    }
}

@GetMapping("/payments")
public String getAllPayments(Model model) {
    List<Payment> p = paymentService.findAllPayment();
    model.addAttribute("payments",p );
    return "paymentList";
}


@GetMapping(value = "/payments/{id}")
public String getPayment(@PathVariable Long id, Model model) {
	model.addAttribute("payment", paymentService.get(id));
    return "paymentDetail";
}

@GetMapping(value="/payments/delete/{id}")
public String deletePayment(@PathVariable Long id, Model model){		
	paymentService.delete(id);
	return "redirect:/payments";
}	

// add House
@GetMapping("/addHouse")
public String addHouse(@ModelAttribute("house") House house) {
	return "addHouse";
}

@GetMapping(value = "/houses")
public String getAllHouses(Model model) {
	   	model.addAttribute("houses", residenceService.getHouses());
    return "houseList";
}

@PostMapping(value = "/addHouse")
public String saveHouse(@Valid House house, BindingResult result,
        RedirectAttributes attributes) {
	    if (result.hasErrors()) {
        attributes.addFlashAttribute("org.springframework.validation.BindingResult.house", result);
        attributes.addFlashAttribute("house", house);
        return "addHouse";
    } else {
    	residenceService.saveResidence(house);
        return "redirect:/houses";
}
}

@GetMapping(value = "/houses/{id}")
public String getHouse(@PathVariable Long id, Model model) {
	model.addAttribute("houses", residenceService.get(id));
    return "houseDetail";
}

@GetMapping(value="/houses/delete/{id}")
public String deleteHouse(@PathVariable Long id, Model model){		
	residenceService.deleteHouse(id);
	return "redirect:/houses";
}	


@GetMapping("/addApartment")
public String addApartment(@ModelAttribute("apartment") Apartment apartment) {
	return "addApartment";
}

@GetMapping(value = "/apartments")
public String getAllApartments(Model model) {
	   	model.addAttribute("apartments", residenceService.getApartments());
    return "apartmentList";
}

@PostMapping(value = "/addApartment")
public String saveApartment(@Valid Apartment apartment, BindingResult result,
        RedirectAttributes attributes) {
    if (result.hasErrors()) {
        attributes.addFlashAttribute("org.springframework.validation.BindingResult.apartment", result);
        attributes.addFlashAttribute("apartment", apartment);
        return "addApartment";
    } else {
    	
    	residenceService.saveResidence(apartment);
        return "redirect:/apartments";
}
	
}

@GetMapping(value = "/apartments/{id}")
public String getApartment(@PathVariable Long id, Model model) {
	model.addAttribute("apartments", residenceService.get(id));
    return "apartmentDetail";
}

@GetMapping(value="/apartments/delete/{id}")
public String deleteApartment(@PathVariable Long id, Model model){		
	residenceService.deleteApartment(id);
	return "redirect:/apartments";
}	


@GetMapping(value="/pdfTenant")
public void pdfTenant(HttpServletRequest request, HttpServletResponse response) {
		List<Tenant> tenants=tenantService.getAllTenant();
		boolean isFlag=tenantService.createPDF(tenants, context, request, response);
		if(isFlag) {
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"tenants"+".pdf");
			filedownload(fullPath,response, "tenants.pdf");
		}
	}




@GetMapping(value="/pdfHouse")
public void pdfHouse(HttpServletRequest request, HttpServletResponse response) {
		List<Residence> houses=residenceService.getHouses();
		List<House> house=new ArrayList<House>();
		for(Residence h:houses)house.add((House) h);
		boolean isFlag=residenceService.createPDFHouse(house, context, request, response);
		if(isFlag) {
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"houses"+".pdf");
			filedownload(fullPath,response, "houses.pdf");
		}
	}

@GetMapping(value="/pdfApartment")
public void createPDF(HttpServletRequest request, HttpServletResponse response) {
	List<Residence> apartments=residenceService.getApartments();
	List<Apartment> apartment=new ArrayList<Apartment>();
	for(Residence a:apartments)apartment.add((Apartment) a);

	boolean isFlag=residenceService.createPDFApartment(apartment, context, request, response);
		if(isFlag) {
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"apartments"+".pdf");
			filedownload(fullPath,response, "apartments.pdf");
		}
	}

private void filedownload(String fullPath, HttpServletResponse response, String filename) {
	File file=new File(fullPath);
	final int BUFFER_SIZE=4096;
	if(file.exists()) {
		try {
			FileInputStream inputStream=new FileInputStream(file);
			String mimeType=context.getMimeType(fullPath);
			response.setContentType(mimeType);
			response.setHeader("content-disposition","attachment; filename="+filename);
			OutputStream outputStream=response.getOutputStream();
			byte buffer[]= new byte[BUFFER_SIZE];
			int byteReads=-1;
			while((byteReads=inputStream.read(buffer))!=-1) {
				outputStream.write(buffer,0, byteReads);
			}
			inputStream.close();
			outputStream.close();
			file.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


}



