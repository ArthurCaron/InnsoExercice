package io.karon.innso_exercise.customer_file.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.karon.innso_exercise.customer_file.Channel;
import io.karon.innso_exercise.customer_file.persistence.Message;

public class MessageGetDto {
	// format: ISO-8601
	private final String date;
	private final String authorName;
	private final String content;
	private final Channel channel;

	@JsonCreator
	public MessageGetDto(final String date, final String authorName, final String content, final Channel channel) {
		this.date = date;
		this.authorName = authorName;
		this.content = content;
		this.channel = channel;
	}

	public MessageGetDto(final Message message) {
		this(
				message.getDate(),
				message.getAuthorName(),
				message.getContent(),
				message.getChannel()
		);
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
