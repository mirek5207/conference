package com.example.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conference")
public class Conference {

    @GetMapping(value = "/plan")
    public String getPlanOfConference(){
        return conferencePlan();
    }
    private String conferencePlan(){
        String plan = "<h2>PLAN KONFERENCJI IT</h2><br>" + System.lineSeparator() +
                "W dniu 1 czerwca 2022 roku odbędzie się konferencja integrująca całą branżę IT.<br>" + System.lineSeparator() +
                "Konferencja IT 2022 (online) odbędą się w formie wirtualnego wydarzenia.<br>" +
                "Konferencja rozpoczyna się o godzinie 10:00 a kończy o godzinie 15:45.<br><br>" +
                "Każda prelekcja trwa 1h 45m (15 minut to przerwa na kawę) :<br>" +
                "- pierwsza prelekcja rozpoczyna się o 10:00 i trwa do 11:45.<br>" +
                "- druga rozpoczyna się o 12:00 i kończy o 13:45<br>" +
                "- trzecia rozpoczyna się o 14:00 i kończy o 15:45<br><br>" +
                "W ramach konferencji obsługiwane są 3 różne ścieżki tematyczne prowadzone równolegle<br>" +
                "Każda prelekcja może pomieścić maksymalnie 5 słuchaczy<br>"  ;
        return plan;
    }
}
