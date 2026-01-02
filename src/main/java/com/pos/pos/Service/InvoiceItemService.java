package com.pos.pos.Service;

import com.pos.pos.Model.InvoiceItem;
import com.pos.pos.Repository.InvoiceItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceItemService implements InvoiceItemServiceInterface {
	private final InvoiceItemRepository invoiceItemRepository;

	@Override
	public List<InvoiceItem> findAll() {
		return invoiceItemRepository.findAll();
	}

	@Override
	public InvoiceItem findById(Long id) {
		return invoiceItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("InvoiceItem not found"));
	}

	@Override
	public InvoiceItem create(InvoiceItem invoiceItem) {
		return invoiceItemRepository.save(invoiceItem);
	}

	@Override
	public void delete(Long id) {
		if (!invoiceItemRepository.existsById(id)) {
			throw new RuntimeException("InvoiceItem not found");
		}
		invoiceItemRepository.deleteById(id);
	}
}

