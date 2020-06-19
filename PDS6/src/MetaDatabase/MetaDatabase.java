package MetaDatabase;

import java.sql.*;

public class MetaDatabase {

	public MetaDatabase() {
		String url ="jdbc:mysql://localhost:3306/MojaBaza";
		Connection connect;
		String tname, ttype;
		int licznik = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // zaladowanie sterownika
		}
		catch(java.lang.ClassNotFoundException e) {
			System.err.print("Klasa nieznaleziona: "+e.getMessage());
		}
		try {
			// polaczenie z baza
			connect = DriverManager.getConnection(url, "root", "");
			// pobranie informacji o wszystkich tabelach bazy danych
			ResultSet r = connect.getMetaData().getTables(null,null,null,null);
			System.out.println(" --- Tabele w bazie: " + url + " ---");
			while(r.next()){
				tname = r.getString(3); // nazwa tabeli
				ttype = r.getString(4); // typ tabeli
				if (tname == null) tname = "brak";
				if (ttype == null) ttype = "brak";
				System.out.println(" " + (++licznik)+". " + tname + " (typ " + ttype + ")");
			}
			System.out.println(" -------------------------------------");
			connect.close();
		}
		catch (Exception e) {
			System.err.println("Problem: "+e.getMessage());
		}
	}

	public static void main(String args[]) {
		new MetaDatabase();
	}
}

