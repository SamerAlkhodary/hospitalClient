package states;

import model.entities.Doctor;
import model.entities.Nurse;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NurseState extends State {
    public final  static  String name= "NurseState";
    private List options;
    private Nurse nurse;
    public  NurseState(){
        options= new LinkedList();
        options.add("1- view all records");
        options.add("2- view my patients");
        options.add("3- log out");
    }
    @Override
    public void render() {
        System.out.println("Welcome nurse "+this.user.getName());
        options.forEach(System.out::println);

    }

    @Override
    public void run(StateHandler stateHandler) {
        nurse= (Nurse) user;
        System.out.print("action > ");
        String input=new Scanner(System.in).nextLine();
        switch (input){
            case "1":
                this.userRepo.getEntities(nurse,nurse.getDivision()).getEntities().forEach(p -> System.out.println(p+"\n-------------------------"));
                break;
            case "2":
                nurse.getPatients().forEach(p -> System.out.println(p+"\n-------------------------"));

                break;
            case "3":
                System.out.println("Good bye!");
                System.out.print("\033\143");
                this.user=null;
               
                stateHandler.changeState(LoginState.name,null);

                break;
        }

    }
}
