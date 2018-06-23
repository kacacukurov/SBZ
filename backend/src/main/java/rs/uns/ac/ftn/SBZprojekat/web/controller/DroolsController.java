package rs.uns.ac.ftn.SBZprojekat.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.SBZprojekat.model.*;
import rs.uns.ac.ftn.SBZprojekat.security.JWTUtils;
import rs.uns.ac.ftn.SBZprojekat.service.*;
import rs.uns.ac.ftn.SBZprojekat.web.dto.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private AccountService accountService;

    @Autowired
    private JWTUtils jwtUtils;

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
            dijagnozaDTO.setId(dijagnoza.getId());
        }
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dijagnozaDTO.setDatum(df.format(dijagnoza.getDatum_uspostavljanja_dijagnoze()));

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

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dijagnozaDTO.setDatum(df.format(dijagnoza.getDatum_uspostavljanja_dijagnoze()));
        dijagnozaDTO.setId(dijagnoza.getId());
        return new ResponseEntity<>(dijagnozaDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/dobaviSimptome",
            method = RequestMethod.GET,
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
    public ResponseEntity prepisiLekove(@RequestBody PrepisaniLekoviDTO prepisaniLekoviDTO,
                                        @RequestHeader("Authentication-Token") String token) {

        Account account = this.accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

        Dijagnoza dijagnoza = this.dijagnozaService.findOne(prepisaniLekoviDTO.getId_dijagnoze());
        dijagnoza.setAccount(account);
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
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity proveriAlergije(@RequestParam String id_dijagnoze) {

        Dijagnoza dijagnoza = this.dijagnozaService.findOne(new Long(id_dijagnoze));
        if(dijagnoza == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        SpisakAlergijaDTO alergije = this.droolsService.proveriAlergije(dijagnoza);

        return new ResponseEntity<>(alergije, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/pacijentiHronicno",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity pacijentiHronicno() {

        List<Pacijent> pacijenti = new ArrayList<>();

        SpisakPacijenataDTO spisakPacijenataDTO = this.droolsService.pacijentiHronicno();

        return new ResponseEntity<>(spisakPacijenataDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/pacijetniZavisnici",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity pacijetniZavisnici() {

        List<Pacijent> pacijenti = new ArrayList<>();

        SpisakPacijenataDTO spisakPacijenataDTO = this.droolsService.pacijetniZavisnici();

        return new ResponseEntity<>(spisakPacijenataDTO, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/pacijentiSlabImunitet",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity pacijentiSlabImunitet() {

        List<Pacijent> pacijenti = new ArrayList<>();

        SpisakPacijenataDTO spisakPacijenataDTO = this.droolsService.pacijentiSlabImunitet();

        return new ResponseEntity<>(spisakPacijenataDTO, HttpStatus.OK);

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
