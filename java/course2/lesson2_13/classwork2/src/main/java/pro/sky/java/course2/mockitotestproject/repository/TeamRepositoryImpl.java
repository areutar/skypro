package pro.sky.java.course2.mockitotestproject.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.mockitotestproject.exception.TeamExistsException;
import pro.sky.java.course2.mockitotestproject.exception.TeamNotExistException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final Set<String> teams = new HashSet<>();

    @Override
    public String add(String teamName) {
        if (teams.contains(teamName)) {
            throw new TeamExistsException();
        }
        teams.add(teamName);
        return teamName;
    }

    @Override
    public String remove(String teamName) {
        if (teams.contains(teamName)) {
            teams.remove(teamName);
            return teamName;
        }
        throw new TeamNotExistException();
    }

    @Override
    public String find(String teamName) {
        if (!teams.contains(teamName)) {
            throw new TeamNotExistException();
        }
        return teamName;
    }

    @Override
    public Collection<String> findAll() {
        return Collections.unmodifiableSet(teams);
    }

    @Override
    public Collection<String> removeAll() {
        Collection<String> result = Collections.unmodifiableSet(teams);
        teams.clear();
        return result;
    }
}
