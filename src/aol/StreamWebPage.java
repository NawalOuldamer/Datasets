/**
 * 
 */
package aol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.tika.language.LanguageIdentifier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author ould
 *
 */
public class StreamWebPage {

	/**
	 * @param args
	 */


	
	public static String path_file_url ="";
	public static String path_documents_collection_xml ="";
	public static String path_documents_collection_text ="";

	public static void main(String[] args) throws IOException {		
		path_file_url = args[0];
		path_documents_collection_xml = "/Volumes/work/data/aol-query-log/corpus/document_xml/";
		path_documents_collection_text = "/Volumes/work/data/aol-query-log/corpus/document_text/";
		Scanner sn = new Scanner(new File(path_file_url));
		while (sn.hasNextLine()) {			
			String vect[] = sn.nextLine().split("\t"); 
			
			getTextUrl(Integer.parseInt(vect[0]), vect[1]);	
		}
		sn.close();
	}


	public static void getTextUrl(int id ,String url) throws IOException{
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			String title = doc.title();
			LanguageIdentifier identifier = new LanguageIdentifier(doc.text());
			String language = identifier.getLanguage();
			if(language.equals("en")){
				fileDocSave(id, title, doc.text(), url);
				fileTextSave(id, title, doc.text());
			}


		} catch (Exception e) {
		}
	}


	public static void fileDocSave(int id, String title, String text, String url) throws IOException{
		
			FileWriter pw = new FileWriter(path_documents_collection_xml+String.valueOf(id)+".xml", true);
			pw.write("<doc>"+"\n");
			pw.write("<docno>" + id + "</docno>" +"\n");
			pw.write("<url>" + url + "</url>" +"\n");
			pw.write("<title>" + title + "</title>" +"\n");
			pw.write("<text>" + "\n");
			pw.write(text + "\n");
			pw.write("</text>" + "\n");
			pw.write("</doc>");
			pw.close();
		
	}

	public static void fileTextSave(int id, String title, String text) throws IOException{
			FileWriter pw = new FileWriter(path_documents_collection_text+String.valueOf(id)+".txt", true);
			pw.write(title+" "+text);
			pw.close();
		
	} 

}



