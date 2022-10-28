import java.util.Scanner;

public class EmployeeMain {
	
	public static  Scanner scanner=new Scanner(System.in);
//   EmployeeServiceImpl employeeService=new EmployeeServiceImpl(scanner);
	public static void main(String[] args) {
		EmployeeServiceImpl employeeService=new EmployeeServiceImpl(scanner);
		int choice=-1; 
		while(choice!=5) {
		
		System.out.println("1. create employee record");
   	    System.out.println("2. select employee records");
		System.out.println("3. update employee record");
		System.out.println("4. delete employee record");
		System.out.println("5. Exit");
		System.out.println("enter choice");
		choice=scanner.nextInt();
		
		
		switch(choice) {
		case 1:
           
			employeeService.createEmp();
			break;
		case 2:
			employeeService.selectEmp();
			break;
		case 3:
			employeeService.updateEmp();
			break;
		case 4:
			employeeService.deleteEmp();
			break;
		case 5:
			break;
		default:
			System.out.println("please select valid option");
		}
		}

	}

}
