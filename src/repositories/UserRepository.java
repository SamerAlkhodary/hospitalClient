package repositories;

import model.entities.Doctor;
import model.entities.Entity;
import model.entities.Government;
import model.entities.Patient;
import online.*;

public class UserRepository implements Repository {
  private Client client = Connector.client;

  @Override
  public LoginResponse login(String userName, String password) {
    return client.login(new LoginRequest(userName, password));
  }

  @Override
  public GetPatientResponse getEntities(Entity issuer, String division) {
    return client.getEntity(new GetPatientRequest(issuer.getId(), division));
  }


  @Override
  public RemoveResponse removePatient(Government issuer, Patient patient) {
    return client.removePatient(new RemoveRequest(issuer, patient));
  }

  @Override
  public CreatePatientResponse createPatient(Doctor issuer, Patient patient) {
    return client.createPatient(new CreatePatientRequest(issuer.getId(),patient));
  }

  @Override
  public UpdateResponse updatePatient(Doctor issuer, Patient patient) {
    return client.updatePatient(new UpdateRequest(issuer, patient));
  }


}
