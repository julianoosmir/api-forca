package api.acessos.novo.service;

import api.acessos.novo.entity.Forca;
import api.acessos.novo.repository.ForcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForcaService {

    @Autowired
    private ForcaRepository forcaRepository;

    public Forca save(Forca forca) throws Exception {
        this.limitWord(forca);
        return this.forcaRepository.save(forca);
    }

    public void limitWord(Forca forca) throws Exception {
        String words[] = forca.getPalavra().split("\\s");
        if (words.length > 1) {
            throw new Exception("cadastre apenas uma palavra");
        }
    }

    public Forca forcaById(Long id) {
        Forca forca = this.forcaRepository.getById(id);
        return forca;
    }

    public List<Forca> getForcas() {
        return forcaRepository.findAll();
    }

    public void delete(Long id) {
        forcaRepository.deleteById(id);
    }

}
