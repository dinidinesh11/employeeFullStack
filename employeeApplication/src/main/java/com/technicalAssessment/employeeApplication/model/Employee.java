package com.technicalAssessment.employeeApplication.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;





@Entity
@Table(name = "Employee")
@RedisHash("Employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long employeeId;

    @NotBlank(message = "please fill your first name")
    private String firstName;

    @NotBlank(message = "please fill your last name")
   private String lastName;

    @Email(message = "Enter a Valid email address")
    @NotBlank
    private String email;

    @Size(min = 0, max = 10)
    private String dob;


    boolean active;
//    @ManyToOne(cascade = CascadeType.ALL)
//    private SkillLevels skillLevels;

    private String selectedSkill;

    public String getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(String selectedSkill) {
        this.selectedSkill = selectedSkill;
    }
    @Min(18)
    @Max(45)
    int age;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employee_Id) {
        this.employeeId = employee_Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {


        return dob;
    }

    public void setDob(String dob)  {

        String[] str = dob.split(" ");
        dob=(str[0]);


    this.dob = dob;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
