package com.klanvision.backend.controller;

import com.klanvision.backend.model.AuditActivity;
import com.klanvision.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<AuditActivity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping
    public AuditActivity addActivity(@RequestBody AuditActivity activity) {
        return activityService.addActivity(activity.getUser(), activity.getAction(), activity.getType(), activity.getStatus(), activity.getDetails());
    }
}
