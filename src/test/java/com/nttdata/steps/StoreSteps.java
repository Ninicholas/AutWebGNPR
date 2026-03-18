package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

public class StoreSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void iniciarSesion(String usuario, String clave) {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.loginLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.emailInput)).sendKeys(usuario);
        driver.findElement(StorePage.passwordInput).sendKeys(clave);
        driver.findElement(StorePage.loginButton).click();
    }

    public void navegarACategoria(String categoria, String subcategoria) {
        By categoryLocator = By.xpath("//ul[@id='top-menu']//a[contains(normalize-space(.), '" + categoria + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(categoryLocator)).click();
        
        By subCategoryLocator = By.xpath("//ul[contains(@class,'category-sub-menu')]//a[contains(normalize-space(.), '" + subcategoria + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(subCategoryLocator)).click();
    }

    public void agregarUnidadesDelPrimerProductoAlCarrito(int unidades) {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.firstProductImage)).click();
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.increaseQuantityButton));
        
        for (int i = 1; i < unidades; i++) {
            driver.findElement(StorePage.increaseQuantityButton).click();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
        
        driver.findElement(StorePage.addToCartButton).click();
    }

    public void validarConfirmacionProductoAgregadoEnPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupModal));
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupProductName));
    }

    public void validarMontoTotalEnPopup() {
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupProductPrice)).getText();
        String qtyText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupProductQuantity)).getText();
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupSubtotal)).getText();
        
        double price = extractPrice(priceText);
        int qty = Integer.parseInt(qtyText.trim());
        double subtotal = extractPrice(subtotalText);
        
        double totalCalculated = price * qty;
        Assertions.assertEquals(totalCalculated, subtotal, 0.01, "El monto total calculado en el popup no coincide con (precio * cantidad).");
    }

    public void finalizarCompra() {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.popupFinishButton)).click();
    }

    public void validarTituloPaginaCarrito() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cartTitle));
        Assertions.assertTrue(title.getText().toUpperCase().contains("CARRITO"), "El titulo de la pagina no es Carrito");
    }

    public void validarCalculoPreciosEnCarrito() {
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cartProductUnitPrice)).getText();
        String qtyText = wait.until(ExpectedConditions.presenceOfElementLocated(StorePage.cartProductQuantity)).getAttribute("value");
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cartTotal)).getText();
        
        double price = extractPrice(priceText);
        int qty = Integer.parseInt(qtyText.trim());
        double totalCart = extractPrice(totalText);
        
        double totalCalculated = price * qty;
        Assertions.assertEquals(totalCalculated, totalCart, 0.01, "El calculo de precios en el carrito no es correcto.");
    }
    
    private double extractPrice(String text) {
        if (text == null || text.trim().isEmpty()) return 0.0;
        String cleanText = text.replaceAll("[^0-9.,]", "").replace(",", ".");
        return Double.parseDouble(cleanText);
    }
}
