package uet.chatapp.interfaces;

import uet.chatapp.types.FriendInfo;
import uet.chatapp.types.MessageInfo;

public interface IUpdateData {
	public void updateData(MessageInfo[] messages, FriendInfo[] friends,
			FriendInfo[] unApprovedFriends, String userKey);

}
