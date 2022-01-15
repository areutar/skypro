package pro.sky.java.course2.examineservice.domain;

import java.util.Objects;

public class Question {
    private final String matter;
    private final String answer;

    public Question(String matter, String answer) {
        this.matter = matter;
        this.answer = answer;
    }

    public String getMatter() {
        return matter;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return matter.equals(question1.matter) && answer.equals(question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matter, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + matter + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
