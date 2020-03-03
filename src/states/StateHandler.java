package states;

import model.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class StateHandler {
  private Map<String, State> states = new HashMap<>();
  private State state = new LoginState();

  public StateHandler() {

    states.put("login", new LoginState());
    states.put("patientstate", new PatientState());
    states.put("doctorstate", new DoctorState());
    states.put("nursestate", new NurseState());
    states.put("governmentstate", new GovernmentState());
  }

  public void changeState(String state, Entity user) {
    State state1= states.get(state);

    state1.setUser(user);
    this.state =state1 ;
  }

  public void render() {
    state.render();
  }

  public void run() {
    state.run(this);
  }
}
