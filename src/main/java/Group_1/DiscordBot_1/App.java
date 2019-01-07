package Group_1.DiscordBot_1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class App extends ListenerAdapter{
	
    public static void main(String[] args) throws Exception{
    	
    	
    	JDA jda = new JDABuilder(AccountType.BOT)
    			.setToken(Ref.token)
    			.buildBlocking();
        jda.addEventListener(new App());
        jda.getSelfUser();
        jda.getPresence().setGame(Game.playing(">ring for help"));
        
        
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
    	
    	try {
    	//Objects
    	User objUser = evt.getAuthor();
    	MessageChannel objMsgCh = evt.getChannel();
    	Message objMsg = evt.getMessage();
    	Timer timer = new Timer();
		
    	DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
    	Date date = new Date();
    	
    	//Commands
    	//These commands are called via a switch statement and calls a specific
    	//command when the case word is entered into a Discord Channel
    	
    	String[] srtArgs = objMsg.getContentRaw().split(" ");
    	String command = srtArgs[0].substring(1);
    	
	    List<String> argsList = new ArrayList<>(Arrays.asList(srtArgs));
	    argsList.remove(0);
	    
	    Commands cmd = new Commands();
    	
	    	//If the message contains the ">" character then the if statement occurs and checks for
	    	//a correct command
	    	if (srtArgs[0].contains(Ref.prefix)) {
	    	
	    		switch(command) {
		    	
	    		//displays all available commands for users to use
		    	case "ring":
		    		objMsgCh.sendMessage("Here is what i can do for you..\n"
		    				+ ">list to view the current movies on the list\n"
		    				+ ">add **movie** to add a movie to the watchlist\n"
		    				+ ">random to get a random movie from the list\n"
		    				+ ">delete **movie** to remove a movie from the list\n"
		    				+ ">8ball to choose your fate\n"
		    				+ ">add8 **add prophecy here** to add to the cauldron of fate\n"
		    				+ ">d **#** to roll a **#** sided die (can have any number of sides)\n"
		    				+ ">version to get the current version of this bot and all updates\n"
		    				).queue();
		    		break;
		    		
		    	//displays the current version of the bot along with dated updates	
		    	case "version":
		    		
		    		objMsgCh.sendMessage("\n"
		    				+ "Group Secretary Bot v2.05 updated on 09/17/2018\n"
		    				+ "\n 6/4/18 ~ made a separate class for commands for readability and easier use.\n"
		    				+ "		  	 ~ added a simple 8ball command that also lets users insert their own 8ball responces.\n"
		    				+ "\n 8/9/18 ~ certain commands delete the user's line to cut down on server clutter.\n"
		    				+ "		  	 ~ added extended responces to certain commands to make up for the lack of information.\n"
		    				+ "\n 9/1/18 ~ Bot now detects when a picture or other media is posted.\n"
		    				+ "\n 9/17/18 ~ new status message informs users of how to find all available commands.\n"
		    				+ "\n 9/18/18 ~ added a new dice roll function.\n").queue();
		    		break;
		    		
		    	//fetches all movies that aren't on the deleteList.txt and prints them to the user	
		    	case "list":
		    		
		    		objMsgCh.sendMessage( "Here is all current movies on the list..." + "\n" + cmd.movieList()).queue(m -> {
			    			timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
			    				
			    			}, 1000);
		    		});
		    		
		    		break;
					
		    	//fetches a random movie from the bigthink.txt and prints it to the user	
		    	case "random":
		    		
		    		objMsgCh.sendMessage("How about... " + cmd.movieRandom()).queue(m -> {
			    			timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
			    			}, 1000);
		    		});
		    		break;
		    	
		    	//takes a user inputed movie title and inserts the movie onto the allmovies.txt	
		    	case "add": 
		    		
		    		objMsgCh.sendMessage(cmd.movieAdd(argsList) + objUser.getAsMention() 
		    		+ " on " 
		    		+ dateformat.format(date)).queue(m -> {
			    			timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
		    				}, 1000);
		    		});
		    		break;
			    
		    	//takes a user inputed movie that is in allmovies.txt and adds it to the deletelist.txt
		    	case "delete":
		    		
		    		objMsgCh.sendMessage(cmd.movieDelete(argsList)).queue(m -> {
			    			timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
			    			}, 1000);
		    		});
		    		break;
		    		
	//	    	case "newdebt":
	//	    		
	//	    		objMsgCh.sendMessage(cmd.newdebt(argsList)).queue();
	//	    		break;
		    		
		    	case "debtlist":
		    		
		    		objMsgCh.sendMessage("Oy vey... " + "\n" + cmd.debtList()).queue(m -> {
			    			timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
		    				}, 1000);
		    		});
		    		break;
		    		
		    	//fetches a random line from the 8ball.txt and prints it to the user
		    	case "8ball":
		    		
		    		objMsgCh.sendMessage(cmd.eightBall()).queue();
		    		break;
		    		
		    	//takes a user submitted sentence and puts it into the 8ball.txt
		    	case "add8":
		    		
		    		objMsgCh.sendMessage(cmd.eightAdd(argsList) + objUser.getAsMention()).queue(m -> {
		    				timer.schedule(new TimerTask() {
			    				@Override
								public void run() {
									objMsg.delete().queue();
			    				}
			    			}, 1000);
		    		});
		    		break;
		    		
		    	//takes a user submitted number and finds a random number between that number and 1
		    	case "d":
		    			
		    		List<Integer> newList = argsList.stream()
		    					.map(s -> Integer.parseInt(s))
		    					.collect(Collectors.toList());
		    		int randomRoll = ThreadLocalRandom.current().nextInt(1, newList.get(0));
		    		objMsgCh.sendMessage(objUser.getAsMention() 
		    				+ " rolled a " + randomRoll 
		    				+ " from a d" + newList.toString()
		    										.replace("[", "")
		    										.replace("]", "")
		    										.trim()).queue(m -> {
		    							    			timer.schedule(new TimerTask() {
		    								    			@Override
		    												public void run() {
		    													objMsg.delete().queue();
		    								    			}
		    								    		}, 1000);
		    		});
		    		break;	
		    	}
	    	}
    	}
    	
    	//if the user puts an image, webm, gif, or other media that the bot doesnt understand
    	//then the bot will have a small chance of liking the post
    	catch(StringIndexOutOfBoundsException e) {
    		int randomInt = ThreadLocalRandom.current().nextInt(1, 10);
    		if (randomInt <= 1) {
    			MessageChannel objMsgCh = evt.getChannel();
    			objMsgCh.sendMessage(" Nice Post :ok_hand:").queue();
    		}
    	}
    	//if the user tries submitting a char or string to the >d command
    	catch(NumberFormatException e) {
    		MessageChannel objMsgCh = evt.getChannel();
			objMsgCh.sendMessage("What are you trying to do >:(").queue();
    	}
    }
}
