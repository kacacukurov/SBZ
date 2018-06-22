package rs.uns.ac.ftn.SBZprojekat.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.service.DijagnozaService;
import rs.uns.ac.ftn.SBZprojekat.service.DroolsService;
import rs.uns.ac.ftn.SBZprojekat.service.PacijentService;
import rs.uns.ac.ftn.SBZprojekat.service.SimptomiService;
import rs.uns.ac.ftn.SBZprojekat.web.dto.DijagnozaDTO;
import rs.uns.ac.ftn.SBZprojekat.web.dto.SimptomDTO;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/drools")
public class DroolsController {

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private SimptomiService simptomiService;

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private DijagnozaService dijagnozaService;

    @RequestMapping(
            value = "/najverovatnijaBolest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity kreirajPacijenta(@RequestBody DijagnozaDTO dijagnozaDTO) {

        Dijagnoza dijagnoza = new Dijagnoza();

        dijagnoza.setDatum_uspostavljanja_dijagnoze(new Date());

        Pacijent pacijent = this.pacijentService.findByJmbg(dijagnozaDTO.getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        dijagnoza.setPacijent(pacijent);
        System.out.println(pacijent.getDijagnoze().size());

        for(SimptomDTO simptomDTO : dijagnozaDTO.getSimptomi()){
            Simptomi s = this.simptomiService.findByNazivAndVrednost(simptomDTO.getNaziv(), simptomDTO.getVrednost());

            if(s == null){
                s = new Simptomi(simptomDTO.getNaziv(), simptomDTO.getVrednost());
                this.simptomiService.save(s);
            }
            dijagnoza.getSimptomi().add(s);
        }

        dijagnoza = this.dijagnozaService.save(dijagnoza);

        boolean zdrav = false;
        dijagnoza = this.droolsService.dobaviNajverovatnijuBolest(dijagnoza);
        if(dijagnoza.getBolest() == null)
            zdrav = true;
        else {
            dijagnozaDTO.setNaziv_bolesti(dijagnoza.getBolest().getNazivBolesti());
        }

        if(zdrav){
            System.out.println("Nema nadjene bolesti!");
            this.dijagnozaService.remove(dijagnoza.getId());
        }
        return new ResponseEntity<>(dijagnozaDTO, HttpStatus.OK);

    }
}
