package firstProject.repository;

import model.ClassSchedule;
import model.ClassType;
import model.Trainer;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClassScheduleRepository {
    void saveClassSchedule(ClassSchedule classSchedule);
    boolean isTrainerAvailable (Trainer trainer, LocalDateTime dateTime);
    boolean isRoomAvailable (LocalDateTime dateTime, int room);
    boolean hasRelevanceBetweenClassTypeAndTrainer(ClassSchedule classSchedule);
    void removeClassSchedule(long idClassSchedule, LocalDateTime dateTime);
    void editClassSchedule(long idClassSchedule, LocalDateTime dateTime, LocalDateTime newDateTime, ClassType newClassType, Trainer newTrainer, int newRoom);
    ClassSchedule findClassScheduleByIdAndDateTime(long idClassSchedule, LocalDateTime dateTime);
    List<ClassSchedule> generateScheduleReport(LocalDate startDate, LocalDate endDate);

}
