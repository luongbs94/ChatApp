package uet.chatapp.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends Activity {

	private EditText signup_username;
	private EditText signup_password;
	private EditText confirm_password;
	private TextView signup_button;
	private TextView signin_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		signup_username = (EditText) findViewById(R.id.signup_username);
		signup_password = (EditText) findViewById(R.id.signup_password);
		confirm_password = (EditText) findViewById(R.id.signup_confirm_password);
		signup_button = (TextView) findViewById(R.id.signup_signup_button);
		signin_button = (TextView) findViewById(R.id.signup_signin_button);

		signin_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Login.class);
				finish();
				startActivity(intent);
			}
		});

		signup_button.setOnClickListener(new View.OnClickListener() {

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
