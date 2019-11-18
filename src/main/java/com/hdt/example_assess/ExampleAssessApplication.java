package com.hdt.example_assess;

import com.hdt.example_assess.model.BillReportDTO;
import com.hdt.example_assess.service.BillService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.hdt.example_assess.entity")
public class ExampleAssessApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExampleAssessApplication.class, args);
    }

//    @Bean
//    public String generateReport(final BillService employeeReportService ) {
//        String msg = employeeReportService.generateReport();
//        System.out.println(msg);
//        return msg;
//    }

}
