package bitproject.lk.thalpitiyaclinic.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bitproject.lk.thalpitiyaclinic.Dao.EmployeeDao;

import bitproject.lk.thalpitiyaclinic.Entity.EmployeeEntity;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


/*
    * implemented mapping to available for use  
    * add implemented mapping to servelet container for use
    * fundemental component for build REST full api  (representational state transfer)
    * restcontroller tell spingboot to class/methods responsible to handle incoming http requests and produce appropiate http response(using json,xml format)
*/
@RestController
//define mapping to employee UI(/employee)
public class EmployeeController {
    
    /* 
     * AutoWired used for automatic dependency injection
     * inject employeeDao Instance into dao variable
     * the method can use dao for save,retrive,maipulate employee data
     */
    @Autowired 
    private EmployeeDao dao;

   


    //request employee ui
    @RequestMapping(value="/employee")
    public ModelAndView employeeUI(){
        ModelAndView empView=new ModelAndView();
        empView.setViewName("employee.html");
        return empView;
    }


    /* 
     * define mapping for get all employee data from employee database
     * produce -> data return format(json,xml)
     * value or path -> specify url pattern to which the method will be mapped
     * List<Employee> -> return the list of Employee object
     * 
     * also you can use
     * @requestMapping(value="/employee/alldata",produces='application.json',method=RequestMethod.GET)
     */
    @GetMapping(value = "/employee/alldata", produces ="application/json" ) 
    public List<EmployeeEntity> allEmployeeData() {
        return dao.findAll();
    }


    //post mapping
    //used for add new employee to the db
    @PostMapping(value = "/employee")
    public String addEmployee(@RequestBody EmployeeEntity employee){
        //Authentication and Autherization

        //Check Duplicate 

        //Check Duplicate of NIC
        EmployeeEntity extEmployeeByNic=dao.getByNic(employee.getNic());

        if(extEmployeeByNic!=null){
            return "Save not Completed : given NIC - "+employee.getNic()+" Already Exist...!";
        }
        
        //check duplicates of Email
        EmployeeEntity extEmployeeByEmail=dao.getByEmail(employee.getEmail());
        
        if(extEmployeeByEmail!=null){
            return "Save not Completed : given Email - "+employee.getEmail()+" Already Exist...!";
        }

        try {
            //set AutoGenarated Value
            String nextNumber=dao.getNextEmployeeNumber();
            if(nextNumber==null){
                employee.setEmpid("003");
            }else{
                employee.setEmpid(nextNumber);
            }

            dao.save(employee);
            return "OK";
        } catch (Exception e) {
            return "Save not Completed : "+e.getMessage();
        }

    }

       

  
   





    
}
