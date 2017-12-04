package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.domain.Artikkeli;
import com.example.harjoitustyo.repository.ArtikkeliRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@SpringBootApplication
public class NewsController {

    @Autowired
    private ArtikkeliRepository artikkelit;

    @RequestMapping("/")
    public String etusivu(Model model) {
        //Pageable pageable = new PageRequest(0, 5, Sort.Direction.DESC, "paivays");
        model.addAttribute("artikkelit", artikkelit.findAll());

        return "index";
    }

}
