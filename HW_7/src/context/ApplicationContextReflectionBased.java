package context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ApplicationContextReflectionBased implements ApplicationContext {

    private HashMap<String, Object> map;

    public ApplicationContextReflectionBased() {
        map = new HashMap<>();
    }

    @Override
    public <T> T getComponent(Class<T> componentType, String name) {

        if (!map.containsKey(name)) {
            createComponent(componentType);
        }

        return (T) map.get(name);
    }

    private Component findComponent(Field field) {

        String className = field.getType().toString().substring(6);

        return (Component) map.entrySet().stream()
                .filter(component -> component.getValue().getClass().getName().equals(className))
                .limit(1).collect(Collectors.toList()).get(0).getValue();
    }

    public <T> void createComponent(Class<T> componentType) {

        if (map.get(componentType.getName()) == null) {

            Field[] fields = componentType.getDeclaredFields();

            Arrays.stream(fields).forEach(field -> {

                Class[] interfaces = field.getType().getInterfaces();

                Arrays.stream(interfaces).forEach(i -> {

                    if (i.getName().equals(Component.class.getName())) {
                        createComponent(field.getType());
                    }

                });
            });

        }

        getAddictions(componentType);
    }

    private <T> void getAddictions(Class<T> componentType) {

        try {

            Object obj = componentType.getDeclaredConstructor().newInstance();

            map.put(((Component) obj).getName(), obj);

            Field[] fields = obj.getClass().getDeclaredFields();

            Arrays.stream(fields).forEach(field -> {

                Class[] interfaces = field.getType().getInterfaces();

                Arrays.stream(interfaces).forEach(i -> {

                    if (i.getName().equals(Component.class.getName())) {
                        field.setAccessible(true);

                        try {

                            field.set(obj, findComponent(field));

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                });

            });

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
