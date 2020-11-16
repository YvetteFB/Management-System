package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SalesReportDAO {
    public static ObservableList<SalesReportModel> searchSales(String from_date, String to_date) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT  sales.Product_Name,product.Price,sales.Quantity FROM sales JOIN product ON sales.Product_Name = product.Product_Name WHERE Date BETWEEN '"+ from_date +"' AND '"+ to_date +"'; ";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_sales = DatabaseConnection.dbSalesReportExecuteQuery(selectStmt);

            //Send ResultSet to the getsalesList method and get sale object
            ObservableList<SalesReportModel> salesList = getSalesList(rs_sales);

            //Return sales object
            return salesList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<SalesReportModel> getSalesList(ResultSet rs)throws SQLException, ClassNotFoundException {
        ObservableList <SalesReportModel> salesList = FXCollections.observableArrayList();

        while(rs.next()){
            SalesReportModel sales = new SalesReportModel();
            sales.setProduct_name(rs.getString("PRODUCT_NAME"));
            sales.setQuantity(rs.getInt("QUANTITY"));
            sales.setPrice(rs.getInt("PRICE"));


            //add sales to the observable list
            salesList.add(sales);
        }
        //observable list of sales
        return salesList;
    }
    public static ObservableList<SalesReportModel> sortQSales (String from_date, String to_date) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT sales.Product_Name,SUM(product.Price)*Quantity AS Price,SUM(sales.Quantity) AS Quantity FROM sales JOIN product ON sales.Product_Name = product.Product_Name WHERE Date BETWEEN '"+ from_date +"' AND '"+ to_date +"' GROUP BY Product_Name;";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_sales = DatabaseConnection.dbSortQuantityExecuteQuery(selectStmt);

            //Send ResultSet to the getsalesList method and get sale object
            ObservableList<SalesReportModel> salesList = getSalesList(rs_sales);

            //Return sales object
            return salesList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<SalesReportModel> totalSales(String from_date, String to_date) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT SUM(product.Price)*sales.Quantity AS Price FROM sales JOIN product ON sales.Product_Name = product.Product_Name WHERE Date BETWEEN '"+ from_date +"' AND '" + to_date +"';";


        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_sales = DatabaseConnection.dbTotalSalesExecuteQuery(selectStmt);
            if(rs_sales.next())
            {
               // System.out.println(rs_sales.getInt(1));
            }
            else
            {
                System.out.println("Record Not Found...");
            }

            //Send ResultSet to the getsalesList method and get sale object
            //ObservableList<SalesReportModel> salesList = getSalesList(rs_sales);



        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
        return null;
    }

}


