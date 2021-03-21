
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * <p>
 * Clasa Network retine informatii despre reteaua de socializare,
 * avand metode de adaugare, stergere, Friend si Unfriend a utilizatorilor, 
 * dar si de afisare a unui grup de prieteni si a gradului acestora de 
 * socializare
 * </p>
 */
public class Network extends User{
	/**
	 * <p>
	 * Structura de date ce contine toti utilizatorii din retea ordonati
	 * crescator dupa ID.
	 *</p>
	 */
	ArrayList<User> Utilizatori ;
	
	/**
	 * <p>
	 * Instantierea clasei Network.
	 * </p>
	 */
	private static final Network INSTANCE = new Network();
	
	/**
	 * <p>
	 * Constructorul clasei Network; rolul lui este de a-l suprascrie pe cel 
	 * default, fiind privat pentru a se realiza instantierea o singura data.
	 * </p>
	 */
	private Network(){
		Utilizatori = new ArrayList<User>();
	}
	
	/**
	 * <p>
	 * Returneaza singura instanta a clasei Network.
	 *</p>
	 * @return single instance of Network
	 */
	public static Network getInstance(){
		return INSTANCE;
	}
	
	/**
	 * <p>
	 * Adauga un nou utilizator in reteaua de socializare.
	 * </p>
	 * <p>
	 * Daca acest utilizator exista deja, se afiseaza "DUPLICATE".
	 * </p>
	 * <p>
	 * Daca utilizatorul a fost adaugat cu succes se afiseaza "OK".
	 * </p>
	 * <p>
	 * Pentru adaugare se cauta intai prin lista utilizatorilor si se verifica
	 * unde trebuie adaugat, astfel incat lista sa ramana sortata.
	 * </p> 
	 * @param ID - ID-ul utilzatorului care va fi adaugat.
	 * @param Nume - Numele utilizatorului care va fi adaugat.
	 */
	public void ADD(int ID, String Nume){
		int i;
		//se verifica daca utilizatorul exista deja
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID == ID){
				System.out.println("DUPLICATE");
				return;
			}
		}
		//se instantiaza un nou utilizator
		User nou = new User(ID, Nume);
		//se cauta printre utilizatori pana se gasesti unul cu ID-ul mai mare,
		//daca se gaseste se adauga la pozitia de dinaintea lui 
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID > ID){
				Utilizatori.add(i,nou);
				System.out.println("OK");
				return;
			}
		}
		//daca nu s-a gasit inca o pozitie, se adauga la sfarsitul listei
			Utilizatori.add(nou);
			System.out.println("OK");
		}
	
	/**
	 * <p>
	 * Sterge un utilizator din lista
	 * </p>
	 * <p>
	 * Daca nu se gaseste se afiseaza mesajul "INEXISTENT".
	 * @param ID - ID-ul userului ce urmeaza a fi sters.
	 * </p>
	 */
	public void REMOVE(int ID){
		int i,j,index = -1;
		
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID == ID){
				index = i;
			}
		}
		//se verifica daca exista in lista
		if(index == -1){
			System.out.println("INEXISTENT");
			return;
		}
		//se cauta in lista prietenilor tuturor utilizatorilor, daca se gaseste
		//cel pe care vrem sa il stergem il scoatem din lista 
		//respectivului utilizator
		for(i = 0; i < Utilizatori.size();i++){
			for(j = 0; j < Utilizatori.get(i).Prieteni.size(); j++){
				if(Utilizatori.get(i).Prieteni.get(j).ID == ID){
					Utilizatori.get(i).Prieteni.remove(j);
				}
			}
		}
		//se sterge utilizatorul
		Utilizatori.remove(index);
		System.out.println("OK");
	}
		
	/**
	 * <p>
	 * Crearea unei relatii de prietenie intre doi utilizatori.
	 *</p>
	 *<p>
	 *Daca unul dintre cei doi sau ambii nu exista se afiseaza mesajul 
	 *"INEXISTENT", in cazul in care erau deja prieteni se afiseaza "OK".
	 *</p>
	 * @param ID1 - ID-ul primului utilizator.
	 * @param ID2 - ID-ul celui de-al doilea utilizator
	 */
	public void FRIEND(int ID1, int ID2){
		int i;
		User user1 = null;
		User user2 = null;
		Boolean bool = false;
		//verific daca cei doi utilizatori exista
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID == ID1){
				user1 = Utilizatori.get(i);
			}
			if(Utilizatori.get(i).ID == ID2){
				user2 = Utilizatori.get(i);
			}
		}
		
		if(user1 == null || user2 == null){
			System.out.println("INEXISTENT");
			return;
		}
		//pentru adaugarea primului utilizator in lista celui de-al doilea caut
		//prin lista de prieteni a celui de-al doilea utilizator pana
		//gasesc unul cu ID-ul mai mare si il adaug la pozitia de dinaintea lui
		for(i = 0; i < user1.Prieteni.size(); i++){
			if(user1.Prieteni.get(i).ID > ID2){
				user1.Prieteni.add(i,user2);
				bool = true;
				break;
			}
		}
		//daca nu a fost gasit se adauga la sfarsitul listei de prieteni
		//utilizatorului cu ID2
		if(bool == false){
			user1.Prieteni.add(user2);
			bool = false;
		}
		//acelasi lucru se face si pentru adaugarea celui de-al doilea
		//utilizator in lista primului
		for(i = 0; i < user2.Prieteni.size(); i++){
			if(user2.Prieteni.get(i).ID > ID1){
				user2.Prieteni.add(i,user1);
				bool = true;
				break;
				
			}
		}
		if(bool == false){
			user2.Prieteni.add(user1);
		}

		System.out.println("OK");
	}
	
	/**
	 * <p>
	 * Stergerea relatiei de prietenie dintre doi utilizatori.
	 * </p>
	 * <p> Daca unul dintre cele doua ID-uri nu a fost gasit sau cei doi 
	 * utilizatori exista dar nu sunt prieteni se afiseaza "INEXISTENT".
	 * </p>
     * @param ID1 - ID-ul primului utilizator.
	 * @param ID2 - ID-ul celui de-al doilea utilizator

	 */
	public void UNFRIEND(int ID1, int ID2){
		int i,index1 = -1,index2 = -1;
		Boolean bool = false;
		//se verifica daca cei doi utilizatori exista
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID == ID1){
				index1 = i;
			}
			if(Utilizatori.get(i).ID == ID2){
				index2 = i;
			}
		}
		if(index1 == -1 || index2 == -1){
			System.out.println("INEXISTENT");
			return;
		}
		//se verifica daca primul utilizator se afla in lista de prieteni a 
		//celui de-al doilea si se sterge din ea in cazul in care exista
		for(i = 0;i < Utilizatori.get(index1).Prieteni.size();i++){
			if(Utilizatori.get(index1).Prieteni.get(i).ID == ID2){
				bool = true;
				Utilizatori.get(index1).Prieteni.remove(i);
			}
		}
		//daca nu exista se afiseaza "INEXISTENT"
		if(bool == false){
			System.out.println("INEXISTENT");
			return;
		}
		//se sterge si cel de-al doilea din lista de prieteni a primului 
		//utilizator daca s-au respectat toate conditiile de pana acum
		for(i = 0;i < Utilizatori.get(index2).Prieteni.size();i++){
			if(Utilizatori.get(index2).Prieteni.get(i).ID == ID1){
				Utilizatori.get(index2).Prieteni.remove(i);
			}
		}
		System.out.println("OK");
	}
	
	/**
	 * <p>
	 * Afisarea detaliilor despre un anumit utilizator:
	 * ID-ul, Numele si ID-urile din lista de prieteni
	 *</p>
	 *<p>
	 *Daca nu se gaseste in lista de utilizatori se afiseaza "INEXISTENT".
	 *</p>
	 * @param ID - ID-ul utilizatorului despre care vrem sa aflam detalii
	 */
	public void print_USER(int ID){
		int i,j;
		//se initializeaza un string cu null
		String rez = null;
		//se parcurge lista de prieteni pentru a vedea daca exista utilizatorul cu
		//ID-ul "ID" si daca este gasit se adauga in string ID-ul, Numele 
		//si ID-urile prietenilor
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).ID == ID){
				rez = new String();
				rez += ID + " " + Utilizatori.get(i).Nume + ": ";
				for(j = 0; j < Utilizatori.get(i).Prieteni.size(); j++){
					rez += Utilizatori.get(i).Prieteni.get(j).ID + " ";
				}
			}
		}
		//in cazul in care stringul ramane null se afiseaza "INEXISTENT"
		if(rez == null){
			System.out.println("INEXISTENT");
		}
		else {
			System.out.println(rez);
		}
	}
	
	/**
	 * <p>
	 * Afisarea intregii retele de socializare.
	 * </p>
	 */
	public void print_NETWORK(){
		//se parcurge lista de utilizatori si se apeleaza functia print_USER
		//pentru fiecare dintre ei
		for(int i = 0; i < Utilizatori.size(); i++){
			print_USER(Utilizatori.get(i).ID);
		}
	}
	
	/**
	 * <p>
	 * Parcurgerea DFS, necesara gasiri tuturor comunitatilor.
	 *</p>
	 * @param user - Utilizatorul din care se porneste parcurgerea
	 * @return the int[] - Returneaza vectorul cu ID-urile comunitatii.
	 */
	public int [] DFS(User user){
		//se foloseste o stiva pentru stocarea temporara a utilizatorilor 
		//in timpul parcurgerii
		Stack <User> stck = new Stack <User> ();
		//se initializeaza un vector pentru a se retine ID-urile utilizatorilor
		// care fac parte din comunitate
		int [] v = new int [Utilizatori.size()];
		//se initializeaza un contor care numara cati utilizatori fac parte 
		//din comunitate
		int i,contor = 0;
		int [] rez;
		Boolean bool = false;
		User topLevel;
		//se adauga ID-ul nodului sursa in vector si utilizatorul in stiva
		v[contor] = user.ID;
		stck.add(user);
		contor++;
		//culoarea acestuia devine gri
		user.Culoare_DFS = "gri";
		
		while(!stck.isEmpty()){
			//se initializeaza topLevel cu utilizatorul din varful stivei
			topLevel = stck.peek();
			//se caut prin lista de prieteni a acestuia pana se gaseste unul nevizitat
			for(i = 0; i < topLevel.Prieteni.size(); i++){
				//daca se gaseste se adauga in stiva si in vectorul de ID-uri
				//si culoarea lui devine gri
				if(topLevel.Prieteni.get(i).Culoare_DFS == "alb"){
					v[contor] = topLevel.Prieteni.get(i).ID;
					contor++;
					topLevel.Prieteni.get(i).Culoare_DFS = "gri";
					stck.add(topLevel.Prieteni.get(i));
					bool = true;
					break;
				}
			}
			//daca nu s-a gasit niciun prieten nevizitat culoarea lui topLevel
			//devine negru si se elimina ultimul element din stiva
			if(bool == false){
				topLevel.Culoare_DFS = "negru";
				stck.pop();
			}
			bool = false;
		}
		//se aloca un nou vector de dimensiunea contor, pentru a sti cati
		//utilizatori au fost gasiti si se muta din v in acesta
		rez = new int[contor];
		for(i = 0; i < contor; i++){
			rez[i] = v[i];
		}
		//se returneaza acest vector
		return rez;
	}
	
	/**
	 * <p>
	 * Afisarea comunitatilor.
	 * </p>
	 */
	public void print_COMMUNITIES(){
		int i,j;
		//se cauta prin lista de utilizatori si se apeleaza DFS pentru orice 
		//utilizator ce este nevizitat
		for(i = 0; i < Utilizatori.size(); i++){
			//daca s-a gasit a fi nevizitat se afiseaza lista de ID-uri a
			//comunitatii din care face parte
			if(Utilizatori.get(i).Culoare_DFS != "negru"){
				int [] v = DFS(Utilizatori.get(i));
				String rez = v.length + ": ";
				for(j = 0; j < v.length; j++){
					rez += v[j] + " ";
				}
				System.out.println(rez);
			}
		}
		//se reseteaza culorile tuturor utilizatorilor
		ResetCuloare_DFS();
	}
	
	/**
	 * <p>
	 * Parcurgerea BFS, necesara gasiri celui mai lung drum de la un utilizator
	 * la altul.
	 *</p>
	 * @param user - Utilizatorul din care se porneste parcurgerea
	 * @return -returneaza distanta maxima de la utilizatorul dat ca parametru
	 *  la oricare altul din retea.
	 */
	public int BFS(User user){
		//se foloseste o coada pentru stocarea temporara a utilizatorilor
		//in timpul parcurgerii
		Queue <User> queue = new LinkedList<User>();
		int i,j,rez = 0;
		User topLevel;
		//culoarea utilizatorului nodului sursa devine gri si se adauga in coada
		user.Culoare_BFS = "gri";
		queue.add(user);
		while(!queue.isEmpty()){
			//topLevel se initializeaza cu elemntul de la inceputul cozii
			topLevel = queue.poll();
			//se cauta printre prietenii lui topLevel
			for(j = 0; j < topLevel.Prieteni.size(); j++){
				//daca se gaseste un utilizator nevizitat, culoarea lui devine 
				//gri, se adauga in coada si distanta lui devine egala cu 
				//distanta lui topLevel+1
				if(topLevel.Prieteni.get(j).Culoare_BFS == "alb"){
					topLevel.Prieteni.get(j).Culoare_BFS = "gri";
					topLevel.Prieteni.get(j).distanta = topLevel.distanta + 1;
					queue.add(topLevel.Prieteni.get(j));
				}
			}
			//dupa ce se termina de parcurs toti prietenii nevizitati ai lui 
			//topLevel, culoarea acestuia devine negru
			topLevel.Culoare_BFS = "negru";
		}
		//se verifica in lista de utilizatori a carui distanta este cea mai 
		//mare fata de cel dat ca parametru
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).distanta > rez){
				rez = Utilizatori.get(i).distanta;
			}
		}
		//dupa ce a fost gasit se reseteaza culorile tuturor utilizatorilor,
		//dar si distanta
		ResetCuloare_BFS();
		Reset_distanta();
		//se returneaza distanta maxima
		return rez;
	}
	
	/**
	 * <p>
	 * Afisarea gradului de socializare al comunitatii din care face parte 
	 * utilizatorul cu idul ID.
	 *</p>
	 *<p>
	 *In caz ca acesta nu exista in lista de utilizatori se afiseaza 
	 *"INEXISTENT"
	 *</p>
	 * @param ID  - ID-ul utilizatorului pentru care se vrea afisarea gradului
	 * de socializare a comunitatii din care face parte.
	 */
	public void print_STRENGTH(int ID){
		int i,j,N = -1,D = -1;
		Boolean bool = false;
		int [] rez = new int[0];
		//se verifica daca exista utilizatorul
		for(i = 0; i < Utilizatori.size();i++){
			if(Utilizatori.get(i).ID == ID){
				bool = true;
			}
		}
		if(bool == false){
			System.out.println("INEXISTENT");
			return;
		}
		bool = false;
		//se parcurge lista de utilizatori si se verifica din ce comunitate
		//face parte
		for(i = 0; i < Utilizatori.size(); i++){
			if(Utilizatori.get(i).Culoare_DFS != "negru"){
				//se salveaza in rez comunitatea gasita
				rez = DFS(Utilizatori.get(i));
				ResetCuloare_DFS();
				for(j = 0; j < rez.length; j++){
					//daca se gaseste utilizatorul se incheie parcurgerile DFS
					if(rez[j] == ID){
						bool = true;
					}
				}
				if(bool == true){
					break;
				}
			}
		}
		//daca marimea comunitatii este 1, se afiseaza 0
		N = rez.length;
		if( N == 1){
			System.out.println("0");
			return;
		}
		//se aplica BFS din fiecare nod al comunitatii si se verifica maximul
		//returnat
		for(i = 0; i < Utilizatori.size();i++)
			for(j = 0; j < N; j++){
				if(Utilizatori.get(i).ID == rez[j]){
					if(D < BFS(Utilizatori.get(i))){
						D = BFS(Utilizatori.get(i));
					}
				}
		}
		//se aplica formula de aflare a gradului de socializare si se afiseaza
		System.out.println((N - D)*100/(N - 1));
		
	}
	
	/**
	 * <p>
	 * Reseteaza culorile pentru parcurgerea BFS.
	 * </p>
	 */
	public void ResetCuloare_BFS(){
		for(int i = 0; i < Utilizatori.size(); i++){
			Utilizatori.get(i).Culoare_BFS = "alb";
		}
	}
	/**
	 * <p>
	 * Reseteaza culorile pentru parcurgerea DFS.
	 * </p>
	 */
	public void ResetCuloare_DFS(){
		for(int i = 0; i < Utilizatori.size(); i++){
			Utilizatori.get(i).Culoare_DFS = "alb";
		}

	}
	/**
	 * <p>
	 * Reseteaza distanta fiecarui utilizator.
	 * </p>
	 */
	public void Reset_distanta(){
		for(int i = 0; i < Utilizatori.size(); i++){
			Utilizatori.get(i).distanta = 0;
		}
	}
	
}