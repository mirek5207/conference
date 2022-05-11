package com.example.conference.services;

import com.example.conference.entities.Lecture;
import com.example.conference.entities.User;
import com.example.conference.repositories.LectureRepository;
import com.example.conference.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private LectureRepository lectureRepository;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(LectureRepository lectureRepository, UserRepository userRepository) {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
    }
    @Override
    public User addUserToLecture(Long lectureId, String login, String email) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie znaleziono wykładu o takim id"));
        User user = getUserIfExist(login,email);
        checkIfLectureHaveFreePlace(lecture);
        if(user != null){
            checkIfUserNotReservedAnotherLectureAtThisTimeFrame(lecture,user);
            user.getLectureSet().add(lecture);
        }
        else{
            Set<Lecture> lectures = new HashSet<>();
            lectures.add(lecture);
            user = new User(null,login,email,lectures);
        }
        userRepository.save(user);
        saveToFile(getDate(),email, ("Lecture:" + lecture.getName() + "\n" + "Time frame:"+ lecture.getTimeFrame()));
        return user;
    }

    @Override
    public Set<Lecture> getAllUserLecture(String login) {
        Set<Lecture> lecture = lectureRepository.getLecture(login);
        if(lecture.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie ma takiego użytkownika lub nie jest zapisany na żaden wykład");
        }
        return lecture;
    }

    @Override
    public void deleteReservation(Long lectureId, String login, String email) {
        User user = getUserIfExist(login,email);
        lectureRepository.deleteReservedLecture(user.getId(),lectureId);
    }

    @Override
    public User updateEmail(String login, String email) {
        User user = userRepository.findUserByLogin(login);
        if(user != null) user.setEmail(email);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nie odnaleziono użytkownika o podanym loginie");
        userRepository.save(user);
        return user;
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

    private User getUserIfExist(String login, String email){
        User user = userRepository.findUserByLogin(login);
        if(user != null && !(user.getEmail().equals(email))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Podany login jest już zajęty");
        }
        return user;
    }
    public void checkIfLectureHaveFreePlace(Lecture lecture){
        if(lecture.getUserSet().size() >= 5){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Limit miejsc wykorzystany, brak możliwości zapisu na wykład");
        }
    }
    public void checkIfUserNotReservedAnotherLectureAtThisTimeFrame(Lecture lecture, User user){
        Set<Lecture> userLectureSet = user.getLectureSet();
        String timeOfLecture = lecture.getTimeFrame();
        for (Lecture lect : userLectureSet) {
            if(lect.getTimeFrame().equals(timeOfLecture)){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Użytkownik jest zapisany na tą prelekcji lub uczestniczy już w innej o tej porze, brak możliwości zapisu");
            }
        }
    }

    private void saveToFile(String date, String email,String description)  {
        try{
            FileWriter fileWriter = new FileWriter("powiadomienia",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("Date: %s \n",date);
            printWriter.printf("Email: %s \n",email);
            printWriter.printf("%s \n\n",description);
            printWriter.close();
        }catch(IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Nie udało się wysłać powiadomienia o dołączeniu użytkownika do wykładu");
        }

    }
    private String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
