package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by viktoriyasidenko on 12/26/16.
 */
public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir(); //new File("/Users/viktoriyasidenko/Documents/Projects/U-RiseLvl2/TestTask/resume-storage/storage");//

    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

//        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective"));
//        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
//        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement11", "Achievement12", "Achievement13"));
//        R1.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "JavaScript"));
//        R1.addSection(SectionType.EXPERIENCE,
//                new OrganizationSection(
//                        new Organization("Organization11", "http://Organization11.ua",
//                                new Organization.DescriptTypeForOrganisation(2005, Month.JANUARY, "position1", "content1"),
//                                new Organization.DescriptTypeForOrganisation(2001, Month.AUGUST, 2005, Month.JANUARY, "position2", "content2"))));
//        R1.addSection(SectionType.EDUCATION,
//                new OrganizationSection(
//                        new Organization("University", null,
//                                new Organization.DescriptTypeForOrganisation(1996, Month.JANUARY, 2000, Month.DECEMBER, "student", null),
//                                new Organization.DescriptTypeForOrganisation(2001, Month.APRIL, 2005, Month.DECEMBER, "aspirant", "economic")),
//                        new Organization("Organization12", "http://Organization12.ua")));
//
//        R1.addContact(ContactType.SKYPE, "skype2");
//        R1.addContact(ContactType.PHONE, "1233456");
//
//        R1.addSection(SectionType.EXPERIENCE,
//                new OrganizationSection(
//                        new Organization("Organization2", "http://Organization2.ua",
//                                new Organization.DescriptTypeForOrganisation(2014, Month.JANUARY, "position11", "content11"))));
//
//        R2.addContact(ContactType.HOME_PHONE, "757939347");
//        R2.addContact(ContactType.MAIL, "mail");
//

    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);

    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(R1);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(R4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }


    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

}