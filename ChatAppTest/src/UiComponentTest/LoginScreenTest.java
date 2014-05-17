package UiComponentTest;

import uet.chatapp.activity.LoginActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class LoginScreenTest extends
		android.test.ActivityUnitTestCase<LoginActivity> {
	private int login_buttonId, signup_textviewId, logo_Id, login_titleId,
			user_nameId, passwordId, remember_accountId, keep_loginId,forgot_passwordId;
	private LoginActivity activity;

	public LoginScreenTest() {
		super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				LoginActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	public void testButtons() {
		login_buttonId = uet.chatapp.chatapp.R.id.login_login_button;
		assertNotNull(activity.findViewById(login_buttonId));
		Button view = (Button) activity.findViewById(login_buttonId);
		assertEquals("Incorrect label of the button", "Login", view.getText());
	}

	public void testTextViews() {
		signup_textviewId = uet.chatapp.chatapp.R.id.login_sign_up;
		assertNotNull(activity.findViewById(signup_textviewId));
		TextView view1 = (TextView) activity.findViewById(signup_textviewId);
		assertEquals("Incorrect signup text", "SignUp", view1.getText());
		
		login_titleId=uet.chatapp.chatapp.R.id.login_title;
		assertNotNull(activity.findViewById(login_titleId));
		TextView view2 = (TextView) activity.findViewById(login_titleId);
		assertEquals("Incorrect login title", "Login to your account", view2.getText());
		
		forgot_passwordId=uet.chatapp.chatapp.R.id.login_fogot_password;		
		assertNotNull(activity.findViewById(forgot_passwordId));
		TextView view3 = (TextView) activity.findViewById(forgot_passwordId);
		assertEquals("Incorrect text", "Forgot your your password ? ", view3.getText());
		
	}
	public void testCheckboxs() {
		remember_accountId = uet.chatapp.chatapp.R.id.login_remember_checkbox;
		assertNotNull(activity.findViewById(remember_accountId));
		CheckBox view = (CheckBox) activity.findViewById(remember_accountId);
		assertEquals("Incorrect label of the Remember account Checkbox", "Remember me", view.getText());
		
		keep_loginId = uet.chatapp.chatapp.R.id.login_keep_logged_checkbox;
		assertNotNull(activity.findViewById(keep_loginId));
		CheckBox view1 = (CheckBox) activity.findViewById(keep_loginId);
		assertEquals("Incorrect label of the Keep logged Checkbox", "Keep logged in", view1.getText());
	}
	
	public void testEditTexts(){
		user_nameId=uet.chatapp.chatapp.R.id.login_username;
		assertNotNull(activity.findViewById(user_nameId));
		EditText view= (EditText) activity.findViewById(user_nameId);
		assertEquals("Incorrect label of the Login user name", "Username", view.getHint());
		
		passwordId=uet.chatapp.chatapp.R.id.login_password;
		assertNotNull(activity.findViewById(passwordId));
		EditText view1= (EditText) activity.findViewById(passwordId);
		assertEquals("Incorrect label of the Login password", "Password", view1.getHint());
	}
	
	public void testImageViews() {
		logo_Id = uet.chatapp.chatapp.R.id.login_logo;
		assertNotNull(activity.findViewById(logo_Id));
		ImageView view = (ImageView) activity.findViewById(logo_Id);
		// assertEquals("Incorrect logo of your App", "@drawable/chatapp",
		// view.getBackground().toString());
	}
}
