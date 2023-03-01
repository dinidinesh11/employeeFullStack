package com.technicalAssessment.employeeApplication.controller;

import com.technicalAssessment.employeeApplication.model.SkillLevels;
import com.technicalAssessment.employeeApplication.repository.SkillLevelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/skills")
public class SkillLevelController {
    @Autowired
    private SkillLevelRepo skillLevelRepo;
    @GetMapping("/list")

    public List<String> getAllSkills() {
        List<SkillLevels> skills = skillLevelRepo.findAll();
        List<String> skillNames = skills.stream()
        .map(skill -> skill.getSkillName())
        .collect(Collectors.toList());
        return skillNames;
    }
}

