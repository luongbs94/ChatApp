package uet.chatapp.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import uet.chatapp.activity.LoginActivity;
import uet.chatapp.activity.MessageBoxActivity;
import uet.chatapp.chatapp.R;
import uet.chatapp.communication.SocketOperator;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.interfaces.ISocketOperator;
import uet.chatapp.interfaces.IUpdateData;
import uet.chatapp.tool.FriendController;
import uet.chatapp.tool.LocalStorageHandler;
import uet.chatapp.tool.MessageController;
import uet.chatapp.tool.StatusController;
import uet.chatapp.tool.XMLHandler;
import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.MessageInfo;
import uet.chatapp.type.StatusInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;



public class IMService extends Service implements IAppManager, IUpdateData {
	
	public static String USERNAME;
	public static final String TAKE_MESSAGE = "Take_Message";
	public static final String FRIEND_LIST_UPDATED = "Take Friend List";
	public static final String MESSAGE_LIST_UPDATED = "Take Message List";
	public static final String STATUS_LIST_UPDATED = "Take Status List";
	
	public ConnectivityManager conManager = null; 
	private final int UPDATE_TIME_PERIOD = 15000;
	private String rawFriendList = new String();
	private String rawMessageList = new String();
	private String rawStatusList = new String();
	
	ISocketOperator socketOperator = new SocketOperator(this);

	private final IBinder mBinder = new IMBinder();
	private String username;
	private String password;
	private boolean authenticatedUser = false;
	private Timer timer;
	

	private LocalStorageHandler localstoragehandler; 
	
	private NotificationManager mNM;

	public class IMBinder extends Binder {
		public IAppManager getService() {
			return IMService.this;
		}
		
	}
	   
    @Override
    public void onCreate() 
    {   	
         mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

         localstoragehandler = new LocalStorageHandler(this);
    	conManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    	new LocalStorageHandler(this);
    	
		timer = new Timer();   
		
		Thread thread = new Thread()
		{
			@Override
			public void run() {			
				
				Random random = new Random();
				int tryCount = 0;
				while (socketOperator.startListening(10000 + random.nextInt(20000))  == 0 )
				{		
					tryCount++; 
					if (tryCount > 10)
					{
						break;
					}
				}
			}
		};		
		thread.start();
    
    }

	@Override
	public IBinder onBind(Intent intent) 
	{
		return mBinder;
	}


    private void showNotification(String username, String msg) 
	{       
        // Set the icon, scrolling text and TIMESTAMP
    	String title = "AndroidIM: You got a new Message! (" + username + ")";
 				
    	String text = username + ": " + 
     				((msg.length() < 5) ? msg : msg.substring(0, 5)+ "...");
    	
    	//NotificationCompat.Builder notification = new NotificationCompat.Builder(R.drawable.stat_sample, title,System.currentTimeMillis());
    	NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
    	.setSmallIcon(R.drawable.stat_sample)
    	.setContentTitle(title)
    	.setContentText(text); 
    	
    	

        Intent i = new Intent(this, MessageBoxActivity.class);
        i.putExtra(FriendInfo.USERNAME, username);
        i.putExtra(MessageInfo.MESSAGETEXT, msg);	
        
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                i, 0);

        mBuilder.setContentIntent(contentIntent); 
        
        mBuilder.setContentText("New message from " + username + ": " + msg);
        
