package springbootthymleafcrud.springbootthymleafcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springbootthymleafcrud.springbootthymleafcrud.model.Employee1;
import springbootthymleafcrud.springbootthymleafcrud.repository.EmployeeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImpl implements EmployeesService {

    @Autowired
   private EmployeeRepo employeeRepo;



    @Override
    public List<Employee1> getAllEmployee() {

       return employeeRepo.findAll();

    }

    @Override
    public void saveEmployee(Employee1 employee) {
        this.employeeRepo.save(employee);

    }

    @Override
    public Employee1 getEmployeeById(long id) {
        Optional<Employee1> optional = employeeRepo.findById(id);

        Employee1 employee= null;

        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException("Employee not found for id ::"+id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {

        this.employeeRepo.deleteById(id);
    }

    @Override
    public Page<Employee1> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {

        Sort sort =sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?  Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
        return this.employeeRepo.findAll(pageable);
    }
}
