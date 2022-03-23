package pro.sky.classwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.classwork.entity.Expense;
import pro.sky.classwork.entity.ExpensesByCategory;
import pro.sky.classwork.service.ExpenseService;

import java.util.List;

@RestController
public class ExpensesByCategoryController {
    private final ExpenseService expenseService;

    public ExpensesByCategoryController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses-by-category")
    public ResponseEntity<List<ExpensesByCategory>> getExpensesByCategory() {
        List<ExpensesByCategory> expenses = expenseService.getExpensesByCategory();
        return ResponseEntity.ok(expenses);
    }
}
