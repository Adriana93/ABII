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
		
		String sqlp = "CREATE TABLE dolgozo(Kod number(6) primary key, nev char(50), szulido date(50), lakohely char(50), iq number(10))";
		dbm.CommandExec(sqlp);
		sqlp = "CREATE TABLE task(tkod number(6) primary key, Ekod number(6), leiras char(30), datum date(50), prioritas number(3))";
		dbm.CommandExec(sqlp);
		//tablak felt�lt�se adatokkal, param�teres �tad�s
	//	Dolgozo[] emp = new Dolgozo[2];
		//emp[0]= new Dolgozo(16, "Kiss Elem�r", 1980 januar 3, "Szeged", 110);
		//emp[1]= new Dolgozo(17, "Moln�r Andrea", "2000-12-24", "H�dmez�v�s�rhely", 96);
	//	dbm.InsertWithPS(emp);
	

		while(1!=0) {
	 menu();
		}
	}
	
	
		static void  menu() {
			System.out.println("\n");
			System.out.println("Men�");
			System.out.println("************");
			System.out.println("0. Kil�p�s");
			System.out.println("1. Dolgoz� List�z�s");
			System.out.println("2. Dolgoz� Besz�r�s");
			System.out.println("3. Dolgoz� T�rl�s");
			System.out.println("4. Dolgoz� M�dos�t�s");
			System.out.println("5. Feladat List�z�s");
			System.out.println("6. Feladat Besz�r�s");
			System.out.println("7. Feladat T�rl�s");
			System.out.println("8. Feladat M�dos�t�s");
			System.out.println("9. IqLista");
			String ms =cm.ReadData("Add meg a v�laszottt men� sz�m�t: ");
			int m= -1;
			if (test(ms)) m= StringToInt(ms);
			switch(m) {
			case 0: System.out.println("A program le�llt"); System.exit(0); break;
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
				System.out.println("Pr�b�ld �jra!");
			
			return false;
			}
			else 
				try {
					int x=Integer.valueOf(s);
					if (x>=0 && x <=9) return true;
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
		
	}
		
	  static void ListingTask() {
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
		String tkod =cm.ReadData("K�rem  a besz�rand� k�dot: ");
		String Ekod =cm.ReadData("K�rem  a Ellenorz� k�dot: ");
		String leiras =cm.ReadData("K�rem  a feladat le�r�s�t: ");
		String datum=cm.ReadData("K�rem  a d�tumot: ");
		String prioritas =cm.ReadData("K�rem  a feladat prioritasat: ");
		dbm.InsertionTask(tkod, Ekod, leiras, datum, prioritas);
		}
	

	private static void Deletion() {
		String Kod =cm.ReadData("k�rem  a t�rlend� k�dot: ");
		dbm.DeleteData(Kod);
		
	}
	
	private static void DeletionTask() {
		String tkod =cm.ReadData("k�rem  a t�rlend� k�dot: ");
		dbm.DeleteData(tkod);
		
	}

	private static void ModitionDolgozo() {
		String Kod =cm.ReadData("k�rem  a M�dos�tand� k�dot: ");
		String iq =cm.ReadData("k�rem  az �j IQ-t: ");
		dbm.Modition(Kod, iq);
	}

	
	private static void ModitionTask() {
		String tkod =cm.ReadData("k�rem  a M�dos�tand� k�dot: ");
		String Ekod =cm.ReadData("k�rem  az �j nevet: ");
		dbm.ModitionTask(tkod, Ekod);
	}

	


	
}
