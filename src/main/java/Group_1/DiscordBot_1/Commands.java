package Group_1.DiscordBot_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Commands {
	
	Methods mtd = new Methods();
	
	DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
	Date date = new Date();

	String stringToSend = null;
	
	public String movieList(){
		
		return mtd.list("bigthink.txt");
	}
	
	public String movieRandom() {
		FileReader file3;
		try {
			file3 = new FileReader("bigthink.txt");
			BufferedReader in = new BufferedReader(file3);
    		
    		String str;
    		
    		List<String> list = new ArrayList<String>();
				while((str = in.readLine()) != null) {
					list.add(str);
				}
			
    		String[] stringArg = list.toArray(new String[0]);
    		
    		int randomInt = ThreadLocalRandom.current().nextInt(stringArg.length);
    		stringToSend =  stringArg[randomInt].toString();
    		
    		in.close();
		} catch (FileNotFoundException e) {
			return ("Something with the movie list is corrupted");
		} catch (IOException e) {
			return ("Something went wrong with my code -_-");
		}
		
		return stringToSend;
	}
	
	public String movieAdd (List<String> argsList) {
		
		PrintWriter out = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("allmovies.txt", true);
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
			return ("Something went wrong with my code -_-");
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
		        // Closing the file writers failed for some obscure reason
		     }
		}
		
		try {
    		PrintWriter writer1 = new PrintWriter(new FileWriter("bigthink.txt"));
    		BufferedReader reader1 = new BufferedReader(new FileReader("allmovies.txt"));
    		
    		String inputLine = reader1.readLine();
    		
    		while(inputLine != null ) {
    		
    			boolean flag = false;
    			
        		BufferedReader reader2 = new BufferedReader(new FileReader("deletelist.txt"));

        		String inputLine2 = reader2.readLine();
        		
        		while(inputLine2 != null) {
        			
        			if(inputLine.equals(inputLine2)) {
        				
        				flag = true;
        				break;
        			}
        			
        			inputLine2 = reader2.readLine(); 
        		}
        		
        		if(!flag) {
    				writer1.println(inputLine);
    				
    			}
        		
        		inputLine = reader1.readLine();
    			reader2.close();
    		}
    		
    		stringToSend = ("'" + argsList.toString().replace(",", "")
    				.replace("[", "")  //remove the right bracket
    			    .replace("]", "")  //remove the left bracket
    			    .trim()           //remove trailing spaces from partially initialized arrays
    			    + "'" + " was added to the list by ");
    		
    		writer1.flush();
    		reader1.close();
    		writer1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stringToSend;
	}
	
	public String movieDelete(List<String> argsList) {
		
		mtd.writeTo("deleteList.txt", argsList);
		
//		try {
//
//    			PrintWriter out1 = null;
//	    		BufferedWriter bw1 = null;
//	    		FileWriter fw1 = null;
//	    		try {
//	    			fw1 = new FileWriter("deletelist.txt", true);
//	    		    bw1 = new BufferedWriter(fw1);
//	    		    out1 = new PrintWriter(bw1);
//	    			out1.println(argsList.toString()
//	    					.replace(",", "")
//	    				    .replace("[", "")
//	    				    .replace("]", "")
//	    				    .trim()
//	    					);
//	    			
//	    		}
//	    		catch(IOException e) {
//	    			return ("Something went wrong with my code -_-");
//	    		}
//	    		finally{
//	    		     try{
//	    		        if( out1 != null ){
//	    		           out1.close(); // Will close bw and fw too
//	    		        }
//	    		        else if( bw1 != null ){
//	    		           bw1.close(); // Will close fw too
//	    		        }
//	    		        else if( fw1 != null ){
//	    		           fw1.close();
//	    		        }
//	    		        else{
//	    		        }
//	    		     }
//	    		     catch( IOException e ){
//	    		    	 stringToSend = ("Something went wrong with trying to read the movie to delete -_-");
//	    		     }
//	    		}
    			
    		//
		try {
    		PrintWriter writer = new PrintWriter(new FileWriter("bigthink.txt"));
    		BufferedReader reader = new BufferedReader(new FileReader("allmovies.txt"));
    		
    		String DinputLine = reader.readLine();
    		
    		while(DinputLine != null ) {
    		
    			boolean flag = false;
    			
        		BufferedReader reader2 = new BufferedReader(new FileReader("deletelist.txt"));

        		String DinputLine2 = reader2.readLine();
        		
        		while(DinputLine2 != null) {
        			
        			if(DinputLine.equals(DinputLine2)) {
        				
        				flag = true;
        				break;
        			}
        			
        			DinputLine2 = reader2.readLine(); 
        		}
        		
        		if(!flag) {
    				writer.println(DinputLine);
    			}
        		
        		DinputLine = reader.readLine();
    			reader2.close();
    		}
    		
    		writer.flush();
    		reader.close();
    		writer.close();
    		
    		stringToSend = ("'" + argsList.toString().replace(",", "")
    				.replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim()           //remove trailing spaces from partially initialized arrays
				    + "'" + " is now off the list");
    		
			} catch (IOException e) {
				stringToSend = ("Something went wrong with my code -_-");
				e.printStackTrace();
			}
		return stringToSend;
	}
	
