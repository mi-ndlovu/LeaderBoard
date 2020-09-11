package com.techiv.leaderboard.dto;

import java.util.List;

public class DataManager {

    private static List<Student> learningHours = null;
    private static List<StudentIQ> skillsIQ = null;

    public static List<Student> getLearningHours() {
        return learningHours;
    }

    public static void setLearningHours(List<Student> hours) {
        learningHours = hours;
    }

    public static List<StudentIQ> getSkillsIQ() {
        return skillsIQ;
    }

    public static void setSkillsIQ(List<StudentIQ> iQ) {
        skillsIQ = iQ;
    }
}
