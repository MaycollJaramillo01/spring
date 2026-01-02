package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.InvoiceItem;
import com.pos.pos.Service.InvoiceItemServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/invoice-items")
@RequiredArgsConstructor
public class InvoiceItemController {
	private final InvoiceItemServiceInterface invoiceItemService;

	@GetMapping
	public List<InvoiceItem> list() {
		return invoiceItemService.findAll();
	}

	@PostMapping
	public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
		return invoiceItemService.create(invoiceItem);
	}

	@GetMapping("/{id}")
	public InvoiceItem getInvoiceItem(@PathVariable Long id) {
		return invoiceItemService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteInvoiceItem(@PathVariable Long id) {
		invoiceItemService.delete(id);
	}
}
