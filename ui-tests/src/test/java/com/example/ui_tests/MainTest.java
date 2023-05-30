package com.example.ui_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void ayuntamientoMadrid() {

        // Variables.
        WebElement acceptCookiesButton, tweetsMadrid, diarioMadridButton, today;
        String targetURL = "https://www.madrid.es/portal/site/munimadrid";
        String expectedTitle = "Inicio - Ayuntamiento de Madrid";
        Calendar calendar = Calendar.getInstance();

        // Abrir la URL del ayuntamiento de Madrid.
        driver.get(targetURL);

        // Aceptar cookies.
        acceptCookiesButton = driver.findElement(By.id("iam-cookie-control-modal-action-primary"));
        if(acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }

        // Locators para los elementos requeridos.
        tweetsMadrid = driver.findElement(By.cssSelector("#wrapper > div.content > div.bg-fluid1 > div > div > ul > li:nth-child(2) > a"));
        diarioMadridButton = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[1]/div/div/ul/li[2]/a"));
        today = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[3]/div[3]/div/div/ul[1]/li[1]/div/div/div[1]/div/p[2]"));

        // Validaciones
        assertEquals(expectedTitle,driver.getTitle());
        assertTrue(tweetsMadrid.isDisplayed(), "El elemento \"Tweets de @MADRID\" no fue encontrado");
        assertTrue(diarioMadridButton.isDisplayed() && diarioMadridButton.isEnabled(), "El elemento \"DIARIO DE MADRID\" no fue encontrado");
        assertEquals(today.getText(), Integer.toString(calendar.get(Calendar.DATE)), "El dia no coincide");
    }
}
