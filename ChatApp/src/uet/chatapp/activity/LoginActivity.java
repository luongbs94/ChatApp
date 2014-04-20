package uet.chatapp.activity;

import java.io.UnsupportedEncodingException;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	public static final String AUTHENTICATION_FAILED = "0";
	public static final String FRIEND_LIST = "FRIEND_LIST";

	private EditText login_username;
	private EditText login_password;
	private Button login_button;
	private TextView signup_link;

	private IAppManager imService;

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			imService = ((IMService.IMBinder) service).getService();
			if (imService.isUserAuthenticated() == true) {
				Intent i = new Intent(LoginActivity.this, MainScreenActivity.class);
				startActivity(i);
				LoginActivity.this.finish();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			imService = null;
			Toast.makeText(LoginActivity.this, R.string.local_service_stopped,
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();

		startService(new Intent(LoginActivity.this, IMService.class));

		setContentView(R.layout.activity_login);

		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);
		login_button = (Button) findViewById(R.id.login_login_button);
		signup_link = (TextView) findViewById(R.id.login_sign_up);

		signup_link.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SignUpActivity.class);
				finish();
				startActivity(intent);
			}
		});

		login_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (imService == null) {
					Toast.makeText(getApplicationContext(),
							R.string.not_connected_to_service,
							Toast.LENGTH_LONG).show();
				} else if (imService.isNetworkConnected() == false) {
					Toast.makeText(getApplicationContext(),
							R.string.not_connected_to_network,
							Toast.LENGTH_LONG).show();
				} else if (login_username.length() > 0
						&& login_password.length() > 0) {
					Thread loginThread = new Thread() {
						private Handler handler = new Handler();

						public void run() {
							String result = null;
							try {
								result = imService.authenticateUser(
										login_username.getText().toString(),
										login_password.getText().toString());
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							if(result == null || result.equals(AUTHENTICATION_FAILED)){
								handler.post(new Runnable() {
									
									@Override
									public void run() {
										Toast.makeText(getApplicationContext(),R.string.make_sure_username_and_password_correct, 
												Toast.LENGTH_LONG).show();
									}
								});
							}else{
								handler.post(new Runnable() {
									
									@Override
									public void run() {
										Intent i = new Intent(LoginActivity.this,MainScreenActivity.class);
										startActivity(i);
										LoginActivity.this.finish();
									}
								});
							}
						}
					};
					loginThread.start();
				}else{
					Toast.makeText(getApplicationContext(),R.string.fill_both_username_and_password, Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	protected void onPause() {
		unbindService(mConnection);
		super.onPause();
	}

	@Override
	protected void onResume() {
		bindService(new Intent(LoginActivity.this, IMService.class), mConnection,
				Context.BIND_AUTO_CREATE);

		super.onResume();
	}

}
