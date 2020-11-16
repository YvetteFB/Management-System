package management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchasesDAO {

    //SELECT Purchases
    public static ObservableList<PurchasesModel> searchPurchases() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT Purchase_id, Item_name,Quantity,Quantity*Price AS Price, Date FROM purchases where Date = (select DATE(now()));";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs_purchases = DatabaseConnection.dbPurchExecuteQuery(selectStmt);

            //Send ResultSet to the getsalesList method and get sale object
            ObservableList<PurchasesModel> purchasesList = getPurchaseList(rs_purchases);

            //Return sales object
            return purchasesList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    public static ObservableList<PurchasesModel> getPurchaseList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<PurchasesModel> PurchasesList = FXCollections.observableArrayList();

        while (rs.next()) {
            PurchasesModel purchases = new PurchasesModel();

            purchases.setItem_name(rs.getString("ITEM_NAME"));
            purchases.setpQuantity(rs.getInt("QUANTITY"));
            purchases.setIprice(rs.getInt("PRICE"));
            purchases.setPurchase_id(rs.getInt("PURCHASE_ID"));
            //add purchases to the observable list
            PurchasesList.add(purchases);
        }
        //observable list of purchases
        return PurchasesList;
    }

    public static PurchasesModel searchPurchase(String purchase_id) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT Purchase_id, Item_name,Quantity,Quantity*Price AS Price, Date FROM purchases WHERE Purchase_id=" + purchase_id +";";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPurchases = DatabaseConnection.dbPurchExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            PurchasesModel purchasesmodel = getPurchasesFromResultSet(rsPurchases);

            //Return employee object
            return purchasesmodel;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("While searching a purchase with " + purchase_id + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static PurchasesModel getPurchasesFromResultSet(ResultSet rs) throws SQLException {
        PurchasesModel purchases = null;
        if (rs.next()) {
            purchases = new PurchasesModel();
            purchases.setItem_name(rs.getString("ITEM_NAME"));
            purchases.setpQuantity(rs.getInt("QUANTITY"));
            purchases.setIprice(rs.getInt("PRICE"));
            purchases.setPurchase_id(rs.getInt("PURCHASE_ID"));
        }
        return purchases;
    }

    //UPDATE a sales quantity
    //*************************************
    public static void updateItemQuantity(String purchase_id, String quantity) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =

                        "UPDATE purchases SET Quantity = " +quantity+ " WHERE Purchase_id = " + purchase_id + ";";

        //Execute UPDATE operation
        try {
            DatabaseConnection.dbPurchExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //DELETE a sales record
    //*************************************
    public static void deletePurchase(String purchase_id) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "DELETE FROM purchases WHERE Purchase_id =" + purchase_id + ";";

        //Execute UPDATE operation
        try {
            DatabaseConnection.dbPurchExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //INSERT a sale record
    //*************************************
    public static void insertPurchase( String item_name, String quantity, String iprice) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                        "INSERT INTO purchases (Item_name, Quantity, Price, Date) VALUES('" + item_name + "'," + quantity + "," +iprice +", NOW());";

        //Execute DELETE operation
        try {
            DatabaseConnection.dbPurchExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
