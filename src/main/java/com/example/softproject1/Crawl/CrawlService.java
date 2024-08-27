//package com.example.softproject1.Crawl;
//
//import org.jsoup.Jsoup;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Service;
//
//import javax.lang.model.util.Elements;
//import javax.swing.text.Document;
//import javax.swing.text.Element;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class CrawlService {
//
//    public List<Program> crawlPrograms() {
//        // ChromeDriver의 경로를 설정합니다.
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//
//        // Headless 모드로 ChromeDriver를 설정합니다.
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.setBinary("/usr/local/bin/chrome");
////        options.addArguments("--no-sandbox");
////        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--enable-logging");
//        options.addArguments("--v=1");
//        WebDriver driver = new ChromeDriver(options);
//
//        List<Program> programs = new ArrayList<>();
//        Set<String> titles = new HashSet<>();
//
//        try {
//            driver.get("https://fun.ssu.ac.kr/ko/program/all/list/0/1?sort=applicant");
//
//            // Find elements with class 'content' which seems to be the container for each program
//            List<WebElement> items = driver.findElements(By.cssSelector(".content"));
//
//            for (WebElement item : items) {
//                try {
//                    // Extract title
//                    WebElement titleElement = item.findElement(By.cssSelector(".content .title_wrap .title"));
//                    String title = titleElement != null ? titleElement.getText().trim() : "";
//
//                    if (!titles.contains(title)) {
//                        // Extract date ranges for "신청" and "운영"
//                        List<WebElement> smallElements = item.findElements(By.cssSelector(".content small"));
//
//                        String applicationDate = "";
//                        String operationDate = "";
//
//                        for (WebElement small : smallElements) {
//                            String text = small.getText();
//                            if (text.contains("신청")) {
//                                applicationDate = text.replace("신청 :", "").trim();
//                            } else if (text.contains("운영")) {
//                                operationDate = text.replace("운영:", "").trim();
//                            }
//                        }
//
//                        String dateRange = "신청: " + applicationDate + " / 운영: " + operationDate;
//
//                        // Extract type
//                        WebElement typeElement = driver.findElement(By.cssSelector(".bottom .info_signin .type"));
//                        String type = typeElement != null ? typeElement.getText().trim() : "";
//
//                        // Add program to the list
//                        programs.add(new Program(title, dateRange, type));
//                        titles.add(title);
//
//                        if (programs.size() == 3) break; // 상위 3개만 선택
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // 적절한 예외 처리 및 로깅
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 적절한 예외 처리 및 로깅
//        } finally {
//            driver.quit(); // 브라우저를 종료합니다.
//        }
//
//        return programs;
//    }
//}