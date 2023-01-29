package scopisto.apprenticeship.petstore.web.graphql;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import scopisto.apprenticeship.petstore.service.HistoryLogService;

@Controller
@AllArgsConstructor
public class HistoryLogControllerGraphQL {

    private final HistoryLogService historyLogService;

    @QueryMapping
    public String historyLog(){
        return this.historyLogService.getHistoryLog();
    }
}
