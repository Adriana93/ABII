package adatbazisokbeadando;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBMethods {
	
	private Statement s = null;
	private Connection conn;
	private ResultSet rs = null;
	
	public void ReadAllData() {
		Connection conn = Connect();
				String nev ="", szid="", lak="", x= "\t";
				int Kod =0, iq=0;
				String sqlp ="select Kod,nev,szulido,lakohely, iq from dolgozo";
				try {
					s = conn.createStatement();
					rs= s.executeQuery(sqlp);
					while(rs.next()) {
						Kod = rs.getInt("Kod");
						nev = rs.getString("nev");
						szid = rs.getString("szulido");
						lak = rs.getString("lakohely");
						iq = rs.getInt("iq");
						SM(Kod+x+nev+x +szid+x+lak+x+ iq);
					}
					rs.close();
				} catch (SQLException e) {SM(e.getMessage());}
	}
	
	public void ReadAllDataTask() {
		Connection conn = Connect();
				String leiras ="", datum="", x= "\t";
				int tkod =0, Ekod=0, prioritas =0;
				String sqlp ="select tkod,Ekod,leiras,datum,prioritas from task";
				try {
					s = conn.createStatement();
					rs= s.executeQuery(sqlp);
					while(rs.next()) {
						tkod = rs.getInt("tkod");
						Ekod = rs.getInt("Ekod");
						leiras = rs.getString("leiras");
						datum = rs.getString("datum");
						prioritas = rs.getInt("prioritas");
						
						SM(tkod+x+Ekod+x +leiras+x+datum+x+prioritas );
					}
					rs.close();
				} catch (SQLException e) {SM(e.getMessage());}
	}
	
	
	public  Connection Connect() {
			Connection conn =null;
			String url ="jdbc:oracle:thin:@193.6.5.58:1521:XE";
			try {
			conn = DriverManager.getConnection(url,"H22_Q3X2Z4","Q3X2Z4");
			return conn;
		} catch (Exception ex) {
			SM(ex.getMessage());
			return conn;
		}
	}

	public void CommandExec(String command) {
	Connection conn = Connect();
	String sqlp = command;
	SM("Command: "+sqlp);
	try {
		Statement s = conn.createStatement();
		s.execute(sqlp);
		SM("Csatlakozás sikerült!");
		} catch(SQLException e) {
			SM("CommandExec: " +e.getMessage());
		}
	DisConnect(conn);
		
	}
	
	public int Identification(String name, String pwd) {
		Connect();
		int pc=-1;
		String sqlp = "select count(*) pc from user where name='"+name+"' and pwd='"+pwd+"'";
			try {
				s = conn.createStatement();
				rs =s.executeQuery(sqlp);
				while(rs.next()){
					pc = rs.getInt("pc");
				}
				rs.close();
			} catch (SQLException e) {SM(e.getMessage());}
		DisConnect(conn);
		return pc;
			
	}
	
	public void Insertion(String Kod, String nev,String szid, String lak, String iq) {
		Connection conn = Connect();
		String sqlp ="insert into dolgozo values("+Kod+",'"+nev+"', '"+szid+"','"+lak+"', '"+iq+"')";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("beszúrás ok!");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		
	}
	public void InsertionTask(String tkod, String Ekod) {
		Connection conn = Connect();
		String sqlp ="insert into task values("+tkod+",'"+Ekod+"')";
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Task beszúrás ok!");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		
	}
	public void DeleteData(String Kod) {
		Connection conn = Connect();
		SM("Melyik dolgozót töröljem?");
		String sqlp ="delete from dolgozo where kod like '"+Kod+"'";
			try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("törlés ok!");
		} catch (SQLException e) {
			SM("JDBC delete: "+e.getMessage());
		}
		
	}
	/*public void Modition(String nev) {
		String sqlp ="update dolgozo set nev where '"+nev+"'";
		try {
			s = conn.createStatement();
			s.execute(sqlp);
			SM("Módosítás ok!");
		} catch (SQLException e) {
			SM("JDBC update: "+e.getMessage());
		}
		
	}*/
public void InsertWithPS(Dolgozo[] emp) {
	Connection conn = Connect();
		int pc=0;
		String sqlp = "insert into dolgozo(kod,nev,szulido,lakohely, iq)"+ "values(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlp);
			for(int i=0; i <emp.length;i++) {
				ps.setInt(1, emp[i].getKod());
				ps.setString(2, emp[i].getNev());
				ps.setString(3, emp[i].getSzulido());
				ps.setString(4, emp[i].getLakohely());
				ps.setInt(5, emp[i].getIq());
				ps.execute();
				pc++;
			} 
			System.out.println(pc+"darab adatsor kiírva");
		} catch (SQLException e) {System.out.println(e.getMessage());}
		DisConnect(conn);
	}

	
public void IQList() {
	Connection conn = Connect();
	String nev; int iqq;
	String sqlp =  "select nev, iq from dolgozo where iq >? and iq<= ?";
	try {
		PreparedStatement ps = conn.prepareStatement(sqlp);
		
		for (int iq=80; iq<=160; iq=+10) {
			int iq1=iq-20, iq2=iq;
			if(iq==80) iq1=0;
			if (iq==160) iq2=200;
			ps.setInt(1, iq1);
			ps.setInt(2, iq2);
			ResultSet rs = ps.executeQuery();
			if(iq1>0) iq1++;
			SM("\nDolgozók "+iq1+" és "+iq2+" közti IQ-val: ");
			SM("-----------------");
			while(rs.next()) {
				nev = rs.getString("nev");
				iqq = rs.getInt("iq");
				if(nev.length() <10) nev=nev+" ";
				SM(nev+" - \t"+iqq);
			}
		}
	} catch (SQLException e) {SM(e.getMessage());}
	DisConnect(conn);
}

	
	public int EmpKodChecker(String nev) {
		Connection conn = Connect();
		int pc =-1;
		String sqlp= "select lod from dolgozo where nev='"+nev+"';";
		try {
			s=conn.createStatement();
			rs=s.executeQuery(sqlp);
			while(rs.next()) {
				pc=rs.getInt("Kod");
			}
			rs.close();
		}	catch(SQLException e) {SM(e.getMessage());}
		DisConnect(conn);
		return pc;
		
		
	}
	
	public void InsertTask(String kod, String task) {
		Connection conn = Connect();
		String sqlp = "insert into task(ekod, leiras) values("+kod+", '"+task+"');";
		try {
			s=conn.createStatement();
			s.executeQuery(sqlp);
			SM("Task beszúrás ok!");
		}	catch(SQLException e) {
			SM(e.getMessage());}
		DisConnect(conn);
	}
	public void DisConnect(Connection conn) {
		if(conn!=null) {
		try {
			conn.close();
			SM("DisConnection OK");
		} catch(Exception e) {SM(e.getMessage());}
		}
	}
		public void Reg() {
			try {
				Class.forName("org.sqlite.JDBC");
				SM("Sikeres driver regisztacio");
			} catch (ClassNotFoundException e) {
				SM("Hibás driver regisztáció!"+e.getMessage());
				
			}
		
		}
	public void SM(String msg) {
		System.out.println(msg);
	}
	
	
}
