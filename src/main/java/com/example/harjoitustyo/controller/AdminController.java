package com.example.harjoitustyo.controller;

import com.example.harjoitustyo.domain.Artikkeli;
import com.example.harjoitustyo.domain.Kategoria;
import com.example.harjoitustyo.repository.ArtikkeliRepository;
import com.example.harjoitustyo.repository.KategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@SpringBootApplication
public class AdminController {

    @Autowired
    private ArtikkeliRepository artikkelit;

    @Autowired
    private KategoriaRepository kategoriatTable;

    @RequestMapping("/admin")
    public String adminPanel(Model model) {
        return "adminpanel";
    }

    @GetMapping("/muokkaa/{id}")
    public String muokkaa(Model model, @PathVariable long id) {

        Artikkeli a = artikkelit.findOne(id);
        model.addAttribute("a", a);
        model.addAttribute("kategoriat", String.join(",", a.getKategoriat().stream().map(x -> x.getNimi()).collect(Collectors.toList())));

        return "muokkaus";
    }

    @DeleteMapping("/poista/{id}")
    @Transactional
    public String poista(@PathVariable long id) {

        Artikkeli a = artikkelit.findOne(id);

        for(Kategoria k :a.getKategoriat()) {
            List<Artikkeli> as = k.getArtikkelit();
            as.remove(a);
            k.setArtikkelit(as);
            kategoriatTable.save(k);
            if(as.isEmpty()) {
                kategoriatTable.delete(k);
            }
        }

        artikkelit.delete(id);


        return "redirect:/";
    }

    @PostMapping("/muokkaa/{id}")
    public String muokkaa(@PathVariable long id, @RequestParam String otsikko, @RequestParam String ingressi,
                        @RequestParam("file") MultipartFile file, @RequestParam String teksti,
                        @RequestParam String kirjoittajat, @RequestParam String kategoriat) throws IOException {
        Artikkeli artikkeli = artikkelit.findOne(id);
        artikkeli.setOtsikko(otsikko);
        artikkeli.setIngressi(ingressi);
        if(!file.isEmpty()) {
            artikkeli.setKuva(file.getBytes());
        }
        artikkeli.setTeksti(teksti);
        artikkeli.setKirjoittajat(kirjoittajat);

        List<String> kategoriatList = Arrays.asList(kategoriat.replaceAll("\\s+", "").split(","));
        List<Kategoria> kaikki = kategoriatTable.findAll();
        List<Kategoria> artikkelinKategoriat = new ArrayList<>();
        for(String k : kategoriatList) {
            if(kaikki.stream().map(x->x.getNimi()).collect(Collectors.toList()).contains(k)) {
                Kategoria vanha = kaikki.stream().filter(x->x.getNimi().equals(k)).findFirst().get();
                List<Artikkeli> vanhat = vanha.getArtikkelit();
                vanhat.add(artikkeli);
                vanha.setArtikkelit(vanhat);

                artikkelinKategoriat.add(vanha);
            } else {
                Kategoria uusi = new Kategoria();
                uusi.setNimi(k);
                List<Artikkeli> tyhja = new ArrayList<>();
                tyhja.add(artikkeli);
                uusi.setArtikkelit(tyhja);
                kategoriatTable.save(uusi);

                artikkelinKategoriat.add(uusi);
            }
        }

        artikkeli.setKategoriat(artikkelinKategoriat);

        artikkelit.save(artikkeli);

        return "redirect:/";

    }

    @PostMapping("/admin")
    public String lisaa(@RequestParam String otsikko, @RequestParam String ingressi,
                      @RequestParam("file") MultipartFile file, @RequestParam String teksti,
                      @RequestParam String kirjoittajat, @RequestParam String kategoriat) throws IOException {
        Artikkeli artikkeli = new Artikkeli();
        artikkeli.setOtsikko(otsikko);
        artikkeli.setIngressi(ingressi);
        if(!file.isEmpty()) {
            artikkeli.setKuva(file.getBytes());
        }
        artikkeli.setTeksti(teksti);
        artikkeli.setKirjoittajat(kirjoittajat);

        List<String> kategoriatList = Arrays.asList(kategoriat.replaceAll("\\s+", "").split(","));
        List<Kategoria> kaikki = kategoriatTable.findAll();
        List<Kategoria> artikkelinKategoriat = new ArrayList<>();
        for(String k : kategoriatList) {
            if(kaikki.stream().map(x->x.getNimi()).collect(Collectors.toList()).contains(k)) {
                Kategoria vanha = kaikki.stream().filter(x->x.getNimi().equals(k)).findFirst().get();
                List<Artikkeli> vanhat = vanha.getArtikkelit();
                vanhat.add(artikkeli);
                vanha.setArtikkelit(vanhat);

                artikkelinKategoriat.add(vanha);
            } else {
                Kategoria uusi = new Kategoria();
                uusi.setNimi(k);
                List<Artikkeli> tyhja = new ArrayList<>();
                tyhja.add(artikkeli);
                uusi.setArtikkelit(tyhja);
                kategoriatTable.save(uusi);

                artikkelinKategoriat.add(uusi);
            }
        }

        artikkeli.setKategoriat(artikkelinKategoriat);

        artikkelit.save(artikkeli);

        return "redirect:/";

    }

}
