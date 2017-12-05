package com.example.harjoitustyo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by alecsiikaluoma on 5.12.2017.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kategoria extends AbstractPersistable<Long> {

    private String nimi;

    @ManyToMany(mappedBy = "kategoriat")
    private List<Artikkeli> artikkelit;

}

