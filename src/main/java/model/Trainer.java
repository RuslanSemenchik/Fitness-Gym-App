package model;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.StringJoiner;

import static model.ClassSchedule.log;

public class Trainer {
    private String id;
    private String trainerName;
    private ClassType classType;
    private double salary;



    public Trainer(String id, String trainerName, ClassType classType, double salary) {
        this.id = id;
        this.trainerName = trainerName;
        this.classType = classType;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public double getSalary() {
        return salary;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setTrainerName(String trainerName) {
        if (trainerName == null || trainerName.trim().isEmpty()) {
            System.out.println("Id trainer : " + trainerName + " is invalid");
            log.warn(" Trainer name : " + trainerName + " is invalid");
        } else {
            this.trainerName = trainerName;
        }
    }

    public void setClassType(ClassType classType){
            if (classType == null) {
                System.out.println("Class type: " + classType + " is invalid");
                log.warn("Class type: " + classType + " is invalid");
            } else {
                this.classType = classType;
            }
        }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("Salary: " + salary + " is invalid");
            log.warn("Salary: " + salary + " is invalid");
        } else {
            this.salary = salary;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(id, trainer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "\n"+"Trainer{" +
                "id='" + id + '\'' +
                ", trainerName='" + trainerName + '\'' +
                ", classType=" + classType +
                ", salary=" + salary +
                '}';
    }
}
