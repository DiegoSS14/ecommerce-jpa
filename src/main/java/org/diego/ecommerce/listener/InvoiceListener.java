package org.diego.ecommerce.listener;

import org.diego.ecommerce.model.Invoice;
import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.service.InvoiceService;

import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;

public class InvoiceListener {

    private InvoiceService invoiceService = new InvoiceService();

    @PrePersist
    @PostUpdate
    void generate(Ordered ordered) {
        if (ordered != null) {
            invoiceService.generate();
            Invoice invoice = new Invoice();
            ordered.setInvoice(invoice);
        }
    }
}
