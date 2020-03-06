package states;

import java.io.Console;
import java.util.Scanner;

import model.entities.*;
import online.LoginResponse;

public class LoginState extends State {
  public final  static  String name= "LoginState";
  private Console console = System.console();


  @Override
  public void render() {
    // console.printf("%s", "log in\n");
    System.out.println("login please");
  }

  @Override
  public void run(StateHandler stateHandler) {
    //String name = console.readLine("%s", "name: ");
    //  char[] password = console.readPassword("%s", "password: ");
    System.out.println("Have you inserted your key to the keys repository? (y/n)");
     String ans= new Scanner(System.in).nextLine();
     if(ans.toLowerCase().equals("yes") || ans.toLowerCase().startsWith("y"))
     this.userRepo.setupConnection();
     else
       System.exit(0);
    System.out.print("user name: ");
    String name= new Scanner(System.in).nextLine();
    System.out.print("password: ");

    String password= new Scanner(System.in).nextLine();
    LoginResponse result= userRepo.login(name, String.valueOf(password));
    if(!result.isSuccess()){
      System.out.println(result.getMessage());
    }
    else {

      switch (result.getEntity().getRole().toUpperCase()){
        case Doctor.role:  stateHandler.changeState(DoctorState.name,result.getEntity());break;
        case Government.role: stateHandler.changeState(GovernmentState.name,result.getEntity());break;
        case Nurse.role:stateHandler.changeState(NurseState.name,result.getEntity()); break;
        case Patient.role: stateHandler.changeState(PatientState.name,result.getEntity());break;
      }

    }


  }



}

