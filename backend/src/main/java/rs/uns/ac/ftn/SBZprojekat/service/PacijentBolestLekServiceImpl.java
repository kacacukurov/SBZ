package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.PacijentBolestLekRepository;

@Service
public class PacijentBolestLekServiceImpl implements PacijentBolestLekService{

    @Autowired
    private PacijentBolestLekRepository pacijentBolestLekRepository;
}
