package com.library.management.repository;
import com.library.management.model.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction,Long>{List<BorrowTransaction> findByMemberId(Long memberId);}
