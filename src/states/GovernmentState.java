package states;

import model.entities.Government;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GovernmentState extends  State {
    private List<String > options;
    public  GovernmentState(){
        options= new LinkedList<>();
        options.add("1- show all records");
        options.add("1- remove records");
    }
    @Override
    public void render() {

        options.forEach(System.out::println);

    }

    @Override
    public void run(StateHandler stateHandler) {
        Government government= (Government)user;
        System.out.print("action > ");
        String input =new Scanner(System.in).nextLine();
        switch (input){
            case "1":
                System.out.println("-------------------------");
                this.userRepo.getEntities(government,government.getDivision()).getEntities().forEach(p -> System.out.println(p+"\n-------------------------"));
                break;
        }
    }


}
