package it.ecommerce.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class DettaglioOrdineServiceImp implements DettaglioOrdineService{
}
