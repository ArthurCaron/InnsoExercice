package io.karon.innso_exercise.customer_file.dto;

import io.karon.innso_exercise.customer_file.Channel;

public class MessagePostDto {
	private final String authorName;
	private final String content;
	private final Channel channel;

	public MessagePostDto(final String authorName, final String content, final Channel channel) {
		this.authorName = authorName;
		this.content = content;
		this.channel = channel;
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
