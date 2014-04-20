package uet.chatapptest.activity;

import com.robotium.solo.Solo;

import uet.chatapp.activity.LoginActivity;
import uet.chatapp.activity.MainScreenActivity;
import uet.chatapp.activity.SignUpActivity;
import uet.chatapp.chatapp.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class LoginActivityTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {
	
	public static final String USERNAME = "luong";
	public static final String PASSWORD = "190594";
	// Solo is a class in Robotium lib support android test
	private Solo solo;

	public LoginActivityTest() {
		super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		// finish all activities that have been opened during test execution.
		solo.finishOpenedActivities();
	}
	
	/**
	 * Test if the current activity is LoginActivity.
	 */	
	public void testCurrentActivity(){
		solo.assertCurrentActivity("Login", LoginActivity.class);
	}

	/**
	 * Test if it is possible to login
	 * 
	 * Provide a correct username and password, enter it to login form and 
	 * check if login sucessfull
	 */	
	public void testLoginWithAnAccount(){
		EditText username = (EditText) solo.getView(R.id.login_username);
		EditText password = (EditText) solo.getView(R.id.login_password);
		
		solo.enterText(username, USERNAME);
		solo.enterText(password, PASSWORD);
		
		solo.clickOnButton("Login");
		solo.assertCurrentActivity("Current activity is not MainScreenActivity",
									MainScreenActivity.class);
	}
	
	/**
	 * Test if it change to Signup activity when click on signup button
	 */		
	public void testClickSignUpButton() {
		solo.clickOnText("SignUp");
		solo.assertCurrentActivity("SignUp class", SignUpActivity.class);
	}
}
