package com.hdt.example_assess.service.Impl;

import com.hdt.example_assess.converter.newConverter;
import com.hdt.example_assess.entity.Bill;
import com.hdt.example_assess.entity.Book;
import com.hdt.example_assess.entity.Book_Bill;
import com.hdt.example_assess.model.*;
import com.hdt.example_assess.respository.BillRepository;
import com.hdt.example_assess.respository.BookRepository;
import com.hdt.example_assess.respository.Book_BillRepository;
import com.hdt.example_assess.service.BillService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    Book_BillRepository book_billRepository;
    @Autowired
    newConverter newConverter;

    @Override
    public BillDTO add(BillDTO obj) {
        Bill bill = newConverter.entityBillFromDto(obj);
        Bill bill1 = billRepository.save(bill);
        for (Book_BillDTO item : obj.getBook_billDTOS()) {
            Book_Bill book_bill = new Book_Bill();
            book_bill.setCounts(item.getCounts());
            book_bill.setBill(bill);
            Book book = bookRepository.findById(item.getBook_id()).get();
            long amountsBookItem = book.getAmounts();
            amountsBookItem -= item.getCounts();
            book.setAmounts(amountsBookItem);
            bookRepository.save(book);
            book_bill.setBook(book);
            book_billRepository.save(book_bill);
        }
        BillDTO billDTO = newConverter.dtoBillFromEntity(billRepository.findById(bill1.getId()).get());
        return billDTO;
    }

    @Override
    public BillDTO delete(BillDTO obj) {
        return null;
    }

    @Override
    public BillDTO delete(int ids) {
        Bill bill = billRepository.findById(ids).get();
        if (bill != null) {
            List<Book_Bill> book_bills = bill.getBooks();
            for (Book_Bill book_bill : book_bills) {
                long countBookRF = book_bill.getCounts();
                Book book = book_bill.getBook();
                book.setAmounts(book.getAmounts() + countBookRF);
                bookRepository.save(book);
            }
            billRepository.deleteById(ids);
            return newConverter.dtoBillFromEntity(bill);
        }

        return null;
    }

    @Override
    public BillDTO update(BillDTO obj) {
        return null;
    }

    @Override
    public BillDTO save(BillDTO obj) {
        return null;
    }

    @Override
    public BillDTO findById(int id) {
        return null;
    }

    @Override
    public List<BillDTO> findAll() {
        List<Bill> bills = billRepository.findAll();
        List<BillDTO> billDTOS = new ArrayList<>();
        for (Bill item : bills) {
            billDTOS.add(newConverter.dtoBillFromEntity(item));
        }
        return billDTOS;
    }

    @Override
    public int counts() {
        return (int) billRepository.count();
    }

    private List<Employee> empList = Arrays.asList(
            new Employee(1, "Sandeep", "Data Matrix", "Front-end Developer", 20000),
            new Employee(2, "Prince", "Genpact", "Consultant", 40000),
            new Employee(3, "Gaurav", "Silver Touch ", "Sr. Java Engineer", 47000),
            new Employee(4, "Abhinav", "Akal Info Sys", "CTO", 700000));

    @Override
    public String generateReport(int id) {
        List<BillReportDTO> billReportDTOS = new ArrayList<>();
        Bill bill = billRepository.findById(id).get();
        newConverter.dtoBillReportFromEntity(bill, billReportDTOS);
        // billReportDTOS.add(newConverter.dtoBillReportFromEntity(bill,billReportDTOS));

        try {
            String reportPath = "E:\\_AJava_WorkPlace\\example_assess\\src\\main\\resources\\reports";
            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\BillReport.jrxml");
            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(billReportDTOS);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            // parameters.put("createdBy:", "ThucPug");
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);
            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\" + id + "Bill-Rpt.pdf");
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\_AJava_WorkPlace\\Bill-Report.pdf");
            System.out.println("Done");
            return "Report successfully generated @path= " + reportPath;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public byte[] generateReportV2(int id) {
        List<BillReportDTO> billReportDTOS = new ArrayList<>();
        Bill bill = billRepository.findById(id).get();
        newConverter.dtoBillReportFromEntity(bill, billReportDTOS);
        byte[] bytes = null;
        String reportPath = "E:\\_AJava_WorkPlace\\example_assess\\src\\main\\resources\\reports";
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\BillReport.jrxml");
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(billReportDTOS);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();
            // parameters.put("createdBy:", "ThucPug");
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return bytes;
    }
  public List<BillReportDTO> getBillReportDTO (int id){
      List<BillReportDTO> billReportDTOS = new ArrayList<>();
      Bill bill = billRepository.findById(id).get();
      newConverter.dtoBillReportFromEntity(bill, billReportDTOS);
      return billReportDTOS;
  }
    @Override
    public List<BillDTO> findAllPagable(Pageable pageable) {
        List<BillDTO> billDTOS = new ArrayList<>();
        List<Bill> bills = billRepository.findAll(pageable).getContent();
        for (Bill item : bills) {
            BillDTO billDTO = new BillDTO();
            billDTOS.add(newConverter.dtoBillFromEntity(item));
        }
        return billDTOS;
    }

}
