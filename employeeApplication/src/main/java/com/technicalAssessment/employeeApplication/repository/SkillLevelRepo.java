package com.technicalAssessment.employeeApplication.repository;

import com.technicalAssessment.employeeApplication.model.SkillLevels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillLevelRepo extends JpaRepository<SkillLevels,String> {
    List<SkillLevels> findAll();
    SkillLevels findBySkillName(String skillName);

}
