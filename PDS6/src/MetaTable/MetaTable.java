package MetaTable;

import java.sql.*;

public class MetaTable {

	private Connection connect;
	private Statement stmt;
	String url ="jdbc:mysql://localhost:3306/MojaBaza";
	// metoda drukujaca kolumny tabeli
	
	public void printColumns(String tabela)
	{
		String cname, ctype;
		int ncols, csize, licznik = 0;
		try {
			// wyslanie zapytania SQL: select * from <nazwa_tabeli>
			ResultSet rs = stmt.executeQuery("select * from " + tabela);
			ResultSetMetaData md = rs.getMetaData(); // zbior wynikow metadata
			ncols = md.getColumnCount(); // ilosc kolumn tabeli
			System.out.println(" --- Kolumny w tabeli " + tabela + " ---");
			for(int i = 1 ; i <= ncols; i++) {
				cname = md.getColumnName(i); // nazwa kolumny
				ctype = md.getColumnTypeName(i); // typ kolumny
				csize = md.getColumnDisplaySize(i); // rozmiar danych kolumny
				System.out.println(" " + (++licznik)+". " + cname
				+ " (typ " + ctype + ")" + " rozmiar [" + csize + "]");
			}
			System.out.println(" -------------------------------------");
		}
		catch(Exception e) {
			System.err.println("Problem z poleceniem getMetaData: "+e.getMessage());
		}
	}

	public MetaTable() {
		int licznik = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // zaladowanie sterownika
		}
		catch(java.lang.ClassNotFoundException e) {
			System.err.print("Klasa nieznaleziona: "+e.getMessage());
		}
		try {
			connect = DriverManager.getConnection(url, "root", ""); // polaczenie
			stmt = connect.createStatement(); // kontener
			printColumns("tabela1"); // kolumny test1
			stmt.close();
			connect.close();
		}
		catch (Exception e) {
			System.err.println("Problem: "+e.getMessage());
		}
	}

	public static void main(String args[]) {
		new MetaTable();
	}
}

