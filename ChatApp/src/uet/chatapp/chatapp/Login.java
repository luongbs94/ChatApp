package uet.chatapp.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	private EditText login_username;
	private EditText login_password;
	private Button login_button;
	private TextView signup_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_login);
		setTitle("Login");
		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);
		login_button = (Button) findViewById(R.id.login_login_button);
		signup_link = (TextView) findViewById(R.id.login_sign_up);

		signup_link.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SignUp.class);
				finish();
				startActivity(intent);
			}
		});

		login_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						MainScreen.class);
				finish();
				startActivity(intent);
			}
		});
	}

}
