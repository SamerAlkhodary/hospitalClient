package states;

import model.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class StateHandler {
  private Map<String, State> states = new HashMap<>();
  private State state = new LoginState();

  public StateHandler() {

    states.put(LoginState.name, new LoginState());
    states.put(PatientState.name, new PatientState());
    states.put(DoctorState.name, new DoctorState());
    states.put(NurseState.name, new NurseState());
    states.put(GovernmentState.name, new GovernmentState());
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
