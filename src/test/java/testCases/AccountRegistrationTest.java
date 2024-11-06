package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {
	
	@Test(groups = {"Regression", "Master"})
	public void verifyAccountRegistration()
	{
		HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
		homePage.clickRegister();
		
		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);
		
		registrationPage.setFirstName(capitalize(randomString(6)));
		registrationPage.setLastName(capitalize(randomString(6)));
		registrationPage.setEmail(randomString(10) + "@gmail.com");
		registrationPage.setTelephone(randomNumber(10));
		
		String password = randomAlphaNumeric();
		
		registrationPage.setPassword(password);
		registrationPage.setConfirmPassword(password);
		
		registrationPage.setPrivacyPolicy();
		registrationPage.clickContinue();
		
		String confmsg=registrationPage.getConfirmationMessage();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	
}