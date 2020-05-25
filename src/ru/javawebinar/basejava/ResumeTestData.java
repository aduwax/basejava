package ru.javawebinar.basejava;

import ru.javawebinar.basejava.webapp.model.*;

import java.time.YearMonth;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static boolean makeSimpleResume = false;
    public static Resume createInstance(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        if (makeSimpleResume) {
            return resume;
        }

        // Set contacts
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STATCKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOME_PAGE, "http://gkislin.ru/");
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
            .addPeriod(YearMonth.of(2013, 10), YearMonth.now(), "Автор проекта",
                    "Создание, организация и проведение Java онлайн проектов и стажировок.")
        );
        experienceSection.add(new TimelineSectionRecord("Wrike").addPeriod(YearMonth.of(2014, 10),
                YearMonth.of(2016, 1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                        "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO.")
                .addPeriod(YearMonth.of(2016, 2), YearMonth.of(2019, 1),
                "Системный архитектор")
        );
        resume.addSection(SectionType.EXPERIENCE, experienceSection);

        return resume;
    }

    public static void main(String[] args) {
        Resume resume = ResumeTestData.createInstance("uuid1", "Григорий Кислин");
        String delimiter = "\n----------------------------";

        // Write
        System.out.println(resume.getFullName() + delimiter);
        for (Map.Entry<ContactType, String> entry: resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        System.out.println(delimiter);

        for (Map.Entry<SectionType, AbstractSection> sectionEntry: resume.getSections().entrySet()) {
            System.out.println(sectionEntry.getValue().getTitle());
            System.out.println(sectionEntry.getValue().toString());
        }
    }
}
