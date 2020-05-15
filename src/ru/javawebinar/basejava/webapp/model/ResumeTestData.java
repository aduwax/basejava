package ru.javawebinar.basejava.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {
    public static Resume get(String uuid, String name) {
        Resume resume = new Resume(uuid, name);

        // Set contacts
        Map<String, String> contacts = new HashMap<>();
        contacts.put("Тел.", "+7(921) 855-0482");
        contacts.put("Skype", "grigory.kislin");
        contacts.put("Почта", "gkislin@yandex.ru");
        contacts.put("LinkedIn", "https://www.linkedin.com/in/gkislin");
        contacts.put("GitHub", "https://github.com/gkislin");
        contacts.put("StackOverflow", "https://stackoverflow.com/users/548473");
        contacts.put("Домашняя страница", "http://gkislin.ru/");
        resume.setContacts(contacts);

        // Personal
        TextSection personalSection = new TextSection(
            SectionType.PERSONAL.getTitle()
        );
        personalSection.setText("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры.");
        resume.addSection(SectionType.PERSONAL, personalSection);

        // Qualifications
        TextListSection qualificationsSection = new TextListSection(
            SectionType.QUALIFICATIONS.getTitle()
        );
        qualificationsSection.addLine("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsSection.addLine("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsSection.addLine("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        resume.addSection(SectionType.QUALIFICATIONS, qualificationsSection);

        // Experience
        TimelineSection experienceSection = new TimelineSection(SectionType.EXPERIENCE.getTitle());
        experienceSection.add(new TimelineSectionRecord("Java Online Projects")
            .addPeriod("10/2013", "Сейчас", "Автор проекта",
                    "Создание, организация и проведение Java онлайн проектов и стажировок.")
        );
        experienceSection.add(new TimelineSectionRecord("Wrike").addPeriod("10/2014", "01/2016",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                        "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO.")
                .addPeriod("02/2016", "01/2019",
                "Системный архитектор")
        );
        resume.addSection(SectionType.EXPERIENCE, experienceSection);

        String delimiter = "\n----------------------------";

        // Write
        System.out.println(resume.getFullName() + delimiter);
        for (Map.Entry<String, String> entry: contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(delimiter);

        for (Map.Entry<SectionType, Section> sectionEntry: resume.getSections().entrySet()) {
            System.out.println(sectionEntry.getValue().getTitle());
            sectionEntry.getValue().write();
        }
        return resume;
    }
}
