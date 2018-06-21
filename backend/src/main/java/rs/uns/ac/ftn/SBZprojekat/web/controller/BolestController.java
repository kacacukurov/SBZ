package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.service.BolestService;
import rs.uns.ac.ftn.SBZprojekat.service.SimptomiService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.NovaBolestDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.SimptomDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bolest")
public class BolestController {

    @Autowired
    private BolestService bolestService;

    @Autowired
    private SimptomiService simptomiService;

    @RequestMapping(
            value = "/kreiraj",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dodajBolest(@RequestBody NovaBolestDTO bolestDTO) {

        Bolest postoji = this.bolestService.findByNaziv(bolestDTO.getNaziv());
        if(postoji != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        List<Simptomi> opsti = new ArrayList<>();
        List<Simptomi> specificni = new ArrayList<>();

        for(SimptomDTO simptomDTO: bolestDTO.getOpsti()){
            opsti.add(this.simptomiService.findByNaziv(simptomDTO.getNaziv()));
        }

        for(SimptomDTO simptomDTO: bolestDTO.getSpecificni()){
            specificni.add(this.simptomiService.findByNaziv(simptomDTO.getNaziv()));
        }

        Bolest bolest = new Bolest();
        bolest.setNazivBolesti(bolestDTO.getNaziv());
        bolest.setOpsti_simptomi(opsti);
        bolest.setSpecificni_simptomi(specificni);

        bolest = this.bolestService.save(bolest);
        return new ResponseEntity<>(bolestDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSveBolesti() {
        List<Bolest> bolesti = this.bolestService.findAll();
        List<NovaBolestDTO> bolestDTOS = new ArrayList<>();

        for(Bolest bolest: bolesti){
            List<SimptomDTO> opsti = new ArrayList<>();
            List<SimptomDTO> spec = new ArrayList<>();
            for(Simptomi simptomi: bolest.getOpsti_simptomi())
                opsti.add(new SimptomDTO(simptomi.getNaziv(), simptomi.getVrednost()));
            for(Simptomi simptomi: bolest.getSpecificni_simptomi())
                spec.add(new SimptomDTO(simptomi.getNaziv(), simptomi.getVrednost()));
            bolestDTOS.add(new NovaBolestDTO(bolest.getNazivBolesti(), opsti, spec));
        }

        return new ResponseEntity<>(bolestDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity obrisiBolest(@RequestParam String naziv){

        Bolest bolest = this.bolestService.findByNaziv(naziv);

        if(bolest == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.bolestService.remove(bolest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/izmeni",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity izmeniBolest(@RequestBody NovaBolestDTO novaBolestDTO) {

        Bolest bolest = this.bolestService.findByNaziv(novaBolestDTO.getNaziv());

        List<Simptomi> zaBrisanjeOpsti = new ArrayList<>();
        List<Simptomi> zaBrisanjeSpec = new ArrayList<>();

        for (Simptomi simptomi: bolest.getOpsti_simptomi()){
            boolean postoji = false;
            for(SimptomDTO simptomDTO: novaBolestDTO.getOpsti()){
                if(simptomi.getNaziv().equals(simptomDTO.getNaziv()))
                    postoji = true;
            }
            if(!postoji) {
                zaBrisanjeOpsti.add(this.simptomiService.findByNaziv(simptomi.getNaziv()));
            }
        }

        for (Simptomi simptomi: bolest.getSpecificni_simptomi()){
            boolean postoji = false;
            for(SimptomDTO simptomDTO: novaBolestDTO.getSpecificni()){
                if(simptomi.getNaziv().equals(simptomDTO.getNaziv()))
                    postoji = true;
            }
            if(!postoji) {
                zaBrisanjeSpec.add(this.simptomiService.findByNaziv(simptomi.getNaziv()));
            }
        }

        for(Simptomi simptomi: zaBrisanjeOpsti){
            bolest.getOpsti_simptomi().remove(simptomi);
        }

        for(Simptomi simptomi: zaBrisanjeSpec){
            bolest.getSpecificni_simptomi().remove(simptomi);
        }

        for(SimptomDTO simptomDTO: novaBolestDTO.getOpsti()){
            Simptomi simptomi = this.simptomiService.findByNaziv(simptomDTO.getNaziv());
            if(!bolest.getOpsti_simptomi().contains(simptomi))
                bolest.getOpsti_simptomi().add(simptomi);
        }

        for(SimptomDTO simptomDTO: novaBolestDTO.getSpecificni()){
            Simptomi simptomi = this.simptomiService.findByNaziv(simptomDTO.getNaziv());
            if(!bolest.getSpecificni_simptomi().contains(simptomi))
                bolest.getSpecificni_simptomi().add(simptomi);
        }

        this.bolestService.save(bolest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
