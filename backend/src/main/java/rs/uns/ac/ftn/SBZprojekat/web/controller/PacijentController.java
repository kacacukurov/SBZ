package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.*;
import rs.uns.ac.ftn.SBZprojekat.repository.LekRepository;
import rs.uns.ac.ftn.SBZprojekat.repository.SastojakRepository;
import rs.uns.ac.ftn.SBZprojekat.service.DroolsService;
import rs.uns.ac.ftn.SBZprojekat.service.PacijentService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.DijagnozaDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.NoviPacijentDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.PacijentDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.SimptomDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private SastojakRepository sastojakRepository;

    @Autowired
    private DroolsService droolsService;

    @RequestMapping(
            value = "/kreiraj",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity kreirajPacijenta(@RequestBody NoviPacijentDTO noviPacijentDTO) {

        Pacijent postoji = this.pacijentService.findByJmbg(noviPacijentDTO.getJmbg());
        if(postoji != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Pacijent pacijent = new Pacijent();
        pacijent.setIme(noviPacijentDTO.getIme());
        pacijent.setJmbg(noviPacijentDTO.getJmbg());
        pacijent.setBroj_zdravstvene_knjizice(noviPacijentDTO.getBroj_knjizice());
        pacijent.setPrezime(noviPacijentDTO.getPrezime());

        pacijent = this.pacijentService.save(pacijent);

        return new ResponseEntity<>(noviPacijentDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSvePacijente() {
        List<Pacijent> pacijenti = this.pacijentService.findAll();
        List<NoviPacijentDTO> noviPacijentDTOS = new ArrayList<>();

        for(Pacijent pacijent: pacijenti){
            noviPacijentDTOS.add(new NoviPacijentDTO(pacijent.getIme(), pacijent.getPrezime(), pacijent.getJmbg(),
                    pacijent.getBroj_zdravstvene_knjizice()));
        }

        return new ResponseEntity<>(noviPacijentDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity obrisiPacijenta(@RequestParam String jmbg){

        Pacijent pacijent = this.pacijentService.findByJmbg(jmbg);
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.pacijentService.remove(pacijent);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/izmeni",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity izmeniPacijenta(@RequestBody NoviPacijentDTO noviPacijentDTO) {

        Pacijent pacijent = this.pacijentService.findByJmbg(noviPacijentDTO.getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        pacijent.setIme(noviPacijentDTO.getIme());
        pacijent.setBroj_zdravstvene_knjizice(noviPacijentDTO.getBroj_knjizice());
        pacijent.setPrezime(noviPacijentDTO.getPrezime());

        pacijent = this.pacijentService.save(pacijent);

        return new ResponseEntity<>(noviPacijentDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/jmbg",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviPacijentaPoJmbg(@RequestParam String jmbg) {
        Pacijent pacijent = this.pacijentService.findByJmbg(jmbg);

        PacijentDTO pacijentDTO = new PacijentDTO();
        NoviPacijentDTO noviPacijentDTO = new NoviPacijentDTO(pacijent.getIme(), pacijent.getPrezime(), pacijent.getJmbg(),
                pacijent.getBroj_zdravstvene_knjizice());
        pacijentDTO.setPacijentDTO(noviPacijentDTO);

        for(Lek lek: pacijent.getLekovi_alergija())
            pacijentDTO.getLekovi_alergija().add(lek.getNaziv());

        for(Sastojak sastojak: pacijent.getSastojci_alergija())
            pacijentDTO.getSastojci_alergija().add(sastojak.getNaziv());

        for(Dijagnoza dijagnoza: pacijent.getDijagnoze()){
            DijagnozaDTO dijagnozaDTO = new DijagnozaDTO(dijagnoza.getBolest().getNazivBolesti(), dijagnoza.getPacijent().getJmbg());
            for(Simptomi simptom: dijagnoza.getSimptomi())
                dijagnozaDTO.getSimptomi().add(new SimptomDTO(simptom.getNaziv(), simptom.getVrednost()));
        }

        return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/alergije",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dodajPacijentuAlergije(@RequestBody PacijentDTO pacijentDTO) {

        Pacijent pacijent = this.pacijentService.findByJmbg(pacijentDTO.getPacijentDTO().getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        for(String naziv_leka: pacijentDTO.getLekovi_alergija()){
            Lek lek = this.lekRepository.findByNaziv(naziv_leka);
            if(!pacijent.getLekovi_alergija().contains(lek))
                pacijent.getLekovi_alergija().add(lek);
        }

        for(String naziv_sastojka: pacijentDTO.getSastojci_alergija()){
            Sastojak sastojak = this.sastojakRepository.findByNaziv(naziv_sastojka);
            if(!pacijent.getSastojci_alergija().contains(sastojak))
                pacijent.getSastojci_alergija().add(sastojak);
        }

        pacijent = this.pacijentService.save(pacijent);

        return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
    }
}
