package firstProject.repository;

import lombok.extern.slf4j.Slf4j;
import model.ClassSchedule;
import model.Client;
import model.Membership;
import model.MembershipType;
import model.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static model.ClassSchedule.log;

@Slf4j


public class InMemoryClientRepositoryImpl implements  ClientRepository {
    private List<Client>clients = new ArrayList<>();

    @Override
    public void saveClient(String idClient, String name, String contactInfo, Membership membership) {
        if (idClient == null|| idClient.trim().isEmpty()||name == null||name.trim().isEmpty()
        ||contactInfo == null|| contactInfo.trim().isEmpty()|| membership == null) {
            System.out.println("IdClient : " + idClient + " or "+ " name : " + name + " or "+ " contact info : " + contactInfo + " or "+" membership : " + membership +" is invalid");
            log.warn("IdClient : " + idClient + " or "+ " name : " + name + " or "+ " contact info : " + contactInfo + " or "+" membership : " + membership +" is invalid");
        } else {
            Client existingClient = findClientById(idClient);
            if (existingClient == null) {
                clients.add(new Client(idClient,name,contactInfo,membership));
                System.out.println("Client " + name + " with Id :  " + idClient);

            } else {
                System.out.println("Client " + name+ " with Id :  " + idClient + " already exists");
                log.info("Client " + name+ " with Id :  " + idClient + " already exists");
            }
        }
    }

        @Override
        public Client findClientById(String idClient) {
            Optional<Client> result = clients.stream()
                    .filter(reader -> reader.getId().equalsIgnoreCase(idClient))
                    .findFirst();
            log.info("Client found " + result.isPresent());
            return result.orElse(null);
        }

        @Override
    public void removeClient(String idClient) {
            if (idClient == null || idClient.trim().isEmpty()) {
                System.out.println("Id client : " + idClient + " is invalid");
                log.warn("Id client : " + idClient + " is invalid");
            } else {
                Client existingClient = findClientById(idClient);
                if (existingClient == null) {
                    System.out.println(" Client with id : " + idClient + " does not exist");
                    log.info(" Client with id : " + idClient + " does not exist");
                } else {
                    clients.remove(existingClient);
                    System.out.println(" Client with id : " + idClient + " removed");
                    log.info(" Client with id : " + idClient + " removed");
                }
            }
    }

    @Override
    public void editClient(String idClient, String newName, String newContactInfo, Membership newMembership) {
        if (idClient == null|| idClient.trim().isEmpty()||newName == null||newName.trim().isEmpty()
                ||newContactInfo == null|| newContactInfo.trim().isEmpty()|| newMembership == null) {
            System.out.println("IdClient : " + idClient + " or "+ " name : " + newName + " or "+ " contact info : " + newContactInfo + " or "+" membership : " + newMembership +" is invalid");
            log.warn("IdClient : " + idClient + " or "+ " name : " + newName + " or "+ " contact info : " + newContactInfo + " or "+" membership : " + newMembership +" is invalid");
        } else {
            Client existingClient = findClientById(idClient);
            if (existingClient == null) {
                System.out.println("Client with id : " + idClient + " does not exist");
                log.info("Client with id : " + idClient + " does not exist");
            }
                existingClient.setName(newName);
                existingClient.setContactInfo(newContactInfo);
                existingClient.setMembership(newMembership);
                System.out.println(" Classschedule with id : " + idClient + " edited");
                log.info(" Classschedule with id : " + idClient + " edited");
            }
    }

    @Override
    public List<Client> allFindClient() {

        return new ArrayList<>(clients);
    }
}
