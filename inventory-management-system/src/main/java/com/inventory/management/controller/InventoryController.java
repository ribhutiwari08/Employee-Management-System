package com.inventory.management.controller;

import com.inventory.management.model.Product;
import com.inventory.management.model.StockMovement;
import com.inventory.management.model.Supplier;
import com.inventory.management.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) { this.service = service; }

    @GetMapping("/products")
    public List<Product> products() { return service.getProducts(); }

    @GetMapping("/products/{id}")
    public Product product(@PathVariable Long id) { return service.getProduct(id); }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product product) { return service.createProduct(product); }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) { service.deleteProduct(id); }

    @GetMapping("/suppliers")
    public List<Supplier> suppliers() { return service.getSuppliers(); }

    @PostMapping("/suppliers")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@Valid @RequestBody Supplier supplier) { return service.createSupplier(supplier); }

    @DeleteMapping("/suppliers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable Long id) { service.deleteSupplier(id); }

    @PatchMapping("/products/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam int quantity, @RequestParam String type) {
        return service.updateStock(id, quantity, type);
    }

    @GetMapping("/products/low-stock")
    public List<Product> lowStock() { return service.getLowStockProducts(); }

    @GetMapping("/products/{id}/stock-history")
    public List<StockMovement> stockHistory(@PathVariable Long id) { return service.getStockHistory(id); }

    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "UP", "service", "inventory-management-system"); }
}
