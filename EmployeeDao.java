
public interface EmployeeDao {
	void connectionDb();
	void createEmpRecord(int empId,String firstName,String lastName,float salary,int addressId);
	void selectEmpRecords();
	void selectEmp(int empId);
	void updateEmpRecord(int empId,int choice,String newData);
	void deleteEmpRecord(int empId);
	boolean addressExits(int addressId);
	boolean insertAddressRecord(int empId,String address);
	

}
