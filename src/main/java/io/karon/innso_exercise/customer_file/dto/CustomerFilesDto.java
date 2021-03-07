package io.karon.innso_exercise.customer_file.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class CustomerFilesDto {
	private final List<CustomerFileGetDto> customerFiles;

	@JsonCreator
	public CustomerFilesDto(final List<CustomerFileGetDto> customerFiles) {
		this.customerFiles = customerFiles;
	}

	public List<CustomerFileGetDto> getCustomerFiles() {
		return customerFiles;
	}
}
