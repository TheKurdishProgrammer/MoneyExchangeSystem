package com.example.mycompany.paymentSystem.services;

import com.example.mycompany.paymentSystem.models.Transaction;
import com.example.mycompany.paymentSystem.repositories.InvoiceRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class InvoiceService {

//    private InvoiceRepository invoiceRepository;

//    @Autowired
//    private InvoiceProductQuantityRepository invoiceProductQuantityRepository;


//    public Optional<Invoice> getInvoice(int id) {
//        return invoiceRepository.findById(id);
//    }

//    public List<Invoice> getInvoices() {
//        return invoiceRepository.findAll();
//    }


    public String generateInvoriceFor(Object order, Locale locale) throws IOException {

        // Create a temporary PDF file
        File pdfFile = File.createTempFile("my-invoice", ".pdf");


        // Initiate a FileOutputStream
        try (FileOutputStream pos = new FileOutputStream(pdfFile)) {
            final JasperReport report = loadTemplate();

            // Create parameters map.
            final Map<String, Object> parameters = parameters(order, locale);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton("Invoice"));

            // Render the PDF file
            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return pdfFile.getAbsolutePath();

    }

    // Fill template order parametres
    private Map<String, Object> parameters(Object order, Locale locale) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("order", order);
        parameters.put("REPORT_LOCALE", locale);

        return parameters;
    }


    // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {


        final InputStream reportInputStream = getClass().getResourceAsStream("/jasper/send_money.jrxml");
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }

    public String printInvoice(Object invoice) {

        String pdfLink = "";
        try {
            pdfLink = generateInvoriceFor(invoice, Locale.ENGLISH);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return pdfLink;
    }
}