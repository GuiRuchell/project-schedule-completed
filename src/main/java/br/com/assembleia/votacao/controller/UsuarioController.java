package br.com.assembleia.votacao.controller;


import br.com.assembleia.votacao.model.Usuario;
import br.com.assembleia.votacao.shared.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    @ResponseBody
    public Usuario getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping
    @ResponseBody
    public Usuario insertUser(@RequestBody Usuario user) {
        return service.insertUser(user);
    }
}

