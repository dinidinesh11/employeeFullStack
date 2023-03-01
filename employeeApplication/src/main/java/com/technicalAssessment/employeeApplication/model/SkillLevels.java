package com.technicalAssessment.employeeApplication.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "Skill_Levels")

public class SkillLevels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long skillLevelId;

    private String skillName;
    private String skillDescription;

//    @OneToMany(mappedBy = "skillLevels")
//    private Collection<Employee> employee;

//    public Collection<Employee> getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Collection<Employee> employee) {
//        this.employee = employee;
//    }

    public long getSkillLevelId() {
        return skillLevelId;
    }

    public void setSkillLevelId(long skillLevelId) {
        this.skillLevelId = skillLevelId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
