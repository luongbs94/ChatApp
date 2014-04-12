package uet.chatapp.chatapp.test;

import uet.chatapp.chatapp.Login;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

public class LoginTest extends ActivityUnitTestCase<Login> {

	private Intent mIntent;

	public LoginTest() {
		super(Login.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mIntent = new Intent(getInstrumentation().getTargetContext(),
				Login.class);
	}

	@MediumTest
	public void testPrecoditions() {
		startActivity(mIntent, null, null);
		final Button login_button = (Button) getActivity().findViewById(
				uet.chatapp.chatapp.R.id.login_login_button);

		assertNotNull("Login is null", getActivity());
		assertNotNull("login_button is null", login_button);

	}

	@MediumTest
	public void testNewActivityWhenClickLogin() {
		startActivity(mIntent, null, null);
		final Button login_button = (Button) getActivity().findViewById(
				uet.chatapp.chatapp.R.id.login_login_button);
		login_button.performClick();
		final Intent launchIntent = getStartedActivityIntent();
		assertNotNull("Intent not null", launchIntent);
		assertTrue(isFinishCalled());
		
	}

}
