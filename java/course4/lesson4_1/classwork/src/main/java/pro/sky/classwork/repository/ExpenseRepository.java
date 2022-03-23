package pro.sky.classwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.classwork.entity.Expense;
import pro.sky.classwork.entity.ExpensesByCategory;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = "select category, sum(amount) as amount " +
            "from expenses group by category", nativeQuery = true)
    List<ExpensesByCategory> getExpensesByCategory();

//    List<Expense> f
}
