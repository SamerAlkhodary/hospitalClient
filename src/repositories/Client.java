package repositories;

import online.*;

public interface Client {
    LoginResponse login(LoginRequest loginRequest);
    GetPatientResponse getEntity(GetPatientRequest getPatientRequest);
    RemoveResponse removePatient(RemoveRequest removeRequest);
    CreatePatientResponse createPatient(CreatePatientRequest createPatientRequest);
    UpdateResponse updatePatient(UpdateRequest updateRequest);


}
