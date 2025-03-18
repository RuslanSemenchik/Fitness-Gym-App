package firstProject.repository;


import model.ClassType;
import model.Membership;
import model.MembershipStatus;
import model.MembershipTime;
import model.MembershipType;

import java.time.LocalDate;
import java.util.List;

public interface MembeshipRepository {

        void saveMembership(String idMembership,MembershipTime membershipTime, MembershipType membershipType, ClassType classType);
        void removeMembership(String idMembership);
        Membership findMembershipById(String idMembership);
        boolean isValidMembership(String idMembership);
        public void  newMembershipStatus(Membership membership);
        List<Membership> allFindMembership();
        void extendMembership(Membership membership,int days);


    }
