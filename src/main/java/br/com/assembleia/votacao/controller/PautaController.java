package br.com.assembleia.votacao.controller;


import br.com.assembleia.votacao.model.Pauta;
import br.com.assembleia.votacao.shared.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService service;

    @GetMapping("/{id}")
    public Pauta getPauta(@PathVariable Long id) {
        return service.getPauta(id);
    }

    @PostMapping
    public Pauta insertPauta(@RequestBody Pauta pauta) {
        return service.insertPauta(pauta);
    }

    @PostMapping("/sessao")
    public Pauta insertSessao(@RequestBody Pauta pauta) {
        return service.insertSessao(pauta);
    }
}


