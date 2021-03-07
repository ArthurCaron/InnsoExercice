package io.karon.innso_exercise.customer_file;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.karon.innso_exercise.customer_file.dto.CustomerFileGetDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilePostDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilesDto;
import io.karon.innso_exercise.customer_file.dto.MessageGetDto;
import io.karon.innso_exercise.customer_file.dto.MessagePostDto;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerFileTest {
	private final CustomerFileHelper customerFileHelper;

	@Autowired
	CustomerFileTest(final MockMvc mvc, final ObjectMapper objectMapper) {
		this.customerFileHelper = new CustomerFileHelper(mvc, objectMapper);
	}

	@Test
	void testValidationScenario() throws Exception {
		final String customerName = "Jérémie Durand";
		final String customerMessage = "Bonjour, j’ai un problème avec mon nouveau téléphone";
		final String supportName = "Sonia Valentin";
		final String supportMessage = "Je suis Sonia, et je vais mettre tout en œuvre pour vous aider. Quel est le modèle de votre téléphone ?";
		final String customerFileReference = "KA-18B6";
		final Channel channelUsedToCommunicate = Channel.FACEBOOK;

		final CustomerFilePostDto customerFileDto = new CustomerFilePostDto(customerName, "");
		final int customerFileId = customerFileHelper.createCustomerFile(customerFileDto).getId();

		final MessagePostDto customerMessageDto = new MessagePostDto(customerName, customerMessage, channelUsedToCommunicate);
		customerFileHelper.addMessageToCustomerFile(customerFileId, customerMessageDto);

		final MessagePostDto supportMessageDto = new MessagePostDto(supportName, supportMessage, channelUsedToCommunicate);
		customerFileHelper.addMessageToCustomerFile(customerFileId, supportMessageDto);

		customerFileHelper.setReference(customerFileId, customerFileReference);

		final CustomerFilesDto customerFilesDto = customerFileHelper.listCustomerFiles();
		assertThat(customerFilesDto.getCustomerFiles()).hasSize(1);

		final CustomerFileGetDto customerFile = customerFilesDto.getCustomerFiles().get(0);
		assertThat(customerFile.getId()).isEqualTo(customerFileId);
		assertThat(customerFile.getName()).isEqualTo(customerName);
		assertThat(customerFile.getReference()).isEqualTo(customerFileReference);

		final List<MessageGetDto> messages = customerFile.getMessages();
		assertThat(messages).hasSize(2);

		assertThat(messages.get(0).getAuthorName()).isEqualTo(customerName);
		assertThat(messages.get(0).getContent()).isEqualTo(customerMessage);
		assertThat(messages.get(0).getChannel()).isEqualTo(channelUsedToCommunicate);

		assertThat(messages.get(1).getAuthorName()).isEqualTo(supportName);
		assertThat(messages.get(1).getContent()).isEqualTo(supportMessage);
		assertThat(messages.get(1).getChannel()).isEqualTo(channelUsedToCommunicate);
	}
}
