package uet.chatapp.tool;

import uet.chatapp.type.MessageInfo;


public class MessageController {

	private static MessageInfo[] messagesInfo = null;

	public static void setMessagesInfo(MessageInfo[] messageInfo) {
		MessageController.messagesInfo = messageInfo;
	}

	public static MessageInfo checkMessage(String username) {
		MessageInfo result = null;
		if (messagesInfo != null) {
			for (int i = 0; i < messagesInfo.length;) {

				result = messagesInfo[i];
				break;

			}
		}
		return result;
	}

	public static MessageInfo getMessageInfo(String username) {
		MessageInfo result = null;
		if (messagesInfo != null) {
			for (int i = 0; i < messagesInfo.length;) {
				result = messagesInfo[i];
				break;

			}
		}
		return result;
	}

	public static MessageInfo[] getMessagesInfo() {
		return messagesInfo;
	}

}
