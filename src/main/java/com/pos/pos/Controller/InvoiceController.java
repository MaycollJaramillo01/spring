package com.pos.pos.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.pos.Model.Invoice;
import com.pos.pos.Service.InvoiceServiceInterface;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
	private final InvoiceServiceInterface invoiceService;

	@GetMapping
	public List<Invoice> list() {
		return invoiceService.findAll();
	}

	@PostMapping
	public Invoice createInvoice(Invoice invoice) {
		return invoiceService.create(invoice);
	}

	@GetMapping("/{id}")
	public Invoice getInvoice(@PathVariable Long id) {
		return invoiceService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteInvoice(@PathVariable Long id) {
		invoiceService.delete(id);
	}
}
