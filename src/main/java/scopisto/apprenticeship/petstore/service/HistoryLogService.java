package scopisto.apprenticeship.petstore.service;

public interface HistoryLogService {
    void insertLog(int successBuys, int notAllowedBuys);
    String getHistoryLog();

}
