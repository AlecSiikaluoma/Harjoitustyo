package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.domain.Artikkeli;
import com.example.harjoitustyo.repository.ArtikkeliRepository;
import com.example.harjoitustyo.repository.KategoriaRepository;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SpringBootApplication
public class NewsController {

    @Autowired
    private ArtikkeliRepository artikkelit;

    @Autowired
    private KategoriaRepository kategoriat;

    public void setLuetuimmatJaKategoriatJaUusimmat(Model model) {
        List<Artikkeli> viikonLuetuimmat = artikkelit.findAll().stream()
                .filter(x->Days.daysBetween(DateTime.parse(x.getPaivays().toString()), DateTime.now()).getDays() <= 7)
                .sorted((x,y)->y.getLukumaarat() - x.getLukumaarat())
                .collect((Collectors.toList()));
        model.addAttribute("viikonLuetuimmat", viikonLuetuimmat);

        Pageable pageable2 = new PageRequest(0, 50, Sort.Direction.DESC, "paivays");
        model.addAttribute("uusimmat", artikkelit.findAll(pageable2));

        model.addAttribute("kategoriat", kategoriat.findAll());
    }

    @RequestMapping("/")
    public String etusivu(Model model) {
        Pageable pageable = new PageRequest(0, 5, Sort.Direction.DESC, "paivays");
        model.addAttribute("artikkelit", artikkelit.findAll(pageable));

        setLuetuimmatJaKategoriatJaUusimmat(model);

        return "index";
    }

    @GetMapping(path = "/kuvat/{id}/content", produces = "image/jpeg")
    @ResponseBody
    public byte[] kuva(@PathVariable Long id) {
        return artikkelit.findOne(id).getKuva();
    }

    @GetMapping("/artikkeli/{id}")
    private String artikkeli(Model model, @PathVariable long id) {
        Artikkeli a = artikkelit.findOne(id);
        if(a != null) {
            a.setLukumaarat(a.getLukumaarat() + 1);
            artikkelit.save(a);
        }
        System.out.println("----");
        System.out.println(a.getLukumaarat());
        System.out.println(artikkelit.findOne(id).getLukumaarat());

        setLuetuimmatJaKategoriatJaUusimmat(model);

        model.addAttribute("artikkeli", a);

        return "uutinen";
    }

    @RequestMapping("/uusimmat")
    public String uusimmat(Model model) {
        Pageable pageable = new PageRequest(0, 50, Sort.Direction.DESC, "paivays");
        model.addAttribute("artikkelit", artikkelit.findAll(pageable));
        setLuetuimmatJaKategoriatJaUusimmat(model);

        return "listaus";
    }

    @RequestMapping("/luetuimmat")
    public String viikonLuetuimmat(Model model) {
        setLuetuimmatJaKategoriatJaUusimmat(model);

        List<Artikkeli> viikonLuetuimmat = artikkelit.findAll().stream()
                .filter(x->Days.daysBetween(DateTime.parse(x.getPaivays().toString()), DateTime.now()).getDays() <= 7)
                .sorted((x,y)->y.getLukumaarat() - x.getLukumaarat())
                .collect((Collectors.toList()));

        model.addAttribute("artikkelit", viikonLuetuimmat);

        return "listaus";
    }

    @RequestMapping("/kategoria/{id}")
    public String kategoria(Model model, @PathVariable long id) {
        kategoriat.findOne(id).getArtikkelit();
        List<Artikkeli> kategorian = kategoriat.findOne(id).getArtikkelit();
        model.addAttribute("artikkelit", kategorian);

        setLuetuimmatJaKategoriatJaUusimmat(model);

        return "listaus";
    }

}
