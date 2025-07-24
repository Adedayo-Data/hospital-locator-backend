package com.mediMap.controller;

import com.mediMap.dto.ReviewResponseDTO;
import com.mediMap.dto.ReviewsDto;
import com.mediMap.model.Hospital;
import com.mediMap.model.Reviews;
import com.mediMap.model.UserPrincipal;
import com.mediMap.model.Users;
import com.mediMap.repository.HospitalRepository;
import com.mediMap.repository.ReviewsRepository;
import com.mediMap.repository.UserRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
public class ReviewsController {

    UserRepositoy userRepo;
    HospitalRepository hostRepo;
    ReviewsRepository reviewRepo;

    // Add a new review
    @PostMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> createReview(@PathVariable("id") Long hospitalId,
                                                          @RequestBody ReviewsDto reviewsDto){
        Reviews reviews = new Reviews();
        // get the user data
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userPrincipal.getUser();
        Optional<Hospital> hospital = hostRepo.findById(hospitalId);

        if(hospital.isEmpty()){
            return null;
        }

        Hospital realHospital = hospital.get();

        reviews.setHospital(realHospital);
        reviews.setUser(user);
        reviews.setRatings(reviewsDto.getRatings());
        reviews.setComment(reviewsDto.getComment());

        // save review
        Reviews savedReview = reviewRepo.save(reviews);

        return ResponseEntity.ok(new ReviewResponseDTO(savedReview.getHospital(), savedReview.getUser(), savedReview.getRatings(), savedReview.getComment()));
    }

    // Get all reviews for a hospital
    @GetMapping("/{id}")
    public ResponseEntity<List<Reviews>> getAllReviewPerHospital(@PathVariable("id") Long hospital_id){
        System.out.println("Method hit!");
        return ResponseEntity.ok(reviewRepo.findByHospitalId(hospital_id));
    }

}
