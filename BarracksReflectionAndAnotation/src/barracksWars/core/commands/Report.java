package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;

public class Report extends Command {
    @Inject
    protected Repository repository;

    public Report( ){

    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
