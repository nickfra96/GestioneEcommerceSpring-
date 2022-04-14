package it.ecommerce.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.ecommerce.ecommerce.entity.*;
import it.ecommerce.ecommerce.exception.BindingException;
import it.ecommerce.ecommerce.exception.DuplicateException;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.service.ArticoliService;
import it.ecommerce.ecommerce.service.CategoriaService;
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
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articoli")
public class ArticoliController {

    @Autowired
    ArticoliService articoliService;

    @Autowired
    DettaglioArticoloService dettaglioArticoloService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    private ResourceBundleMessageSource errMessage;


    //inserendo la psw e la email
    @GetMapping(value ="/test")
    public ResponseEntity<?> testConnex(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode= mapper.createObjectNode();
        responseNode.put("code",HttpStatus.OK.toString());
        responseNode.put("message","Test Connessione ok");
        return new ResponseEntity<>(responseNode,new HttpHeaders(),HttpStatus.OK);
    }


    @GetMapping(value = "/cerca/{id}", produces = "application/json")
    public ResponseEntity<Articoli> getArticoloId (@PathVariable("id") long id) throws
    it.ecommerce.ecommerce.exception.NotFoundException {

        Articoli a = articoliService.selById(id);
        if (a == null){
            throw new NotFoundException("L'articolo non è presente");
        }
        return new ResponseEntity<Articoli>(a, HttpStatus.OK);

    }

    @PostMapping(value = "/inserisci",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addArticolo (@RequestBody @Valid Articoli articoli, BindingResult
                                                 bindingResult) throws BindingException, DuplicateException

    {
        if (bindingResult.hasErrors()){
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }
        else {
            Articoli checkArticolo = articoliService.selById(articoli.getId());
            if (checkArticolo != null){
                String MsgErr = String.format("L'articolo con codice %s è gia presente nel magazzino! "
                        + "Impossibile utilizzare il metodo POST", articoli.getId());
                throw new DuplicateException(MsgErr);
            }
        }

        articoliService.insArticolo(articoli);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Aggiunta articolo " + articoli.getId() + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping(value = "/listaArticoli",produces = "application/json")
    public ResponseEntity<Iterable<Articoli>> listaArticoli () throws NotFoundException{

       return new ResponseEntity<Iterable<Articoli>>(articoliService.selTutti(),HttpStatus.OK);


    }

    @RequestMapping(value = "/modifica", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> updateArticolo(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
            throws BindingException,NotFoundException
    {

        if (bindingResult.hasErrors())
        {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            throw new BindingException(MsgErr);
        }

        Articoli checkArt =  articoliService.selById(articolo.getId());

        if (checkArt == null)
        {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! "
                    + "Impossibile utilizzare il metodo PUT", articolo.getId());

            throw new NotFoundException(MsgErr);
        }

        articoliService.delArticolo(checkArt);
        articoliService.insArticolo(articolo);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Aggiornamento articolo " + articolo.getId() + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json" )
    public ResponseEntity<?> deleteArt(@PathVariable("id") long id)
            throws NotFoundException
    {



        Articoli articolo = articoliService.selById(id);


        if (articolo == null)
        {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! ",id);

            throw new NotFoundException(MsgErr);
        }

        articoliService.delArticolo(articolo);


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Articolo " + id + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }


    // ------------------- Ricerca Per Descrizione ------------------------------------
    @GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
    public ResponseEntity<List<Articoli>> listArtByDesc(@PathVariable("filter") String Filter)
            throws NotFoundException
    {

        List<Articoli> articoli = articoliService.selByDescrizione("%" + Filter + "%");

        if (articoli == null)
        {
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);

            throw new NotFoundException(ErrMsg);

        }

        return new ResponseEntity<List<Articoli>>(articoli, HttpStatus.OK);
    }

    @GetMapping(value ="/cerca/prezzo/{prezzo}",produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliPrezzo (@PathVariable("prezzo") long prezzo) throws NotFoundException {

        List<Articoli> articoli = articoliService.findArticoliByPrezzo(prezzo);

        if (articoli.isEmpty()){
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente prezzo %s", prezzo);
            throw new NotFoundException(ErrMsg);
        }

        return new ResponseEntity<>(articoli, HttpStatus.OK);

    }

    @GetMapping(value ="/cerca/prezzoUnder/{prezzoUnder}", produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliPrezzoUnder (@PathVariable("prezzoUnder") long prezzo)
        throws NotFoundException{

        List<Articoli> articoli = articoliService.findArticoliByPrezzoBefore(prezzo);
        if (articoli.isEmpty()){
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente prezzo minore" +
                    " di %s", prezzo);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<>(articoli,HttpStatus.OK);

    }

    @GetMapping(value ="/cerca/prezzoAfter/{prezzoAfter}", produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliPrezzoAfter (@PathVariable("prezzoAfter")
                                                                  long prezzo) throws NotFoundException{


        List<Articoli> articoli = articoliService.findArticoliByPrezzoAfter(prezzo);
        if (articoli.isEmpty()){
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente prezzo maggiore" +
                    " di %s", prezzo);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<>(articoli,HttpStatus.OK);


    }

    @GetMapping(value ="/cerca/prezzoBetween/{prezzo1}/{prezzo2}", produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliPrezzoAfter (@PathVariable("prezzo1") long prezzo1,
                                                                  @PathVariable("prezzo2")  long prezzo2)
    throws NotFoundException{


        List<Articoli> articoli = articoliService.findArticoliByPrezzoBetween(prezzo1,prezzo2);
        if (articoli.isEmpty()){
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente prezzo comrpeso tra" +
                    "  %s e %s", prezzo1,prezzo2);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<>(articoli,HttpStatus.OK);


    }

    @GetMapping(value ="/cerca/categoria", produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliCategoria (@RequestBody Categoria categoria)

            throws NotFoundException {
        List<Articoli> a = articoliService.findByCategoria(categoria);
        if (a.isEmpty()) {
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente categoria " +
                    " %s", categoria.getCategoria());
            throw new NotFoundException(ErrMsg);
        }

        return new ResponseEntity<>(a, HttpStatus.OK);
    }


}//ArticoliController
