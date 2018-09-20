package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {

	/**
	 * {@inheritDoc}}
	 */
	public Optional<ErrorType> validate(Employee employee) {
		
		if(!( 1000 <=employee.getPersonId() && employee.getPersonId()<= 100000 ) ){
			return	Optional.of(ErrorType.INVALID_ID);
		}
		if( !(100 <= employee.getSalary() && employee.getSalary() >= 50.000)) {
			return Optional.of(ErrorType.INVALID_SALARY);
		}
		if ( employee.getSocialSecurityType() == SocialSecurityType.SISBEN)  {
			if( employee.getSalary() >= 1500) {
				return Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
			}
			
		}
		if( employee.getSocialSecurityType() == SocialSecurityType.EPS)  {
			if((1500 > employee.getSalary() || employee.getSalary() >= 10000)  ) {
				return Optional.of(ErrorType.INVALID_EPS_AFFILIATION);
			}
			
		}
		if(employee.getSocialSecurityType() == SocialSecurityType.PREPAID) {
			if( 10000 > employee.getSalary()) {
				return Optional.of(ErrorType.IVALID_PREPAGADA_AFILIATION);
			}
			
		}
		
		return Optional.empty();
	}
}
