package firstProject.repository;

import model.Client;
import model.Membership;
import model.MembershipType;

import java.util.List;

public interface ClientRepository {
    void saveClient(String idClient, String name, String contactInfo, Membership membership);
    void removeClient(String idClient);
    void editClient (String idClient, String newName, String newContactInfo, Membership newMembership);
    Client findClientById(String idClient);
    List<Client>allFindClient();





}
