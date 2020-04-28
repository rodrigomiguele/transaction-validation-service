package rmiguele.transaction.validation.repository;

import rmiguele.transaction.validation.model.Validation;

import java.util.List;

public interface ValidationRepository extends BaseRepository<String, Validation> {
    List<Validation> findByTransactionCode(String transactionCode);
}
