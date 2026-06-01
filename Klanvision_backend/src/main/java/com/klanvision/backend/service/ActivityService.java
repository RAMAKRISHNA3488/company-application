package com.klanvision.backend.service;

import com.klanvision.backend.model.AuditActivity;
import com.klanvision.backend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<AuditActivity> getAllActivities() {
        return activityRepository.findAllByOrderByTimestampDesc();
    }

    public AuditActivity addActivity(String user, String action, String type, String status, String details) {
        AuditActivity activity = new AuditActivity();
        activity.setUser(user);
        activity.setAction(action);
        activity.setType(type);
        activity.setStatus(status);
        activity.setDetails(details);
        return activityRepository.save(activity);
    }
}
