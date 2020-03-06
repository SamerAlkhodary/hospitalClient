package states;

import model.entities.Patient;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PatientState extends State {
  public final  static  String name= "PatientState";
  private List<String> options;

  public PatientState() {
    options = new LinkedList<>();
    options.add("1- Show my record");
    options.add("2- log out");
  }

  @Override
  public void render() {
    System.out.println("Welcome Mr. "+this.user.getName());
    options.forEach(a -> System.out.println(a));
  }

  @Override
  public void run(StateHandler stateHandler) {
    Patient patient= (Patient)user;
    System.out.print("Action > ");
    String input =new Scanner(System.in).nextLine();
    switch (input){
      case "1":
        System.out.println(patient);
        break;
      case "2":
        System.out.println("Good bye!");
        System.out.print("\033\143");
        this.user=null;

        stateHandler.changeState(LoginState.name,null);
    }
  }

}