        mNM.notify((username+msg).hashCode(), mBuilder.build());
    }
	 

	public String getUsername() {
		return this.username;
	}

	
	public String sendMessage(String  username, String  tousername, String message) throws UnsupportedEncodingException 
	{			
		String params = "username="+ URLEncoder.encode(this.username,"UTF-8") +
						"&password="+ URLEncoder.encode(this.password,"UTF-8") +
						"&to=" + URLEncoder.encode(tousername,"UTF-8") +
						"&message="+ URLEncoder.encode(message,"UTF-8") +
						"&action="  + URLEncoder.encode("sendMessage","UTF-8")+
						"&";		
		Log.i("PARAMS", params);
		return socketOperator.sendHttpRequest(params);		
	}
	
	public String postStatus(String username, String status, String time) throws UnsupportedEncodingException
	{
		String params = "username="+ URLEncoder.encode(this.username,"UTF-8") +
				"&password="+ URLEncoder.encode(this.password,"UTF-8") +
				"&status="+ URLEncoder.encode(status,"UTF-8") +
				"&time="+ URLEncoder.encode(time,"UTF-8") +
				"&action="  + URLEncoder.encode("postStatus","UTF-8")+
				"&";		
		Log.i("PARAMS", params);
		String result = socketOperator.sendHttpRequest(params);
		Log.i("result", result);
		return result;
	}

	private String getStatusList() throws UnsupportedEncodingException 	{		
		// after authentication, server replies with friendList xml
		
		rawStatusList = socketOperator.sendHttpRequest(getAuthenticateUserParams(username, password));
		 if (rawFriendList != null) {
			 this.parseStatusInfo(rawStatusList);
		 }
		 return rawStatusList;
	}
	
	private String getFriendList() throws UnsupportedEncodingException 	{		
		// after authentication, server replies with friendList xml
		
		 rawFriendList = socketOperator.sendHttpRequest(getAuthenticateUserParams(username, password));
		 if (rawFriendList != null) {
			 this.parseFriendInfo(rawFriendList);
		 }
		 return rawFriendList;
	}
	
	private String getMessageList() throws UnsupportedEncodingException 	{		
		// after authentication, server replies with friendList xml
		
		 rawMessageList = socketOperator.sendHttpRequest(getAuthenticateUserParams(username, password));
		 if (rawMessageList != null) {
			 this.parseMessageInfo(rawMessageList);
		 }
		 return rawMessageList;
	}
	
	public String authenticateUser(String usernameText, String passwordText) throws UnsupportedEncodingException 
	{
		this.username = usernameText;
		this.password = passwordText;	
		
		this.authenticatedUser = false;
		
		String result = this.getFriendList(); 
		if (result != null && !result.equals(LoginActivity.AUTHENTICATION_FAILED)) 
		{			
			this.authenticatedUser = true;
			rawFriendList = result;
			USERNAME = this.username;
			Intent i = new Intent(FRIEND_LIST_UPDATED);					
			i.putExtra(FriendInfo.FRIEND_LIST, rawFriendList);
			sendBroadcast(i);
			
			timer.schedule(new TimerTask()
			{			
				public void run() 
				{
					try {					
						Intent i = new Intent(FRIEND_LIST_UPDATED);
						Intent i2 = new Intent(MESSAGE_LIST_UPDATED);
						Intent i3 = new Intent(STATUS_LIST_UPDATED);
						
						String tmp = IMService.this.getFriendList();
						String tmp2 = IMService.this.getMessageList();
						String tmp3 = IMService.this.getStatusList();
						
						if (tmp != null) {
							i.putExtra(FriendInfo.FRIEND_LIST, tmp);
							sendBroadcast(i);	
						
						if (tmp2 != null) {
							i2.putExtra(MessageInfo.MESSAGE_LIST, tmp2);
							sendBroadcast(i2);		}
						
						if (tmp3 != null) {
							i3.putExtra(StatusInfo.STATUS_LIST, tmp3);
							sendBroadcast(i3);	
							
						}
						}
						else {
							Log.i("friend list returned null", "");
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}					
				}			
			}, UPDATE_TIME_PERIOD, UPDATE_TIME_PERIOD);
		}
		
		return result;		
	}

	public void messageReceived(String username, String message) 
	{				
		
		//FriendInfo friend = FriendController.getFriendInfo(username);
		MessageInfo msg = MessageController.checkMessage(username);
		if ( msg != null)
		{			
			Intent i = new Intent(TAKE_MESSAGE);
		
			i.putExtra(MessageInfo.USERID, msg.userid);			
			i.putExtra(MessageInfo.MESSAGETEXT, msg.messagetext);			
			sendBroadcast(i);
			String activeFriend = FriendController.getActiveFriend();
			if (activeFriend == null || activeFriend.equals(username) == false) 
			{
				localstoragehandler.insert(username,this.getUsername(), message.toString());
				showNotification(username, message);
			}
			
			Log.i("TAKE_MESSAGE broadcast sent by im service", "");
		}	
		
	}  
	
	private String getAuthenticateUserParams(String usernameText, String passwordText) throws UnsupportedEncodingException 
	{			
		String params = "username=" + URLEncoder.encode(usernameText,"UTF-8") +
						"&password="+ URLEncoder.encode(passwordText,"UTF-8") +
						"&action="  + URLEncoder.encode("authenticateUser","UTF-8")+
						"&port="    + URLEncoder.encode(Integer.toString(socketOperator.getListeningPort()),"UTF-8") +
						"&";		
		
		return params;		
	}

	public void setUserKey(String value) 
	{		
	}

	public boolean isNetworkConnected() {
		return conManager.getActiveNetworkInfo().isConnected();
	}
	
	public boolean isUserAuthenticated(){
		return authenticatedUser;
	}
	
	public String getLastRawFriendList() {		
		return this.rawFriendList;
	}
	
	@Override
	public void onDestroy() {
		Log.i("IMService is being destroyed", "...");
		super.onDestroy();
	}
	
	public void exit() 
	{
		timer.cancel();
		socketOperator.exit(); 
		socketOperator = null;
		this.stopSelf();
	}
	
	public String signUpUser(String usernameText, String passwordText,
			String emailText) 
	{
		String params = "username=" + usernameText +
						"&password=" + passwordText +
						"&action=" + "signUpUser"+
						"&email=" + emailText+
						"&";
		Log.d("params", params);
		
		String result = socketOperator.sendHttpRequest(params);	
		Log.d("result ", result);
		
		return result;
	}

	public String addNewFriendRequest(String friendUsername) 
	{
		String params = "username=" + this.username +
		"&password=" + this.password +
		"&action=" + "addNewFriend" +
		"&friendUserName=" + friendUsername +
		"&";
		
		Log.d("params", params);

		String result = socketOperator.sendHttpRequest(params);		
		
		Log.d("result", result);
		
		return result;
	}

	public String sendFriendsReqsResponse(String approvedFriendNames,
			String discardedFriendNames) 
	{
		String params = "username=" + this.username +
		"&password=" + this.password +
		"&action=" + "responseOfFriendReqs"+
		"&approvedFriends=" + approvedFriendNames +
		"&discardedFriends=" +discardedFriendNames +
		"&";

		String result = socketOperator.sendHttpRequest(params);		
		
		return result;
		
	} 
	
private void parseStatusInfo(String xml)
	{			
		try 
		{
			SAXParser sp = SAXParserFactory.newInstance().newSAXParser();
			sp.parse(new ByteArrayInputStream(xml.getBytes()), new XMLHandler(IMService.this));		
		} 
		catch (ParserConfigurationException e) {			
			e.printStackTrace();
		}
		catch (SAXException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}	
	}	
	
	private void parseFriendInfo(String xml)
	{			
		try 
		{
			SAXParser sp = SAXParserFactory.newInstance().newSAXParser();
			sp.parse(new ByteArrayInputStream(xml.getBytes()), new XMLHandler(IMService.this));		
		} 
		catch (ParserConfigurationException e) {			
			e.printStackTrace();
		}
		catch (SAXException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	
	private void parseMessageInfo(String xml)
	{			
		try 
		{
			SAXParser sp = SAXParserFactory.newInstance().newSAXParser();
			sp.parse(new ByteArrayInputStream(xml.getBytes()), new XMLHandler(IMService.this));		
		} 
		catch (ParserConfigurationException e) {			
			e.printStackTrace();
		}
		catch (SAXException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {			
			e.printStackTrace();
		}	
	}

	public void updateData(MessageInfo[] messages,FriendInfo[] friends,
			FriendInfo[] unApprovedFriends, StatusInfo[] statuses, String userKey) 
	{
		this.setUserKey(userKey);
		MessageController.setMessagesInfo(messages);
		
		int i = 0;
		while (i < messages.length){
			messageReceived(messages[i].userid,messages[i].messagetext);
			i++;
		}
				
		FriendController.setFriendsInfo(friends);
		FriendController.setUnapprovedFriendsInfo(unApprovedFriends);	
		
		StatusController.setStatusesInfo(statuses);
		Intent tmp = new Intent(STATUS_LIST_UPDATED);			
		sendBroadcast(tmp);
		Log.i("XXXXXXXXXXXXXXX",StatusController.getStatusesInfo()[0].text);
		
	}	
}