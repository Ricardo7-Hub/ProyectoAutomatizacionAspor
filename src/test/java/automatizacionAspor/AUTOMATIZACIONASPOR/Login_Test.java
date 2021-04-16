package automatizacionAspor.AUTOMATIZACIONASPOR;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;


class Login_Test extends BaseClass{
	
	/*
	 * Scenario: Validar LOGIN DE SESIÓN
	 *  - Despliegue
	 *  - Realizar Login de Inicio de Sesión 
			- Sin datos (todos los campos vacíos).
	 *  - Realizar Login de Inicio de Sesión 
			- campo Usuario vacío.
	 *  - Realizar Login de Inicio de Sesión 
			- campo Contraseña vacío.
	 *  - Realizar Login de Inicio de Sesión con Datos incorrectos.
			- campo Contraseña no válido.
	 *  Realizar Login de Inicio de Sesión
			- EXITOSO
	 */	


	@Test
	void login_Despliegue() throws Exception {
		/*
		 * Implementación: PoC_FUN_001
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Despliegue");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		
		String tituloPagina = this.getDriver().getTitle();
		assertEquals("Login - ASPOR", tituloPagina, "Valida título de página");
		
		WebElement tituloVentana = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(), 'Ingreso')]")));
		assertEquals("Ingreso", tituloVentana.getText(), "Valida título de la sección Login");
	
		WebElement texto_Usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Usuario')]")));
		assertEquals("Usuario", texto_Usuario.getText(), "Valida texto campo Usuario");
		WebElement campo_Usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		assertTrue(campo_Usuario.isEnabled());
		
		WebElement texto_Contrasena = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Contraseña')]")));
		assertEquals("Contraseña", texto_Contrasena.getText(), "Valida texto campo Contraseña");
		WebElement campo_Contrasena = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		assertTrue(campo_Contrasena.isEnabled());
		
		WebElement Btn_ENVIAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
		assertEquals("ENVIAR", Btn_ENVIAR.getText(), "Valida botón ENVIAR");
		assertTrue(Btn_ENVIAR.isEnabled());
		
		WebElement texto_Glosa_Recuperacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), '¿Olvidaste tu contraseña? Ingresa aquí y')]")));
		assertEquals("¿Olvidaste tu contraseña? Ingresa aquí y recuperar tu contraseña .", texto_Glosa_Recuperacion.getText(), "Valida glosa Recuperación");
		
		WebElement hipervinculo_Recuperar_Contrasena = getWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText("recuperar tu contraseña")));
		assertEquals("recuperar tu contraseña", hipervinculo_Recuperar_Contrasena.getText(), "Valida hipervínculo Recuperar Contraseña");
		
		takeScreenShotTest(getDriver(), "Login login_Despliegue");
		System.out.println("Prueba PoC_FUN_001 Exitosa");	
	}
	
	@Test
	void login_Sin_Datos() throws Exception {
		/*
		 * Implementación: PoC_FUN_002
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Sin_Datos");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		
		WebElement popup_Usuario_Error = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Usuario: Validation Error: Value is required.')]")));
		assertEquals("Usuario: Validation Error: Value is required.", popup_Usuario_Error.getText(), "Valida campo Usuario");
		takeScreenShotTest(getDriver(), "Login login_Sin_Datos");
		WebElement popup_Contrasena_Error = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Contraseña: Validation Error: Value is required.')]")));
		assertEquals("Contraseña: Validation Error: Value is required.", popup_Contrasena_Error.getText(), "Valida campo Contraseña");
		takeScreenShotTest(getDriver(), "Login login_Sin_Datos");
		Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_002 Exitosa");	
	}
	
	@Test
	void login_Usuario_Vacio() throws Exception {
		/*
		 * Implementación: PoC_FUN_003
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Usuario_Vacio");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		takeScreenShotTest(getDriver(), "Login login_Usuario_Vacio");
		
		WebElement contraseña = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		contraseña.sendKeys(Configuration.PASS);
		takeScreenShotTest(getDriver(), "Login login_Usuario_Vacio");

		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		
		WebElement popup_Usuario_Error = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Usuario: Validation Error: Value is required.')]")));
		assertEquals("Usuario: Validation Error: Value is required.", popup_Usuario_Error.getText(), "Valida PopUp Usuario Error");
		takeScreenShotTest(getDriver(), "Login login_Usuario_Vacio");
		Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_003 Exitosa");	
	}
	
	@Test
	void login_Contrasena_Vacio() throws Exception {
		/*
		 * Implementación: PoC_FUN_004
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Contrasena_Vacio");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		takeScreenShotTest(getDriver(), "Login login_Contrasena_Vacio");
		
		WebElement usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usuario.sendKeys(Configuration.USER);
		takeScreenShotTest(getDriver(), "Login login_Contrasena_Vacio");
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		
		WebElement popup_Contrasena_Error = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Contraseña: Validation Error: Value is required.')]")));
		assertEquals("Contraseña: Validation Error: Value is required.", popup_Contrasena_Error.getText(), "Valida PopUp Contraseña Error");
		takeScreenShotTest(getDriver(), "Login login_Contrasena_Vacio");
		Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_004 Exitosa");	
	}
	
	@Test
	void login_Contrasena_No_Valida() throws Exception {
		/*
		 * Implementación: PoC_FUN_005
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Contrasena_No_Valida");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		takeScreenShotTest(getDriver(), "Login login_Contrasena_No_Valida");
		
		WebElement usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usuario.sendKeys(Configuration.USER);
		
		WebElement contraseña = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		contraseña.sendKeys(Configuration.PASS_NO_VALIDA);
		takeScreenShotTest(getDriver(), "Login login_Contrasena_No_Valida");

		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		
		takeScreenShotTest(getDriver(), "Login login_Contrasena_No_Valida");
		WebElement popup_Contrasena_Error = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Usuario y/o contraseña invalido')]")));
		assertEquals("Usuario y/o contraseña invalido", popup_Contrasena_Error.getText(), "Valida PopUp Contraseña Incorrecta");
		takeScreenShotTest(getDriver(), "Login login_Contrasena_No_Valida");
		Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_005 Exitosa");
	}
	
	@Test
	void login_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_006
		 */
		
		takeScreenShotTest(getDriver(), "Login login_Exitoso");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		
		WebElement usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usuario.sendKeys(Configuration.USER);
		
		WebElement contraseña = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		contraseña.sendKeys(Configuration.PASS);
		takeScreenShotTest(getDriver(), "Login login_Exitoso");
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		takeScreenShotTest(getDriver(), "Login login_Exitoso");
		Thread.sleep(5000);
		
		//Validación Acceso a página PORVENIR
		String tituloPaginaLogin = this.getDriver().getTitle();
		assertEquals("Porvenir", tituloPaginaLogin, "Valida título de página");
		takeScreenShotTest(getDriver(), "Login login_Exitoso");
		System.out.println("Prueba PoC_FUN_006 Exitosa");	
	}

}
