package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void WriteAPublicationTest() {

        WebElement userIcon = driver.findElement(By.cssSelector("svg[data-test-id='menu-toggle-guest']"));
        userIcon.click();

        WebElement beAnAuthor = driver.findElement(By.xpath("//a[contains(text(), 'Как стать автором')][@class=\"tm-user-menu__menu-link\"]"));
        beAnAuthor.click();

        assertTrue(driver.findElement(By.xpath("//a[1][contains(text(), 'Написать публикацию')][@class=\"button\"]")).isDisplayed(), "Первая кнопка Написать публикацию не найдена");

    }

    @Test
    public void NewAuthorTest() {

        WebElement userIcon = driver.findElement(By.cssSelector("svg[data-test-id='menu-toggle-guest']"));
        userIcon.click();

        WebElement beAnAuthor = driver.findElement(By.xpath("//a[contains(text(), 'Как стать автором')][@class=\"tm-user-menu__menu-link\"]"));
        beAnAuthor.click();

        assertTrue(driver.findElement(By.xpath("//*[@class='tm-block__title tm-block__title']")).isDisplayed(), "блок Новые авторы не найден");

    }

}
