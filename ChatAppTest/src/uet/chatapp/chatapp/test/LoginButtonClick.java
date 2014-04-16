package uet.chatapp.chatapp.test;

import uet.chatapp.chatapp.Login;
import uet.chatapp.chatapp.MainScreen;
import uet.chatapp.chatapp.SignUp;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import uet.chatapp.chatapp.R;

import com.robotium.solo.Solo;


public class LoginButtonClick extends ActivityInstrumentationTestCase2<Login>{
	
	private Solo solo;
	EditText login_username;
	EditText login_password;

	public LoginButtonClick() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		login_username = (EditText) solo.getView(R.id.login_username);
		login_password = (EditText) solo.getView(R.id.login_password);
	}
	
	public void testCurrentActivity(){
		solo.assertCurrentActivity("Login", Login.class);
	}
	
	public void testLoginButtonClick(){
		solo.enterText(login_username, "luong");
		solo.enterText(login_password, "190594");
		solo.clickOnButton("Login");
		solo.assertCurrentActivity("Check current Activity", MainScreen.class);
		solo.goBackToActivity("Login");
	}
	
	@Override
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
