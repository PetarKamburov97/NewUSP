import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Movies {
	
	private static List<Movie> movieList = new ArrayList<Movie>();

	private static Connection connect = null;
	
	public static List<Movie> loadFromDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies","root","");
			ResultSet r = null;
			PreparedStatement p = null;
			
			
			String q = "SELECT * FROM movies";
			p = connect.prepareStatement(q);
			r = p.executeQuery();
			
			while(r.next()) {
				movieList.add(new Movie(r.getString("title"),r.getString("genre"),r.getString("year"),r.getString("actors")));
			}
			connect.close();
			return movieList;
		}catch(SQLException  e) {
			e.printStackTrace();
		}catch(Exception  e) {
			e.printStackTrace();
		}
		return movieList;
	}
	
	public static List<Movie> insertIntoDb(String title, String genre, String year, String actors) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies","root","");
			PreparedStatement p = null;
			
			
			String q = "INSERT INTO movies values(?,?,?,?);";

			p = connect.prepareStatement(q);
			p.setString(1, title);
			p.setString(2, genre);
			p.setString(3, year);
			p.setString(4, actors);
			p.execute();
			
			connect.close();
			return movieList;
		}catch(SQLException  e) {
			e.printStackTrace();
		}catch(Exception  e) {
			e.printStackTrace();
		}
		return movieList;
	}
}
