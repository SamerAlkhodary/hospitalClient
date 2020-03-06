package states;

import model.Record;
import model.entities.Doctor;
import model.entities.Patient;
import online.CreatePatientResponse;
import online.UpdateResponse;

import javax.print.Doc;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DoctorState extends State {
    public final  static  String name= "DctorState";
    private List <String>options;
    private Doctor doctor;
    public DoctorState(){

        options= new LinkedList<>();
        options.add("1- view all records");
        options.add("3- list my patients");
        options.add("4- Create Patient");
        options.add("5- update patient");
        options.add("6- log out");


    }

    @Override
    public void render() {
        System.out.println("----------------------------------------------------");
        System.out.println("Welcome Dr. "+this.user.getName());
        options.forEach(System.out::println);

    }

    @Override
    public void run(StateHandler stateHandler) {
        doctor= (Doctor)user;
        System.out.print("action > ");
        String option=new Scanner(System.in).nextLine();
        System.out.println("----------------------------------------------------");
        switch (option){
            case "1": this.userRepo.getEntities(this.user,this.user.getDivision()).getEntities().forEach(p -> System.out.println(p+"\n-------------------------"));
                break;
            case "2":break;
            case "3":{
                if(doctor.getPatients().size()==0) System.out.println("No patients!");
                else doctor.getPatients().forEach(p -> System.out.println(p+"\n-------------------------"));
            };
            break;
            case "4":{
                Patient p=patientCreator();
                CreatePatientResponse response=this.userRepo.createPatient(doctor,p);
                doctor.addPatient(response.getPatient());
                System.out.println(response.getMessage());


            }
            break;
            case "5":
                Patient patient=patientChooser();
                if(patient!=null){
                    UpdateResponse response=this.userRepo.updatePatient(doctor,patient);
                    System.out.println(response.getMessage());

                }

                break;
            case "6":
                System.out.println("Good bye!");
                System.out.print("\033\143");
                this.user=null;
                stateHandler.changeState(LoginState.name,null);
                break;
            default:
                System.out.println("Invalid input");
        }

    }
    private Patient patientChooser(){
        if(this.doctor.getPatients().isEmpty()){
            System.out.println("No patients!");
            return null ;
        }
        final int[] i = {1};
        this.doctor.getPatients().forEach(p -> {
            System.out.println(i[0] +"- "+ p.getName());
            i[0] +=1;
        });
        String patientNumber=new Scanner(System.in).nextLine();
        while (true){
            try {
                System.out.print("Add description : ");
                String description=new Scanner(System.in).nextLine();
                Patient patient=doctor.getPatients().get(Integer.parseInt(patientNumber)-1);
                patient.setRecord(Record.builder().
                        assignDoctor(doctor).
                        assignNurse(patient.
                                getRecord().getNurse()).
                        addDescription(description));
                return patient;

            }catch (Exception e){
                System.out.println("Invalid input");

            }

        }


    }
    private Patient patientCreator(){
        while (true){
            try {
                System.out.print("Enter patient's name: ");
                String name=new Scanner(System.in).nextLine().trim();

                System.out.print("Assign nurse by ID: ");
                String nurseId=new Scanner(System.in).nextLine().trim();
                Integer.parseInt(nurseId);
                System.out.print("Add description : ");
                String description=new Scanner(System.in).nextLine();
                Patient p= new Patient(0,name,doctor.getDivision(),"patient");
                p.setRecord(
                        Record.builder()
                                .assignDoctor(doctor)
                                .assignNurse(nurseId)
                                .addDescription(description)

                );

                return p;

            }catch (Exception e){
                System.out.println("invalid input");
            }



        }


    }
}
