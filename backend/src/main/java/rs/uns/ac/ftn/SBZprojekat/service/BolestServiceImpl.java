package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.BolestRepository;

@Service
public class BolestServiceImpl implements BolestService{

    @Autowired
    private BolestRepository bolestRepository;
}
