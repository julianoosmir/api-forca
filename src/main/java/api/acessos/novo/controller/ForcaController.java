package api.acessos.novo.controller;

import api.acessos.novo.entity.Forca;
import api.acessos.novo.service.ForcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/forca")
public class ForcaController {

    @Autowired
    private ForcaService forcaService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('PLAYER')")
    public Forca getForca(@PathVariable Long id) {
        return forcaService.forcaById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') or hasAuthority('PLAYER')")
    public List<Forca> getAll() {
        return this.forcaService.getForcas();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Forca salvar(@RequestBody Forca forca) throws Exception {
        return this.forcaService.save(forca);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Forca alterar(@RequestBody Forca forca) throws Exception {
        return this.forcaService.save(forca);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void testDelete(@PathVariable Long id) {
        this.forcaService.delete(id);
    }
}
