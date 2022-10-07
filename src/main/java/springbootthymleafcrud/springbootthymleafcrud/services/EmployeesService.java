package springbootthymleafcrud.springbootthymleafcrud.services;

import org.springframework.data.domain.Page;
import springbootthymleafcrud.springbootthymleafcrud.model.Employee1;

import java.util.List;

public interface EmployeesService {

    //for get all employee
    List<Employee1> getAllEmployee();

    //for add employee
    void saveEmployee(Employee1 employee);

    // for update
    Employee1 getEmployeeById(long id);

    //for delete
    void deleteEmployeeById(long id);

    //for pagination
    Page<Employee1> findPaginated(int pagNo, int pageSize,String sortField,String sortDirection);

}
