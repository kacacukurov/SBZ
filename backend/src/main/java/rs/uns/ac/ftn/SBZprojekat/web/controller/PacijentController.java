package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.*;
import rs.uns.ac.ftn.SBZprojekat.service.*;
import rs.uns.ac.ftn.SBZprojekat.web.dto.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private LekService lekService;

    @Autowired
    private SastojakService sastojakService;

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private DijagnozaService dijagnozaService;

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

        for(String lek: noviPacijentDTO.getLekovi_alergija()){
            pacijent.getLekovi_alergija().add(this.lekService.findByNaziv(lek));
        }
        for(String sastojak: noviPacijentDTO.getSastojci_alergija()){
            pacijent.getSastojci_alergija().add(this.sastojakService.findByNaziv(sastojak));
        }

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
            NoviPacijentDTO noviPacijentDTO = new NoviPacijentDTO(pacijent.getIme(), pacijent.getPrezime(), pacijent.getJmbg(),
                    pacijent.getBroj_zdravstvene_knjizice());
            for(Lek l : pacijent.getLekovi_alergija())
                noviPacijentDTO.getLekovi_alergija().add(l.getNaziv());
            for(Sastojak s: pacijent.getSastojci_alergija())
                noviPacijentDTO.getSastojci_alergija().add(s.getNaziv());
            noviPacijentDTOS.add(noviPacijentDTO);
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

        // azuriranje sastojaka na koje je pacijent alergican
        List<Sastojak> zaBrisanjeSastojci = new ArrayList<>();

        for (Sastojak sastojak: pacijent.getSastojci_alergija()){
            boolean postoji = false;
            for(String naziv_sastojka: noviPacijentDTO.getSastojci_alergija()){
                if(sastojak.getNaziv().equals(naziv_sastojka))
                    postoji = true;
            }
            if(!postoji) {
                zaBrisanjeSastojci.add(this.sastojakService.findByNaziv(sastojak.getNaziv()));
            }
        }

        for(Sastojak sastojak: zaBrisanjeSastojci){
            pacijent.getSastojci_alergija().remove(sastojak);
        }

        for(String naziv: noviPacijentDTO.getSastojci_alergija()){
            Sastojak sastojak = this.sastojakService.findByNaziv(naziv);
            if(!pacijent.getSastojci_alergija().contains(sastojak))
                pacijent.getSastojci_alergija().add(sastojak);
        }

        // azuriranje lekova na koje je pacijent alergican
        List<Lek> zaBrisanjeLekovi = new ArrayList<>();

        for (Lek lek: pacijent.getLekovi_alergija()){
            boolean postoji = false;
            for(String naziv_leka: noviPacijentDTO.getLekovi_alergija()){
                if(lek.getNaziv().equals(naziv_leka))
                    postoji = true;
            }
            if(!postoji) {
                zaBrisanjeLekovi.add(this.lekService.findByNaziv(lek.getNaziv()));
            }
        }

        for(Lek lek: zaBrisanjeLekovi){
            pacijent.getLekovi_alergija().remove(lek);
        }

        for(String naziv: noviPacijentDTO.getLekovi_alergija()){
            Lek lek = this.lekService.findByNaziv(naziv);
            if(!pacijent.getLekovi_alergija().contains(lek))
                pacijent.getLekovi_alergija().add(lek);
        }
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

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        for(Dijagnoza dijagnoza: pacijent.getDijagnoze()){
            DijagnozaDTO dijagnozaDTO = new DijagnozaDTO(dijagnoza.getBolest().getNazivBolesti(),
                    dijagnoza.getPacijent().getJmbg(), df.format(dijagnoza.getDatum_uspostavljanja_dijagnoze()), dijagnoza.getId());
            for(Simptomi simptom: dijagnoza.getSimptomi())
                dijagnozaDTO.getSimptomi().add(new SimptomDTO(simptom.getNaziv(), simptom.getVrednost()));
            pacijentDTO.getDijagnoze().add(dijagnozaDTO);
        }

        return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/dijagnoza",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviDijagnozuPoId(@RequestParam String id_dijagnoze) {
        Dijagnoza dijagnoza = this.dijagnozaService.findOne(new Long(id_dijagnoze));
        if(dijagnoza == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        DijagnozaPoIdDTO dijagnozaPoIdDTO = new DijagnozaPoIdDTO();
        dijagnozaPoIdDTO.setNaziv_bolesti(dijagnoza.getBolest().getNazivBolesti());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dijagnozaPoIdDTO.setDatum(df.format(dijagnoza.getDatum_uspostavljanja_dijagnoze()));

        for(Lek lek : dijagnoza.getLekovi_terapija())
            dijagnozaPoIdDTO.getLekovi().add(lek.getNaziv());

        for(Simptomi simptomi: dijagnoza.getSimptomi())
            dijagnozaPoIdDTO.getSimptomi().add(simptomi.getNaziv());

        return new ResponseEntity<>(dijagnozaPoIdDTO, HttpStatus.OK);
    }

}
