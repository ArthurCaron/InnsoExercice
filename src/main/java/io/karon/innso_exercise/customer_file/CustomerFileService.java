package io.karon.innso_exercise.customer_file;

import io.karon.innso_exercise.IdDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFileGetDto;
import io.karon.innso_exercise.customer_file.dto.CustomerFilesDto;
import io.karon.innso_exercise.customer_file.persistence.CustomerFile;
import io.karon.innso_exercise.customer_file.persistence.CustomerFileDao;
import io.karon.innso_exercise.customer_file.persistence.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Service
public class CustomerFileService {
	private final CustomerFileDao customerFileDao;

	@Autowired
	public CustomerFileService(final CustomerFileDao customerFileDao) {
		this.customerFileDao = customerFileDao;
	}

	public CustomerFilesDto listCustomerFiles() {
		final List<CustomerFileGetDto> customerFileGetDtos = new ArrayList<>();
		final Map<Integer, CustomerFile> customerFiles = customerFileDao.getAll();

		for (Map.Entry<Integer, CustomerFile> entry : customerFiles.entrySet()) {
			final CustomerFileGetDto customerFileGetDto = new CustomerFileGetDto(entry.getKey(), entry.getValue());
			customerFileGetDtos.add(customerFileGetDto);
		}

		return new CustomerFilesDto(customerFileGetDtos);
	}

	public IdDto createCustomerFile(final String name, final String reference) {
		final CustomerFile customerFile = new CustomerFile(name, getCurrentTimeInISO_8601Format(), reference, new ArrayList<>());
		int customerFileId = customerFileDao.create(customerFile);
		return new IdDto(customerFileId);
	}

	public void setReference(final int customerFileId, final String reference) {
		customerFileDao.get(customerFileId)
				.setReference(reference);
	}

	public void addMessageToCustomerFile(final int customerFileId, final String authorName, final String content, final Channel channel) {
		final Message message = new Message(getCurrentTimeInISO_8601Format(), authorName, content, channel);
		customerFileDao.get(customerFileId)
				.getMessages()
				.add(message);
	}

	private String getCurrentTimeInISO_8601Format() {
		// Quoted "Z" to indicate UTC, no timezone offset
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(new Date());
	}
}
