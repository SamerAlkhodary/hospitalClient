package states;

import model.entities.Entity;
import repositories.Repository;
import repositories.UserRepository;

public abstract class State {
    protected Repository userRepo = new UserRepository();
    protected Entity user;
    public  void setUser(Entity user){
        this.user=user;
    }
  public abstract void render();

  public abstract void run(StateHandler stateHandler);

}
