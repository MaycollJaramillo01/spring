package com.pos.pos.Service;

import com.pos.pos.Model.InvoiceItem;
import java.util.List;

public interface InvoiceItemServiceInterface {
	List<InvoiceItem> findAll();
	InvoiceItem findById(Long id);
	InvoiceItem create(InvoiceItem invoiceItem);
	void delete(Long id);
}

