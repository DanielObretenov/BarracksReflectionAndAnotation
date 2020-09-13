package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;

public class Retire extends Command{
    @Inject
    protected String []data ;
    @Inject
    protected Repository repository;

    public Retire(){

    }

    @Override
    public String execute() {
        try{
            this.repository.removeUnit(data[1]);
            return data[1] +" retired!";
        }catch (RuntimeException ex){
            return ex.getMessage();
        }
    }
}
