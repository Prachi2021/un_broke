package com.prachi.un_broke.repository;

import com.prachi.un_broke.dto.Expense_DTO;
import com.prachi.un_broke.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
    @Query("SELECT new com.prachi.un_broke.dto.Expense_DTO(e.id,  e.description, e.amount, e.date, sc.id, sc.subcategory, c.category) " +
            "FROM Expense e " +
            "JOIN e.subCategory sc " +
            "JOIN sc.category c " +
            "JOIN e.user_id u " +
            "WHERE e.user_id = :userId")
    List<Expense_DTO> getExpensesWithCategory(@Param("userId") int userId);

    @Query("SELECT e FROM Expense e WHERE e.user_id = :userId")
    List<Expense> findByUserId(@Param("userId") int userId);

    @Query("SELECT e FROM Expense e WHERE e.id = :expenseId AND e.user_id = :userId")
    Expense findExpenseByIdAndUserId(@Param("expenseId") int expenseId, @Param("userId") int userId);
}
