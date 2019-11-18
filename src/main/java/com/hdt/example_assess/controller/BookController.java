package com.hdt.example_assess.controller;

import com.hdt.example_assess.utils.pageable.PageableOutput;
import com.hdt.example_assess.model.BookDTO;
import com.hdt.example_assess.response.SuccessfulResponse;
import com.hdt.example_assess.service.BookService;
import lombok.extern.slf4j.Slf4j;

import lombok.var;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping(value = "/book")
    @ResponseBody
    public PageableOutput getBook(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit) {
        PageableOutput bookOutput = new PageableOutput<BookDTO>();
        bookOutput.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        bookOutput.setTotalPage((int) Math.ceil((double) (bookService.counts()) / limit));
        bookOutput.setListResult(bookService.findAllPagable(pageable));
        return bookOutput;
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity getBookOne(@PathVariable("id") int id) {
        BookDTO bookDTO = bookService.findById(id);
        return new ResponseEntity(new SuccessfulResponse("success", bookDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/book")
    public ResponseEntity addBook(@RequestBody BookDTO bookDTO) {
        bookService.add(bookDTO);
        return new ResponseEntity(new SuccessfulResponse("success", bookDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") int id) {
        BookDTO item = bookService.delete(id);
        return new ResponseEntity(new SuccessfulResponse("success", item), HttpStatus.OK);
    }

    @PutMapping(value = "/book/{id}")
    public ResponseEntity updateBook(@RequestBody BookDTO bookDTO, @PathVariable("id") int id) {
        bookDTO.setId(id);
        BookDTO bookDTO1 = bookService.update(bookDTO);
        return new ResponseEntity(new SuccessfulResponse("success", bookDTO1), HttpStatus.OK);
    }

    @RequestMapping(value = "/images/{url}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("url") String url) throws IOException {
        Resource imgFile = new ClassPathResource("static/images/" + url);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile file) throws IOException {
        logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
        File newFile = new File("E:/_AJava_WorkPlace/example_assess/src/main/resources/static/images/" + file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return ResponseEntity.ok().build();
    }
}
