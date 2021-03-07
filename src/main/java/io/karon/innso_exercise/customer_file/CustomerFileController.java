package io.karon.innso_exercise.customer_file;

import io.karon.innso_exercise.IdDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilePostDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilesDto;
import io.karon.innso_exercise.customer_file.dto.MessagePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerFileController {
	private final CustomerFileService customerFileService;

	@Autowired
	public CustomerFileController(final CustomerFileService customerFileService) {
		this.customerFileService = customerFileService;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/customerFiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerFilesDto listCustomerFiles() {
		return customerFileService.listCustomerFiles();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/customerFiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public IdDto createCustomerFile(@RequestBody final CustomerFilePostDto customerFilePostDto) {
		return customerFileService.createCustomerFile(customerFilePostDto.getName(), customerFilePostDto.getReference());
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/customerFiles/{customerFileId}/reference", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setReference(@PathVariable final int customerFileId, @RequestBody final String reference) {
		customerFileService.setReference(customerFileId, reference);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/customerFiles/{customerFileId}/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addMessageToCustomerFile(@PathVariable final int customerFileId, @RequestBody final MessagePostDto messagePostDto) {
		customerFileService.addMessageToCustomerFile(
				customerFileId,
				messagePostDto.getAuthorName(),
				messagePostDto.getContent(),
				messagePostDto.getChannel()
		);
	}
}
