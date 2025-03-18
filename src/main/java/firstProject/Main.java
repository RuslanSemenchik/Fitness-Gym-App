package firstProject;

import firstProject.repository.InMemoryClassScheduleAndTrainerRepositoryImpl;
import firstProject.repository.InMemoryClientRepositoryImpl;
import firstProject.repository.InMemoryMembershipRepositoryImpl;
import model.ClassSchedule;
import model.ClassType;
import model.Client;
import model.Membership;
import model.MembershipStatus;
import model.MembershipTime;
import model.MembershipType;
import model.Trainer;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        InMemoryMembershipRepositoryImpl inMemoryMembershipRepository = new InMemoryMembershipRepositoryImpl();
        inMemoryMembershipRepository.saveMembership("1",MembershipTime.DAY,MembershipType.MONTHLY,ClassType.YOGA);
        inMemoryMembershipRepository.saveMembership("2",MembershipTime.DAY,MembershipType.ANNUAL,ClassType.YOGA);
        inMemoryMembershipRepository.allFindMembership();
        inMemoryMembershipRepository.isValidMembership("2");








    }
}
/*




        Trainer ivanov = new Trainer("1","Ivanov", ClassType.YOGA,40);
        Trainer semenchik = new Trainer("2", "Semenchik",ClassType.YOGA,40);
        Trainer petrov = new Trainer("2", "Petrov",ClassType.STRENGTH_TRAINING,50);
        Trainer lee = new Trainer("3", "Lee",ClassType.JUDO,40);

        InMemoryClassScheduleAndTrainerRepositoryImpl inMemoryClassScheduleRepository = new InMemoryClassScheduleAndTrainerRepositoryImpl();

        inMemoryClassScheduleRepository.saveTrainer(ivanov);
        inMemoryClassScheduleRepository.saveTrainer(semenchik);
        inMemoryClassScheduleRepository.allFindTrainer();
        inMemoryClassScheduleRepository.removeTrainer("1");
        inMemoryClassScheduleRepository.editTrainer("2","Nichachu",ClassType.JUDO,100);
        inMemoryClassScheduleRepository.allFindTrainer();
        System.out.println(inMemoryClassScheduleRepository.isTrainerAvailable(ivanov, LocalDateTime.of(2025,1,1,23,0)));


        ClassSchedule classSchedule1 = new ClassSchedule(LocalDateTime.of(2025,1,1,7,0),ClassType.YOGA,ivanov, 1);
        ClassSchedule classSchedule2 = new ClassSchedule(LocalDateTime.of(2025,1,1,7,0),ClassType.YOGA,ivanov, 2);
        ClassSchedule classSchedule3 = new ClassSchedule(LocalDateTime.of(2025,1,1,11,0),ClassType.CROSSFIT,ivanov, 1);
        ClassSchedule classSchedule4 = new ClassSchedule(LocalDateTime.of(2025,1,1,13,0),ClassType.YOGA,ivanov, 1);
        ClassSchedule classSchedule5 = new ClassSchedule(LocalDateTime.of(2025,1,1,15,0),ClassType.YOGA,ivanov, 1);


        //InMemoryTrainerRepositoryImpl inMemoryTrainerRepository = new InMemoryTrainerRepositoryImpl();
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule1);
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule1);
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule2);
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule3);
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule4);
        inMemoryClassScheduleRepository.saveClassSchedule(classSchedule5);
         inMemoryClassScheduleRepository.generateScheduleReport(LocalDate.of(2025,1,1),LocalDate.of(2025,1,1));
inMemoryClassScheduleRepository.removeClassSchedule(5,LocalDateTime.of(2025,1,1,15,0));
        inMemoryClassScheduleRepository.generateScheduleReport(LocalDate.of(2025,1,1),LocalDate.of(2025,1,1));
        inMemoryClassScheduleRepository.editClassSchedule(1,LocalDateTime.of(2025,1,1,7,0),LocalDateTime.of(2025,1,1,18,0),ClassType.CROSSFIT, ivanov,6);
        inMemoryClassScheduleRepository.generateScheduleReport(LocalDate.of(2025,1,1),LocalDate.of(2025,1,1));







        //inMemoryTrainerRepository.saveTrainer(ivanov);
        //inMemoryTrainerRepository.saveTrainer(semenchik);
        //System.out.println(inMemoryTrainerRepository.allFindTrainer());


        Client client1 = new Client("1","Ivanov","semdnfne.@.com",);
        Client client2 = new Client("2","Petrov","semdnfne.@.com",MembershipType.EVENING);
        Client client3 = new Client("3","Siko","semdnfne.@.com",MembershipType.DAY);
        Client client4 = new Client("4","Ruminaldo","semdnfne.@.com",MembershipType.UNLIMITED);
        Client client5 = new Client("5","Semenchik","semdnfne.@.com",MembershipType.DAY);
        Client client6 = new Client("6","Kozel","semdnfne.@.com",MembershipType.EVENING);


        InMemoryClientRepositoryImpl inMemoryClientRepository = new InMemoryClientRepositoryImpl();







    }
}







 */