/* TwitterClient Application
* Uses Twitter4j library (java)
* Uses Twitter API 1.1
*/
import twitter4j.*;
import twitter4j.auth.AccessToken;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
	
public class OptimusTwitterMain {
	public static TwitterFactory twitterFactory;
	public static Twitter twitter;
	public static void createTwiterInstance(){
	 	//Your Twitter App's Credentials 
	    String consumerKey = "rbTbOa158nNZfwShr1qiOX8Qf";
	    String consumerSecret = "u7QhUNZnkadbmIV1KKV4w2vtQFg9QM1cQsqSOqa3lLc8UKTKCG";
	    String accessToken = "4277510608-dBSDltt4moQ8eNSHd85aEZYvWiULZkZ6Ao8XJZd";
	    String accessTokenSecret = "LokefJUXeySTMwNaHgcNm41Mt1L6ebYxIcusbGbKio6m7";
	        
	    //Instantiate a re-usable and thread-safe factory
	    twitterFactory = new TwitterFactory();
	     
	    //Instantiate a new Twitter instance
	    twitter = twitterFactory.getInstance();
	        
	    //setup OAuth Consumer Credentials and OAuth Access Tokens
	    twitter.setOAuthConsumer(consumerKey, consumerSecret);
	    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
	}
	    
	public static void statusUpdate(String message){
		//Instantiate and initialize a new twitter status update
	    StatusUpdate statusUpdate = new StatusUpdate(message);
	    //attach any media, if you want to
	    /*statusUpdate.setMedia(
	            //title of media
	            "http://simpledeveloper.com"
	            , new URL("https://si0.twimg.com/profile_images/1733613899/Published_Copy_Book.jpg").openStream());*/
	    
	    //tweet or update status
	    try {
	      Status status = twitter.updateStatus(statusUpdate);
    	  //response from twitter server
	      System.out.println("status.toString() = " + status.toString());
		  System.out.println("status.getInReplyToScreenName() = " + status.getInReplyToScreenName());
		  System.out.println("status.getSource() = " + status.getSource());
		  System.out.println("status.getText() = " + status.getText());
		   
		  System.out.println("status.getURLEntities() = " + Arrays.toString(status.getURLEntities()));
		  System.out.println("status.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities()));
	    } catch (TwitterException e) {
		  // TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	// Find keyword in first 20 mentions
	public static void searchMentions(String keyword){
		// The factory instance is re-useable and thread safe.
	    /*Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    } */
		try {
			List<Status> mentions = twitter.getMentionsTimeline();
			System.out.println("Shoing mentions timeline");
			for (Status mention : mentions){
				System.out.println(mention.getUser().getName() + ":" + mention.getText());
			}
		} catch (TwitterException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, TwitterException {
	  	createTwiterInstance();
	   	//statusUpdate("What did the electrical engineer say when he got shocked?      That Hertz");
	  	searchMentions("force");
	}
}
