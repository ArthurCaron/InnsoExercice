package io.karon.innso_exercise.customer_file.persistence;


import io.karon.innso_exercise.customer_file.Channel;

public class Message {
	// format: ISO-8601
	private final String date;
	private final String authorName;
	private final String content;
	private final Channel channel;

	public Message(final String date, final String authorName, final String content, final Channel channel) {
		this.date = date;
		this.authorName = authorName;
		this.content = content;
		this.channel = channel;
	}

	public String getDate() {
		return date;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getContent() {
		return content;
	}

	public Channel getChannel() {
		return channel;
	}
}
