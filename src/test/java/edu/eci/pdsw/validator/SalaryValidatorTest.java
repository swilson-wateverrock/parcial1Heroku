package edu.eci.pdsw.validator;

import static org.junit.Assert.assertTrue;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Optional;

import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
		
		
		qt().forAll(EmployeeGenerator.Employees()).check(
				employee -> {

				Optional<ErrorType> opt;
				opt = validator.validate(employee);
				ErrorType result = opt.orElse(null);
				if(!( 1000 <=employee.getPersonId() && employee.getPersonId()<= 100000 ) ){
					return	result == ErrorType.INVALID_ID;
				}
				if( !(100 <= employee.getSalary() && employee.getSalary() >= 50.000)) {
					return result ==ErrorType.INVALID_SALARY;
				}
				if ( employee.getSocialSecurityType() == SocialSecurityType.SISBEN)  {
					if( employee.getSalary() >= 1500) {
					return result == ErrorType.INVALID_SISBEN_AFFILIATION;
					}
				}
				if( employee.getSocialSecurityType() == SocialSecurityType.EPS)  {
					if((1500 > employee.getSalary() || employee.getSalary() >= 10000)  ) {
						return result ==ErrorType.INVALID_EPS_AFFILIATION;
					}
				}
				if(employee.getSocialSecurityType() == SocialSecurityType.PREPAID) {
					if( 10000 > employee.getSalary()) {
						return result ==ErrorType.IVALID_PREPAGADA_AFILIATION;
					}
				}
				
				return result == null;
				
				 
				});
		

		
	}
}
