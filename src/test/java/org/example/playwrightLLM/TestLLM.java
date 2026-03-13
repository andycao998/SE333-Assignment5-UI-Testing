package org.example.playwrightLLM;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class TestLLM {
    @Test
    void testLLMPlaywright() throws Exception {
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
            Locator jblBrandOption = page.locator("#facet-brand li")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("\\bJBL\\b", Pattern.CASE_INSENSITIVE)))
                .first();
            jblBrandOption.locator("svg").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Color")).click();
            Locator blackColorOption = page.locator("#facet-Color li")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("Black", Pattern.CASE_INSENSITIVE)))
                .first();
            blackColorOption.locator("svg").first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Price")).click();
            Locator over50PriceOption = page.locator("#facet-price li")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("(Over|\\$50)", Pattern.CASE_INSENSITIVE)))
                .first();
            over50PriceOption.locator("svg").first().click();
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
            assertThat(page.locator("body")).containsText("Create Account");
            page.getByText("Proceed As Guest").first().click();
            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Contact Information"))).isVisible();

            long generatedSeed = System.currentTimeMillis();
            String generatedFirstName = "TestFirst" + generatedSeed;
            String generatedLastName = "TestLast" + generatedSeed;
            String generatedEmail = "test" + generatedSeed + "@example.com";
            String generatedPhone = "312" + String.format("%07d", (int) (generatedSeed % 10000000));

            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name (required)")).fill(generatedFirstName);
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name (required)")).fill(generatedLastName);
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email address (required)")).fill(generatedEmail);
            page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Phone Number (required)")).fill(generatedPhone);

            assertThat(page.getByText("Order Subtotal $").nth(1)).containsText("Order Subtotal");
            assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
            assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
            assertThat(page.getByText("Tax TBD").nth(1)).containsText("Tax TBD");
            assertThat(page.getByText("Total $167.98 167.98 $").nth(1)).containsText("$167.98");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            assertThat(page.locator("body")).containsText(generatedFirstName + " " + generatedLastName);
            assertThat(page.locator("body")).containsText(generatedEmail);
            assertThat(page.locator("body")).containsText(generatedPhone.substring(generatedPhone.length() - 4));
            assertThat(page.getByText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise").nth(1)).containsText("DePaul University Loop Campus & SAIC");
            assertThat(page.getByText("I'll pick them up")).containsText("I'll pick them up");
            assertThat(page.getByText("Order Subtotal $").nth(1)).containsText("Order Subtotal");
            assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
            assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
            assertThat(page.getByText("Tax TBD").nth(1)).containsText("Tax TBD");
            assertThat(page.getByText("Total $167.98 167.98 $").nth(1)).containsText("$167.98");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

            assertThat(page.getByText("Order Subtotal $").nth(1)).containsText("Order Subtotal");
            assertThat(page.getByText("$164.98").nth(2)).containsText("$164.98");
            assertThat(page.getByText("$3.00").nth(3)).containsText("$3.00");
            assertThat(page.locator("body")).containsText("Tax");
            assertThat(page.locator("body")).containsText("Total");
            assertThat(page.getByText("PICKUP DePaul University Loop Campus & SAIC JBL Quantum True Wireless Noise").nth(1)).containsText("JBL Quantum True Wireless Noise");
            assertThat(page.getByText("$164.98").nth(3)).containsText("$164.98");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back to cart")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove product JBL Quantum")).click();
            assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Your cart is empty"))).isVisible();
        }
    }
}
