package firstProject.repository;

import model.ClassSchedule;
import model.ClassType;
import model.Membership;
import model.MembershipStatus;
import model.MembershipTime;
import model.MembershipType;
import model.Trainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.ClassSchedule.log;

public class InMemoryMembershipRepositoryImpl implements MembeshipRepository {
    private List<Membership>memberships = new ArrayList<>();

    @Override
    public void saveMembership(String idMembership, MembershipTime membershipTime, MembershipType membershipType, ClassType classType) {
        if (membershipTime == null || membershipType == null || classType == null) {
            System.out.println("Membership time : " + membershipTime + " or " + "membership type : " + membershipType + " or " +
                    " class type : " + classType + " is invalid");
            log.warn("Membership time : " + membershipTime + " or " + "membership type : " + membershipType + " or " +
                    " class type : " + classType + " is invalid");
        } else {
            Membership existingMembership = findMembershipById(idMembership);
            if (existingMembership == null) {
                memberships.add(new Membership(idMembership, membershipTime, membershipType, classType));
                System.out.println("Membership  with Id :  " + idMembership + " added");
                log.info("Membership  with Id :  " + idMembership + " added");

            } else {
                System.out.println("Membership with id : " + idMembership + " already exists");
                log.info(" Membership with id : " + idMembership + " already exists");

            }

        }
    }

    @Override
    public Membership findMembershipById(String  idMembership) {
        Optional<Membership> result = memberships.stream()
                .filter(membership -> membership.getId().equalsIgnoreCase(idMembership))
                .findFirst();
        log.info("Membership found " + result.isPresent());
        return result.orElse(null);
    }

    @Override
    public void removeMembership(String idMembership) {

            if (idMembership == null || idMembership.trim().isEmpty()) {
                System.out.println("Id membership : " + idMembership + " is invalid");
                log.warn("Id membership : " + idMembership + " is invalid");
            } else {
                Membership existingMembership = findMembershipById(idMembership);
                if (existingMembership == null) {
                    System.out.println("Membership with id : " + idMembership + " does not exist");
                    log.info("Membership with id : " + idMembership + " does not exist");
                } else {
                    memberships.remove(existingMembership);
                    System.out.println(" Trainer with id : " + idMembership + " removed");
                    log.info(" Trainer with id : " + idMembership + " removed");
                }
            }
        }

    @Override
    public boolean isValidMembership(String idMembership) {
        if (idMembership == null || idMembership.trim().isEmpty()) {
            System.out.println("Id membership : " + idMembership + " is invalid");
            log.warn("Id membership : " + idMembership + " is invalid");
        return false;
        } else {
            Membership existingMembership = findMembershipById(idMembership);
            if (existingMembership == null) {
                System.out.println("Membership with id : " + idMembership + " does not exist");
                log.info("Membership with id : " + idMembership + " does not exist");
                return false;
            }
            boolean result = LocalDate.now().isBefore(existingMembership.getValidUntil());
            log.info("Membership is valid :" + result);
            return result;
        }
    }


    @Override
    public void  newMembershipStatus(Membership membership) {
        if (membership == null) {
            System.out.println("Membership : " + membership + " is invalid");
            log.warn("Membership : " + membership + " is invalid");
        } else if (isValidMembership(membership.getId())) {
            membership.setMembershipStatus(MembershipStatus.ACTIVE);
        }
    }


    @Override
    public List<Membership> allFindMembership() {
        System.out.println(memberships);
        return new ArrayList<>(memberships);

    }

    @Override
    public void extendMembership(Membership membership,int days) {
        if (days <= 0) {
            System.out.println("Days are invalid");
            log.warn("Days are invalid");

        } else if (isValidMembership(membership.getId())){
            LocalDate newValidUntil = membership.getValidUntil().plusDays(days);
            membership.setValidUntil(newValidUntil);
            System.out.println(days + " - day(s) membership extension was successful and valid until "+ newValidUntil);
            log.info(days + " - day(s)  membership extension was successful and valid until "+ newValidUntil);
        }

    }
}

