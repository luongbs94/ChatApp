package uet.chatapp.chatapp.test;

import com.robotium.solo.Solo;

import uet.chatapp.chatapp.Login;
import uet.chatapp.chatapp.SignUp;
import android.test.ActivityInstrumentationTestCase2;

public class TestSignupButtonClick extends
		ActivityInstrumentationTestCase2<Login> {

	private Solo solo;

	public TestSignupButtonClick() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testCurrentActivity(){
		solo.assertCurrentActivity("Login", Login.class);
	}

	public void testSignUpButtonClick() {
		solo.clickOnText("SignUp");
		solo.assertCurrentActivity("SignUp class", SignUp.class);
	}

}
