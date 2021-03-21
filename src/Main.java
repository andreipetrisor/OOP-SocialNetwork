
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * This execution entry point class handles parsing and executing commands from the input
 * file.
 * </p>
 */
public class Main {

	/**
	 * <p>
	 * Execution entry point.
	 *</p>
	 *
	 * @param args the name of the file containing commands to be executed
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java -cp <classpath> Main <input file>");
			System.exit(1);
		}
		//instantierea clasei Network
		Network network = Network.getInstance();
		//instantierea clasei FileParser
		FileParser parser = new FileParser(args[0]);
		//instantierea unei liste in care se vor retine liniile pe rand
		List<String> line = new ArrayList<String>();
		
		//se deschide fisierul ce urmeaza a fi procesat
		parser.open();
		
		//se parcurge linie cu linie fisierul si se compara fiecare string 
		//pentru a vedea ce metoda se apeleaza
		while((line = parser.parseNextLine()) != null){
		
			if(Objects.equals(line.get(0) ,"add")){
				network.ADD(Integer.parseInt(line.get(1)),line.get(2));	
			}
				if(Objects.equals(line.get(0), "remove")){
				network.REMOVE(Integer.parseInt(line.get(1)));
			}
			if(Objects.equals(line.get(0), "print")){
				if(Objects.equals(line.get(1), "network"))
					network.print_NETWORK();
				if(Objects.equals(line.get(1), "user")){
					network.print_USER(Integer.parseInt(line.get(2)));
				}
				if(Objects.equals(line.get(1), "communities")){
					network.print_COMMUNITIES();
				}
				if(Objects.equals(line.get(1), "strength")){
					network.print_STRENGTH(Integer.parseInt(line.get(2)));
				}
			}
			if(Objects.equals(line.get(0), "friend")){
				network.FRIEND(Integer.parseInt(line.get(1)),Integer.parseInt(line.get(2)));
			}
			if(Objects.equals(line.get(0), "unfriend")){
				network.UNFRIEND(Integer.parseInt(line.get(1)),Integer.parseInt(line.get(2)));
				}
			
			}
		
		//se inchide fisierul
		parser.close();
		
	}

}	