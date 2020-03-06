package states;

import model.entities.Government;
import model.entities.Patient;
import online.RemoveResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GovernmentState extends  State {
    public final  static  String name= "GovernmentState";
    private List<String > options;
    public  GovernmentState(){
        options= new LinkedList<>();
        options.add("1- show all records");
        options.add("2- remove records");
        options.add("3- log out");
    }

    @Override
    public void render() {

        options.forEach(System.out::println);

    }

    @Override
    public void run(StateHandler stateHandler) {
        Government government = (Government) user;
        System.out.print("action > ");
        String input = new Scanner(System.in).nextLine();
        switch (input) {
            case "1":
                System.out.println("-------------------------");
                this.userRepo.getEntities(government, government.getDivision()).getEntities().forEach(p -> System.out.println(p + "\n-------------------------"));
                break;
            case "2":
                System.out.println(patientDeleter(government).getMessage());

                break;


            case "3":
                System.out.println("Good bye!");
                System.out.print("\033\143");
                this.user = null;
                stateHandler.changeState(LoginState.name, null);

                break;


        }
    }

    private RemoveResponse patientDeleter(Government government) {
        List<Patient> list = this.userRepo.getEntities(government, government.getDivision()).getEntities();
        if (list.isEmpty()) {
            System.out.println("No patients are available!");
            return null;
        }
        final int[] i = {1};
        System.out.println("Choose patient:");
        list.forEach(p -> {
            System.out.println(i[0] + "- " + p.getName());
            i[0]++;
        });

        while (true) {
            String input = new Scanner(System.in).nextLine();
            try {


                int in = Integer.parseInt(input);
                if (in < 1 || in > list.size())
                    throw new Exception();
                return this.userRepo.removePatient(government, list.get(in - 1));


            } catch (Exception e) {
                System.out.println("invalid input!");
            }
        }


    }


}
