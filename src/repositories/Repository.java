package repositories;

import model.entities.Doctor;
import model.entities.Entity;
import model.entities.Government;
import model.entities.Patient;
import online.*;

public interface Repository {
  LoginResponse login(String userName, String password);
  GetPatientResponse getEntities(Entity issuer, String division);
  RemoveResponse removePatient(Government issuer, Patient patient);
    CreatePatientResponse createPatient(Doctor issuer, Patient patient);
    UpdateResponse updatePatient(Doctor issuer, Patient patient);



}
