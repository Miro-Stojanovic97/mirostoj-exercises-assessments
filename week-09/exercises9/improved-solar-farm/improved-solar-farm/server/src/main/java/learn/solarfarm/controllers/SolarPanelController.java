package learn.solarfarm.controllers;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.data.SolarPanelRepository;
import learn.solarfarm.domain.ResultType;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.AppUser;
import learn.solarfarm.models.SolarPanel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solarpanel")
public class SolarPanelController {
    private final SolarPanelService service;

    public SolarPanelController(SolarPanelService service) {
        this.service = service;
    }

    @GetMapping
    public List<SolarPanel> findAll() throws DataAccessException {
        return service.findAll();
    }

//    @GetMapping
//    public ResponseEntity<?> findAll() throws DataAccessException {
//        try {
//            List<SolarPanel> panels = service.findAll();
//            return new ResponseEntity<>(panels, HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/section/{section}")
    public List<SolarPanel> findBySection(@PathVariable String section) throws DataAccessException {
        return service.findBySection(section);
    }

    @GetMapping("/user")
    public List<SolarPanel> findByUser(UsernamePasswordAuthenticationToken principal) throws DataAccessException {
        AppUser appUser = (AppUser) principal.getPrincipal();

        // TODO add repo method to get solar panels by user

        return service.findAll().stream()
                .filter(sp -> sp.getAppUser().getAppUserId() == appUser.getAppUserId())
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarPanel> findById(@PathVariable int id) throws DataAccessException {
        SolarPanel solarPanel = service.findById(id);
        if (solarPanel == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(solarPanel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SolarPanel solarPanel, UsernamePasswordAuthenticationToken principal) throws DataAccessException {
        AppUser appUser = (AppUser) principal.getPrincipal();

        // I'm forcing the solar panel's user to set to the currently authenticated user.
        solarPanel.setAppUser(appUser);

        SolarPanelResult result = service.create(solarPanel);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(result.getSolarPanel(), HttpStatus.CREATED); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody SolarPanel solarPanel, UsernamePasswordAuthenticationToken principal) throws DataAccessException {
        if (id != solarPanel.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        AppUser appUser = (AppUser) principal.getPrincipal();

        // Make sure that the existing record actually belongs to the authenticated user.
        SolarPanel existingSolarPanel = service.findById(id);
        if (existingSolarPanel.getAppUser().getAppUserId() != appUser.getAppUserId()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404... 401 UNAUTHORIZED
        }

        // Force set the user on the solar panel.
        solarPanel.setAppUser(appUser);

        SolarPanelResult result = service.update(solarPanel);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws DataAccessException {
        SolarPanelResult result = service.deleteById(id);
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
