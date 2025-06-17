package com.rbac;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurer le driver Selenium
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Exécuter le navigateur en mode headless (sans interface graphique)
        driver = new ChromeDriver(options);
    }

    @Test
    public void testAddTask() {
        driver.get("file:///chemin/vers/votre/projet/src/main/resources/index.html");

        WebElement inputField = driver.findElement(By.id("newTask"));
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Ajouter']"));

        inputField.sendKeys("Nouvelle tâche");
        addButton.click();

        WebElement taskList = driver.findElement(By.id("taskList"));
        List<WebElement> tasks = taskList.findElements(By.tagName("li"));
        boolean taskAdded = false;

        for (WebElement task : tasks) {
            if (task.getText().contains("Nouvelle tâche")) {
                taskAdded = true;
                break;
            }
        }
        assertTrue(taskAdded);
    }

    @Test
    public void testDeleteTask() {
        driver.get("file:///chemin/vers/votre/projet/src/main/resources/index.html");

        WebElement inputField = driver.findElement(By.id("newTask"));
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Ajouter']"));
        inputField.sendKeys("Tâche à supprimer");
        addButton.click();

        WebElement deleteButton = driver.findElement(By.xpath("//li[contains(text(),'Tâche à supprimer')]//button[text()='Supprimer']"));
        deleteButton.click();

        WebElement taskList = driver.findElement(By.id("taskList"));
        List<WebElement> tasks = taskList.findElements(By.tagName("li"));
        boolean taskDeleted = true;

        for (WebElement task : tasks) {
            if (task.getText().contains("Tâche à supprimer")) {
                taskDeleted = false;
                break;
            }
        }
        assertTrue(taskDeleted);
    }

    @Test
    public void testMarkTaskAsCompleted() {
        driver.get("file:///chemin/vers/votre/projet/src/main/resources/index.html");

        WebElement inputField = driver.findElement(By.id("newTask"));
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Ajouter']"));
        inputField.sendKeys("Tâche à marquer");
        addButton.click();

        WebElement task = driver.findElement(By.xpath("//li[contains(text(),'Tâche à marquer')]"));
        task.click();

        assertTrue(task.getAttribute("class").contains("completed"));
    }

    @AfterEach
    public void tearDown() {
        // Fermer le navigateur après chaque test
        if (driver != null) {
            driver.quit();
        }
    }
}