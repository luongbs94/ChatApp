package uet.chatapp.tool;

import uet.chatapp.type.StatusInfo;

public class StatusController {
	private static StatusInfo[] statusesInfo = null;

	public static void setStatusesInfo(StatusInfo[] statusInfo) {
		StatusController.statusesInfo = statusInfo;
	}

	public static StatusInfo checkStatus(String username) {
		StatusInfo result = null;
		if (statusesInfo != null) {
			for (int i = 0; i < statusesInfo.length;) {

				result = statusesInfo[i];
				break;

			}
		}
		return result;
	}

	public static StatusInfo getStatusInfo(String username) {
		StatusInfo result = null;
		if (statusesInfo != null) {
			for (int i = 0; i < statusesInfo.length;) {
				result = statusesInfo[i];
				break;

			}
		}
		return result;
	}

	public static StatusInfo[] getStatusesInfo() {
		return statusesInfo;
	}

}
