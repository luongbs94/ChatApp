package uet.chatapp.interfaces;

import uet.chatapp.type.FriendInfo;
import uet.chatapp.type.MessageInfo;
import uet.chatapp.type.StatusInfo;

public interface IUpdateData {
	public void updateData(MessageInfo[] messages, FriendInfo[] friends,
			FriendInfo[] unApprovedFriends,StatusInfo[] statuses, String userKey);

}
