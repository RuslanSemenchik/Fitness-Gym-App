package firstProject.repository;


import model.ClassType;
import model.Trainer;

import java.time.LocalDate;
import java.util.List;

public interface TrainerRepository {
    void saveTrainer(Trainer trainer);
    void removeTrainer(String idTrainer);
    void editTrainer (String idTrainer, String newTrainerName, ClassType newClassType, double newSalary );
    Trainer findTrainerById(String idTrainer);
    List<Trainer> allFindTrainer();
    double getTotalTrainerSalary(LocalDate startDate, LocalDate endDate);

}
