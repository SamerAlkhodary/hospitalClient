
import repositories.Connector;
import repositories.Client;
import states.StateHandler;

public class Application {

  public static void main(String[] args) {
    StateHandler stateHandler = new StateHandler();

    Client connector= Connector.client;
    while (true) {
      stateHandler.render();
      stateHandler.run();
    }
  }
}
