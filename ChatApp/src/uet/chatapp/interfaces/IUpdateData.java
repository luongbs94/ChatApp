package uet.chatapp.interfaces;

import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.MessageInfo;

public interface IUpdateData {
	public void updateData(MessageInfo[] messages, FriendInfo[] friends,
			FriendInfo[] unApprovedFriends, String userKey);

}
