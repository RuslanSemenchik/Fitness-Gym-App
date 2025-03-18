package firstProject.repository;

import model.ClassSchedule;
import model.ClassType;
import model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static model.ClassSchedule.log;

public class InMemoryClassScheduleAndTrainerRepositoryImpl implements ClassScheduleRepository, TrainerRepository {
    private final LocalTime OPENTIME = LocalTime.of(7, 0);
    private final LocalTime CLOSETIME = LocalTime.of(21, 59);
    private static final Logger log = LoggerFactory.getLogger(InMemoryClassScheduleAndTrainerRepositoryImpl.class);
    private List<ClassSchedule> schedules = new ArrayList<>();
    private List<Trainer> trainers = new ArrayList<>();


    @Override
    public void saveTrainer(Trainer trainer) {
        if (trainer == null) {
            System.out.println("Trainer : " + trainer + " is invalid");
            log.warn("Trainer : " + trainer + " is invalid");
        } else {
            Trainer existingTrainer = findTrainerById(trainer.getId());
            if (existingTrainer == null) {
                trainers.add(trainer);
                System.out.println("Trainer " + trainer.getTrainerName() + " with Id :  " + trainer.getId() + " added");
                log.info("Trainer " + trainer.getTrainerName() + " with Id :  " + trainer.getId() + " added");

            } else {
                System.out.println(" Trainer with id : " + trainer.getId() + " already exists");
                log.info(" Trainer with id : " + trainer.getId() + " already exists");
            }
        }
    }

    @Override
    public Trainer findTrainerById(String idTrainer) {
        Optional<Trainer> result = trainers.stream()
                .filter(trainer -> trainer.getId().equalsIgnoreCase(idTrainer))
                .findFirst();
        log.info("Trainer found " + result.isPresent());
        return result.orElse(null);
    }

    @Override
    public void removeTrainer(String idTrainer) {
        if (idTrainer == null || idTrainer.trim().isEmpty()) {
            System.out.println("Id trainer : " + idTrainer + " is invalid");
            log.warn("Id trainer : " + idTrainer + " is invalid");
        } else {
            Trainer existingTrainer = findTrainerById(idTrainer);
            if (existingTrainer == null) {
                System.out.println(" Trainer with id : " + idTrainer + " does not exist");
                log.info(" Trainer with id : " + idTrainer + " does not exist");
            } else {
                trainers.remove(existingTrainer);
                System.out.println(" Trainer with id : " + idTrainer + " removed");
                log.info(" Trainer with id : " + idTrainer + " removed");
            }
        }
    }

    @Override
    public void editTrainer(String idTrainer, String newTrainerName, ClassType newClassType, double newSalary) {
        if (idTrainer == null || idTrainer.trim().isEmpty()) {
            System.out.println("Id trainer : " + idTrainer + " is invalid");
            log.warn("Id trainer : " + idTrainer + " is invalid");
        } else {
            Trainer existingTrainer = findTrainerById(idTrainer);
            if (existingTrainer == null) {
                System.out.println(" Trainer with id : " + idTrainer + " does not exist");
                log.info(" Trainer with id : " + idTrainer + " does not exist");
            } else {
                existingTrainer.setTrainerName(newTrainerName);
                existingTrainer.setClassType(newClassType);
                existingTrainer.setSalary(newSalary);
                System.out.println(" Trainer with id : " + idTrainer + " edited");
                log.info(" Trainer with id : " + idTrainer + " edited");
            }
        }
    }

    @Override
    public List<Trainer> allFindTrainer() {
        System.out.println(trainers);
        return new ArrayList<>(trainers);
    }


    @Override
    public double getTotalTrainerSalary(LocalDate startDate, LocalDate endDate) {
        return schedules.stream()
                .filter(classSchedule -> classSchedule.getDateTime().toLocalDate().isAfter(startDate.minusDays(1))
                        && classSchedule.getDateTime().toLocalDate().isBefore(endDate.plusDays(1)))
                .map(ClassSchedule::getTrainer)
                .mapToDouble(Trainer::getSalary)
                .sum();
    }



    @Override
    public void saveClassSchedule(ClassSchedule classSchedule) {
        if (classSchedule == null) {
            System.out.println("Class schedule  :" + classSchedule + "  is invalid ");
            log.warn("Class schedule  :" + classSchedule + "  is invalid ");
        } else if (hasRelevanceBetweenClassTypeAndTrainer(classSchedule)) {

            if (isTrainerAvailable(classSchedule.getTrainer(), classSchedule.getDateTime()) &&
                    isRoomAvailable(classSchedule.getDateTime(), classSchedule.getRoom())) {

                schedules.add(classSchedule);
                System.out.println("Class schedule with Id : " + (classSchedule.getId() + " at " + classSchedule.getDateTime() + " added!"));
                log.info("Class schedule with Id : " + (classSchedule.getId() + " at " + classSchedule.getDateTime() + " added!"));
            }
        } else {
            System.out.println(" The wrong trainer or type class!");
            log.warn(" The wrong trainer or type class!");
        }
    }


    @Override
    public ClassSchedule findClassScheduleByIdAndDateTime(long idClassSchedule, LocalDateTime dateTime) {
        Optional<ClassSchedule> result = schedules.stream()
                .filter(classSchedule -> classSchedule.getId() == idClassSchedule && classSchedule.getDateTime().equals(dateTime))
                .findFirst();
        return result.orElse(null);
    }

