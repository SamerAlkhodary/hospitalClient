package repositories;

import online.*;

public interface Client {
    /***
     * Sends a LoginRequest to server and returns a LoginResponse
     * @param loginRequest
     * @return LoginResponse
     */
    LoginResponse login(LoginRequest loginRequest);

    /***
     * Sends a getPatientRequest to server and returns a GetPatientResponse
     * @param getPatientRequest
     * @return GetPatientResponse
     */
    GetPatientResponse getEntity(GetPatientRequest getPatientRequest);

    /***
     * Sends a removeRequest to server and returns a RemoveResponse
     * @param removeRequest
     * @return RemoveResponse
     */
    RemoveResponse removePatient(RemoveRequest removeRequest);

    /***
     * Sends a createPatientRequest to server and returns a CreatePatientResponse
     * @param createPatientRequest
     * @return CreatePatientResponse
     */
    CreatePatientResponse createPatient(CreatePatientRequest createPatientRequest);

    /***
     * Sends a updateRequest to server and returns a UpdateResponse
     * @param updateRequest
     * @return
     */
    UpdateResponse updatePatient(UpdateRequest updateRequest);

    /***
     * Sets the key in the ssl connection before logging in
     */
    void setupConnection();


}
