package com.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pc\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        // ⚠️ Ne pas activer le mode headless si tu veux voir le navigateur
        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testAddTask() throws InterruptedException {
        driver.get("file:///C:/Users/pc/Downloads/ism-demo/Selenium/src/main/index.html");

        WebElement inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("newTask")));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ajouter']")));

        inputField.sendKeys("Nouvelle tâche");
        addButton.click();

        WebElement taskList = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("taskList")));
        List<WebElement> tasks = taskList.findElements(By.tagName("li"));
        boolean taskAdded = tasks.stream().anyMatch(task -> task.getText().contains("Nouvelle tâche"));

        assertTrue(taskAdded);

        Thread.sleep(600_000); // 🔁 Garde la fenêtre ouverte 10 minutes
    }

    @Test
    public void testDeleteTask() throws InterruptedException {
        driver.get("file:///C:/Users/pc/Downloads/ism-demo/Selenium/src/main/index.html");

        WebElement inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("newTask")));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ajouter']")));
        inputField.sendKeys("Tâche à supprimer");
        addButton.click();

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(),'Tâche à supprimer')]//button[text()='Supprimer']")));
        deleteButton.click();

        WebElement taskList = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("taskList")));
        List<WebElement> tasks = taskList.findElements(By.tagName("li"));
        boolean taskDeleted = tasks.stream().noneMatch(task -> task.getText().contains("Tâche à supprimer"));

        assertTrue(taskDeleted);

        Thread.sleep(600_000); // 🔁 Garde la fenêtre ouverte 10 minutes
    }

    @Test
    public void testMarkTaskAsCompleted() throws InterruptedException {
        driver.get("file:///C:/Users/pc/Downloads/ism-demo/Selenium/src/main/index.html");

        WebElement inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("newTask")));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ajouter']")));
        inputField.sendKeys("Tâche à marquer");
        addButton.click();

        WebElement task = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//li[contains(text(),'Tâche à marquer')]")));
        task.click();

        assertTrue(task.getAttribute("class").contains("completed"));

        Thread.sleep(600_000); // 🔁 Garde la fenêtre ouverte 10 minutes
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

