//package com.example.demo.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.service.VisitorLogService;
//import com.example.demo.util.ApiResponse;
//
//@RestController
//@RequestMapping("/api/visitors")
//public class VisitorLogController {
//
//    @Autowired
//    private VisitorLogService visitorLogService;
//
//    // 방문 로그 저장 API
//    @PostMapping("/log")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<Void>> logVisit(@RequestBody VisitorLogDTO visitorLog) {
//        try {
//            visitorLogService.logVisit(visitorLog);
//            return ResponseEntity.ok(new ApiResponse<>(200, "방문 기록 저장 성공", null));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                .body(new ApiResponse<>(400, "방문 기록 저장 실패: " + e.getMessage(), null));
//        }
//    }
//
//    // 통계 관련 API들
//    @GetMapping("/stats/hourly")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getHourlyStats() {
//        try {
//            List<Map<String, Object>> stats = visitorLogService.getHourlyStats();
//            return ResponseEntity.ok(new ApiResponse<>(200, "시간대별 통계 조회 성공", stats));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                .body(new ApiResponse<>(400, "통계 조회 실패: " + e.getMessage(), null));
//        }
//    }
//
//    @GetMapping("/stats/daily")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getDailyStats() {
//        try {
//            List<Map<String, Object>> stats = visitorLogService.getDailyStats();
//            return ResponseEntity.ok(new ApiResponse<>(200, "요일별 통계 조회 성공", stats));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                .body(new ApiResponse<>(400, "통계 조회 실패: " + e.getMessage(), null));
//        }
//    }
//
//    @GetMapping("/stats/hourly/date-range")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getHourlyStatsByDateRange(
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate) {
//        try {
//            List<Map<String, Object>> stats = visitorLogService.getHourlyStatsByDateRange(startDate, endDate);
//            return ResponseEntity.ok(new ApiResponse<>(200, "기간별 통계 조회 성공", stats));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                .body(new ApiResponse<>(400, "통계 조회 실패: " + e.getMessage(), null));
//        }
//    }
//
//    @GetMapping("/stats/hourly/{dayOfWeek}")
//    @ResponseBody
//    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getHourlyStatsByDayOfWeek(
//            @PathVariable("dayOfWeek") String dayOfWeek) {
//        try {
//            List<Map<String, Object>> stats = visitorLogService.getHourlyStatsByDayOfWeek(dayOfWeek);
//            return ResponseEntity.ok(new ApiResponse<>(200, "요일별 시간대 통계 조회 성공", stats));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                .body(new ApiResponse<>(400, "통계 조회 실패: " + e.getMessage(), null));
//        }
//    }
//}