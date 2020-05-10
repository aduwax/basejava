package ru.javawebinar.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class ResumeDataTest {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");

        // Set contacts
        Map<String, String> contacts = new HashMap<>();
        contacts.put("Тел.", "+7(921) 855-0482");
        contacts.put("Skype", "grigory.kislin");
        contacts.put("Почта", "gkislin@yandex.ru");
        contacts.put("LinkedIn", "https://www.linkedin.com/in/gkislin");
        contacts.put("GitHub", "https://github.com/gkislin");
        contacts.put("StackOverflow", "+7(921) 855-0482");
        contacts.put("Домашняя страница", "+7(921) 855-0482");
        resume.setContacts(contacts);

        // Personal
        Resume.TextSection personalSection = new Resume.TextSection(
            SectionType.PERSONAL.getTitle()
        );
        personalSection.setText("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры.");
        resume.addSection(SectionType.PERSONAL, personalSection);
    }
}
