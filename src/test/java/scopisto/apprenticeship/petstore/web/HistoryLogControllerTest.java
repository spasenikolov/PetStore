package scopisto.apprenticeship.petstore.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import scopisto.apprenticeship.petstore.service.HistoryLogService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HistoryLogControllerTest {
    @MockBean
    private HistoryLogService historyLogService;

    @Autowired
    MockMvc mockMvc;

    String exampleLog;

    @Before
    public void setUp() {
    }

    @Test
    public void getLogs_success() throws Exception {
        when(this.historyLogService.getHistoryLog()).thenReturn(exampleLog);

        mockMvc.perform(get("/history-log"))
                .andExpect(status().isOk()).andReturn();

        verify(this.historyLogService,times(1)).getHistoryLog();
    }
}