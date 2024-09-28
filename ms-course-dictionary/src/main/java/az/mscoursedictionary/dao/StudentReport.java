package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class StudentReport {//progress achievement
    private String username;

    private Map<String, Long> completedActivities;

    private Map<String, Long> progress;

    private List<String> certificationTitle;

    @Override
    public String toString() {
        return "\n" +
                "username='" + username + "'\n" +

                "completedActivities : " + completedActivities.entrySet().stream()
                .filter(entry -> entry.getKey().contains("id :"))
                .map(entry -> entry.getKey().substring(0, entry.getKey().indexOf("id")) + "-> " + entry.getValue())
                .reduce("", (a, b) -> a + "\n  " + b + ", ") +

                "\nprogress : " + progress.entrySet().stream()
                .filter(entry -> entry.getKey().contains("id :"))
                .map(entry -> entry.getKey().substring(0, entry.getKey().indexOf("id")) + "-> " + entry.getValue())
                .reduce("", (a, b) -> a + "\n  " + b + ", ") +

                "\ncertificationTitle : " + certificationTitle +
                '}';


    }
}
