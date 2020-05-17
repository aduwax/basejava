package ru.javawebinar.basejava.webapp;

import ru.javawebinar.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("uuid1", "Unknown Person");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        // invoke r.toString via reflection
        System.out.println(r.getClass().getDeclaredMethod("toString").invoke(r));
    }
}