package uet.chatapp.chatapp.test;

import uet.chatapp.chatapp.Login;
import uet.chatapp.chatapp.SignUp;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;


public class LoginTest extends ActivityInstrumentationTestCase2<Login>{
	
	private Solo solo;

	public LoginTest() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testCheckCurrentActivity(){
		solo.assertCurrentActivity("Check current Activity", Login.class);
	}
	
	public void testLoginNotFillAllField(){
		
	}
	
	public void testLoginUserNameOrPasswordNotCorrect(){
		
	}
	
	public void testLoginButtonClick(){
		
	}
	
	public void testSingUpButtonClick(){
		solo.clickOnText("Dont have an account. Create now");
		solo.assertCurrentActivity("SignUp class", SignUp.class);
		
		
	}
	
}
