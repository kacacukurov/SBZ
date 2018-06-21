package rs.uns.ac.ftn.SBZprojekat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.service.SimptomiService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.SimptomDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/simptomi")
public class SimptomiController {

    @Autowired
    private SimptomiService simptomiService;

    @RequestMapping(
            value = "/kreiraj",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dodajSimptom(@RequestBody SimptomDTO simptomDTO) {

        Simptomi postoji = this.simptomiService.findByNaziv(simptomDTO.getNaziv());
        if(postoji != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Simptomi simptomi = new Simptomi(simptomDTO.getNaziv(), simptomDTO.getVrednost());

        simptomi = this.simptomiService.save(simptomi);
        return new ResponseEntity<>(simptomDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/svi",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSveSimptome() {
        List<Simptomi> simptomi = this.simptomiService.findAll();
        List<SimptomDTO> simptomDTOS = new ArrayList<>();
        for(Simptomi s: simptomi){
            simptomDTOS.add(new SimptomDTO(s.getNaziv(), s.getVrednost()));
        }

        return new ResponseEntity<>(simptomDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity obrisiSimptom(@RequestParam String naziv){

        Simptomi simptom = this.simptomiService.findByNaziv(naziv);
        if(simptom == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.simptomiService.remove(simptom);

        return new ResponseEntity(HttpStatus.OK);
    }

}
