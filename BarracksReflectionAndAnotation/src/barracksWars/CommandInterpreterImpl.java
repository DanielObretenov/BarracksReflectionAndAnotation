package barracksWars;

import barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private Repository repository;
    private UnitFactory unitFactory;
    private static final String COMMAND_PATH = "barracksWars.core.commands.";

    public CommandInterpreterImpl(Repository repository,UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory =unitFactory;
    }


    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        commandName = commandName.substring(0,1).toUpperCase() + commandName.substring(1).toLowerCase();
        Executable executable = null;
        try {
            Class<?> commandClass = Class.forName(COMMAND_PATH + commandName);
            Constructor<?> declaredConstructor = commandClass.getDeclaredConstructor();
            executable = (Executable) declaredConstructor.newInstance();
            setFields(executable,data);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("No such command " + commandName);
        }

        return executable;
    }

    private void setFields(Executable executable,String[] data) throws IllegalAccessException {
        Field[] executableFields = executable.getClass()
                .getDeclaredFields();
        Field[] localFields = this.getClass().getDeclaredFields();

        for (Field field : executableFields) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation!=null) {
                field.setAccessible(true);
                if (field.getType() == String[].class) {
                    field.set(executable, data);
                } else {
                    for (Field localField : localFields) {
                        if (localField.getType() == field.getType()) {
                            field.set(executable, localField.get(this));
                        }
                    }
                }
            }


        }
    }


}
