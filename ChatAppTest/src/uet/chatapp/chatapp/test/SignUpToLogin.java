package uet.chatapp.chatapp.test;

import com.robotium.solo.Solo;

import uet.chatapp.chatapp.Login;
import uet.chatapp.chatapp.SignUp;
import android.test.ActivityInstrumentationTestCase2;

public class SignUpToLogin extends ActivityInstrumentationTestCase2<SignUp>{
	
	private Solo solo;

	public SignUpToLogin() {
		super(SignUp.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}
	
	public void testCurrentActivity(){
		solo.assertCurrentActivity("SignUp.class", SignUp.class);
	}
	
	
	public void testClickButtonLogin(){
		solo.clickOnText("Sign in");
		solo.assertCurrentActivity("Login.class", Login.class);
		
	}
	

}
