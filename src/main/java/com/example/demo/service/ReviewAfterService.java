package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReviewAfter;
import com.example.demo.repository.ReviewAfterRepository;

@Service
public class ReviewAfterService {

	@Autowired
	private ReviewAfterRepository reviewAfterRepository;

	public void deleteById(int reviewAfterSeq) {
		reviewAfterRepository.deleteById(reviewAfterSeq);
	}

	// 리뷰 페이징 처리 (검색어 없이)
    public List<ReviewAfter> getReviewAfterPaging(Pageable pageable) {
        Page<ReviewAfter> pageResult = reviewAfterRepository.findAll(pageable); // 모든 리뷰 페이징
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }

    // 전체 리뷰 개수 반환
    public long getTotalCount() {
        return reviewAfterRepository.count();
    }

    // 검색 키워드를 기준으로 전체 리뷰 개수 반환
    public long getTotalCountKeyword(String value) {
        return reviewAfterRepository.countByContentContaining(value); // 리뷰 내용에 키워드가 포함된 리뷰 개수 반환
    }

    // 검색 키워드를 기준으로 리뷰 페이징 처리
    public List<ReviewAfter> getReviewAfterPagingKeyword(Pageable pageable, String value) {
        Page<ReviewAfter> pageResult = reviewAfterRepository.findByContentContaining(value, pageable); // 키워드로 리뷰 검색하여 페이징 처리
        return pageResult.getContent(); // 페이징된 결과 목록 반환
    }
}