package repositories;

import online.*;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Connector implements Client {
  public static final Client client = new Connector();
  private String host="localhost";
  private int port= 8888;
  private SSLSocket socket;
  private PrintWriter sender;
  private BufferedReader getter;
  private  ObjectOutputStream objectSender;
  private  ObjectInputStream objectReciever;
  private Connector() {
      try {
        init();
      } catch (Exception e) {
        e.printStackTrace();
      }

  }




  void init()throws Exception{
    socketBuilder();
    socket.startHandshake();
    SSLSession sslSession =socket.getSession();
    System.out.println(sslSession.getPeerPrincipal().getName());
    sender = new PrintWriter(socket.getOutputStream(), true);
    getter = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      objectSender = new ObjectOutputStream(socket.getOutputStream());
      objectReciever= new ObjectInputStream(socket.getInputStream());

  }
  private void socketBuilder()throws Exception{


    String keyPassword="password";
    KeyStore keyStore= KeyStore.getInstance("JKS");
    KeyStore trustStore= KeyStore.getInstance("JKS");
    KeyManagerFactory keyManagerFactory=KeyManagerFactory.getInstance("SunX509");
    TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance("SunX509");
    SSLContext context= SSLContext.getInstance("TLS");
    keyStore.load(new FileInputStream("keys/clientkeystore"),keyPassword.toCharArray());
    trustStore.load(new FileInputStream("keys/clienttruststore"),keyPassword.toCharArray());
    trustManagerFactory.init(trustStore);
    keyManagerFactory.init(keyStore,keyPassword.toCharArray());
    context.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);
    SSLSocketFactory factory= context.getSocketFactory();
    socket= (SSLSocket)factory.createSocket(host,port);


  }

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    LoginResponse response=new LoginResponse( false,"failed");;
    try {
      objectSender.writeObject(loginRequest);
      return (LoginResponse) objectReciever.readObject();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }


    return response ;


  }

  @Override
  public GetPatientResponse getEntity(GetPatientRequest request) {
    GetPatientResponse response=new GetPatientResponse( false,"failed");;
    try {
      objectSender.writeObject(request);
      response= (GetPatientResponse) objectReciever.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return response;
  }



  @Override
  public RemoveResponse removePatient(RemoveRequest removeRequest) {
    RemoveResponse response=new RemoveResponse(false,"failed");
    try {
      objectSender.writeObject(removeRequest);
      response=(RemoveResponse) objectReciever.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return response;
}

  @Override
  public CreatePatientResponse createPatient(CreatePatientRequest createPatientRequest) {
    CreatePatientResponse response=new CreatePatientResponse( null,false,"failed");;
    try {
      objectSender.writeObject(createPatientRequest);
      response= (CreatePatientResponse) objectReciever.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public UpdateResponse updatePatient(UpdateRequest updateRequest) {
    UpdateResponse response=new UpdateResponse( false,"failed");;
    try {
      objectSender.writeObject(updateRequest);
      response= (UpdateResponse) objectReciever.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return response;
  }

  @Override
  public void setupConnection() {
    try {
      init();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}