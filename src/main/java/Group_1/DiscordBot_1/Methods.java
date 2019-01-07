package Group_1.DiscordBot_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Methods {

	public String list(String txtDoc) {
		
		String stringToSend = null;
		FileReader file2;
		
		try {
			file2 = new FileReader(txtDoc);
			BufferedReader in = new BufferedReader(file2);
	    		
			int counter = 0;
	    	String str;
	    	
	    	List<String> list = new ArrayList<String>();
	    		while((str = in.readLine()) != null) {
					list.add(str);
	    		}
				
	    	String[] stringArg = list.toArray(new String[0]);
	    		
		    	while(counter != stringArg.length) {
		    		stringToSend += stringArg[counter].toString() + "\n";
		    		counter++;
		    	}
		    	
    		in.close();
			
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringToSend.substring(4);
	}
	
	public void writeTo(String txtDoc, List<String> argsList) {
		
		PrintWriter out = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(txtDoc, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
			out.println(argsList.toString()
					.replace(",", "")
				    .replace("[", "")
				    .replace("]", "")
				    .trim()
					);
		}
		catch(IOException e) {
			System.out.print("Something went wrong with adding your request -_-");
		}
		finally{
		     try{
		        if( out != null ){
		           out.close(); // Will close bw and fw too
		        }
		        else if( bw != null ){
		           bw.close(); // Will close fw too
		        }
		        else if( fw != null ){
		           fw.close();
		        }
		        else{
		        }
		     }
		     catch( IOException e ){
		    	 System.out.print ("Something went wrong with closing the document while adding your request -_-");
		     }
		}
	}
}
	

