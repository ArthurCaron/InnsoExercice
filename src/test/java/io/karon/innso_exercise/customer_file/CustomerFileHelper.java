package io.karon.innso_exercise.customer_file;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.karon.innso_exercise.IdDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilePostDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilesDto;
import io.karon.innso_exercise.customer_file.dto.MessagePostDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

public class CustomerFileHelper {
	private final MockMvc mvc;
	private final ObjectMapper objectMapper;

	CustomerFileHelper(final MockMvc mvc, final ObjectMapper objectMapper) {
		this.mvc = mvc;
		this.objectMapper = objectMapper;
	}

	public CustomerFilesDto listCustomerFiles() throws Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/customerFiles")
				.accept(MediaType.APPLICATION_JSON_VALUE);

		final String contentAsString = mvc.perform(request)
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		return objectMapper.readValue(contentAsString, CustomerFilesDto.class);
	}

	public IdDto createCustomerFile(final CustomerFilePostDto customerFilePostDto) throws Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customerFiles")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customerFilePostDto));

		final String contentAsString = mvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		return objectMapper.readValue(contentAsString, IdDto.class);
	}

	public void setReference(final int customerFileId, final String reference) throws Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/customerFiles/{customerFileId}/reference", customerFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(reference);

		mvc.perform(request)
				.andExpect(status().isOk());
	}

	public void addMessageToCustomerFile(final int customerFileId, final MessagePostDto messagePostDto) throws Exception {
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customerFiles/{customerFileId}/messages", customerFileId)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(messagePostDto));

		mvc.perform(request)
				.andExpect(status().isCreated());
	}
}
