package SelectDB;

import java.sql.*;

public class SelectDB {

	public SelectDB() {
		String url = "jdbc:mysql://localhost:3306/MojaBaza";
		Connection connect;
		Statement stmt;
		ResultSet r;
		String imie, nazwisko,id,wiek,wzrost;
		int i = 0;
		String selectString = "select * from Tabela1"; // zapytanie SQL
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // zaladowanie sterownika
		}
		catch(java.lang.ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		try {
			connect = DriverManager.getConnection(url,"root",""); // polaczenie z baza
			stmt = connect.createStatement(); // zainicjowanie Statement - rodzaj kontenera
			r = stmt.executeQuery(selectString); // wyslanie zapytania, r - wynik zapytania
			while(r.next()){ // kolejny rekord
				id = r.getString(1); // odczyt 5 kolejnych wartosci z rekordu
				imie = r.getString(2);
				nazwisko = r.getString(3);
				wzrost = r.getString(4);
				wiek = r.getString(5);
				System.out.println("|"+id+"|"+imie+"|"+nazwisko+"|"+wzrost+"|"+wiek+"|");
			}
			stmt.close();
			connect.close();
		}
		catch (Exception e) {
			System.err.println("Problem z poleceniem select SQL do "+url+ ": "+e.getMessage());
		}
	}

	public static void main(String args[])
	{
		new SelectDB();
	}
}
