package scopisto.apprenticeship.petstore.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scopisto.apprenticeship.petstore.service.HistoryLogService;

@RestController
@AllArgsConstructor
@RequestMapping("/history-log")
public class HistoryLogController {

    private final HistoryLogService historyLogService;

    @GetMapping
    public ResponseEntity<String> getLogs(){
        return new ResponseEntity<>(historyLogService.getHistoryLog(), HttpStatus.OK);
    }

}
