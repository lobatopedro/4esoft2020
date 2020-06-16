package forumSegundoBimestre.Produto;

import forumSegundoBimestre.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<String> getAll() {
        return service.getAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public Produto getById(@PathVariable("id") String id) {
        return service.getById(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody Produto novo) {
        return service.save(novo);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody Produto novo) {
        if (!id.equals(novo.getId())) {
            throw new BadRequestException();
        }
        service.save(novo);
    }
}
