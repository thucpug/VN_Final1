package com.hdt.example_assess.service;

import com.hdt.example_assess.entity.Bill;
import com.hdt.example_assess.model.BillDTO;
import com.hdt.example_assess.model.BillReportDTO;
import com.hdt.example_assess.model.BookDTO;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface BillService extends BaseService<BillDTO> {
    public String generateReport(int id);

    public byte[] generateReportV2(int id);

    public List<BillReportDTO> getBillReportDTO(int id);

    public List<BillDTO> findAllPagable(Pageable pageable);
}
