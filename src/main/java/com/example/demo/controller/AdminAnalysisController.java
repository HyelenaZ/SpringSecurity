//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.example.demo.service.VisitorLogService;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/secure/admin")
//public class AdminAnalysisController {
//    
//    @Autowired
//    private VisitorLogService visitorLogService;
//
//    // 관리자 분석 페이지를 보여주는 메서드
//    @GetMapping("/analysis")
//    public String analysisPage(Model model) {
//        try {
//            // 데이터 가져오기
//            List<Map<String, Object>> hourlyStats = visitorLogService.getHourlyStats();
//            List<Map<String, Object>> dailyStats = visitorLogService.getDailyStats();
//            long totalVisitors = visitorLogService.getTotalVisitorCount();
//
//            // 모델에 데이터 추가
//            model.addAttribute("hourlyStats", hourlyStats);
//            model.addAttribute("dailyStats", dailyStats);
//            model.addAttribute("totalVisitors", totalVisitors);
//
//            // analysis.html 뷰 반환
//            return "admin/body/analysis";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }
//}