package database;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;


public class Database {
    static Statement stmt;
      public Database() {
        String url = "jdbc:postgresql://10.232.86.204:5432/gendul";
        String user = "postgres";
        String password = "postgres";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("connected");
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS employee (id int PRIMARY KEY, fname VARCHAR(50), state VARCHAR(50));");
            System.out.println("table created");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void insert(int id,String fname, String state){
        String query = String.format("INSERT INTO EMPLOYEE VALUES(%d,'%s','%s')",id,fname,state);
        try{
            stmt.executeUpdate(query);
        } catch (Exception e){
            System.out.println("error in insert");
        }
    }

    public static void update(int id,String column, String value ){
        String query = String.format("UPDATE employee set %s = '%s' WHERE id = %d", column,value,id);
        try{
            stmt.executeUpdate(query);
        } catch (Exception e){
            System.out.println("error in update");
        }
    }

    public static String Printtable(){
        String output = "";
        try{
        ResultSet res = stmt.executeQuery("SELECT * FROM EMPLOYEE;");
        output = output + "\n id   fname   state";
        output = output + "\n ______________________________";
        
        while (res.next()) {

            String stringLine = "\n"+res.getInt("id")+"     "+res.getString("fname")+"      "+res.getString("state");
            output = output + stringLine;
        }
        
        } catch (Exception e){
            System.out.println("error in printing");
        }
        return output;
    }

    public static void Delete(int id){
        String query = String.format("DELETE FROM employee WHERE id = %d;",id);
        try{
            stmt.executeUpdate(query);
        } catch(Exception e){
            System.out.println("error in delete");
        }
    }

}