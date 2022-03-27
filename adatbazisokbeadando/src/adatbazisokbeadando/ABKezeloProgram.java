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
		System.out.println("Add meg a felhasználóneved: ");
		name = input.nextLine();
	
		System.out.println("Add meg a jelszavadat: ");
		jelszo = input.nextLine();
					
			
		
		if(name.equals("admin") && (jelszo.equals("admin12"))) {
			System.out.println("Minden rendben!");
		} else{
			System.out.println("Nincs hozzáférési jogod");
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
			System.out.println("Menü");
			System.out.println("************");
			System.out.println("0. Kilépés");
			System.out.println("1. Listázás");
			System.out.println("2. Beszúrás");
			System.out.println("3. Törlés");
			System.out.println("4. Módosítás");
			System.out.println("5. Paraméterezés");
			System.out.println("6. IqLista");
			System.out.println("7. Feladat beszúrás");
			String ms =cm.ReadData("Add meg a válaszottt menü számát: ");
			int m= -1;
			if (test(ms)) m= StringToInt(ms);
			switch(m) {
			case 0: System.out.println("A program leállt"); System.exit(0); break;
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
		emp[0]= new Dolgozo(16, "Kiss Elemér", "1980.5.3", "Szeged", 110);
		emp[1]= new Dolgozo(17, "Molnár Andrea", "2000.12.24", "Hódmezõvásárhely", 96);
		dbm.InsertWithPS(emp);
			
		}



	static boolean test(String s) {
			if (s.length()==0) {
				System.out.println("Próbáld újra!");
			
			return false;
			}
			else 
				try {
					int x=Integer.valueOf(s);
					if (x>=0 && x <8) return true;
					else {
						System.out.println("Mintha nem jól adtad volna meg!");
						return false;
					}
					} catch (NumberFormatException nfe) { 
						System.out.println("Ez nem tûnt jó adatnak! Próbáld újra!");
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
			String Kod =cm.ReadData("Kérem  a beszúrandó kódot: ");
			String nev =cm.ReadData("Kérem  a nevet: ");
			String szi =cm.ReadData("Kérem  a születési idõt: ");
			String lak =cm.ReadData("Kérem  a lakóhelyet: ");
			String iqq =cm.ReadData("Kérem  az IQ értéket");
			dbm.Insertion(Kod, nev, szi, lak, iqq);
		
	}
			
		
	static void InsertionTask() {
/*		boolean ok=true;
		while (ok) {
			String Kod = cm.ReadData("Add meg a dolgozó kódját: ");
			int x = dbm.EmpKodChecker(Kod);
			if(x==1) {
				ok=false;
				String task = cm.ReadData("Add meg a dolgozó feladatát");
				dbm.InsertTask(Kod, task);
			} else System.out.println("Nem lézetõ kód! Próbáld Újra!");
		}*/
	}
	

	private static void Deletion() {
		String Kod =cm.ReadData("kérem  a törlendõ kódot: ");
		dbm.DeleteData(Kod);
		
	}

	/*private static void Modition() {
		String  nev =cm.ReadData("Kérem a módosítandó nevet");
		dbm.Modition(nev);
		
	}*/

	
		
	


	
}
