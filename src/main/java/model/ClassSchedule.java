package model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Objects;

public class ClassSchedule {
    public static final Logger log = LoggerFactory.getLogger(ClassSchedule.class);
    private static long counterID =1;
    private long id;
    private LocalDateTime dateTime;
    private ClassType classType;
    private Trainer trainer;
    private int room;



    public ClassSchedule(LocalDateTime time, ClassType classType, Trainer trainer, int room) {
        this.id = counterID++;
        this.dateTime = time;
        this.classType = classType;
        this.trainer = trainer;
        this.room = room;

    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ClassType getClassType() {
        return classType;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public int getRoom() {
        return room;
    }

    public static long getCounterID() {
        return counterID;
    }


    public void setClassType(ClassType classType) {
        if (trainer == null) {
            System.out.println("ClassType  is null ");
            log.warn("Trainer name  is null ");

        } else {

            this.classType = classType;
        }
    }


    public void setTrainerName(Trainer trainer) {
        if (trainer == null) {
            System.out.println("Trainer name  is null ");
            log.warn("Trainer name  is null ");
        } else {
            this.trainer = trainer;
        }
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            System.out.println("Time  is null");
            log.warn("Time  is null ");
            throw new DateTimeException( " Date or time : " + dateTime + " is invalid ");
        } else {
            this.dateTime = dateTime;
        }
    }

    public void setRoom(int room) {
        if (room <=0 ) {
            System.out.println("Room  is zero or negative value");
            log.warn("Room  is zero or negative value");
        } else {

            this.room = room;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSchedule that = (ClassSchedule) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }



    @Override
    public String toString() {
        return "\n"+"ClassSchedule{" +
                "id='" + id + '\'' +
                ", time=" + dateTime +
                ", classType='" + classType + '\'' +
                ", trainer='" + trainer + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
