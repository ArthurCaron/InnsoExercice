package io.karon.innso_exercise.customer_file.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.karon.innso_exercise.customer_file.persistence.CustomerFile;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerFileGetDto {
	private final String name;
	// format: ISO-8601
	private final String creationDate;
	private final List<MessageGetDto> messages;
	private int id;
	private String reference;

	@JsonCreator
	public CustomerFileGetDto(
			final int id,
			final String name,
			final String creationDate,
			final String reference,
			final List<MessageGetDto> messages
	) {
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.reference = reference;
		this.messages = messages;
	}

	public CustomerFileGetDto(final int id, final CustomerFile customerFile) {
		this(
				id,
				customerFile.getName(),
				customerFile.getCreationDate(),
				customerFile.getReference(),
				customerFile.getMessages().stream()
						.map(MessageGetDto::new)
						.collect(Collectors.toList())
		);
	}

	public int getId() {
		return id;
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

	public List<MessageGetDto> getMessages() {
		return messages;
	}
}
