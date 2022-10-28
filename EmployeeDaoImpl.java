import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class EmployeeDaoImpl implements EmployeeDao{
	public static Connection connection=null;
	public ResultSet resultSet;
	public void connectionDb() {
		try {
					
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","2323");
			if(connection!=null) {
				System.out.println("connection established succesfully");	
			}
			else {
				System.out.println("Failed  to connect");
			}
		}
		catch(Exception e) {
			System.out.println(e);
	    }
	}
	
	public void createEmpRecord(int empId,String firstName,String lastName,float salary,int addressId) {
		try {
			
			String query="insert into employee(emp_id,first_name,last_name,salary,address_id) values(?,?,?,?,?)";
	        PreparedStatement preparedStatement=connection.prepareStatement(query);
	        preparedStatement.setInt(1,empId);
			preparedStatement.setString(2,firstName);
			preparedStatement.setString(3,lastName);
			preparedStatement.setFloat(4,salary);
			preparedStatement.setInt(5,addressId);
			

			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				System.out.println("Record inserted sucessfully!!");
			}
			
			
	        
			}
			catch(Exception e) {
				System.out.println(e);
			}
		
	}
	
	public void selectEmpRecords() {
		try {
			String s="SELECT emp.emp_id, emp.first_Name,emp.last_name,emp.salary,emp.address_id,ad.address "
					+ "FROM employee emp LEFT JOIN address ad ON emp.address_id =ad.ad_id";
				
			Statement statement=connection.createStatement();
			resultSet=statement.executeQuery(s);
			while(resultSet.next()) {
				
			System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6)+" ");
			} 
		  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public void updateEmpRecord(int empId,int choice,String newData) {
		try {
			PreparedStatement preparedStatement;
			String str="update employee set ";
			int rows;
			switch(choice) {
			case 1:
				
				str=str+"first_name =? where emp_id = "+empId;
				preparedStatement=connection.prepareStatement(str);
				preparedStatement.setString(1,newData);
				rows=preparedStatement.executeUpdate();
			    if(rows>0) {
			    	System.out.println("first name updated successfully!!");
			    }
			    else {
			    	System.out.println("something wrong!!");
			    }
			   break;
			case 2:
				str=str+"last_name =? where emp_id = "+empId;
				preparedStatement=connection.prepareStatement(str);
				preparedStatement.setString(1,newData);
				rows=preparedStatement.executeUpdate();
			    if(rows>0) {
			    	System.out.println("last name updated successfully!!");
			    }
			    else {
			    	System.out.println("something wrong!!");
			    }
			    break;
			case 3:
				str=str+"salary =? where emp_id = "+empId;
				preparedStatement=connection.prepareStatement(str);
				preparedStatement.setFloat(1, Float.parseFloat(newData));
				rows=preparedStatement.executeUpdate();
			    if(rows>0) {
			    	System.out.println("salary updated successfully!!");
			    }
			    else {
			    	System.out.println("something went wrong!!");
			    }
			    break;
			case 4:
				str=str+"address_id =? where emp_id = "+empId;
				preparedStatement=connection.prepareStatement(str);
				preparedStatement.setFloat(1, Integer.parseInt(newData));
				rows=preparedStatement.executeUpdate();
			    if(rows>0) {
			    	System.out.println("address updated successfully!!");
			    }
			    else {
			    	System.out.println("something wrong!!");
			    }
			    break;
			 default:
				 break;
			}
					
		}  
		catch(Exception e) {
			System.out.println(e);
		}
		 
	 }
	  
	public void deleteEmpRecord(int empId) {
		try {
			String sql="delete from employee where emp_id= "+empId;
			PreparedStatement preparedstatement=connection.prepareStatement(sql);
		    int rows=preparedstatement.executeUpdate();
		    if(rows>0) {
		    	System.out.println("record deleted successfully!!");
		    }
		    else {
		    	System.out.println("something went wrong!!");
		    }
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	 }
	public void selectEmp(int empId) {
		try {
			String s="SELECT emp.emp_id, emp.first_Name,emp.last_name,emp.salary,emp.address_id,ad.address "
					+ "FROM employee emp LEFT JOIN address ad ON emp.address_id =ad.ad_id where emp_id="+empId;
		    Statement statement=connection.createStatement();
			resultSet=statement.executeQuery(s);
				
			if(resultSet.next()) {
				System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5)+" "+resultSet.getString(6)+" ");
				
			}
			else {
				System.out.println("record not found");	
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
			
	}
	public boolean insertAddressRecord(int empId,String address) {
		try {
			String query="insert into address(ad_id,address) values(?,?)";
	        PreparedStatement preparedStatement=connection.prepareStatement(query);
	        preparedStatement.setInt(1,empId);
			preparedStatement.setString(2,address);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) 
				return true;
		}
		catch(Exception e) {
			System.out.println(e);
		}
	return false;
	}
	public boolean addressExits(int addressId) {
		try {
			String s="select * from address where ad_id="+addressId;
		    Statement statement=connection.createStatement();
			resultSet=statement.executeQuery(s);
				
			if(resultSet.next()) {
				return true;
			}
				
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
		
	}
}
