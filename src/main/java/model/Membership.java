package model;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Membership {
    private static final Logger log = LoggerFactory.getLogger(Membership.class);
    private String id;
    private MembershipTime membershipTime;
    private MembershipType membershipType;
    private ClassType classType;
    private MembershipStatus membershipStatus;
    private LocalDate validUntil;



    public Membership(String id, MembershipTime membershipTime, MembershipType membershipType, ClassType classType) {
        this.id = id;
        this.membershipTime = membershipTime;
        this.membershipType = membershipType;
        this.classType = classType;
        this.membershipStatus = MembershipStatus.INACTIVE;
        if (this.membershipType.equals(MembershipType.MONTHLY)){
        this.validUntil = MembershipType.MONTHLY.getDateEnd();}
        else{
            validUntil = MembershipType.ANNUAL.getDateEnd();}
    }

    public String getId() {
        return id;
    }

    public MembershipTime getMembershipTime() {
        return membershipTime;
    }

    public ClassType getClassType() {
        return classType;
    }

    public MembershipType getMembershipType() {
        return membershipType;

    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }


    public LocalDate getValidUntil() {
        return validUntil;
    }


    public void setValidUntil(LocalDate validUntil) {
        if (validUntil == null) {
            System.out.println(" Date validUntil" + validUntil + " invalid");
            log.warn(" Date validUntil" + validUntil + " invalid");
        } else {
            this.validUntil = validUntil;
        }
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        if (validUntil == null) {
            System.out.println(" MembershipStatus " + membershipStatus + " invalid");
            log.warn(" MembershipStatus " + membershipStatus + " invalid");
        } else {
            this.membershipStatus = membershipStatus;
        }
    }



    @Override
    public String toString() {
        return "\n"+"Membership{" +
                "id='" + id + '\'' +
                ", membershipTime=" + membershipTime +
                ", membershipType=" + membershipType +
                ", classType=" + classType +
                ", membershipStatus=" + membershipStatus +
                ", validUntil=" + validUntil +
                '}';
    }
}

