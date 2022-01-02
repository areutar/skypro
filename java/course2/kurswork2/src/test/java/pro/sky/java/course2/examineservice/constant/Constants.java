package pro.sky.java.course2.examineservice.constant;

import pro.sky.java.course2.examineservice.domain.Question;

import java.util.HashSet;
import java.util.Set;

public class Constants {
    public static final String JAVA_QUESTION1 = "JAVA_QUESTION1";
    public static final String JAVA_QUESTION2 = "JAVA_QUESTION2";
    public static final String JAVA_QUESTION3 = "JAVA_QUESTION3";
    public static final String JAVA_QUESTION4 = "JAVA_QUESTION4";
    public static final String JAVA_QUESTION5 = "JAVA_QUESTION5";
    public static final String JAVA_ANSWER1 = "JAVA_ANSWER1";
    public static final String JAVA_ANSWER2 = "JAVA_ANSWER2";
    public static final String JAVA_ANSWER3 = "JAVA_ANSWER3";
    public static final String JAVA_ANSWER4 = "JAVA_ANSWER4";
    public static final String JAVA_ANSWER5 = "JAVA_ANSWER5";
    public static final Question J_QUEST1 = new Question(JAVA_QUESTION1, JAVA_ANSWER1);
    public static final Question J_QUEST2 = new Question(JAVA_QUESTION2, JAVA_ANSWER2);
    public static final Question J_QUEST3 = new Question(JAVA_QUESTION3, JAVA_ANSWER3);
    public static final Question J_QUEST4 = new Question(JAVA_QUESTION4, JAVA_ANSWER4);
    public static final Question J_QUEST5 = new Question(JAVA_QUESTION5, JAVA_ANSWER5);
    public static final Set<Question> JAVA_QUESTIONS_SET =
            Set.of(J_QUEST1, J_QUEST2, J_QUEST3, J_QUEST4, J_QUEST5);

    public static final String MATH_QUESTION1 = "MATH_QUESTION1";
    public static final String MATH_QUESTION2 = "MATH_QUESTION2";
    public static final String MATH_QUESTION3 = "MATH_QUESTION3";
    public static final String MATH_QUESTION4 = "MATH_QUESTION4";
    public static final String MATH_QUESTION5 = "MATH_QUESTION5";
    public static final String MATH_ANSWER1 = "MATH_ANSWER1";
    public static final String MATH_ANSWER2 = "MATH_ANSWER2";
    public static final String MATH_ANSWER3 = "MATH_ANSWER3";
    public static final String MATH_ANSWER4 = "MATH_ANSWER4";
    public static final String MATH_ANSWER5 = "MATH_ANSWER5";
    public static final Question M_QUEST1 = new Question(MATH_QUESTION1, MATH_ANSWER1);
    public static final Question M_QUEST2 = new Question(MATH_QUESTION2, MATH_ANSWER2);
    public static final Question M_QUEST3 = new Question(MATH_QUESTION3, MATH_ANSWER3);
    public static final Question M_QUEST4 = new Question(MATH_QUESTION4, MATH_ANSWER4);
    public static final Question M_QUEST5 = new Question(MATH_QUESTION5, MATH_ANSWER5);
    public static final Set<Question> MATH_QUESTIONS_SET =
            Set.of(M_QUEST1, M_QUEST2, M_QUEST3, M_QUEST4, M_QUEST5);
    public static final Set<Question> ALL_QUESTIONS_SET = new HashSet<>();

    static {
        ALL_QUESTIONS_SET.addAll(MATH_QUESTIONS_SET);
        ALL_QUESTIONS_SET.addAll(JAVA_QUESTIONS_SET);
    }

}
