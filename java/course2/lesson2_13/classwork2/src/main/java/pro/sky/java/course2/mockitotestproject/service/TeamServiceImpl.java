package pro.sky.java.course2.mockitotestproject.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mockitotestproject.repository.TeamRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public String add(String teamName) {
        return teamRepository.add(teamName);
    }

    @Override
    public String find(String teamName) {
        return teamRepository.find(teamName);
    }

    @Override
    public String remove(String teamName) {
        return teamRepository.remove(teamName);
    }

    @Override
    public String findTeamsAsString() {
        return String.join(", ", findTeams());
    }

    @Override
    public Collection<String> findTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Collection<String> findTeamsByPrefix(String prefix) {
        return findTeams().stream()
                .filter(team -> team.startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<String> removeAll() {
        return teamRepository.removeAll();
    }
}