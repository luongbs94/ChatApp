package uet.chatapp.activity;

import uet.chatapp.chatapp.R;
import uet.chatapp.interfaces.IAppManager;
import uet.chatapp.services.IMService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	private static final String SERVER_RES_RES_SIGN_UP_SUCCESFULL = "1";
	private static final String SERVER_RES_SIGN_UP_USERNAME_CRASHED = "2";
	private EditText signup_username;
	private EditText signup_password;
	private EditText confirm_password;
	private TextView signup_button;
	private TextView signin_button;

	private IAppManager imService;
	private Handler handler = new Handler();

	private ServiceConnection mConnection = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			imService = ((IMService.IMBinder) service).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			imService = null;
			Toast.makeText(SignUpActivity.this, R.string.local_service_stopped,
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	//getActionBar().hide();
		setContentView(R.layout.signup);

		signup_username = (EditText) findViewById(R.id.signup_username);
		signup_password = (EditText) findViewById(R.id.signup_password);
		confirm_password = (EditText) findViewById(R.id.signup_confirm_password);
		signup_button = (TextView) findViewById(R.id.signup_signup_button);
		signin_button = (TextView) findViewById(R.id.signup_signin_button);

		signin_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				finish();
				startActivity(intent);
			}
		});

		signup_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (signup_username.length() > 0 &&
					signup_password.length() > 0 &&
					confirm_password.length() > 0)
				{
					if (signup_password.getText().toString().equals(confirm_password.getText().toString()))
					{
						if (signup_username.length() > 5 && signup_password.length() > 5) 
						{
							Thread thread = new Thread(){
								String result = new String();						
								public void run(){
									result = imService.signUpUser(signup_username.getText().toString(),
											signup_password.getText().toString(),
											signup_username.getText().toString());
									handler.post(new Runnable() {
										
										@Override
										public void run() {
											if(result.equals(SERVER_RES_RES_SIGN_UP_SUCCESFULL))
											{
												Toast.makeText(getApplicationContext(),R.string.signup_successfull, Toast.LENGTH_LONG).show();
												Intent intent = new Intent(getApplicationContext(),MainScreenActivity.class);
												finish();
												startActivity(intent);
											}else if(result.equals(SERVER_RES_SIGN_UP_USERNAME_CRASHED))
											{
												Toast.makeText(getApplicationContext(),R.string.signup_user_crash, Toast.LENGTH_LONG).show();
											}else{
												Toast.makeText(getApplicationContext(),R.string.signup_failed, Toast.LENGTH_LONG).show();
											}
										}
									});
								}
								
							};
							thread.start();
						}else{
							Toast.makeText(getApplicationContext(),R.string.username_and_password_length_short, Toast.LENGTH_LONG).show();
						}

					}else{
						Toast.makeText(getApplicationContext(),R.string.signup_type_same_password_in_password_fields, Toast.LENGTH_LONG).show();
					}

				}else{
					Toast.makeText(getApplicationContext(),R.string.signup_fill_all_fields, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		bindService(new Intent(SignUpActivity.this, IMService.class), mConnection , Context.BIND_AUTO_CREATE);
		   
		super.onResume();
	}
	
	@Override
	protected void onPause() 
	{
		unbindService(mConnection);
		super.onPause();
	}
	

}
