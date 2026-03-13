package org.example.playwrightTraditional;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class TestTraditional {
  @Test
  void testTraditionalPlaywright() throws Exception {
    Path vidDirectory = Paths.get("videos/");
    Files.createDirectories(vidDirectory);

    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(true));
      BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(1280, 720));
      Page page = context.newPage();
      page.navigate("https://depaul.bncollege.com/");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).fill("earbuds");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search")).press("Enter");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("brand")).click();
      page.locator(".facet__list.js-facet-list.js-facet-top-values > li:nth-child(3) > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
      page.getByText("brand brand JLab (17) brand").click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
      page.locator("#facet-Color > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").first().click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
      page.locator("#facet-price > .facet__values > .facet__list > li > form > label > .facet__list__label > .facet__list__mark > .facet-unchecked > svg").click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless")).click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black")).first()).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black");
      page.getByText("sku").nth(1).click();
      assertThat(page.getByText("sku").nth(1)).containsText("668972707");
      assertThat(page.getByText("$164.98")).containsText("$164.98");
      assertThat(page.getByText("Adaptive noise cancelling")).containsText("Adaptive noise cancelling allows awareness of environment when gaming on the go. Light weight, durable, water resist. USB-C dongle for low latency connection < than 30ms.");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).click();
      assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items"))).containsText("Cart 1 items");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart 1 items")).click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your Shopping Cart(1 Item)")).first()).isVisible();
      assertThat(page.getByText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds- Black qty: FAST In-")).containsText("JBL Quantum True Wireless Noise Cancelling Gaming Earbuds");
      assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Quantity, edit and press"))).hasValue("1");
      assertThat(page.getByText("$").first()).isVisible();
      page.getByText("FAST In-Store PickupDePaul").click();
      assertThat(page.getByText("Subtotal $164.98 Handling To")).containsText("$164.98");
      page.locator(".js-cart-totals > div:nth-child(2)").click();
      assertThat(page.getByText("$3.00", new Page.GetByTextOptions().setExact(true))).containsText("$3.00");
      assertThat(page.getByText("Taxes TBD")).containsText("Taxes TBD");
      page.getByText("Estimated Total $").click();
      assertThat(page.getByText("$167.98")).containsText("$167.98");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Promo Code")).fill("TEST");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Apply Promo Code")).click();
      assertThat(page.getByText("The coupon code entered is")).containsText("The coupon code entered is not valid.");
      page.getByLabel("Proceed To Checkout").click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Create Account"))).isVisible();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed As Guest")).click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information"))).isVisible();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill("Andy");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill("Cao");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill("acao6@depaul.edu");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill("3129989699");
      page.getByText("Order Subtotal $").nth(1).click();
      assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
      page.locator(".bned-order-summary-container.bned-step-summary-inner-container > .bned-order-summary-order-totals > .subtotals > div:nth-child(2)").click();
      assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
      assertThat(page.getByText("Tax TBD").nth(1)).containsText("Tax TBD");
      assertThat(page.getByText("Total $167.98 167.98 $").nth(1)).containsText("$167.98");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
      page.getByText("Full Name").first().click();
      assertThat(page.getByText("Andy Cao")).containsText("Andy Cao");
      page.getByText("Email Address", new Page.GetByTextOptions().setExact(true)).click();
      assertThat(page.getByText("acao6@depaul.edu")).containsText("acao6@depaul.edu");
      page.getByText("Phone Number").first().click();
      assertThat(page.getByText("+13129989699")).containsText("3129989699");
      assertThat(page.getByText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise").nth(1)).containsText("DePaul University Loop Campus & SAIC");
      page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Pickup Person")).click();
      assertThat(page.getByText("I'll pick them up")).containsText("I'll pick them up");
      page.getByText("Order Subtotal $").nth(1).click();
      assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
      page.locator(".bned-order-summary-container.bned-step-summary-inner-container > .bned-order-summary-order-totals > .subtotals > div:nth-child(2)").click();
      assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
      assertThat(page.getByText("Tax TBD").nth(1)).containsText("Tax TBD");
      assertThat(page.getByText("Total $167.98 167.98 $").nth(1)).containsText("$167.98");
      assertThat(page.getByText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise").nth(1)).containsText("JBL Quantum True Wireless Noise");
      assertThat(page.getByText("$164.98").nth(3)).containsText("$164.98");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
      page.getByText("Order Subtotal $").nth(1).click();
      assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
      page.locator(".bned-order-summary-container.bned-step-summary-inner-container > .bned-order-summary-order-totals > .subtotals > div:nth-child(2)").click();
      assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
      page.getByText("Tax $").nth(1).click();
      assertThat(page.getByText("$17.22").nth(1)).containsText("$17.22");
      assertThat(page.getByText("Total $185.20 185.2 $").nth(1)).containsText("$185.20");
      assertThat(page.getByText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise").nth(1)).containsText("JBL Quantum True Wireless Noise");
      assertThat(page.getByText("$164.98").nth(3)).containsText("$164.98");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your cart is empty"))).isVisible();
    }
  }
}