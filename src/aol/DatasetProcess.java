/**
 * 
 */
package aol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author ould
 *
 */
public class DatasetProcess {

	/**
	 * 
	 */
	public DatasetProcess() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */

	public static HashMap<String, Integer> map_id_url = new HashMap<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//getClikedUrls();
		file();
		//urlSelection();
	}
	
	public static void getClikedUrls() throws IOException{
		FileWriter file = new FileWriter("aol-dataset-file-cliked-url.txt");
		Scanner s = new Scanner(new File("aol-dataset-file.txt"));
		int count = 1;
		while (s.hasNextLine()) {
			String line = s.nextLine();			
			if(!line.startsWith("AnonID")){
				String [] c = line.split("\t");
				if(c.length==5){
					if(map_id_url.containsKey(c[4])){
						file.write(map_id_url.get(c[4]) +"\t"+line +"\n");
						count++;
					}
					else {
						map_id_url.put(c[4], count);
						file.write(count +"\t"+line+"\n");
						count++;
					}
				}
				file.flush();
			}
		}
		s.close();
		file.close();
	}
	
	public static void urlSelection() throws IOException{
		FileWriter file = new FileWriter("docId_numberClkick.txt");
		FileWriter file2 = new FileWriter("docId_numberClkick_usersCliked.txt");

		Scanner s = new Scanner(new File("docId"));
		while (s.hasNextLine()) {
			HashSet<String> user_url_clik = new HashSet<>();
			String line = s.nextLine();
			System.out.println(line);
			Scanner sc = new Scanner(new File("docId_userId.txt"));
			while (sc.hasNextLine()) {
				String string = sc.nextLine();
				System.err.println(string);
				String [] c = string.split("\t");
				if(c[0].equalsIgnoreCase(line)){
					System.out.println("tto");
					if(!user_url_clik.contains(c[1])){
						user_url_clik.add(c[1]);
					}
				}				
			}
			sc.close();
			file.write(line+ " "+user_url_clik.size());
			file.flush();
			file2.write(line+" ");
			for (Iterator iterator = user_url_clik.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				file2.write(string+" ");
				file2.flush();
			}
			file2.write("\n");
			file.write("\n");
					
		}
		
		s.close();
		file.close();
		file2.close();	
	}
	
	public static void file() throws IOException{
		FileWriter f = new FileWriter("./data/aol/aol.txt");
		
		Scanner  s = new Scanner(new File("./data/aol/aol-dataset-file-cliked-url-cleanned.txt"));
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String [] c = line.split("\t");
			f.write(c[0]+"\t"+c[1]+"\t"+c[2]+"\t"+c[3]+"\t"+c[4]+"\t"+c[5]+"\n");
			f.flush();

		}
		s.close();
		f.close();
	}
	public static void URLSelection() throws IOException{
		FileWriter file = new FileWriter("docId_numberClkick.txt");
		FileWriter file2 = new FileWriter("docId_numberClkick_usersCliked.txt");

		String [] f = new File("./files/").list(); 
		for (int i=0; i<f.length;i++) {
			HashSet<String> user_url_clik = new HashSet<>();
			Scanner  s = new Scanner(new File("./files/"+f));
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String [] c = line.split("\t");
				if(!user_url_clik.contains(c[1])){
					user_url_clik.add(c[1]);
				}
			}
			s.close();
			file.write(f[i]+ " "+user_url_clik.size());
			file.flush();
			file2.write(f[i]+" ");
			for (Iterator<String> iterator = user_url_clik.iterator(); iterator.hasNext();) {
				String string = iterator.next();
				file2.write(string+" ");
				file2.flush();
			}
			file2.write("\n");
			file.write("\n");
			
			
		}
		file.close();
		file2.close();
	}
}
