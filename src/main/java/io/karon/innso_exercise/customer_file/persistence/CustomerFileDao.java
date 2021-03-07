package io.karon.innso_exercise.customer_file.persistence;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// "Dao"
@Repository
public class CustomerFileDao {
	private final Map<Integer, CustomerFile> customerFiles;
	private final AtomicInteger idGenerator;

	public CustomerFileDao() {
		this.customerFiles = new HashMap<>();
		this.idGenerator = new AtomicInteger();
	}

	public Map<Integer, CustomerFile> getAll() {
		return customerFiles;
	}

	public int create(final CustomerFile customerFile) {
		final int id = idGenerator.incrementAndGet();
		customerFiles.put(id, customerFile);
		return id;
	}

	public CustomerFile get(final int customerFileId) {
		return customerFiles.get(customerFileId);
	}
}
