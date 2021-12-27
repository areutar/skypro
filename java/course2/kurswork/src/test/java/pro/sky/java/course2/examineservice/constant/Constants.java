package pro.sky.java.course2.examineservice.constant;

import pro.sky.java.course2.examineservice.domain.Question;

import java.util.List;
import java.util.Set;

public class Constants {
    public static final String QUESTION1 = "QUESTION1";
    public static final String QUESTION2 = "QUESTION2";
    public static final String QUESTION3 = "QUESTION3";
    public static final String QUESTION4 = "QUESTION4";
    public static final String QUESTION5 = "QUESTION5";
    public static final String ANSWER1 = "ANSWER1";
    public static final String ANSWER2 = "ANSWER2";
    public static final String ANSWER3 = "ANSWER3";
    public static final String ANSWER4 = "ANSWER4";
    public static final String ANSWER5 = "ANSWER5";
    public static final Question QUEST1 = new Question(QUESTION1, ANSWER1);
    public static final Question QUEST2 = new Question(QUESTION2, ANSWER2);
    public static final Question QUEST3 = new Question(QUESTION3, ANSWER3);
    public static final Question QUEST4 = new Question(QUESTION4, ANSWER4);
    public static final Question QUEST5 = new Question(QUESTION5, ANSWER5);
    public static final Set<Question> ALL_QUESTIONS_SET = Set.of(QUEST1, QUEST2, QUEST3, QUEST4, QUEST5);
    public static final List<Question> ALL_QUESTIONS_LIST = List.of(QUEST1, QUEST2, QUEST3, QUEST4, QUEST5);

}
