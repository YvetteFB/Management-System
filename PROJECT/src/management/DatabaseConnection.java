package management;

import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class DatabaseConnection {
    public static Connection conn = null;

    public static Connection ConDB() {

        try {
            //Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost/login_credentials", "root", "Fiona");
            return conn;
        } catch (SQLException e) {
            System.out.println("The connection is not complete" + e.getMessage() + "cause : " + e.getCause());

            return null;
        }


    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT * FROM sales WHERE Sale ID = ?";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        //Statement stmt = null;
        Statement stmt = null;
        String sql = "UPDATE sales WHERE Sale ID = ?";
        try {
            //Connect to DB
            ConDB();
            //Create Statement
            stmt = conn.prepareStatement(sql);
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    public static ResultSet dbPurchExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT * FROM purchases WHERE Purchase ID = ?";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbPurchExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        //Statement stmt = null;
        Statement stmt = null;
        String sql = "UPDATE purchases WHERE Purchase_id = ?";
        try {
            //Connect to DB
            ConDB();
            //Create Statement
            stmt = conn.prepareStatement(sql);
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    //DB Execute Query Operation
    public static ResultSet dbSortQuantityExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT sales.Product_Name,SUM(product.Price) AS Price,SUM(sales.Quantity) AS Quantity FROM sales JOIN product ON sales.Product_Name = product.Product_Name WHERE Date BETWEEN '\"+ from_date +\"' AND '\"+ to_date +\"' GROUP BY Product_Name;";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
    public static ResultSet dbSalesReportExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT sales.Product_Name,sales.Quantity,product.Price FROM sales JOIN products ON sales.Product_Name = products.Product_Name;";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }


    public static ResultSet dbEmployeeExecuteQuery(String queryStmt) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT * FROM employeedetails;";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbEmployeeExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        //Statement stmt = null;
        Statement stmt = null;
        String sql = "UPDATE employeedetails WHERE id = ?";
        try {
            //Connect to DB
            ConDB();
            //Create Statement
            stmt = conn.prepareStatement(sql);
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    public static ResultSet dbTotalSalesExecuteQuery(String queryStmt) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        String sql = "SELECT SUM(product.Price)*sales.Quantity AS Price FROM sales JOIN product ON sales.Product_Name = product.Product_Name WHERE Date BETWEEN ? AND ?;";
        try {
            //Connect to DB
            ConDB();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.prepareStatement(sql);

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
}

