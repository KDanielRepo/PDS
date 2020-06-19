package OdbcApp;

import java.sql.*;

public class OdbcApp {

	String url = "jdbc:mysql://localhost:3306/MojaBaza";
	Connection connect;
	String queryString;
	Statement stmt;
	String createString = "create table Tabela1"+
	"(ID int," +
	" IMIE varchar(30)," +
	" NAZWISKO varchar(30)," +
	" WZROST real," +
	" WIEK float)";
	String insertString1 =
	"insert into Tabela1 values(1, 'Darek', 'Kowalski ', 1.74, '25,5')";
	String insertString2 =
	"insert into Tabela1 values(2, 'Hans ', 'Kloss ', 1.82, '26,5')";
	String insertString3 =
	"insert into Tabela1 values(3, 'Olaf ', 'Ludwig', 1.95, '27,5')";
	String insertString4 =
	"insert into Tabela1 values(4, 'Darek', 'Robbo ', 1.65, '28,5')";
	String updateString = "update Tabela1 set wzrost = 2.01, wiek = 29.5 where id = 2";
	String deleteString = "delete from Tabela1";
	String selectString1 = "select * from Tabela1";
	String selectString2 = "select * from Tabela1 order by nazwisko, imie, wzrost, wiek";
	String selectString3 = "select * from Tabela1 where imie = 'Darek'";

	public void goUpdate(String query)
	{
		try {
			stmt.executeUpdate(query);
			System.out.println("Pol. SQL : " + query);
		}
		catch(SQLException ex) 
		{
			ex.printStackTrace();
			System.err.println("Wyjatek SQL - jakis blad SQL");
		}
	}

	public void goExecute(String query)
	{
		String imie, nazwisko,id,wiek,wzrost;
		int i = 0;
		try {
			ResultSet r=stmt.executeQuery(query);
			while(r.next()){
				id = r.getString(1);
				imie=r.getString(2);
				nazwisko=r.getString(3);
				wzrost = r.getString(4);
				wiek = r.getString(5);
				System.out.println("| "+id+" | "+imie+" | "+nazwisko+" | "+wzrost+" | "+wiek+" |");
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			System.err.println("Problem z select SQL do "+url+ ": "+e.getMessage());
		}
	}

	public OdbcApp()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(java.lang.ClassNotFoundException e) 
		{
			System.err.print("ClassNotFoundException");
			System.err.println(e.getMessage());
		}
		try {
			connect = DriverManager.getConnection(url,"root","");
			stmt = connect.createStatement();
		}
		catch(SQLException ex) 
		{
			System.err.println("Wyjatek SQL - jakis blad SQL");
		}
		goUpdate(createString);
		goUpdate(deleteString);
		goUpdate(insertString1);
		goUpdate(insertString2);
		goUpdate(insertString3);
		goUpdate(insertString4);
		goUpdate(updateString);
		System.out.println("\n ----- Wszystkie rekordy -----------------------");
		goExecute(selectString1);
		System.out.println("\n ----- Wszystkie rekordy posortowane -----------");
		goExecute(selectString2);
		System.out.println("\n ----- Wszystkie rekordy o imieniu Darek -------");
		goExecute(selectString3);
		try {
			stmt.close();
			connect.close();
		}
		catch(SQLException ex) 
		{
			ex.printStackTrace();
			System.err.println("Wyjatek SQL - jakis blad SQL");
		}
	}

	public static void main(String args[])
	{
		new OdbcApp();
	}
}


