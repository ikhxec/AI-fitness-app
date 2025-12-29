package com.fitness.aiservice.Service;

import com.fitness.aiservice.Repository.RecommendationRepository;
import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService actvityAIService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {

        log.info("Recived Activty for processing: {}", activity.getUserId());
        Recommendation recommendation =  actvityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}