//	public String newdebt(List<String> argsList) {
//		
//		PrintWriter out = null;
//		BufferedWriter bw = null;
//		FileWriter fw = null;
//		
//		try {
//			fw = new FileWriter("alldebt.txt", true);
//		    bw = new BufferedWriter(fw);
//		    out = new PrintWriter(bw);
//			out.println(argsList.get(0).toString()
//					.replace(",", "")
//				    .replace("[", "")
//				    .replace("]", "")
//				    .trim() 
//				    + ":"
//				    + argsList.get(1).toString()
//					.replace(",", "")
//				    .replace("[", "")
//				    .replace("]", "")
//				    .trim()
//				    + " entered on "
//				    + dateformat.format(date)
//					);
//			
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//			return ("Something went wrong with opening alldebt -_-");
//		}
//		finally{
//		     try{
//		        if( out != null ){
//		           out.close(); // Will close bw and fw too
//		        }
//		        else if( bw != null ){
//		           bw.close(); // Will close fw too
//		        }
//		        else if( fw != null ){
//		           fw.close();
//		        }
//		        else{
//		        }
//		     }
//		     catch( IOException e ){
//		        // Closing the file writers failed for some obscure reason
//		     }
//		}
//		
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader("debt.txt"));
//			String DinputLine = reader.readLine();
//			fw = new FileWriter("pastdebt.txt", true);
//		    bw = new BufferedWriter(fw);
//		    out = new PrintWriter(bw);
//		    
//		    while(DinputLine != null) {
//		    	
//		    	
//		    	if(DinputLine.substring(0, 1).equals(argsList.get(0).substring(0, 1))) {
//		    		
//		    		out.println(DinputLine);
//		    		break;
//		    	}
//		    	
//		    }
//			
//		    reader.close();
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//			return ("Something went wrong with opening pastdebt -_-");
//		}
//		finally{
//		     try{
//		        if( out != null ){
//		           out.close(); // Will close bw and fw too
//		        }
//		        else if( bw != null ){
//		           bw.close(); // Will close fw too
//		        }
//		        else if( fw != null ){
//		           fw.close();
//		        }
//		        else{
//		        }
//		     }
//		     catch( IOException e ){
//		        // Closing the file writers failed for some obscure reason
//		     }
//		}
//		try {
//			PrintWriter writer = new PrintWriter(new FileWriter("debt.txt"));
//			BufferedReader reader = new BufferedReader(new FileReader("alldebt.txt"));
//    		
//    		String DinputLine = reader.readLine();
//    		
//    		while(DinputLine != null ) {
//        		System.out.print("check");
//    			boolean flag = false;
//    			
//        		BufferedReader reader2 = new BufferedReader(new FileReader("pastdebt.txt"));
//
//        		String DinputLine2 = reader2.readLine();
//        		
//        		while(DinputLine2 != null) {
//        			
//        			if(DinputLine.equals(DinputLine2)) {
//        				
//        				flag = true;
//        				break;
//        			}
//        			
//        			DinputLine2 = reader2.readLine(); 
//        		}
//        		
//        		if(!flag) {
//    				writer.println(DinputLine);
//    			}
//        		
//        		DinputLine = reader.readLine();
//    			reader2.close();
//    		}
//    		
//    		writer.flush();
//    		reader.close();
//    		writer.close();
//    		
//    		stringToSend = (argsList.get(0).toString().replace(",", "")
//    				.replace("[", "")  //remove the right bracket
//				    .replace("]", "")  //remove the left bracket
//				    .trim()           //remove trailing spaces from partially initialized arrays
//				    + "'s balence is now " +
//				    argsList.get(1).toString().replace(",", "")
//    				.replace("[", "")  //remove the right bracket
//				    .replace("]", "")  //remove the left bracket
//				    .trim()           //remove trailing spaces from partially initialized arrays
//					+ " steamBERTS"
//				    );
//    	}
//		catch(IOException e) {
//		e.printStackTrace();
//		return "Something went wrong with my code -_-";
//		}
//		
//		return stringToSend;
//	}
	
	public String debtList(){
		FileReader file2;
		try {
			file2 = new FileReader("debt.txt");
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
	
	public String eightBall() {
		FileReader file3;
		try {
			file3 = new FileReader("8ball.txt");
			BufferedReader in = new BufferedReader(file3);
    		
    		String str;
    		
    		List<String> list = new ArrayList<String>();
				while((str = in.readLine()) != null) {
					list.add(str);
				}
			
    		String[] stringArg = list.toArray(new String[0]);
    		
    		int randomInt = ThreadLocalRandom.current().nextInt(stringArg.length);
    		stringToSend =  stringArg[randomInt].toString();
    		
    		in.close();
		} catch (FileNotFoundException e) {
			return ("Something with the 8ball text doc is corrupted");
		} catch (IOException e) {
			return ("Something went wrong with my code -_-");
		}
		
		return stringToSend;
	}
	
	public String eightAdd(List<String> argsList) {
		
		mtd.writeTo("8ball.txt", argsList);
			
			stringToSend = ("'" + argsList.toString()
					.replace(",", "")
    				.replace("[", "")  //remove the right bracket
    			    .replace("]", "")  //remove the left bracket
    			    .trim()           //remove trailing spaces from partially initialized arrays
    			    + "'" + " was put in the 8ball by ");
			
		return stringToSend;
	}
}
