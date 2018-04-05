package com.grsu.committee;

import com.grsu.committee.dataaccess.impl.EnrolleeDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.utils.Utils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EnrolleeDaoTest {

    private static EnrolleeDao enrolleeDao = new EnrolleeDao();

    @BeforeClass
    public static void createDao() {
    }

    @AfterClass
    public static void deleteTestXmlData() {
        ensureDataRemoved();
    }

    @Test
    public void testAdd() {
        System.out.println("Start 'save' test for Enrollee");
        final Enrollee newEnrollee = saveNewEnrollee();
        Assert.assertNotNull(enrolleeDao.get(newEnrollee.getId()));
    }

    @Test
    public void testDelete() {
        System.out.println("Start 'delete' test for Enrollee");
        final Enrollee newEnrollee = saveNewEnrollee();
        enrolleeDao.delete(newEnrollee.getId());
        Assert.assertNull(enrolleeDao.get(newEnrollee.getId()));
    }

    @Test
    public void testGetAll() {
        System.out.println("Start 'getAll' test for Enrollee");
        final List<Enrollee> enrolleeList = new ArrayList<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            Enrollee enrollee = Utils.generateRandomEnrollee();
            enrolleeDao.save(enrollee);
            enrolleeList.add(enrollee);
        }

        final List<Enrollee> enrolleeListFromFile = enrolleeDao.getAll();
        Assert.assertEquals(size, enrolleeListFromFile.size() - 1);

        for (Enrollee enrollee : enrolleeListFromFile) {
            enrolleeDao.delete(enrollee.getId());
        }
    }

    private Enrollee saveNewEnrollee() {
        final Enrollee newEnrollee = Utils.generateRandomEnrollee();
        enrolleeDao.save(newEnrollee);
        return newEnrollee;
    }

    private static void ensureDataRemoved() {
        for (Enrollee enrollee : enrolleeDao.getAll()) {
            enrolleeDao.delete(enrollee.getId());
        }
    }

}
