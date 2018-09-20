package edu.eci.pdsw.validator;

import static org.quicktheories.generators.SourceDSL.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

public class EmployeeGenerator {

	public static Gen<Employee> Employees(){
		return personsId().zip(salary(), securityType(),(id,salary,securityType) 
				-> new Employee(id,salary,securityType));			
	}
	
	public static Gen<Integer> personsId() {
		return integers().between(990, 100000);
	}
	
	public static Gen<Long> salary(){
		return longs().between(50, 50000);
	}
	
	public static Gen<SocialSecurityType> securityType(){
		return Generate.enumValues(SocialSecurityType.class);
	}
	
}
