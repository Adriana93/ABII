package adatbazisokbeadando;
import java.util.Scanner;


public class ABKezeloProgram  {

	static DBMethods dbm = new DBMethods();
	static ConsoleMethods cm =new ConsoleMethods();
	
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		dbm.Reg();
		dbm.DisConnect(dbm.Connect());
		Scanner input= new Scanner(System.in);
		String name, jelszo;
			

		System.out.println("Bejelentkezes: ");
		System.out.println("Add meg a felhaszn�l�neved: ");
		name = input.nextLine();
	
		System.out.println("Add meg a jelszavadat: ");
		jelszo = input.nextLine();
					
			
		
		if(name.equals("admin") && (jelszo.equals("admin12"))) {
			System.out.println("Minden rendben!");
		} else{
			System.out.println("Nincs hozz�f�r�si jogod");
			System.exit(0);
					
		}
		
		String sqlp = "CREATE TABLE dolgozo(Kod number(6) primary key, nev char(50), szulido char(50), lakohely char(50), iq number(10))";
		dbm.CommandExec(sqlp);
		sqlp = "CREATE TABLE task(tkod number(6) primary key, Ekod number(6), leiras char(30), datum char(50), prioritas number(3))";
		dbm.CommandExec(sqlp);
	

		while(1!=0) {
	 menu();
		}
	
		
	
	
		
		
	}
	
	
		
		static void  menu() {
			System.out.println("\n");
			System.out.println("Men�");
			System.out.println("************");
			System.out.println("0. Kil�p�s");
			System.out.println("1. List�z�s");
			System.out.println("2. Besz�r�s");
			System.out.println("3. T�rl�s");
			System.out.println("4. M�dos�t�s");
			System.out.println("5. Param�terez�s");
			System.out.println("6. IqLista");
			System.out.println("7. Feladat besz�r�s");
			String ms =cm.ReadData("Add meg a v�laszottt men� sz�m�t: ");
			int m= -1;
			if (test(ms)) m= StringToInt(ms);
			switch(m) {
			case 0: System.out.println("A program le�llt"); System.exit(0); break;
			case 1: Listing(); break;
			case 2: Insertion(); break;
			case 3: Deletion(); break;
			//case 4: Modition();break;
			case 5: Param(); break;
			case 6: Iq();break;
			case 7: InsertionTask();
			}
		}
		

	 


	private static void Iq() {
			dbm.IQList();			
		}



	private static void Param() {
		Dolgozo[] emp = new Dolgozo[3];
		emp[0]= new Dolgozo(16, "Kiss Elem�r", "1980.5.3", "Szeged", 110);
		emp[1]= new Dolgozo(17, "Moln�r Andrea", "2000.12.24", "H�dmez�v�s�rhely", 96);
		dbm.InsertWithPS(emp);
			
		}



	static boolean test(String s) {
			if (s.length()==0) {
				System.out.println("Pr�b�ld �jra!");
			
			return false;
			}
			else 
				try {
					int x=Integer.valueOf(s);
					if (x>=0 && x <8) return true;
					else {
						System.out.println("Mintha nem j�l adtad volna meg!");
						return false;
					}
					} catch (NumberFormatException nfe) { 
						System.out.println("Ez nem t�nt j� adatnak! Pr�b�ld �jra!");
						return false;
					}
	}
	 static int StringToInt(String s) {
			int x= 0;
			try {
				x=Integer.valueOf(s);
			} catch (NumberFormatException nfe) {}
				return x;
			
		}

	
	  static void Listing() {
		dbm.ReadAllData();
		dbm.ReadAllDataTask();
	}
		
	static void Insertion() {
			String Kod =cm.ReadData("K�rem  a besz�rand� k�dot: ");
			String nev =cm.ReadData("K�rem  a nevet: ");
			String szi =cm.ReadData("K�rem  a sz�let�si id�t: ");
			String lak =cm.ReadData("K�rem  a lak�helyet: ");
			String iqq =cm.ReadData("K�rem  az IQ �rt�ket");
			dbm.Insertion(Kod, nev, szi, lak, iqq);
		
	}
			
		
	static void InsertionTask() {
/*		boolean ok=true;
		while (ok) {
			String Kod = cm.ReadData("Add meg a dolgoz� k�dj�t: ");
			int x = dbm.EmpKodChecker(Kod);
			if(x==1) {
				ok=false;
				String task = cm.ReadData("Add meg a dolgoz� feladat�t");
				dbm.InsertTask(Kod, task);
			} else System.out.println("Nem l�zet� k�d! Pr�b�ld �jra!");
		}*/
	}
	

	private static void Deletion() {
		String Kod =cm.ReadData("k�rem  a t�rlend� k�dot: ");
		dbm.DeleteData(Kod);
		
	}

	/*private static void Modition() {
		String  nev =cm.ReadData("K�rem a m�dos�tand� nevet");
		dbm.Modition(nev);
		
	}*/

	
		
	


	
}
