package CreateDB;

import java.sql.*;


public class CreateDB {

	public CreateDB() {
		String url = "jdbc:mysql://localhost:3306/MojaBaza";
		Connection connect;
		Statement stmt;
		String createString = "create table Tabela1"+
			"(ID int," +
			" IMIE varchar(30)," +
			" NAZWISKO varchar(30)," +
			" WZROST real," +
			" WIEK float)"; // zapytanie SQL
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // zaladowanie sterownika
		}
		catch(java.lang.ClassNotFoundException e)
		{
			System.err.print("Blad: ClassNotFoundException");
		}
		try
		{
			connect = DriverManager.getConnection(url,"root",""); // polaczenie z baza
			stmt = connect.createStatement(); // zainicjowanie Statement - rodzaj kontenera
			stmt.executeUpdate(createString); // wyslanie polecenia SQL
			stmt.close();
			connect.close();
			System.out.println("Polecenie SQL wykonane");
		} 
		catch(SQLException ex) {
			ex.printStackTrace();
			System.err.println("Wyjatek SQL - jakis polaczenia lub zapytania SQL");
		}
	}
	
	public static void main(String args[])
	{
		new CreateDB();
	}
}
