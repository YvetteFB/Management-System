package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    public static ObservableList<EmployeeModel> searchEmployees() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employeedetails ";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_employees = DatabaseConnection.dbEmployeeExecuteQuery(selectStmt);

            //Send ResultSet to the getsalesList method and get sale object
            ObservableList<EmployeeModel> employeeList = getEmployeesList(rs_employees);

            //Return sales object
            return employeeList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<EmployeeModel> getEmployeesList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeModel> employeeList = FXCollections.observableArrayList();

        while (rs.next()) {
            EmployeeModel employee = new EmployeeModel();
            employee.setId(rs.getInt("ID"));
            employee.setName(rs.getString("NAME"));
            employee.setContact(rs.getInt("CONTACT"));
            employee.setPassword(rs.getString("PASSWORD"));

            //add sales to the observable list
            employeeList.add(employee);
        }
        //observable list of sales
        return employeeList;
    }

    public static EmployeeModel Employeesearch(Integer id) throws SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employeedetails WHERE id='"+id+"';";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmployee = DatabaseConnection.dbEmployeeExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            EmployeeModel employeemodel = getEmployeeFromResultSet(rsEmployee);

            //Return employee object
            return employeemodel;
        } catch (SQLException  e) {
            System.out.println("While searching for an employee with " + id + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static EmployeeModel getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        EmployeeModel employee = null;
        if (rs.next()) {
            employee = new EmployeeModel();
            employee.setId(rs.getInt("ID"));
            employee.setName(rs.getString("NAME"));
            employee.setContact(rs.getInt("CONTACT"));
            employee.setPassword(rs.getString("PASSWORD"));
            //employee.setType(rs.getString("TYPE"));
        }
        return employee;
    }

    //UPDATE a sales quantity
    //*************************************
    public static void updateContact(String id, String contact) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE employeedetails SET Contact ="+contact+" WHERE id="+id+";";

        //Execute UPDATE operation
        try {
            DatabaseConnection.dbEmployeeExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //DELETE a sales record
    //*************************************
    public static void deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM employeedetails WHERE id ="+id+";";

        //Execute UPDATE operation
        try {
            DatabaseConnection.dbEmployeeExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //INSERT a sale record
    //*************************************
    public static void insertEmployee(String id, String name, String contact, String password, String type) throws SQLException, ClassNotFoundException {
        //Declare an insert  statement

        String updateStmt =
                "INSERT INTO employeedetails (id, Name, Contact, Password, Type) VALUES ("+id+", '"+name+"', "+contact+", '"+password+"', '"+type+"');";
        //Execute an insert  operation
        try {
            DatabaseConnection.dbEmployeeExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            System.out.println("The error is " + e.getMessage());
            throw e;
        }

    }
}
