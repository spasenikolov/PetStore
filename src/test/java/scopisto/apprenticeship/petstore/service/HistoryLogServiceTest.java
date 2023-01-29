package scopisto.apprenticeship.petstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import scopisto.apprenticeship.petstore.model.HistoryLog;
import scopisto.apprenticeship.petstore.repository.jpa.HistoryLogRepository;
import scopisto.apprenticeship.petstore.service.impl.HistoryLogServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HistoryLogServiceTest {

    @Mock
    HistoryLogRepository historyLogRepository;
    @InjectMocks
    HistoryLogServiceImpl historyLogService;
    HistoryLog historyLog;

    @Before
    public void setUp(){
        historyLog = HistoryLog.builder()
                .dateOfExecution(LocalDate.now())
                .numOfSuccessfulPetBuyers(2)
                .numOfUsersNotAllowedToBuyPet(3)
                .id(0L)
                .build();
    }

    @Test
    public void insertLog() {
        when(historyLogRepository.save(any(HistoryLog.class))).thenReturn(historyLog);

        this.historyLogService.insertLog(historyLog.getNumOfSuccessfulPetBuyers(),historyLog.getNumOfUsersNotAllowedToBuyPet());

        verify(this.historyLogRepository, times(1)).save(any(HistoryLog.class));
    }

    @Test
    public void getHistoryLog() {
        List<HistoryLog> historyLogList = List.of(historyLog);
        when(this.historyLogRepository.findAll()).thenReturn(historyLogList);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-15s%-15s%-15s\n","Date", "Successful","Not allowed"));
        stringBuilder.append(String.format("%-15s%-15d%-15d",
                historyLog.getDateOfExecution().toString(),
                historyLog.getNumOfSuccessfulPetBuyers(),
                historyLog.getNumOfUsersNotAllowedToBuyPet()));
        var expected = stringBuilder.toString();

        var result = this.historyLogService.getHistoryLog();

        assertEquals(expected, result);
    }

}