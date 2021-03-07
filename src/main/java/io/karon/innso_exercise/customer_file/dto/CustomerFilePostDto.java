package io.karon.innso_exercise.customer_file.dto;

public class CustomerFilePostDto {
	private final String name;
	private final String reference;

	public CustomerFilePostDto(final String name, final String reference) {
		this.name = name;
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public String getReference() {
		return reference;
	}
}
