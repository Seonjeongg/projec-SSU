//package com.example.softproject1.Crawl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/programs")
//@CrossOrigin("*")
//public class CrawlController {
//    @Autowired
//    private CrawlService crawlService;
//
//    @GetMapping
//    public List<Program> getPrograms() {
//        return crawlService.crawlPrograms();
//    }
//}
