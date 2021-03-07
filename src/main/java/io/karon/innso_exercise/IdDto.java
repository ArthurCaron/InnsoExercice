package io.karon.innso_exercise;

import com.fasterxml.jackson.annotation.JsonCreator;

public class IdDto {
	private final int id;

	@JsonCreator
	public IdDto(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
