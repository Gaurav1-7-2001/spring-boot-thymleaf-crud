package springbootthymleafcrud.springbootthymleafcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootthymleafcrud.springbootthymleafcrud.model.Employee1;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee1,Long> {
}
