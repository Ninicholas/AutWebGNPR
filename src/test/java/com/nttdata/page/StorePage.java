package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {
    // Login
    public static By loginLink = By.xpath("//div[@id='_desktop_user_info']//a");
    public static By emailInput = By.id("field-email");
    public static By passwordInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");

    // Category
    public static By categoryClothes = By.xpath("//ul[@id='top-menu']/li[@id='category-3']/a");
    public static By subCategoryMen = By.xpath("//ul[contains(@class,'category-sub-menu')]//a[contains(@href, '4-men')]");
    
    // Product List Elements
    public static By firstProductImage = By.xpath("//div[@id='js-product-list']//article//img");
    public static By increaseQuantityButton = By.xpath("//button[contains(@class,'bootstrap-touchspin-up')]");
    public static By addToCartButton = By.xpath("//button[@data-button-action='add-to-cart']");

    // Popup
    public static By popupModal = By.id("blockcart-modal");
    public static By popupModalBody = By.className("modal-body");
    public static By popupTitle = By.xpath("//h4[@id='myModalLabel']");
    public static By popupProductName = By.className("product-name");
    public static By popupProductPrice = By.cssSelector("p.product-price");
    public static By popupProductQuantity = By.cssSelector("span.product-quantity strong");
    public static By popupSubtotal = By.cssSelector("span.subtotal.value");
    public static By popupFinishButton = By.xpath("//div[@class='cart-content-btn']//a[contains(text(), 'Finalizar compra')]");

    // Cart
    public static By cartTitle = By.xpath("//h1[contains(text(),'Carrito')]");
    public static By cartProductUnitPrice = By.cssSelector(".current-price .price");
    public static By cartProductQuantity = By.cssSelector("input.js-cart-line-product-quantity");
    public static By cartProductTotal = By.cssSelector(".product-line-grid-right .product-price strong");
    public static By cartTotal = By.cssSelector(".cart-summary-totals .cart-total .value");
}
