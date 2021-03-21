
import java.util.ArrayList;

/**
 * <p> Clasa User retine informatii despre fiecare utilizator al retelei
 *  Network.
 *</p>
 */

public class User {
	
	/**
	 * <p>
	 * Structura de date ce retine lista de prieteni a utilizatorului.
	 * Aceasta este ordonata crescator dupa ID-uri.
	 * </p>
	 */
	
	protected ArrayList<User> Prieteni;
	/**
	 * <p>
	 * Retine ID-ul user-ului.
	 * </p>
	 */
	
	protected int ID;
	/**
	 * <p>
	 * Retine numele Userului.
	 * </p>
	 */
	
	protected String Nume;
	/**
	 * <p>
	 * Cele doua stringuri se retin informatii despre starea user-ului
	 * in timpul parcurgerilor DFS si BFS.
	 * Daca este alb, user-ul este nevizitat; daca este gri acesta a fost
	 * vizitat, dar inca este in curs de procesare; cand devine negru
	 * nu mai este nevoie de el in parcurgere.
	 * </p> 
	 */
	
	protected String Culoare_DFS;
	protected String Culoare_BFS;

	/*
	 * <p>
	 * Retine distanta de la user-ul sursa la acest user in timpul parcurgerii BFS. 
	 * </p>
	 */
	protected int distanta;
	
	/**
	 * Constructor fara parametri.
	 */
	public User() {
		Prieteni = new ArrayList<User>();
		ID = 0;
		Nume = null;
		Culoare_DFS = "alb";
		Culoare_BFS = "alb";
		distanta = 0;
	}
	
	/**
	 * Constructor cu parametri.
	 *<p>
	 * @param ID - ID-ul cu care urmeaza sa fie instantiat user-ul.
	 * @param Nume - Numele cu care urmeaza sa fie instantiat user-ul.
	 * </p>
	 */
	public User(int ID, String Nume){
		this.ID = ID;
		this.Nume = Nume;
		Prieteni = new ArrayList<User>();
		Culoare_DFS = "alb";
		Culoare_BFS = "alb";
		distanta = -1;
		
	}
	
}
