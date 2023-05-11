package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Feedback;
import com.graduation.furniture.repository.FeedbackRepo;
import com.graduation.furniture.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;
    @Override
    public Feedback save(Feedback feedback) {
        LocalDate  now = LocalDate.now();
        feedback.setCreatedAt(now);
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback update(Feedback feedback) {
        LocalDateTime now = LocalDateTime.now();
        feedback.setUpdatedAt(now);
        return feedbackRepo.save(feedback);
    }

    @Override
    public void deleteById(Integer id) {
        feedbackRepo.deleteById(id);
    }

    @Override
    public Optional<Feedback> findById(Integer id) {
        return feedbackRepo.findById(id);
    }

    @Override
    public Page<Feedback> findAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum-1, size, Sort.by("createdAt").descending());
        return feedbackRepo.findAll(pageable);
    }

    @Override
    public List<Feedback> findByProduct_ProductIdAndUser_UserName(Integer productId, String userName) {
        return feedbackRepo.findByProduct_ProductIdAndUser_UserName(productId, userName);
    }

    @Override
    public List<Feedback> findByProduct_ProductId(Integer productId) {
        return feedbackRepo.findByProduct_ProductId(productId);
    }
}
