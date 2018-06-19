package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.PacijentBolestRepository;

@Service
public class PacijentBolestServiceImpl implements PacijentBolestService{

    @Autowired
    private PacijentBolestRepository pacijentBolestRepository;
}
