package firstProject.repository;



import model.FinanceRecord;

import java.time.LocalDate;
import java.util.List;

public interface FinanceRecordRepository {
    void saveFinanceReport(FinanceRecord financeRecord);
    void removeFinanceReport(long idFinanceReport);
    FinanceRecord findFinanceRecordById(long idFinanceReport);
    List<FinanceRecord> generateFinancialReport(LocalDate startDate,LocalDate endDate);


}
