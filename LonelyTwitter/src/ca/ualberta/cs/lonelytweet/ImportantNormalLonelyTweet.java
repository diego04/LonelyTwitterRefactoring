package ca.ualberta.cs.lonelytweet;

import java.io.Serializable;
import java.util.Date;

public class ImportantNormalLonelyTweet extends LonelyTweet implements Serializable {

	public ImportantNormalLonelyTweet() {
	}

	public ImportantNormalLonelyTweet(String text, Date date) {
		this.tweetDate = date;
		this.tweetBody = text;
	}

	@Override
	public boolean isValid() {
		if (tweetBody.trim().length() == 0
				|| tweetBody.trim().length() > 20) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String string = getTweetDate() + " | " + getTweetBody();
		return string ;
	}

	public String getTweetBody() {
		return tweetBody.toUpperCase();
	}
}