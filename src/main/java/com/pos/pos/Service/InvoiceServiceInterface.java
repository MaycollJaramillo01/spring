package com.pos.pos.Service;

import com.pos.pos.Model.Invoice;
import java.util.List;

public interface InvoiceServiceInterface {
	List<Invoice> findAll();
	Invoice findById(Long id);
	Invoice create(Invoice invoice);
	void delete(Long id);
}

