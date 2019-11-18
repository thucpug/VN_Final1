package com.hdt.example_assess.controller;

import com.hdt.example_assess.model.BillDTO;
import com.hdt.example_assess.model.BillReportDTO;
import com.hdt.example_assess.model.BookDTO;
import com.hdt.example_assess.response.SuccessfulResponse;
import com.hdt.example_assess.service.BillService;
import com.hdt.example_assess.utils.pageable.PageableOutput;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.ParseException;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class BillController {

    @Autowired
    BillService billService;

    //    @GetMapping(value = "/bill")
//    @ResponseBody
//    public List<BillDTO> getBill() {
//
//        List<BillDTO> billDTOS = billService.findAll();
//        return billDTOS;
//    }
    @GetMapping(value = "/bill")
    @ResponseBody
    public PageableOutput getBill(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit) {
        PageableOutput billOutput = new PageableOutput<BillDTO>();
        billOutput.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        billOutput.setTotalPage((int) Math.ceil((double) (billService.counts()) / limit));
        billOutput.setListResult(billService.findAllPagable(pageable));
        return billOutput;
    }


    @PostMapping(value = "/bill")
    @ResponseBody
    public ResponseEntity addBill(@RequestBody BillDTO billDTO) {
        BillDTO billDTO1 = billService.add(billDTO);
        return new ResponseEntity(new SuccessfulResponse("success", billDTO1), HttpStatus.OK);
    }

    @DeleteMapping(value = "/bill/{id}")
    public ResponseEntity deleteBill(@PathVariable("id") int id) {
        BillDTO billDTO = billService.delete(id);
        return new ResponseEntity(new SuccessfulResponse("success", billDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/report/{id}")
    @ResponseBody
    public String getReport(@PathVariable("id") int id) throws ParseException {
        return billService.generateReport(id);
    }

    @GetMapping(value = "/reportv2/{id}")
    public ResponseEntity<byte[]> report(@PathVariable("id") int id) {

        byte[] bytes = billService.generateReportV2(id);
        return ResponseEntity
                .ok()
                // Specify content type as PDF
                .header("Content-Type", "application/pdf; charset=UTF-8")
                // Tell browser to display PDF if it can
                .header("Content-Disposition", "inline; filename=\"" + id + ".pdf\"")
                .body(bytes);
    }

    @GetMapping(value = "/reportv3/{id}")
    public ResponseEntity<InputStreamResource> download2(HttpServletRequest request, @PathVariable("id") int id) throws IOException {
        HttpHeaders responseHeader = new HttpHeaders();
        try {

            byte[] data = billService.generateReportV2(id);
            // Set mimeType trả về
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // Thiết lập thông tin trả về

            responseHeader.set("Content-disposition", "attachment; filename=" + id + ".pdf");
            responseHeader.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeader, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/download2")
    public ResponseEntity<InputStreamResource> download2(HttpServletRequest request) throws IOException {
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            File file = ResourceUtils.getFile("classpath:reports/15.pdf");
            byte[] data = FileUtils.readFileToByteArray(file);
            // Set mimeType trả về
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // Thiết lập thông tin trả về
            responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
            responseHeader.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeader, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

