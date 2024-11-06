package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void verifyLogin()
	{
		try {
			HomePage homePage = new HomePage(driver);
			
			homePage.clickMyAccount();
			homePage.clickLogin();
			
			LoginPage loginPage = new LoginPage(driver);
			
			loginPage.setEmail(property.getProperty("email"));
			loginPage.setPassword(property.getProperty("password"));
			loginPage.clickLogin();
			
			MyAccountPage accountPage = new MyAccountPage(driver);
			
			boolean targetPageExists = accountPage.isMyAccountPageExists();
			
			Assert.assertEquals(targetPageExists, true, "Login failed");
		}
		catch (Exception e) {
			Assert.fail();
		}
	}
}