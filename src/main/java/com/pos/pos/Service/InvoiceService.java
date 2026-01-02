package com.pos.pos.Service;

import com.pos.pos.Model.Invoice;
import com.pos.pos.Repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceService implements InvoiceServiceInterface {
	private final InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice findById(Long id) {
		return invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Invoice not found"));
	}

	@Override
	public Invoice create(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public void delete(Long id) {
		if (!invoiceRepository.existsById(id)) {
			throw new RuntimeException("Invoice not found");
		}
		invoiceRepository.deleteById(id);
	}
}

