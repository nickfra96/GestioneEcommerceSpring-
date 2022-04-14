package it.ecommerce.ecommerce.controller;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.DettaglioOrdine;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.BindingException;
import it.ecommerce.ecommerce.exception.DuplicateException;
import it.ecommerce.ecommerce.exception.ErrorResponse;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.service.ArticoliService;
import it.ecommerce.ecommerce.service.DettaglioOrdineService;
import it.ecommerce.ecommerce.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    OrdineService ordineService;
    @Autowired
    ArticoliService articoliService;
    @Autowired
    private ResourceBundleMessageSource errMessage;
    @Autowired
    DettaglioOrdineService dettaglioOrdineService;

    /*
    public ResponseEntity<Ordine> piazzaOrdine (@RequestBody@Valid Ordine ordine, BindingResult bindingResult)
        throws DuplicateException, BindingException,NotFoundException,Exception{

        if (bindingResult.hasErrors()){
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }
        else
        {
            Ordine o = ordineService.findOrdineById_ordine(ordine.getId_ordine());
            Ordine order = new Ordine();
            if (o == null){
                String MsgErr = String.format("Ordine gia presente", ordine.getId_ordine());
                throw new DuplicateException(MsgErr);
            }
        }


        for (Articoli i : ordine.getListaArticoli()){

            Articoli check = articoliService.selById(i.getId());
            if (check == null){
                throw new  NotFoundException();
            }

            else
            {
                if (ordine.getQuantita() > check.getQuantita()){
                    throw new Exception();

                }

                check.setQuantita(check.getQuantita()-ordine.getQuantita());
                articoliService.update(check.getId(),check.getQuantita());

            }


        }

        ordineService.inserisciOrdine(ordine);
        return new ResponseEntity<>(ordine, HttpStatus.CREATED);

    }*/

    @GetMapping(value = "/listaOrdini",produces = "application/json")
    public ResponseEntity<List<Ordine>> getListaOrdini () throws NotFoundException {

        List<Ordine> listaOrdini = ordineService.findAll();
        if (listaOrdini.isEmpty()){
            throw new NotFoundException("Non sono presenti ordini");
        }
        return new ResponseEntity<List<Ordine>>(listaOrdini,HttpStatus.OK);
    }



}//OrdineController
