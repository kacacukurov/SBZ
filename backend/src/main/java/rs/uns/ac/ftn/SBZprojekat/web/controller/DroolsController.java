package rs.uns.ac.ftn.SBZprojekat.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.Bolest;
import rs.uns.ac.ftn.SBZprojekat.model.Dijagnoza;
import rs.uns.ac.ftn.SBZprojekat.model.Pacijent;
import rs.uns.ac.ftn.SBZprojekat.model.Simptomi;
import rs.uns.ac.ftn.SBZprojekat.service.*;
import rs.uns.ac.ftn.SBZprojekat.web.dto.*;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private BolestService bolestService;

    @Autowired
    private LekService lekService;

    @RequestMapping(
            value = "/najverovatnijaBolest",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity najverovatnijaBolest(@RequestBody DijagnozaDTO dijagnozaDTO) {

        Dijagnoza dijagnoza = new Dijagnoza();

        dijagnoza.setDatum_uspostavljanja_dijagnoze(new Date());

        Pacijent pacijent = this.pacijentService.findByJmbg(dijagnozaDTO.getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        dijagnoza.setPacijent(pacijent);

        dijagnoza = dodajSimptomeDijagnozi(dijagnozaDTO.getSimptomi(), dijagnoza);


        boolean zdrav = false;
        dijagnoza = this.droolsService.dobaviNajverovatnijuBolest(dijagnoza);
        if(dijagnoza.getBolest() == null)
            zdrav = true;
        else {
            dijagnozaDTO.setNaziv_bolesti(dijagnoza.getBolest().getNazivBolesti());
        }

        if(zdrav){
            System.out.println("Nema nadjene bolesti!");
        }else{
            dijagnoza = this.dijagnozaService.save(dijagnoza);
        }
        return new ResponseEntity<>(dijagnozaDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/licnoDijagnoza",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity licnoDijagnoza(@RequestBody DijagnozaDTO dijagnozaDTO) {

        Dijagnoza dijagnoza = new Dijagnoza();

        dijagnoza.setDatum_uspostavljanja_dijagnoze(new Date());

        Pacijent pacijent = this.pacijentService.findByJmbg(dijagnozaDTO.getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        dijagnoza.setPacijent(pacijent);

        Bolest bolest = this.bolestService.findByNaziv(dijagnozaDTO.getNaziv_bolesti());

        if(bolest == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        dijagnoza.setBolest(bolest);

        dijagnoza = dodajSimptomeDijagnozi(dijagnozaDTO.getSimptomi(), dijagnoza);

        dijagnoza = this.dijagnozaService.save(dijagnoza);

        return new ResponseEntity<>(dijagnozaDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/dobaviSimptome",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity dobaviSimptome(@RequestParam String naziv_bolesti) {

        NovaBolestDTO novaBolestDTO = this.droolsService.dobaviSimptome(naziv_bolesti);

        if(novaBolestDTO.getOpsti().size() == 0 && novaBolestDTO.getSpecificni().size() == 0)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(novaBolestDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/izlistajBolesti",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity izlistajBolesti(@RequestBody DijagnozaDTO dijagnozaDTO) {

        Dijagnoza dijagnoza = new Dijagnoza();
        dijagnoza.setDatum_uspostavljanja_dijagnoze(new Date());

        Pacijent pacijent = this.pacijentService.findByJmbg(dijagnozaDTO.getJmbg());
        if(pacijent == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        dijagnoza.setPacijent(pacijent);

        dijagnoza = dodajSimptomeDijagnozi(dijagnozaDTO.getSimptomi(), dijagnoza);

        ListaBolestiDTO listaBolestiDTO = this.droolsService.dobaviMoguceBolesti(dijagnoza);

        return new ResponseEntity<>(listaBolestiDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/prepisiLekove",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity prepisiLekove(@RequestBody PrepisaniLekoviDTO prepisaniLekoviDTO) {

        Dijagnoza dijagnoza = this.dijagnozaService.findOne(prepisaniLekoviDTO.getId_dijagnoze());
        if(dijagnoza == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        for(NoviLekDTO lek: prepisaniLekoviDTO.getLekovi()){
            dijagnoza.getLekovi_terapija().add(this.lekService.findByNaziv(lek.getNaziv()));
        }

        dijagnoza = this.dijagnozaService.save(dijagnoza);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(
            value = "/proveriAlergije",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity proveriAlergije(@RequestParam String id_dijagnoze) {

        Dijagnoza dijagnoza = this.dijagnozaService.findOne(new Long(id_dijagnoze));
        if(dijagnoza == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        SpisakAlergijaDTO alergije = this.droolsService.proveriAlergije(dijagnoza);

        return new ResponseEntity<>(alergije, HttpStatus.OK);

    }

    private Dijagnoza dodajSimptomeDijagnozi(List<SimptomDTO> simptomDTOS, Dijagnoza dijagnoza){
        for(SimptomDTO simptomDTO : simptomDTOS){
            Simptomi s = this.simptomiService.findByNazivAndVrednost(simptomDTO.getNaziv(), simptomDTO.getVrednost());

            if(s == null){
                s = new Simptomi(simptomDTO.getNaziv(), simptomDTO.getVrednost());
                this.simptomiService.save(s);
            }
            dijagnoza.getSimptomi().add(s);
        }
        return dijagnoza;
    }
}
