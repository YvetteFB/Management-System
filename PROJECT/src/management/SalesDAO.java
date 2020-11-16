package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SalesDAO {


    //SELECT Sale
    public static ObservableList<SalesModel> searchSales () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM sales where Date = (select DATE(now()))";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_sales = DatabaseConnection.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getsalesList method and get sale object
            ObservableList<SalesModel> salesList = getSalesList(rs_sales);

            //Return sales object
            return salesList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<SalesModel> getSalesList(ResultSet rs)throws SQLException, ClassNotFoundException {
    ObservableList <SalesModel> salesList = FXCollections.observableArrayList();

        while(rs.next()){
            SalesModel sales = new SalesModel();
            sales.setProduct_name(rs.getString("PRODUCT_NAME"));
            sales.setQuantity(rs.getInt("QUANTITY"));
            sales.setSale_id(rs.getInt("SALE_ID"));

            //add sales to the observable list
            salesList.add(sales);
        }
        //observable list of sales
        return salesList;
    }

    public static SalesModel searchSale(Integer sale_id) throws SQLException, ClassNotFoundException  {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM sales WHERE Sale_ID="+sale_id+";";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSales = DatabaseConnection.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            SalesModel salesmodel = getSalesFromResultSet(rsSales);

            //Return employee object
            return salesmodel;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("While searching a sale with " + sale_id + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    private static SalesModel getSalesFromResultSet(ResultSet rs) throws SQLException {
        SalesModel sales = null;
        if (rs.next()) {
            sales = new SalesModel();
            sales.setProduct_name(rs.getString("PRODUCT_NAME"));
            sales.setQuantity(rs.getInt("QUANTITY"));
            sales.setSale_id(rs.getInt("SALE_ID"));
        }
        return sales;
    }

        //UPDATE a sales quantity
        //*************************************
        public static void updateProductQuantity (String sale_id, String quantity) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "UPDATE sales SET quantity = "+quantity+" WHERE Sale_ID = "+sale_id+";";

        //Execute UPDATE operation
        try {
            DatabaseConnection.dbExecuteUpdate(updateStmt);

        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    //DELETE a sales record
    //*************************************
    public static void deleteSale (String sales_id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
               "DELETE FROM sales WHERE SALE_ID = "+sales_id+";";

        //Execute UPDATE operation
        try {
           DatabaseConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    //INSERT a sale record
    //*************************************
    public static void insertSale( String product_name, String quantity) throws SQLException, ClassNotFoundException {
        //Declare an insert  statement
        String updateStmt =
                "INSERT INTO sales " +
                        "(Product_Name, Quantity, Date)" +
                        " VALUES ('" +product_name+"',"+quantity+ ", NOW());";
        //Execute an insert  operation
        try {
            DatabaseConnection.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            System.out.println("The error is " +e.getMessage());
            throw e;
        }

    }




















}
