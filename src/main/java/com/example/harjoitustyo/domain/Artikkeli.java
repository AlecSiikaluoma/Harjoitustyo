package com.example.harjoitustyo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@Data
public class Artikkeli extends AbstractPersistable<Long> {

    private String otsikko;
    private String ingressi;
    private String teksti;

    @Lob
    //@Basic(fetch = FetchType.LAZY)
    private byte[] kuva;

    private LocalDateTime paivays;
    private String kirjoittajat;

    @ManyToMany
    private List<Kategoria> kategoriat;

    //private String kategoriat;

    public Artikkeli() {
        this.paivays = LocalDateTime.now();
    }


}
