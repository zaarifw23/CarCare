package DatabaseLayer;

import java.sql.*;

public class DatabaseConnection_02 {
    private final String URL="jdbc:mysql://localhost:3306/carcaredb";
    private final String UName="root";
    private final String Password="";
    private static DatabaseConnection_02 instance;
    private Connection con;

    private DatabaseConnection_02()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL,UName,Password);
            System.out.println("Database Connection Sucess");
        }catch (ClassNotFoundException ex)
        {
            System.out.println("Driver Class Error "+ex.getMessage());
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
        }
    }
    public static DatabaseConnection_02 getSingleInstance()//Singleton Design Pattern
    {
        try {
            if (instance == null) {
                instance = new DatabaseConnection_02();
            } else if (instance.con.isClosed()) {
                instance = new DatabaseConnection_02();
            }else{
                return instance;
            }
            return instance;
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
            return null;
        }
    }
    public boolean ExecuteQuery(String sqlQ)
    {
        try
        {
            Statement st=con.createStatement();
            int result=st.executeUpdate(sqlQ);
            return result>0;
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return false;
        }
    }

    public ResultSet ExecuteSelectQuery(String sqlQ)
    {
        try
        {
            Statement st=con.createStatement();
            ResultSet result=st.executeQuery(sqlQ);
            return result;
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return null;
        }
    }

}
