import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class AddToBasket extends AbstractClass{
    @Test
    @Order(1)
    void findText() throws InterruptedException {
        Assertions.assertTrue(
                textToBePresentInElement(getDriver().findElement(
                        By.xpath("/html/body/div[1]/header/div/div/div/noindex[2]/nav/ul/li[5]/div/div/div/div[1]/a/span")
                ), "Войти").apply(getDriver())
        );
    }
    @Test
    @Order(2)
    void chooseProductAndAddToBasket() throws InterruptedException {
        Actions search = new Actions(getDriver());
        // заходим в каталог
        search.click(getDriver().findElement(By.xpath("//*[@id=\"catalogPopupButton\"]/span/div/span")))
                .pause(500L).build().perform();
        //ищем товар
        search.sendKeys(getDriver().findElement(By.xpath("//*[@id=\"header-search\"]")), "Умная колонка")
                .pause(500L).build().perform();
        search.click(getDriver().findElement(By.xpath("/html/body/div[1]/header/div/div/div/div/div/div/div/form/div[1]/button/span")))
                .pause(500L).build().perform();
        // добавляем товар в корзину ( пришлось использовать полный xPath, так как короткий или селектор содержат постоянно изменяющийся ID страницы.
        search.click(getDriver().findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[5]/div/div/div/div/div/div/div[5]/div/div/div/div/main/div/div/div/div/div/div/div[1]/div/div/div/article/div[3]/div[1]/div[3]/div/button/span/div/span")))
                .pause(500L).build().perform();
        // открываем корзину
        search.click(getDriver().findElement(By.xpath("/html/body/div[20]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div[2]/a/span")))
                .pause(500L).build().perform();
        Thread.sleep(1000);
        // проверяем, что товар доступен к оформлению
        Assertions.assertTrue(
                textToBePresentInElement(getDriver().findElement(
                        By.xpath("//*[@id=\"cartCheckoutButton\"]")
                ), "Перейти к оформлению").apply(getDriver())
        );
    }
}
