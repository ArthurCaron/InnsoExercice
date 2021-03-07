package io.karon.innso_exercise.customer_file.persistence;

import java.util.List;

public class CustomerFile {
	private final String name;
	// format: ISO-8601
	private final String creationDate;
	private final List<Message> messages;
	private String reference;

	public CustomerFile(final String name, final String creationDate, final String reference, final List<Message> messages) {
		this.name = name;
		this.creationDate = creationDate;
		this.reference = reference;
		this.messages = messages;
	}

	public String getName() {
		return name;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public List<Message> getMessages() {
		return messages;
	}
}
