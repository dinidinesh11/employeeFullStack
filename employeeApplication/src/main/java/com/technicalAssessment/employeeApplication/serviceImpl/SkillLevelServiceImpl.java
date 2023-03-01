package com.technicalAssessment.employeeApplication.serviceImpl;

import com.technicalAssessment.employeeApplication.model.SkillLevels;
import com.technicalAssessment.employeeApplication.repository.SkillLevelRepo;
import com.technicalAssessment.employeeApplication.service.SkillLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SkillLevelServiceImpl implements SkillLevelService {
    @Autowired
    private SkillLevelRepo skillLevelRepo;


    @Override
    public List<SkillLevels> getAllSkillLevels() {
        return skillLevelRepo.findAll();
    }
}
