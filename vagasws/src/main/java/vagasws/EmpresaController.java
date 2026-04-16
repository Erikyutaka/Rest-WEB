package vagasws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpresaController {

    @Autowired
    private EmpresaRepo empresasRepo;

    @GetMapping("/fci/api/empresas")
    public Iterable<Empresa> getEmpresas() {
        return empresasRepo.findAll();
    }

    @GetMapping("/fci/api/empresas/{id}")
    public Empresa getEmpresa(@PathVariable long id) {
        return empresasRepo.findById(id).orElse(null);
    }

    @PostMapping("/fci/api/empresas")
    public Empresa createEmpresa(@RequestBody Empresa novaEmpresa) {
        return empresasRepo.save(novaEmpresa);
    }

    @PutMapping("/fci/api/empresas/{id}")
    public Empresa updateEmpresa(@PathVariable long id, @RequestBody Empresa empresaAtualizada) {
        return empresasRepo.findById(id).map(e -> {
            e.setNome(empresaAtualizada.getNome());
            e.setCnpj(empresaAtualizada.getCnpj());
            e.setEmail(empresaAtualizada.getEmail());
            return empresasRepo.save(e);
        }).orElse(null);
    }

    @DeleteMapping("/fci/api/empresas/{id}")
    public void deleteEmpresa(@PathVariable long id) {
        empresasRepo.deleteById(id);
    }
}