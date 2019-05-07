import java.util.List;

public class testing {
	public static void main(String args[]) {
		if( Movies.loadFromDb()==null)
{System.out.println("empty");}
		else {List<Movie> list = Movies.loadFromDb();
		System.out.println(list);}
		
	}
}
