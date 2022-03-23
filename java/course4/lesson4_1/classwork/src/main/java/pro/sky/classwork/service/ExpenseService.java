package pro.sky.classwork.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pro.sky.classwork.entity.Expense;
import pro.sky.classwork.entity.ExpensesByCategory;
import pro.sky.classwork.repository.ExpenseRepository;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository repository) {
        this.expenseRepository = repository;
    }

    public void createExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void deleteExpense(Integer id) {
        expenseRepository.deleteById(id);
    }

    public Expense findExpenseById(Integer id) {
        return expenseRepository.findById(id).get();
    }

    public List<Expense> getAllExpenses(int pageNumber, int pageSize) {
        var pageRequest = PageRequest.of(--pageNumber, pageSize);
        return expenseRepository.findAll(pageRequest).getContent();
    }

    public List<ExpensesByCategory> getExpensesByCategory() {
        return expenseRepository.getExpensesByCategory();
    }

}

