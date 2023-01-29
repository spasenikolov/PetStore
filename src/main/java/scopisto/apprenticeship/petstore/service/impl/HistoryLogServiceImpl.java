package scopisto.apprenticeship.petstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scopisto.apprenticeship.petstore.model.HistoryLog;
import scopisto.apprenticeship.petstore.repository.jpa.HistoryLogRepository;
import scopisto.apprenticeship.petstore.service.HistoryLogService;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class HistoryLogServiceImpl implements HistoryLogService {

    private final HistoryLogRepository historyLogRepository;

    @Override
    public void insertLog(int successBuys, int notAllowedBuys) {
        this.historyLogRepository.save(HistoryLog.builder()
                .dateOfExecution(LocalDate.now())
                .numOfSuccessfulPetBuyers(successBuys)
                .numOfUsersNotAllowedToBuyPet(notAllowedBuys)
                .build());
    }

    @Override
    public String getHistoryLog() {
        StringBuilder logsBuilder = new StringBuilder();
        logsBuilder.append(String.format("%-15s%-15s%-15s\n", "Date", "Successful", "Not allowed"));
        this.historyLogRepository.findAll()
                .forEach(historyLog -> {
                            logsBuilder.append(String.format("%-15s%-15d%-15d\n",
                                    historyLog.getDateOfExecution().toString(),
                                    historyLog.getNumOfSuccessfulPetBuyers(),
                                    historyLog.getNumOfUsersNotAllowedToBuyPet()));
                        }
                );
        logsBuilder.delete(logsBuilder.length()-1, logsBuilder.length());
        return logsBuilder.toString();
    }
}
