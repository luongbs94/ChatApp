package uet.chatapptest.UIComponentTest;

import uet.chatapp.activity.SignUpActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpScreenTest extends
		android.test.ActivityUnitTestCase<SignUpActivity> {
	private SignUpActivity activity;
	private int signUp_buttonId, signIn_buttonId, user_nameId, passwordId,
			repeat_passwordId, signup_titleId, forgot_passwordId, logo_Id;

	public SignUpScreenTest() {
		super(SignUpActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				SignUpActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	public void testButtons() {
		signUp_buttonId = uet.chatapp.chatapp.R.id.signup_signup_button;
		assertNotNull(activity.findViewById(signUp_buttonId));
		TextView view = (TextView) activity.findViewById(signUp_buttonId);
		assertEquals("Incorrect label of the button", "Sign up", view.getText());
		signIn_buttonId = uet.chatapp.chatapp.R.id.signup_signin_button;
		assertNotNull(activity.findViewById(signIn_buttonId));
		TextView view1 = (TextView) activity.findViewById(signIn_buttonId);
		assertEquals("Incorrect label of the button", "Sign in",
				view1.getText());
	}

	public void testEditTexts() {
		user_nameId = uet.chatapp.chatapp.R.id.signup_username;
		assertNotNull(activity.findViewById(user_nameId));
		EditText view = (EditText) activity.findViewById(user_nameId);
		assertEquals("Incorrect label of the Signup user name", "Username",
				view.getHint());

		passwordId = uet.chatapp.chatapp.R.id.signup_password;
		assertNotNull(activity.findViewById(passwordId));
		EditText view1 = (EditText) activity.findViewById(passwordId);
		assertEquals("Incorrect label of the Signup password", "Password",
				view1.getHint());

		repeat_passwordId = uet.chatapp.chatapp.R.id.signup_confirm_password;
		assertNotNull(activity.findViewById(repeat_passwordId));
		EditText view2 = (EditText) activity.findViewById(repeat_passwordId);
		assertEquals("Incorrect label of the Confirm password",
				"Confirm password", view2.getHint());

	}

	public void testTextViews() {
		signup_titleId = uet.chatapp.chatapp.R.id.signup_title;
		assertNotNull(activity.findViewById(signup_titleId));
		TextView view1 = (TextView) activity.findViewById(signup_titleId);
		assertEquals("Incorrect login title", "Creat an account",
				view1.getText());

		forgot_passwordId = uet.chatapp.chatapp.R.id.signup_fogot_password;
		assertNotNull(activity.findViewById(forgot_passwordId));
		TextView view2 = (TextView) activity.findViewById(forgot_passwordId);
		assertEquals("Incorrect text", "Forgot your your password ? ",
				view2.getText());

	}

	public void testImageViews() {
		logo_Id = uet.chatapp.chatapp.R.id.signup_logo;
		assertNotNull(activity.findViewById(logo_Id));
		ImageView view = (ImageView) activity.findViewById(logo_Id);
		// assertEquals("Incorrect logo of your App", "@drawable/chatapp",
		// view.getBackground().toString());
	}
}
