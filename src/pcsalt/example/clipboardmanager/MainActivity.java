package pcsalt.example.clipboardmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText etMessage;
	Button btnCopy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etMessage	= (EditText) findViewById(R.id.etMessage);
		btnCopy 	= (Button)	 findViewById(R.id.btnCopy);
		
		// on button click copy text in EditText to clipboard
		btnCopy.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				String text = etMessage.getText().toString().trim();
				if(text.length() > 0) {
					if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
						@SuppressWarnings("deprecation")
						android.text.ClipboardManager clipboardMgr = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						clipboardMgr.setText(text);
					} else {
						// this api requires SDK version 11 and above, so suppress warning for now
						android.content.ClipboardManager clipboardMgr = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						ClipData clip = ClipData.newPlainText("Copied text", text);
						clipboardMgr.setPrimaryClip(clip);
					}
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
