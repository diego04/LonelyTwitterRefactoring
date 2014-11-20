package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.util.List;

import ca.ualberta.cs.lonelytweet.NormalLonelyTweet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LonelyTwitterActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<NormalLonelyTweet> tweets;
	private ArrayAdapter<NormalLonelyTweet> adapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		setTweets(tweetsProvider.loadTweets());
		adapter = new ArrayAdapter<NormalLonelyTweet>(this, R.layout.list_item,
				getTweets());
		oldTweetsList.setAdapter(adapter);
	}

	public void save(View v) {
	
		NormalLonelyTweet tweet = determineImportant();

//		String text = bodyText.getText().toString();
//
//		LonelyTweet tweet;
//
//		if (text.contains("*")) {
//			tweet = new ImportantLonelyTweet(text);
//		} else {
//			tweet = new NormalLonelyTweet(text);
//		}
		
		if (tweet.isValid()) {
			getTweets().add(tweet);
			adapter.notifyDataSetChanged();

			bodyText.setText("");
			tweetsProvider.saveTweets(getTweets());
		} else {
			Toast.makeText(this, "Invalid tweet", Toast.LENGTH_SHORT).show();
		}
	}

	private NormalLonelyTweet determineImportant() {
		String text = bodyText.getText().toString();

		NormalLonelyTweet tweet;

		tweet = new NormalLonelyTweet(text, new Date());
		return tweet;
	}

	public void clear(View v) {
		getTweets().clear();
		adapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(getTweets());
	}

	private List<NormalLonelyTweet> getTweets() {
		return tweets;
	}

	private void setTweets(List<NormalLonelyTweet> tweets) {
		this.tweets = tweets;
	}

}