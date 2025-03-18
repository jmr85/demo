package com.example;

import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class HabilitarInformes {

    private SelfHealingDriver driver;
    private WebDriver delegate;

    @BeforeSuite
    public void navegadorChrome() throws MalformedURLException {

        WebDriverManager.chromedriver().setup();
        // Instance delegate
        delegate = new ChromeDriver();
        // Instance Self-healing driver
        driver = SelfHealingDriver.create(delegate);

        driver.manage().window().maximize();
        driver.get("https://elenastepuro.github.io/test_env/index.html");
    }

    @Test
    //@DisableHealing
    public void iniciarPrueba() throws InterruptedException {

        // CREAR UN OBJETO DE LA PAGINA PRINCIPAL
        WebElement txtCambiaName = driver.findElement(By.xpath("//*[@id='change:name']"));
        txtCambiaName.sendKeys("Hola Prueba");
        Thread.sleep(2000);
        txtCambiaName.clear();

        // CREAR UN OBJETO DE LA PAGINA PRINCIPAL
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,9000)");
        WebElement btnCambiarLocator = driver.findElement(By.xpath("//*[@id='Submit']"));
        btnCambiarLocator.click();
        Thread.sleep(2000);

        WebElement txtCambiaName2 = driver.findElement(By.xpath("//*[@id='change:name']"));
        txtCambiaName2.sendKeys("Cambiando el localizador");

        Thread.sleep(2000);
        // cerrarNavegador();
    }

    @AfterSuite
    public void cerrarNavegador() {
        driver.quit();
    }

}
