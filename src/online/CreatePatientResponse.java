package online;

import model.entities.Patient;

public class CreatePatientResponse implements Message {
    private boolean success;
    private String message;
    private  Patient patient;

    public CreatePatientResponse(Patient patient, boolean success, String message) {
        this.patient=patient;
        this.success = success;
        this.message = message;

    }

    public Patient getPatient() {
        return patient;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}


