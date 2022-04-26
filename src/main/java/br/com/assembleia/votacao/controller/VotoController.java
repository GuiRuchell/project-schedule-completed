package br.com.assembleia.votacao.controller;


import br.com.assembleia.votacao.dto.ResultadoVotacaoDto;
import br.com.assembleia.votacao.model.Voto;
import br.com.assembleia.votacao.shared.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private VotosService service;

    @GetMapping("/{id}")
    @ResponseBody
    public Voto getVoto(@PathVariable Long id) {
        return service.getVoto(id);
    }

    @PostMapping
    @ResponseBody
    public Voto insertVoto(@RequestBody Voto voto) {
        return service.insertVoto(voto);

    }

    @GetMapping("/resultado/{id}")
    @ResponseBody
    public ResultadoVotacaoDto getResultado(@PathVariable Long id) {
        return service.resultadoVotacao(id);
    }
}
