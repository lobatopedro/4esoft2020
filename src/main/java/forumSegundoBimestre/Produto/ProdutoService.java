package forumSegundoBimestre.Produto;

import mySpringBootApp.livro.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Produto getById(String id) {
        return (Produto) repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public String save(Produto produto) {
        return this.repository.save(produto).getId();
    }

}
