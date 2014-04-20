package uet.chatapptest.activity;

import com.robotium.solo.Solo;

import uet.chatapp.activity.LoginActivity;
import uet.chatapp.activity.MainScreenActivity;
import uet.chatapp.activity.SignUpActivity;
import uet.chatapp.chatapp.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

public class SignUpActivityTest extends
			ActivityInstrumentationTestCase2<SignUpActivity> {
	
	public static final String USERNAME = "test002";
	public static final String PASSWORD = "123456";
	
	// Solo is a class in Robotium lib support android test
	private Solo solo;

	public SignUpActivityTest() {
		super(SignUpActivity.class);
	}

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception{
		// finish all activities that have been opened during test execution.
		solo.finishOpenedActivities();
	}
	
	/**
	 * Test if the current activity is SignUpActivity.
	 */		
	public void testCurrentActivity(){
		solo.assertCurrentActivity("Current activity is not SignUpActivity",
								   SignUpActivity.class);
	}
	
	/**
	 * Test if it change to Login activity when click on SignIn button
	 */			
	public void testClickSignInButton(){
		solo.clickOnText("Sign in");
		solo.assertCurrentActivity("Current activity is not LogInActivity", 
								   LoginActivity.class);
	}
	
	/**
	 * Test if it is possible to signup an account
	 * 
	 * Provide valid username and password, enter it to signup form and 
	 * check if signup sucessfull
	 * 
	 * Note: After run this test please change USERNAME to run next time
	 */	
	public void testSignUpAnAccount(){
		EditText username = (EditText) solo.getView(R.id.signup_username);
		EditText password = (EditText) solo.getView(R.id.signup_password);		
		EditText confirmPassword = (EditText) solo.getView(R.id.signup_confirm_password);		
		
		solo.enterText(username, USERNAME);
		solo.enterText(password, PASSWORD);
		solo.enterText(confirmPassword, PASSWORD);
		
		solo.clickOnText("Sign up");
		solo.waitForActivity(MainScreenActivity.class);
		solo.sleep(300);
		solo.assertCurrentActivity("Current activity is not MainScreenActivity",
									MainScreenActivity.class);
	}	
}