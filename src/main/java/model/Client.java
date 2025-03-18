package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

        private static final Logger log = LoggerFactory.getLogger(Client.class);
        private String id;
        private String name;
        private String contactInfo;
        private Membership membership;
        private List<LocalDateTime> visits;



        public Client(String id, String name, String contactInfo,Membership membership) {
            this.id = id;
            this.name = name;
            this.contactInfo = contactInfo;
            this.membership = membership;
            visits =new ArrayList<>();


        }

        public void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Name is null or empty");
                log.warn("Name is null or empty");
            } else {
                this.name = name;
            }
        }

        public void setContactInfo(String contactInfo) {
            if (contactInfo == null || contactInfo.trim().isEmpty()) {
                System.out.println("Contact info is null or empty");
                log.warn("Contact info is null or empty");
            } else {
                this.contactInfo = contactInfo;
            }
        }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }


    public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getContactInfo() {
            return contactInfo;
        }



    public List<LocalDateTime> getVisits() {
            if (visits.isEmpty()) {
                System.out.println("Visit list is empty");
                log.info("Visit list is empty");
                return null;
            } else {
                int counter = 1;
                System.out.println("Visits of client with id  "+ id);

                for (LocalDateTime dateTime : visits) {
                    System.out.println( counter + " . " + dateTime);
                    counter++;
                }
            }
            return visits;
        }
/*
        public boolean isVisitValid (LocalTime time){
            if (time == null) {
                System.out.println("Time date  is null");
                log.warn("Time  date is null ");
                return  false;
            } else if (time.isAfter(Membership.().getTimeStart().minusMinutes(1))&&
                    time.isBefore(getMembershipType().getTimeEnd().plusMinutes(1))){
                return true;
            } else {
                return false;
            }
        }



        public void addVisit (LocalDateTime dateTime) {
            LocalTime visitTime = dateTime.toLocalTime();
            if (dateTime == null) {
                System.out.println("Time date  is null");
                log.warn("Time  date is null ");
            } else {
                if (visits.contains(dateTime)){
                    System.out.println("This date and time : "+ dateTime + " already exists and do not added");
                }
                if(!isVisitValid(visitTime)){
                    System.out.println("This client with id  " + getId() +  " is unable to attend the class at " + visitTime +" because  MembershipType " + getMembershipType());

                } else {
                    visits.add(dateTime);
                    System.out.println("Visit at " + dateTime + " for client with id : " + getId() + " added");
                    log.info("Visit at " + dateTime + " for client with id : " + getId() + " added");
                }
            }
        }



 */

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", membership=" + membership +
                ", visits=" + visits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

