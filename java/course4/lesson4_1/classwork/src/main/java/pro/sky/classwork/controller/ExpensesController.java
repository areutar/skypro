package pro.sky.classwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.classwork.entity.Expense;
import pro.sky.classwork.entity.ExpensesByCategory;
import pro.sky.classwork.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpenseService expenseService;

    public ExpensesController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Void> createExpense(@RequestParam Expense expense) {
        expenseService.createExpense(expense);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findExpense(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(expenseService.findExpenseById(id));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam("page") int pageNumber,
                                                        @RequestParam("size") int pageSize) {
        List<Expense> expenses = expenseService.getAllExpenses(pageNumber, pageSize);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expenses-by-category")
    public ResponseEntity<List<ExpensesByCategory>> getExpensesByCategory() {
        List<ExpensesByCategory> expenses = expenseService.getExpensesByCategory();
        return ResponseEntity.ok(expenses);
    }
}
