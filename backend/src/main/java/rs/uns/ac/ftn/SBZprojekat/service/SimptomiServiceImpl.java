package rs.uns.ac.ftn.SBZprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.SBZprojekat.repository.SimptomiRepository;

@Service
public class SimptomiServiceImpl implements SimptomiService{

    @Autowired
    private SimptomiRepository simptomiRepository;
}
