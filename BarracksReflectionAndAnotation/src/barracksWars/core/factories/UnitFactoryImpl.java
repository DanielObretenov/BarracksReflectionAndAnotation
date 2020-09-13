package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		try {
			Class<?> unitModelClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<?> unitModelConstructor = unitModelClass.getDeclaredConstructor();
			Object obj = unitModelConstructor.newInstance();
			if (obj instanceof Unit){
				return (Unit) unitModelConstructor.newInstance();
			}
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Not such unit " + unitType);
		}
		return null;
	}
}




