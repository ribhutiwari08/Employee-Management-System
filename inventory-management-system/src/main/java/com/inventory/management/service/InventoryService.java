package com.inventory.management.service;

import com.inventory.management.model.Product;
import com.inventory.management.model.StockMovement;
import com.inventory.management.model.Supplier;
import com.inventory.management.repository.ProductRepository;
import com.inventory.management.repository.StockMovementRepository;
import com.inventory.management.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {
    private final ProductRepository products;
    private final SupplierRepository suppliers;
    private final StockMovementRepository movements;

    public InventoryService(ProductRepository products, SupplierRepository suppliers,
                            StockMovementRepository movements) {
        this.products = products;
        this.suppliers = suppliers;
        this.movements = movements;
    }

    public List<Product> getProducts() { return products.findAll(); }
    public Product getProduct(Long id) { return products.findById(id).orElseThrow(() -> new RuntimeException("Product not found")); }
    public Product createProduct(Product product) {
        if (product.getSupplier() != null && product.getSupplier().getId() != null) {
            product.setSupplier(suppliers.findById(product.getSupplier().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found")));
        }
        return products.save(product);
    }
    public Product updateProduct(Long id, Product input) {
        Product product = getProduct(id);
        product.setName(input.getName());
        product.setSku(input.getSku());
        product.setPrice(input.getPrice());
        product.setReorderLevel(input.getReorderLevel());
        if (input.getSupplier() != null && input.getSupplier().getId() != null) {
            product.setSupplier(suppliers.findById(input.getSupplier().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found")));
        }
        return products.save(product);
    }
    public void deleteProduct(Long id) { products.delete(getProduct(id)); }

    public List<Supplier> getSuppliers() { return suppliers.findAll(); }
    public Supplier createSupplier(Supplier supplier) { return suppliers.save(supplier); }
    public void deleteSupplier(Long id) { suppliers.deleteById(id); }

    @Transactional
    public Product updateStock(Long productId, int quantity, String type) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        if (!type.equalsIgnoreCase("IN") && !type.equalsIgnoreCase("OUT"))
            throw new IllegalArgumentException("Type must be IN or OUT");
        Product product = getProduct(productId);
        int newQuantity = type.equalsIgnoreCase("IN")
                ? product.getQuantity() + quantity
                : product.getQuantity() - quantity;
        if (newQuantity < 0) throw new IllegalStateException("Insufficient stock");
        product.setQuantity(newQuantity);
        Product saved = products.save(product);

        StockMovement movement = new StockMovement();
        movement.setProduct(saved);
        movement.setType(type.toUpperCase());
        movement.setQuantity(quantity);
        movement.setCreatedAt(LocalDateTime.now());
        movements.save(movement);
        return saved;
    }

    public List<Product> getLowStockProducts() {
        return products.findByQuantityLessThanEqual(10);
    }
    public List<StockMovement> getStockHistory(Long productId) {
        getProduct(productId);
        return movements.findByProductIdOrderByCreatedAtDesc(productId);
    }
}
