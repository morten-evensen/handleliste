package handleliste.lagre.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class VareController {

    @Autowired
    private JdbcTemplate db;

    private final Logger logger = LoggerFactory.getLogger(VareController.class);

    @PostMapping("/lagre")
    public void lagre(Vare innVare, HttpServletResponse response) throws IOException {
        String sql = "INSERT INTO Varer (vare,antall) VALUES(?,?)";
        try {
                db.update(sql, innVare.getVare(), innVare.getAntall());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i lagring i database - prøv igjen senere");
        }
    }

    @GetMapping("/hentAlle")
    public List<Vare> hentAlle(HttpServletResponse response) throws IOException {
        String sql = "SELECT * FROM Varer";
        List<Vare> alleVarer = db.query(sql, new BeanPropertyRowMapper(Vare.class));
        try {
            return alleVarer;
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i henting i database - prøv igjen senere");
            return null;
        }
    }

    @GetMapping("/hentEnVare")
    public Vare hentEnVare(int id,HttpServletResponse response) throws IOException {
        Object[] param = new Object[1];
        param[0] = id;
        String sql = "SELECT * FROM Varer WHERE id=?";
        try {
            Vare enVare = db.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Vare.class));
                return enVare;

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i henting i database - prøv igjen senere");
            return null;
        }
    }

    @PostMapping("/endreEnVare")
    public void endreEnVare (Vare vare, HttpServletResponse response) throws IOException {
        String sql = "UPDATE Varer SET vare=?,antall=? where id=?";
        try {
            db.update(sql, vare.getVare(), vare.getAntall(), vare.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i endring i database - prøv igjen senere");
        }
    }

    @GetMapping("/slettEnVare")
    public void slettEnVare (int id, HttpServletResponse response) throws IOException {
        String sql = "DELETE FROM Varer WHERE id=?";
        try {
            db.update(sql, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i sletting i database - prøv igjen senere");
        }
    }

    @GetMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response) throws IOException {
        String sql = "DELETE FROM Varer";
        try {
            db.update(sql);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i sletting i database - prøv igjen senere");
        }
    }
}
