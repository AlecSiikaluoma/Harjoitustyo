package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.domain.Artikkeli;
import com.example.harjoitustyo.repository.ArtikkeliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@SpringBootApplication
public class AdminController {

    @Autowired
    private ArtikkeliRepository artikkelit;

    @RequestMapping("/admin")
    public String adminPanel() {
      return "adminpanel";
    }

    @PostMapping("(/admin")
    public String lisaa(@RequestParam String otsikko, @RequestParam String ingressi,
                      @RequestParam("kuva") MultipartFile kuva, @RequestParam String teksti,
                      @RequestParam String kirjoittajat, @RequestParam String kategoriat) throws IOException {

        Artikkeli artikkeli = new Artikkeli();
        artikkeli.setOtiskko(otsikko);
        artikkeli.setIngressi(ingressi);
        artikkeli.setKuva(kuva.getBytes());
        artikkeli.setTeksti(teksti);
        artikkeli.setKirjoittajat(kirjoittajat);
        artikkeli.setKategoriat(kategoriat);

        artikkelit.save(artikkeli);

        return "redirect:/";

    }

}
