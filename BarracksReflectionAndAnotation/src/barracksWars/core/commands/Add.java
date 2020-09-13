package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

public class Add extends Command {

    @Inject
    protected String []data ;

    @Inject
    protected UnitFactory unitFactory;
    @Inject
    protected Repository repository;

    public Add(){
    }

    @Override
    public String execute() {
        String unitType = data[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }

    public Repository getRepository() {
        return repository;
    }
}
