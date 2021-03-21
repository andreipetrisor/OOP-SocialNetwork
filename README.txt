Clasa User contine informatii despre fiecare utilizator, avand campurile:
 - Prieteni - o structura de date de tip ArrayList care retine toti prietenii 
utilizatorului
 - ID - un intreg ce retine ID-ul utilizatorului
 - Nume - un String ce contine numele utilizatorului
 - distanta - un intreg pentru distanta
 - Culoare_BFS si Culoare_DFS - Stringuri pentru culorile utilizatorilor din 
timpul parcurgerilor DFS si BFS
	Aceasta contine de asemenea si 2 constructori: unul cu parametrii ID si
Nume iar celalalt fara.


	Clasa Network retine informatii despre reteaua de socializare, avand
campurile: 
 - Utilizatori - o structura de date de tip ArrayList care retine toti
utilizatorii din retea
 - INSTANCE - instanta pentru clasa Network de tip static final, pentru a 
nu se permite instantierea acesteia decat o singura data
	-din acest motiv, constructorul are ca specificator de acces private,
	suprascriind constructorul default
	-instanta clasei se transmite prin intermediul metodei getInstance()
Metodele acestei clase sunt:
 - public void getInstance() - returneaza singura instanta a clasei
 - public void ADD(int ID) - cauta in lista de utilizatori acest ID, daca 
	nu il gaseste il adauga si afiseaza mesajul "OK", in caz contrar 
afiseaza "DUPLICATE".
 - public void REMOVE(int ID) - cauta in lista de utilizatori acest ID, daca il
	 gaseste il sterge si afiseaza mesajul "OK", in caz contrar afiseaza 
	"INEXISTENT".
 - public void FRIEND(int ID1, int ID2) -  verifica daca cei doi utilizatori
	 exista, daca nu il gaseste pe unul dintre ei(sau pe amandoi) se va 
	afisa "INEXISTENT"; in caz contrar se va parcurge lista de prieteni 
	a fiecaruia si se vor adauga reciproc
	- in timpul celor doua adaugari se verifica intai locul in care trebuie
	adaugati pentru ca lista de prieteni a celor doi sa ramana sortata
- public void UNFRIEND(int ID1, int ID2) -  verifica daca cei doi utilizatori
	 exista, daca nu il gaseste pe unul dintre ei(sau amandoi) se afiseaza 
	"INEXISTENT"; apoi se verifica daca cei doi sunt prieteni, in cazul in 
	care nu sunt se afiseaza acelasi mesaj. Altfel se parcurg listele de 
	prieteni ale celor doi si se sterg reciproc.
 - public void Print_USER(int ID) - daca utilizatorul exista se afiseaza ID-ul,
	 Numele si lista ID-urilor acestora sortate crescator
	- in cazul in care acesta nu se gaseste in lista se afiseaza "INEXISTENT" 
 - public void Print_NETWORK() - parcurge lista de utilizatori si afiseaza 
	informatii despre fiecare, apeland functia print_USER(int ID)
 - public int [] DFS(User user) - face o parcurgere in adancime avand ca nod 
	sursa utilizatorul user 
	- aceasta metoda returneaza un vector cu ID-urile comunitatii din care
	face parte "user"
 - public void print_COMMUNITIES() - afiseaza toate comunitatile din lista
	de utilizatori
 - public void int BFS(User user) -  face o parcurgere in latime avand ca nod 
	sursa utilizatorul user 
	- retine distanta de la acesta la oricare utilizator, in final 
	returnand distanta maxima
 - public void print_STRENGTH(int ID) - cauta comunitatea din care face parte
	utilizatorul cu acest ID (cu ajutorul metodei DFS), astfel returnandu-se
	un vector cu utilizatorii din aceasta comunitate si aflandu-se N.
	- daca N este 1 se afiseaza "0" si se iese din functie
	- dupa ce se afla comunitatea se aplica BFS din fiecare nod al 
	comunitatii si se gaseste distanta maxima D
	- in cele din urma se aplica formula pentru aflarea gradului de 
	socializare si se afiseaza
	- in cazul in care ID nu a fost gasit se afiseaza "INEXISTENT"
 - Metodele: public void ResestCuloare_BFS(),public void ResestCuloare_DFS() si 
	public void ResestCuloare_distanta() reseteaza aceste campuri ale tuturor 
	utilizatorilor in urma parcurgerilor BFS si DFS


	Clasa Main este folosita pentru citirea din fisier si apelarea
metodelor din Network atunci cand este cazul.


	Mai multe detalii despre implementarea metodelor se regasesc in 
comentarii.

