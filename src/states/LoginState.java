package states;

import java.io.Console;
import java.util.Scanner;

import model.entities.*;
import online.LoginResponse;

public class LoginState extends State {
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
    System.out.println("Press 'Enter' after adding your key!");
     new Scanner(System.in).next();
     this.userRepo.setupConnection();
    System.out.print("user name: ");
    String name= new Scanner(System.in).nextLine();
    System.out.print("password: ");

    String password= new Scanner(System.in).nextLine();
    LoginResponse result= userRepo.login(name, String.valueOf(password));
    if(!result.isSuccess()){
      System.out.println(result.getMessage());
    }
    else {
      System.out.println(result.getEntity().getRole());
      switch (result.getEntity().getRole().toLowerCase()){
        case "doctor":  stateHandler.changeState("doctorstate",result.getEntity());break;
        case "government": stateHandler.changeState("governmentstate",result.getEntity());break;
        case "nurse":stateHandler.changeState("nursestate",result.getEntity()); break;
        case "patient": stateHandler.changeState("patientstate",result.getEntity());break;
      }

    }


  }



}

