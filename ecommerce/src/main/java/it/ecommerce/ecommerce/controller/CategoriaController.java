package it.ecommerce.ecommerce.controller;


import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.BindingException;
import it.ecommerce.ecommerce.exception.DuplicateException;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    @PostMapping(value = "/aggiungi", produces = "application/json")
    public ResponseEntity<Categoria> aggiungiCategoria (@RequestBody@Valid Categoria categoria,
                                                        BindingResult bindingResult)
            throws DuplicateException,BindingException{

        if (bindingResult.hasErrors()){
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }
        else {

            Categoria c = categoriaService.findByCategoriaLike(categoria.getCategoria());
            if (c != null){
                String MsgErr = String.format("La categoria %s è gia presente in anagrafica! "
                        + "Impossibile utilizzare il metodo POST", categoria.getCategoria());
                throw new DuplicateException(MsgErr);
            }

        }

        categoriaService.inserisciCategoria(categoria);
        return new ResponseEntity<> (new HttpHeaders(), HttpStatus.CREATED);


    }


    @GetMapping(value = "/listaCategorie", produces = "application/json")
    public ResponseEntity<List<Categoria>> getAllCategorie () throws NotFoundException {

        List<Categoria> cat = categoriaService.findAll();
        if (cat == null){
            throw new NotFoundException("Nessuna cateoria presente");
        }

        return new ResponseEntity<>(cat,HttpStatus.OK);
    }

@GetMapping(value = "/articoli/{categoria}",produces = "application/json")
    public ResponseEntity<List<Articoli>> getArticoliCategoria (@PathVariable("categoria") String categoria)
throws NotFoundException {

    List<Articoli> artCategoria = categoriaService.findByArticoli(categoria);
    if (artCategoria.isEmpty()){
        String MsgErr = String.format("Non è presente nessun articolo con la categoria: %s inserita", categoria);
        throw new  NotFoundException(MsgErr);
    }

    return new ResponseEntity<List<Articoli>>(artCategoria,HttpStatus.OK);

}


}//CategoriaController