    @Override
    public boolean isTrainerAvailable(Trainer trainer, LocalDateTime dateTime) {
        if (trainer == null || dateTime == null) {
            log.info("Trainer : " + trainer.getTrainerName() + " or dateTime : " + dateTime + " is invalid ");
            throw new DateTimeException("Trainer :" + trainer.getTrainerName() + " or dateTime : " + dateTime + " is invalid ");
        }
        LocalTime timeTime = dateTime.toLocalTime();
        timeTime = timeTime.minusMinutes(dateTime.getMinute());
        if (timeTime.isAfter(CLOSETIME) || timeTime.isBefore(OPENTIME)) {
            log.info("Trainer : " + trainer.getTrainerName() + " at " + dateTime + " is not available ");
            return false;
        }
        boolean result = schedules.stream()
                .anyMatch(classSchedule -> classSchedule.getTrainer().equals(trainer) && classSchedule.getDateTime().equals(dateTime));
        return !result;
    }

    public boolean isRoomAvailable(LocalDateTime dateTime, int room) {
        if (room <= 0 || dateTime == null) {
            log.info("Room : " + room + " or dateTime : " + dateTime + " is invalid ");
            throw new DateTimeException("Room :" + room + " or dateTime : " + dateTime + " is invalid ");
        }
        LocalTime timeTime = dateTime.toLocalTime();
        timeTime = timeTime.minusMinutes(dateTime.getMinute());
        if (timeTime.isAfter(CLOSETIME) || timeTime.isBefore(OPENTIME)) {
            log.info("Room : " + room + " at " + dateTime + " is not available ");
            return false;
        }
        boolean result = schedules.stream()
                .anyMatch(classSchedule -> classSchedule.getRoom() == room && classSchedule.getDateTime().equals(dateTime));
        return !result;
    }


    @Override
    public boolean hasRelevanceBetweenClassTypeAndTrainer(ClassSchedule classSchedule) {
        if (classSchedule == null) {
            System.out.println("Class schedule  :" + classSchedule + "  is invalid ");
            log.warn("Class schedule  :" + classSchedule + "  is invalid ");
            return false;
        }
        return classSchedule.getClassType().equals(classSchedule.getTrainer().getClassType());
    }


    @Override
    public void removeClassSchedule(long idClassSchedule, LocalDateTime dateTime) {
        if (idClassSchedule <= 0 || dateTime == null) {
            System.out.println("Classschedule with id : " + idClassSchedule + " is invalid");
            log.warn("Id classschedule : " + idClassSchedule + " is invalid");
        } else {
            ClassSchedule existingClassSchedule = findClassScheduleByIdAndDateTime(idClassSchedule, dateTime);
            if (existingClassSchedule == null) {
                System.out.println("Classschedule with id : " + idClassSchedule + " does not exist");
                log.info("Classschedule with id : " + idClassSchedule + " does not exist");
            } else {
                schedules.remove(existingClassSchedule);
                System.out.println("Classschedule with id : " + idClassSchedule  + " removed");
                log.info("Classschedule with id : " + idClassSchedule  + " removed");
            }
        }
    }

    @Override
    public void editClassSchedule(long idClassSchedule, LocalDateTime dateTime, LocalDateTime newDateTime, ClassType newClassType, Trainer newTrainer, int newRoom) {
        if (idClassSchedule <= 0 || dateTime == null ) {
            System.out.println("Classschedule with id : " + idClassSchedule + " is invalid");
            log.warn("Id classschedule : " + idClassSchedule + " is invalid");
        } else {
            ClassSchedule existingClassSchedule = findClassScheduleByIdAndDateTime(idClassSchedule, dateTime);
            if (existingClassSchedule == null) {
                System.out.println("Classschedule with id : " + idClassSchedule + " does not exist");
                log.info("Classschedule with id : " + idClassSchedule + " does not exist");
            }
            if (newClassType.equals(newTrainer.getClassType())) {
                existingClassSchedule.setDateTime(newDateTime);
                existingClassSchedule.setClassType(newClassType);
                existingClassSchedule.setTrainerName(newTrainer);
                existingClassSchedule.setRoom(newRoom);

                System.out.println(" Classschedule with id : " + idClassSchedule + " edited");
                log.info(" Classschedule with id : " + idClassSchedule + " edited");
            }else{
                System.out.println(" The wrong trainer or type class!");
                log.warn(" The wrong trainer or type class!");
            }
        }
    }


    @Override
    public List<ClassSchedule> generateScheduleReport(LocalDate startDate, LocalDate endDate) {
        List<ClassSchedule> classScheduleList = schedules.stream()
                .filter(classSchedule -> classSchedule.getDateTime().toLocalDate().isAfter(startDate.minusDays(1))
                        && classSchedule.getDateTime().toLocalDate().isBefore(endDate.plusDays(1)))
                .collect(Collectors.toList());

        System.out.println("  List schedules from " + startDate + " to  " + endDate);

        int count = 1;
        for (ClassSchedule classSchedule : schedules) {
            System.out.println(count + ". " + " Id: " + classSchedule.getId() + ", date and time : " + classSchedule.getDateTime() + ", class type : " + classSchedule.getClassType() + ", trainer name : " + classSchedule.getTrainer().getTrainerName() + ", room: " + classSchedule.getRoom());
            count++;
        }
        return classScheduleList;
    }
}


