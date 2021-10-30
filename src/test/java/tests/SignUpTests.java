package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class SignUpTests extends TestBase{

    @Test
    public void positiveSignUp(){

        Faker faker = new Faker();

        SignUpPage signUpPage = new SignUpPage();
        driver.get(ConfigReader.getProperty("url"));
        signUpPage.link.click();
        Assert.assertTrue(driver.getCurrentUrl().equals(" http://automationpractice.com/index.php?controller=authentication&back=my-account"));

        signUpPage.email.sendKeys(faker.internet().emailAddress());
        signUpPage.creatAccount.click();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        signUpPage.genderMale.click();
        signUpPage.firstName.sendKeys(faker.name().firstName());
        signUpPage.lastName.sendKeys(faker.name().lastName());


        //Verify Email text box has email address defined as used in step
        String attr = signUpPage.emailCopy.getText();
        signUpPage.email.equals(attr);

        signUpPage.password.sendKeys("dt123");
        Select select = new Select(signUpPage.dayBirth);
        select.selectByVisibleText("15");
        Select select1 = new Select(signUpPage.yearBirth);
        select1.selectByVisibleText("1984");
        Select select2 = new Select(signUpPage.monthBirth);
        select2.selectByValue("9");

        signUpPage.firstName.sendKeys(faker.name().firstName());
        signUpPage.lastName.sendKeys(faker.name().lastName());
        signUpPage.companyName.sendKeys(faker.company().name());
        signUpPage.cityName.sendKeys(faker.address().city());
        signUpPage.streetName.sendKeys(faker.address().streetAddress());
        Select select3 = new Select(signUpPage.stateName);
        select3.selectByVisibleText("New York");
        signUpPage.postcode.sendKeys(faker.address().zipCode());
        Select select4 = new Select(signUpPage.country);
        select4.selectByVisibleText("United States");

        signUpPage.phone.sendKeys(faker.phoneNumber().phoneNumber());
        signUpPage.phoneMobile.sendKeys(faker.phoneNumber().phoneNumber());
        signUpPage.home.sendKeys("home");


        signUpPage.registerButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=my-account"));

        Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=my-account"));
    }


        @Test
            public void NegativeSignUp(){

            Faker faker = new Faker();
            SignUpPage signUpPage = new SignUpPage();
            driver.get(ConfigReader.getProperty("url"));
            signUpPage.link.click();
            Assert.assertTrue(driver.getCurrentUrl().equals(" http://automationpractice.com/index.php?controller=authentication&back=my-account"));

            signUpPage.email.sendKeys(faker.internet().emailAddress());
            signUpPage.creatAccount.click();
            Assert.assertTrue(driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

            signUpPage.registerButton.click();

            String msg = signUpPage.message1.getText();
            Assert.assertTrue(msg.contains("You must register at least one phone number."));

            Assert.assertTrue(msg.contains("lastname is required."));

            Assert.assertTrue(msg.contains("firstname is required."));
            Assert.assertTrue(msg.contains("passwd is required."));
            Assert.assertTrue(msg.contains("address1 is required."));
            Assert.assertTrue(msg.contains("city is required."));
            Assert.assertTrue(msg.contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
            Assert.assertTrue(msg.contains("This country requires you to choose a State."));


}
    @Test
    public void negativeSignUp() throws InterruptedException {
        Faker faker = new Faker();
        SignUpPage signUpPage = new SignUpPage();
        driver.get(ConfigReader.getProperty("url"));
        signUpPage.link.click();
        Assert.assertTrue(driver.getCurrentUrl().equals(" http://automationpractice.com/index.php?controller=authentication&back=my-account"));

        signUpPage.email.sendKeys("abcdef");
        signUpPage.creatAccount.click();
        String msg2=driver.findElement(By.xpath ("//li[contains(text(),'Invalid email address.')]")).getText();
        Assert.assertTrue(msg2.equals("Invalid email address."));

    }
    }
