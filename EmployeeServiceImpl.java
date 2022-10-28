import java.util.Scanner;


public class EmployeeServiceImpl implements EmployeeService{
	public static Scanner scanner;
	EmployeeDaoImpl employeeDao;
	
	public EmployeeServiceImpl(Scanner scan) {
		scanner=scan;
		employeeDao=new EmployeeDaoImpl();
		employeeDao.connectionDb();
	}
	
    public void createEmp() {
    	employeeDao.connectionDb();
    	System.out.println("Enter employee id");
    	int empId=scanner.nextInt();
    	System.out.println("Enter employee first name");
    	String firstName=scanner.next();
    	System.out.println("Enter employee last name");
    	String lastName=scanner.next();
    	System.out.println("Enter employee salary");
    	Float salary=scanner.nextFloat();
    	System.out.println("Enter employee address Id");
    	int addressId=scanner.nextInt();
    	employeeDao.createEmpRecord(empId,firstName,lastName,salary,addressId);
    }
    
    public void selectEmp() {
    	System.out.println("select choice:");
    	System.out.println("1.select all records");
    	System.out.println("2. select single emp record");
    	int choice=scanner.nextInt();
		switch(choice) {
		case 1:
			employeeDao.selectEmpRecords();
			break;
		case 2:
			System.out.println("Enter employee id to display");
			int empId=scanner.nextInt();
			employeeDao.selectEmp(empId);
			break;
		default:
			System.out.println("Invalid choice");
		}
    
    }
    
    public void updateEmp() {
    	
    	System.out.println("Enter employee id to update");
    	int empId=scanner.nextInt();
    	employeeDao.selectEmp(empId);
    	System.out.println("select what you want update");
    	System.out.println("1. update first name");
    	System.out.println("2. update last name");
    	System.out.println("3. update salary");
    	System.out.println("4. update address id");
    	int choice=scanner.nextInt();
    	System.out.println("please enter new data");
    	String newData=scanner.next();
    	if(choice==4) {
    		if(!employeeDao.addressExits(Integer.parseInt(newData))) {
    		System.out.println("please enter address to update");
    		String address=scanner.next();
    		employeeDao.insertAddressRecord(Integer.parseInt(newData),address);
    		}
    	}
    	
    	
    	employeeDao.updateEmpRecord(empId,choice,newData);
    	
    	
    }
    
    public void deleteEmp() {
    	System.out.println("Enter employee id to delete");
    	int empId=scanner.nextInt();
    	employeeDao.deleteEmpRecord(empId);
    }

}
