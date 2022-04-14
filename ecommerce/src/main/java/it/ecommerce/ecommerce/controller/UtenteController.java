package it.ecommerce.ecommerce.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.BindingException;
import it.ecommerce.ecommerce.exception.DuplicateException;
import it.ecommerce.ecommerce.exception.ErrorResponse;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.service.ArticoliService;
import it.ecommerce.ecommerce.service.OrdineService;
import it.ecommerce.ecommerce.service.OrdineServiceImp;
import it.ecommerce.ecommerce.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

  @Autowired
  UtenteService utenteService;

  @Autowired
    OrdineService ordineService;

  @Autowired
  private ResourceBundleMessageSource errMessage;


    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }


    @GetMapping(value = "/find/{email}", produces = "application/json")
    public ResponseEntity<Utente> getUtente(@PathVariable("email") String email) throws it.ecommerce.ecommerce.exception.NotFoundException {


        Utente utente = utenteService.findByEmailLike(email);


        if (utente == null){
            throw new NotFoundException("Utene non presente in anagrafica");
        }
        return new ResponseEntity<Utente>(utente,HttpStatus.OK);
    }

    @GetMapping(value="/listaUtenti", produces = "application/json")
    public ResponseEntity<Iterable<Utente>> getAllUtenti (){

        return new ResponseEntity<>(utenteService.getUtenti(),HttpStatus.OK);
    }


    @PostMapping(value = "/inserisci",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
    public ResponseEntity<Utente> createUtente (@Valid @RequestBody Utente utente, BindingResult bindingResult) throws BindingException, DuplicateException {

        if (bindingResult.hasErrors()){
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }
        else {
            Utente checkUtente = utenteService.findByEmailLike(utente.getEmail());
            if (checkUtente != null){

                String MsgErr = String.format("L'utente con email %s è gia presente in anagrafica! "
                        + "Impossibile utilizzare il metodo POST", utente.getEmail());
                throw new DuplicateException(MsgErr);
            }
        }
        String psw = utente.getPassword();
        String pswEncoding = bCryptPasswordEncoder().encode(psw);
        utente.setPassword(pswEncoding);
        utenteService.insUtente(utente);
        return new ResponseEntity<Utente> (new HttpHeaders(),HttpStatus.CREATED);
    }//createUtente

  /*  @GetMapping(value = "/listaOrdinii/{utente}", produces = "application/json")
    public List<Ordine> listaOrdiniUtente (@PathVariable("utente") String utente)
    throws it.ecommerce.ecommerce.exception.NotFoundException {

        try {
            return ordineService.findOrdineByUtente(utente);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!", e);
        }

    }*/

    @GetMapping(value = "/listaOrdini/{utente}", produces = "application/json")
    public ResponseEntity<List<Ordine>> listaOrdiniUtente (@PathVariable("utente") String utente)
            throws it.ecommerce.ecommerce.exception.NotFoundException {

       // List<Object> lista = ordineService.findOrdineByUtentee(utente);
        List<Ordine> lista = utenteService.findOrdineByUtente(utente);
        if (lista.isEmpty()) {
            String MsgErr = String.format("Non è stato trovato nessun ordine con utente %s! "
                    , utente);
            throw new NotFoundException(MsgErr);


        }
        return new ResponseEntity<>(lista,HttpStatus.OK);

    }




    @GetMapping(value = "/listaArticoli/{utente}", produces = "application/json")
    public List<Articoli> findByListaArticoliByutente (@PathVariable("utente") String utente)
            throws it.ecommerce.ecommerce.exception.NotFoundException{

        try {
            return ordineService.findByListaArticoliByutente(utente);

        }
        catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"utente non trovato",e);
        }


    }

    @RequestMapping(value = "/elimina/{email}", method = RequestMethod.DELETE, produces = "application/json" )
    public ResponseEntity<?> deleteArt(@PathVariable("email") String email)
            throws NotFoundException
    {
        Utente utente = utenteService.findByEmailLike(email);
        if (utente == null)
        {
            String MsgErr = String.format("Utente %s non presente in anagrafica! ",email);

            throw new NotFoundException(MsgErr);
        }

        utenteService.delUtente(utente);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione utente " + email + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }






}//UtenteController
