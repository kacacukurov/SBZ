package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.Lek;
import rs.uns.ac.ftn.SBZprojekat.model.Sastojak;
import rs.uns.ac.ftn.SBZprojekat.service.LekService;
import rs.uns.ac.ftn.SBZprojekat.service.SastojakService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.NoviLekDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/lek")
public class LekController {

    @Autowired
    private LekService lekService;

    @Autowired
    private SastojakService sastojakService;

    @RequestMapping(
            value = "/kreiraj",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dodajLek(@RequestBody NoviLekDTO lekDTO) {

        Lek postoji = this.lekService.findByNaziv(lekDTO.getNaziv());
        if(postoji != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        List<Sastojak> sastojciLeka = new ArrayList<>();

        for (String sas : lekDTO.getSastojci()){
            Sastojak sastojak = this.sastojakService.findByNaziv(sas);
            if(sastojak == null)
                sastojak = this.sastojakService.save(new Sastojak(sas));
            sastojciLeka.add(sastojak);
        }

        Lek lek = new Lek(lekDTO.getTipLeka(), lekDTO.getNaziv(), sastojciLeka);

        lek = this.lekService.save(lek);
        for(Sastojak sastojak: sastojciLeka){
            this.sastojakService.save(sastojak);
        }
        return new ResponseEntity<>(lekDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSveLekove() {
        List<Lek> lekovi = this.lekService.findAll();
        List<NoviLekDTO> lekDTOS = new ArrayList<>();
        for(Lek l: lekovi){
            List<String> sastojci = new ArrayList<>();
            for(Sastojak sastojak: l.getSastojci())
                sastojci.add(sastojak.getNaziv());
            lekDTOS.add(new NoviLekDTO(l.getNaziv(), sastojci, l.getTipLeka()));
        }

        return new ResponseEntity<>(lekDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/sviSastojci",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSveSastojke() {
        List<Sastojak> sastojaci = this.sastojakService.findAll();
        List<String> nazivi_sastojaka = new ArrayList<>();
        for(Sastojak sastojak: sastojaci){
            nazivi_sastojaka.add(sastojak.getNaziv());
        }
        return new ResponseEntity<>(nazivi_sastojaka, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity obrisiLek(@RequestParam String naziv){

        Lek lek = this.lekService.findByNaziv(naziv);
        if(lek == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.lekService.remove(lek);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/izmeni",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity izmeniLek(@RequestBody NoviLekDTO noviLekDTO) {

        Lek lek = this.lekService.findByNaziv(noviLekDTO.getNaziv());
        lek.setTipLeka(noviLekDTO.getTipLeka());

        List<Sastojak> zaBrisanje = new ArrayList<>();

        for (Sastojak sastojak: lek.getSastojci()){
            boolean postoji = false;
            for(String naziv_sastojka: noviLekDTO.getSastojci()){
                if(sastojak.getNaziv().equals(naziv_sastojka))
                    postoji = true;
            }
            if(!postoji) {
                zaBrisanje.add(this.sastojakService.findByNaziv(sastojak.getNaziv()));
            }
        }

        for(Sastojak sastojak: zaBrisanje){
            this.sastojakService.remove(sastojak);
            lek.getSastojci().remove(sastojak);
        }

        for(String naziv: noviLekDTO.getSastojci()){
            Sastojak sastojak = this.sastojakService.findByNaziv(naziv);
            if(sastojak == null){
                Sastojak novi = new Sastojak(naziv);
                novi = this.sastojakService.save(novi);
                lek.getSastojci().add(novi);
            }
            if(!lek.getSastojci().contains(sastojak))
                lek.getSastojci().add(sastojak);
        }

        this.lekService.save(lek);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
