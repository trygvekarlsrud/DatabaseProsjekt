package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
	private static String mysqlAddr = "jdbc:mysql://mysql.stud.ntnu.no:3306/trygvevk_prosjekt?allowMultiQueries=true";
    private static String mysqlUser = "trygvevk_4140";
    private static String mysqlPass = "karlsrud";
    
    
    public static boolean runQuery(String query, String... args){
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 0;
            for(String arg : args)
                stmt.setString(++i, arg);
            return stmt.executeUpdate() != 0;
        }
        catch(SQLException e){
            return false;
        }
    }
    
    // Legge til treningsøkt i databasen
    
    public static void createLecture(Training training){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Treningsokt (oktID, dato, varighet) VALUES (?,?,?)");
            stmt.setInt(1, training.getTrainingID());
            stmt.setDate(2, Integer.parseInt(training.getTrainingDate())); //SKAL DET VÆRE setINT or what ? 
            stmt.setInt(3, training.getTrainingTime());
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    /*
     * Finner alle treningsøkter, og gjør de om til en liste som kan bli vist i tableview/column
     */
    
    public static ObservableList<Training> trainings(){
    	// Makes a new observable list
    	ObservableList<Training> treningList = FXCollections.observableArrayList();
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Treningsokt");
            
            //
            ResultSet rs = stmt.executeQuery();
            
            // While the
            while(rs.next()){
            	ArrayList<String> trening = new ArrayList<String>();
            	for(int i = 1; i < 4; i++){
            		trening.add(rs.getString(i));
            	}treningList.add(new Training(trening.get(0), trening.get(1), trening.get(2)));
            }
            return treningList;
        }
        catch(SQLException e){
        	System.out.println(e);
            return null;
        }	
    }
}
