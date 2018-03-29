package com.grsu.committee.utils;

import com.grsu.committee.entities.Administrator;
import com.grsu.committee.entities.Enrollee;

import java.util.*;

public class Utils {
    private static List<String> firstNames = Arrays.asList("Tessie", "Kala", "Carlo", "Natalia", "Dovie", "Blair", "Le",
            "Wendell", "Dong", "Shauna", "Kendal", "Janessa", "Michaele", "Jaclyn", "Destiny", "Larraine", "Mario",
            "Deirdre", "Ruthe", "Adelle", "Stasia", "Michiko", "Alejandro", "Joye", "Moriah", "Madison", "Gertie",
            "Hermelinda", "Marcie", "Reina", "Josephine", "Lois", "Avery", "Vanda", "Lajuana", "Brad", "Jolie", "Miesha",
            "Felipe", "Annis", "Herb", "Ali", "Wilton", "Clemencia", "Tamika", "Everett", "Alvina", "Tanja", "Omega", "Hsiu");
    private static List<String> lastNames = Arrays.asList("Barron", "Lowe", "Estrada", "Mann", "George", "Wilkerson",
            "Miller", "Joyce", "Calhoun", "Collins", "Mcintosh", "Burns", "Dickson", "Boyd", "Webb", "Stanton", "Patrick",
            "Reese", "Combs", "Patel", "Harris", "Stevens", "Vasquez", "Cardenas", "Rivera", "Compton", "Solomon", "Wyatt",
            "Sanchez", "Woodard", "Thomas", "Larson", "Barrett", "Mata", "Blackburn", "Yates", "Valencia", "Cobb", "Weber",
            "Kim", "Guerra", "Munoz", "Holden", "Thompson", "Leon", "Bender", "Buckley", "Gallegos", "Huff", "Jensen");

    private static Random random;

    public static String generateRandomFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public static String generateRandomLastName() {
        return firstNames.get(random.nextInt(lastNames.size()));
    }

    public static Administrator generateRandomAdministrator() {
        Administrator administrator = new Administrator();
        administrator.setFirstName(generateRandomFirstName());
        administrator.setLastName(generateRandomLastName());
        administrator.setAge(23 + random.nextInt(30));
        return administrator;
    }

    public static Enrollee generateRandomEnrollee() {
        Enrollee enrollee = new Enrollee();
        enrollee.setFirstName(generateRandomFirstName());
        enrollee.setLastName(generateRandomLastName());
        enrollee.setAge(17 + random.nextInt(10));
        return enrollee;
    }

    public static Comparator<Enrollee> getSheetEnrolleeComparator() {

        return (o1, o2) -> {
            Integer o1SummaryPoints = calculateSummaryPoints(o1.getPointsMap().values());
            Integer o2SummaryPoints = calculateSummaryPoints(o2.getPointsMap().values());

            return o2SummaryPoints.compareTo(o1SummaryPoints);
        };
    }

    private static int calculateSummaryPoints(Collection<Integer> pointsList) {
        int sum = 0;
        for (int value : pointsList) {
            sum += value;
        }

        return sum;
    }
}
