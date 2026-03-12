package it.unical.inf.ea.app2.autowiring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
public class EmployeeBean {
    private String fullName;

    //@Autowired on properties
    @Autowired
    @Qualifier("humanResource")
    private DepartmentBean departmentBean;

    /*  @Autowired on constructors*/
//  @Autowired
//  public EmployeeBean(@Qualifier("humanResource") DepartmentBean departmentBean) {
//    this.departmentBean = departmentBean;
//  }


    /* @Autowired on property setters */
    //	@Autowired
    //  @Qualifier("humanResource")
    //	public void setDepartmentBean(DepartmentBean departmentBean) {
    //		this.departmentBean = departmentBean;
    //	}

}