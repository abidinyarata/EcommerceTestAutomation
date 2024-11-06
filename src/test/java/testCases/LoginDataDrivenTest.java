package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_loginDDT(String email, String password, String result)
	{
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(password);
			loginPage.clickLogin();

			MyAccountPage accountPage = new MyAccountPage(driver);
			boolean targetPageExists = accountPage.isMyAccountPageExists();

			if (result.equalsIgnoreCase("Valid")) 
				if (targetPageExists) {
					accountPage.clickLogout();
					Assert.assertTrue(true);
				} else 
					Assert.assertTrue(false);

			if (result.equalsIgnoreCase("Invalid")) 
				if (targetPageExists) {
					accountPage.clickLogout();
					Assert.assertTrue(false);
				} else 
					Assert.assertTrue(true);
		} 
		catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}
	}

}