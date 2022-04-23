package adatbazisokbeadando;
import java.util.Scanner;
import java.sql.Date;

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
		
		String sqlp = "CREATE TABLE dolgozo(Kod number(6) primary key, nev char(50), szulido date(50), lakohely char(50), iq number(10))";
		dbm.CommandExec(sqlp);
		sqlp = "CREATE TABLE task(tkod number(6) primary key, Ekod number(6), leiras char(30), datum date(50), prioritas number(3))";
		dbm.CommandExec(sqlp);
		//tablak feltöltése adatokkal, paraméteres átadás
	//	Dolgozo[] emp = new Dolgozo[2];
		//emp[0]= new Dolgozo(16, "Kiss Elemér", 1980 januar 3, "Szeged", 110);
		//emp[1]= new Dolgozo(17, "Molnár Andrea", "2000-12-24", "Hódmezõvásárhely", 96);
	//	dbm.InsertWithPS(emp);
	

		while(1!=0) {
	 menu();
		}
	}
	
	
		static void  menu() {
			System.out.println("\n");
			System.out.println("Menü");
			System.out.println("************");
			System.out.println("0. Kilépés");
			System.out.println("1. Dolgozó Listázás");
			System.out.println("2. Dolgozó Beszúrás");
			System.out.println("3. Dolgozó Törlés");
			System.out.println("4. Dolgozó Módosítás");
			System.out.println("5. Feladat Listázás");
			System.out.println("6. Feladat Beszúrás");
			System.out.println("7. Feladat Törlés");
			System.out.println("8. Feladat Módosítás");
			System.out.println("9. IqLista");
			String ms =cm.ReadData("Add meg a válaszottt menü számát: ");
			int m= -1;
			if (test(ms)) m= StringToInt(ms);
			switch(m) {
			case 0: System.out.println("A program leállt"); System.exit(0); break;
			case 1: Listing(); break;
			case 2: Insertion(); break;
			case 3: Deletion(); break;
			case 4: ModitionDolgozo();break;
			case 5: ListingTask(); break;
			case 6: InsertionTask();break;
			case 7: DeletionTask(); break;
			case 8: ModitionTask(); break;
			case 9: Iq();break;
			}
		}
		

	 


	private static void Iq() {
			dbm.IQList();			
		}


	static boolean test(String s) {
			if (s.length()==0) {
				System.out.println("Próbáld újra!");
			
			return false;
			}
			else 
				try {
					int x=Integer.valueOf(s);
					if (x>=0 && x <=9) return true;
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
		
	}
		
	  static void ListingTask() {
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
		String tkod =cm.ReadData("Kérem  a beszúrandó kódot: ");
		String Ekod =cm.ReadData("Kérem  a Ellenorzõ kódot: ");
		String leiras =cm.ReadData("Kérem  a feladat leírását: ");
		String datum=cm.ReadData("Kérem  a dátumot: ");
		String prioritas =cm.ReadData("Kérem  a feladat prioritasat: ");
		dbm.InsertionTask(tkod, Ekod, leiras, datum, prioritas);
		}
	

	private static void Deletion() {
		String Kod =cm.ReadData("kérem  a törlendõ kódot: ");
		dbm.DeleteData(Kod);
		
	}
	
	private static void DeletionTask() {
		String tkod =cm.ReadData("kérem  a törlendõ kódot: ");
		dbm.DeleteData(tkod);
		
	}

	private static void ModitionDolgozo() {
		String Kod =cm.ReadData("kérem  a Módosítandó kódot: ");
		String iq =cm.ReadData("kérem  az új IQ-t: ");
		dbm.Modition(Kod, iq);
	}

	
	private static void ModitionTask() {
		String tkod =cm.ReadData("kérem  a Módosítandó kódot: ");
		String Ekod =cm.ReadData("kérem  az új nevet: ");
		dbm.ModitionTask(tkod, Ekod);
	}

	


	
}
