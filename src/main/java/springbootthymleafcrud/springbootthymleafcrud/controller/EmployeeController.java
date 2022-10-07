package springbootthymleafcrud.springbootthymleafcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbootthymleafcrud.springbootthymleafcrud.model.Employee1;
import springbootthymleafcrud.springbootthymleafcrud.services.EmployeesService;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeesService employeeservice;

    @GetMapping("/")
    public String viewHomePage(Model model){

        model.addAttribute("listEmployees", employeeservice.getAllEmployee());

        return findPaginated(1,"firstName", "asc",model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){

        //create model attribute to bind form data
        Employee1 employee =new Employee1();
        model.addAttribute("employee",employee);
        return "new_employee";

    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee1 employee){

        //save employee in db
        employeeservice.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,Model model) {
        //get Employee from the services

        Employee1 employee = employeeservice.getEmployeeById(id);

        // set employee as a model attribute to pre populate the form
        model.addAttribute("employee",employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){

        this.employeeservice.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (name = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,

                                Model model){

        int pageSize =5;
        Page<Employee1> page= employeeservice.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Employee1> listEmployee = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "dsc" : "asc");


        model.addAttribute("listEmployees",listEmployee);

        return "index";
    }

}
