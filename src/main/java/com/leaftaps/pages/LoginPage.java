package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods {
    public LoginPage enterUsername() {
        clearAndType(locateElement(Locators.ID, "username"), username, "Username entered successfully");
        return this;
    }

    public LoginPage enterPassword() {
        clearAndType(locateElement(Locators.ID, "password"), password, "Password entered successfully");
        return this;
    }

    public HomePage clickLogin() {
        click(locateElement(Locators.CSS, "input.decorativeSubmit"), "Login button clicked successfully");
        return new HomePage();
    }
}
