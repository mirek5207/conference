package com.example.conference.services;

import com.example.conference.entities.User;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class OrganiserServiceImpl implements OrganiserService {

    private UserRepository userRepository;
    private LectureRepository lectureRepository;

    @Autowired
    public OrganiserServiceImpl(UserRepository userRepository, LectureRepository lectureRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Set<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Set<String> getLecturesReport() {
        return lectureRepository.getPercentageOfUsersParticipatingInLectures();
    }

    public String getConferencePlan(){
        return "<h2>PLAN KONFERENCJI IT</h2><br>" +
                "W dniu 1 czerwca 2022 roku odbędzie się konferencja integrująca całą branżę IT.<br>" +
                "Konferencja IT 2022 (online) odbędą się w formie wirtualnego wydarzenia.<br>" +
                "Konferencja rozpoczyna się o godzinie 10:00 a kończy o godzinie 15:45.<br><br>" +
                "Każda prelekcja trwa 1h 45m (15 minut to przerwa na kawę) :<br>" +
                "- pierwsza prelekcja rozpoczyna się o 10:00 i trwa do 11:45.<br>" +
                "- druga rozpoczyna się o 12:00 i kończy o 13:45<br>" +
                "- trzecia rozpoczyna się o 14:00 i kończy o 15:45<br><br>" +
                "W ramach konferencji obsługiwane są 3 różne ścieżki tematyczne prowadzone równolegle<br>" +
                "Każda prelekcja może pomieścić maksymalnie 5 słuchaczy<br>";
    }


}
