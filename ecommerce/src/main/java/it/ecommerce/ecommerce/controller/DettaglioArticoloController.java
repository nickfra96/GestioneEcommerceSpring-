package it.ecommerce.ecommerce.controller;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.entity.DettaglioArticolo;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.BindingException;
import it.ecommerce.ecommerce.exception.DuplicateException;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.service.ArticoliService;
import it.ecommerce.ecommerce.service.DettaglioArticoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dettaglioArticolo")
public class DettaglioArticoloController {

    @Autowired
    DettaglioArticoloService dettaglioArticoloService;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    @Autowired
    ArticoliService articoliService;


    @PostMapping(value = "/aggiungi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DettaglioArticolo> aggiungiDettaglio (@RequestBody  @Valid
                                                                DettaglioArticolo dettaglioArticolo,
                                                                BindingResult bindingResult)
        throws  DuplicateException,BindingException{


       if (bindingResult.hasErrors()){
           String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
           throw new BindingException(MsgErr);

       }
            dettaglioArticoloService.inserisciDettaglioArticolo(dettaglioArticolo);
            return new ResponseEntity<DettaglioArticolo> (new HttpHeaders(),HttpStatus.CREATED);

    }







}//DettaglioArticoloController
