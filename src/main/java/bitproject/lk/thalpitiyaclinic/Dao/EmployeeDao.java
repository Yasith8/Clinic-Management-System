package bitproject.lk.thalpitiyaclinic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bitproject.lk.thalpitiyaclinic.Entity.EmployeeEntity;
//import java.util.List;
import java.util.List;


/* 
 * EmployeeDao extended from jparepository
 * EmployeeDao inherit all the methods provides by  JPARepository for CRUD operations on EmployeeEntity
 * Integer spesify the type of primary key in EmployeeEntity is Integer
 */
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Integer> {

  

    // Query
    /*
     * 1. Native Query
     * native are not included in JPA
     * some native queries are ot available in JPA
     * 
     * 2.JPA Query --default
     */


}
