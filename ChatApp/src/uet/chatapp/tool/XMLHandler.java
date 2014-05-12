package uet.chatapp.tool;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uet.chatapp.interfaces.IUpdateData;
import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.MessageInfo;
import uet.chatapp.type.StatusInfo;
import uet.chatapp.type.Status;
import android.util.Log;


public class XMLHandler extends DefaultHandler {
	private String userKey = new String();
	private IUpdateData updater;

	public XMLHandler(IUpdateData updater) {
		super();
		this.updater = updater;
	}

	private Vector<FriendInfo> mFriends = new Vector<FriendInfo>();
	private Vector<FriendInfo> mOnlineFriends = new Vector<FriendInfo>();
	private Vector<FriendInfo> mUnapprovedFriends = new Vector<FriendInfo>();
	
	private Vector<MessageInfo> mUnreadMessages = new Vector<MessageInfo>();

	private Vector<StatusInfo> mStatuses = new Vector<StatusInfo>();
	
	public void endDocument() throws SAXException {
		FriendInfo[] friends = new FriendInfo[mFriends.size()
				+ mOnlineFriends.size()];
		MessageInfo[] messages = new MessageInfo[mUnreadMessages.size()];

		StatusInfo[] statuses = new StatusInfo[mStatuses.size()];

		int onlineFriendCount = mOnlineFriends.size();
		for (int i = 0; i < onlineFriendCount; i++) {
			friends[i] = mOnlineFriends.get(i);
		}

		int offlineFriendCount = mFriends.size();
		for (int i = 0; i < offlineFriendCount; i++) {
			friends[i + onlineFriendCount] = mFriends.get(i);
		}

		int unApprovedFriendCount = mUnapprovedFriends.size();
		FriendInfo[] unApprovedFriends = new FriendInfo[unApprovedFriendCount];

		for (int i = 0; i < unApprovedFriends.length; i++) {
			unApprovedFriends[i] = mUnapprovedFriends.get(i);
		}

		int unreadMessagecount = mUnreadMessages.size();
		// Log.i("MessageLOG", "mUnreadMessages="+unreadMessagecount );
		for (int i = 0; i < unreadMessagecount; i++) {
			messages[i] = mUnreadMessages.get(i);
			Log.i("MessageLOG", "i=" + i);
		}

		int Statuscount = mStatuses.size();
		for (int i=0; i<Statuscount; i++){
			statuses[i] = mStatuses.get(i);
		}
		
		this.updater.updateData(messages, friends, unApprovedFriends, statuses, userKey);
		super.endDocument();
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (localName == "friend") {
			FriendInfo friend = new FriendInfo();
			friend.userName = attributes.getValue(FriendInfo.USERNAME);
			String status = attributes.getValue(FriendInfo.STATUS);
			friend.ip = attributes.getValue(FriendInfo.IP);
			friend.port = attributes.getValue(FriendInfo.PORT);
			friend.userKey = attributes.getValue(FriendInfo.USER_KEY);
			// friend.expire = attributes.getValue("expire");

			if (status != null && status.equals("online")) {
				friend.status = Status.ONLINE;
				mOnlineFriends.add(friend);
			} else if (status.equals("unApproved")) {
				friend.status = Status.UNAPPROVED;
				mUnapprovedFriends.add(friend);
			} else {
				friend.status = Status.OFFLINE;
				mFriends.add(friend);
			}
		} else if (localName == "user") {
			this.userKey = attributes.getValue(FriendInfo.USER_KEY);
		} else if (localName == "message") {
			MessageInfo message = new MessageInfo();
			message.userid = attributes.getValue(MessageInfo.USERID);
			message.sendt = attributes.getValue(MessageInfo.SENDT);
			message.messagetext = attributes.getValue(MessageInfo.MESSAGETEXT);
			Log.i("MessageLOG", message.userid + message.sendt
					+ message.messagetext);
			mUnreadMessages.add(message);
		} else if (localName == "status"){
			StatusInfo status = new StatusInfo();
			status.userMame = attributes.getValue(StatusInfo.USERNAME);
			status.text = attributes.getValue(StatusInfo.STATUSTEXT);
	//		Log.i("statustext", status.text);
			mStatuses.add(status);
		}
			
		super.startElement(uri, localName, name, attributes);
	}

	@Override
	public void startDocument() throws SAXException {
		this.mFriends.clear();
		this.mOnlineFriends.clear();
		this.mUnreadMessages.clear();
		this.mStatuses.clear();
		super.startDocument();
	}

}