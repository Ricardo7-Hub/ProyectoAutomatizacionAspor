package automatizacionAspor.AUTOMATIZACIONASPOR;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



class Produccion_Recepcion_Test extends BaseClass{
	
	/*
	 * Scenario: Validar PRODUCCIÓN - RECEPCIÓN
	 *  - Despliegue página "Recepción".
	 *  - Despliegue página "Recepción", opción Póliza.
	 *  - Ingresar los datos necesarios para la generación de un número de póliza.
	 *  	- Sin datos (todos los campos vacíos).
	 *  	- Datos Obligatorios Incompletos.
	 *  	- Datos Obligatorios Incorrectos (NO VÁLIDOS)
	 *  	- EXITOSO
	 */	

	
	@BeforeEach
	public void login() throws Exception {

		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		
		WebElement usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usuario.sendKeys(Configuration.USER);
		
		WebElement contraseña = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		contraseña.sendKeys(Configuration.PASS);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		Thread.sleep(3000);	
	}
	
	//@Test
	void despliegue_Recepcion() throws Exception {
		/*
		 * Implementación: PoC_FUN_007
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion despliegue_Recepcion");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		Thread.sleep(3000);
		
		String tituloPagina = this.getDriver().getTitle();
		//assertEquals("Recepción", tituloPagina, "Valida título de página");
		assertEquals("Recepcion", tituloPagina, "Valida título de página");
		
		WebElement texto_Recepcion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Recepción de:')]")));
		assertEquals("Recepción de:", texto_Recepcion.getText(), "Valida texto campo Recepción de");
		WebElement check_Poliza = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza")));
		check_Poliza.isEnabled();
		WebElement check_Renovacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esRenovacion")));
		check_Renovacion.isEnabled();
		WebElement check_Endoso = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esEndoso")));
		check_Endoso.isEnabled();
	
		WebElement texto_Moneda = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Moneda:')]")));
		assertEquals("Moneda:", texto_Moneda.getText(), "Valida texto campo Moneda");
		Select select_Moneda = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("moneda")))); 
		String monedaSeleccionado = select_Moneda.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.MONEDA_UF, monedaSeleccionado, "Verificando Moneda seleccionada");
		// Validación Lista
		WebElement lista_Moneda = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("moneda"))); 
		assertEquals(Configuration.LISTA_MONEDA, lista_Moneda.getText(), "Verificando Lista");
		
		WebElement texto_Ramo = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Ramo:')]")));
		assertEquals("Ramo:", texto_Ramo.getText(), "Valida texto campo Ramo");
		Select select_Ramo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ramo")))); 
		String ramoSeleccionado = select_Ramo.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.RAMO_ACCIDENTES_PERSONALES, ramoSeleccionado, "Verificando Ramo seleccionado");
		// Validación Lista
		WebElement lista_Ramo = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ramo"))); 
		assertEquals(Configuration.LISTA_RAMO, lista_Ramo.getText(), "Verificando Lista");

		takeScreenShotTest(getDriver(), "Produccion_Recepcion despliegue_Recepcion");
		System.out.println("Prueba PoC_FUN_007 Exitosa");	
	}
	
	//@Test
	void despliegue_Recepcion_Poliza() throws Exception {
		/*
		 * Implementación: PoC_FUN_008
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion despliegue_Recepcion_Poliza");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion despliegue_Recepcion_Poliza");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion despliegue_Recepcion_Poliza");
		Thread.sleep(3000);
		
		String tituloPagina = this.getDriver().getTitle();
		//assertEquals("Recepción", tituloPagina, "Valida título de página");
		assertEquals("Recepcion", tituloPagina, "Valida título de página");
		
		//Despliegue de nuevos campos
		WebElement texto_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Rut Asegurado:')]")));
		assertEquals("Rut Asegurado:", texto_Rut_Asegurado.getText(), "Valida texto campo Rut Asegurado");
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		assertTrue(campo_Rut_Asegurado.isEnabled());
		WebElement campo_Nombre_Asegurado2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("nombreAsegurado")));
		assertTrue(campo_Nombre_Asegurado2.isEnabled());
		
		WebElement texto_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Rut Agente:')]")));
		assertEquals("Rut Agente:", texto_Rut_Agente.getText(), "Valida texto campo Rut Agente");
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		assertTrue(campo_Rut_Agente.isEnabled());
		WebElement campo_Nombre_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("nombreAgente")));
		assertTrue(campo_Nombre_Agente.isEnabled());
		
		WebElement texto_Sucursal = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Sucursal:')]")));
		assertEquals("Sucursal:", texto_Sucursal.getText(), "Valida texto campo Sucursal");
		Select select_Sucursal = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sucursalBase")))); 
		String sucursalSeleccionado = select_Sucursal.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SUCURSAL, sucursalSeleccionado, "Verificando Ramo seleccionado");
		// Validación Lista
		WebElement lista_Sucursal = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sucursalBase")));
		assertEquals(Configuration.LISTA_SUCURSALES, lista_Sucursal.getText(), "Verificando Lista");
		
		WebElement texto_Propuesta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Propuesta:')]")));
		assertEquals("Propuesta:", texto_Propuesta.getText(), "Valida texto campo Propuesta");
		WebElement campo_Propuesta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ppta")));
		assertTrue(campo_Propuesta.isEnabled());
		
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		assertEquals("Guardar", Btn_GUARDAR.getAttribute("value"), "Valida botón GUARDAR");
		assertTrue(Btn_GUARDAR.isEnabled());
		
		WebElement Btn_VOLVER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonVolver")));
		assertEquals("Volver", Btn_VOLVER.getAttribute("value"), "Valida botón VOLVER");
		assertTrue(Btn_VOLVER.isEnabled());
		
		WebElement Btn_OTRO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("otro")));
		assertEquals("Otro", Btn_OTRO.getAttribute("value"), "Valida botón OTRO");
		assertTrue(Btn_OTRO.isEnabled());
		
		System.out.println("Prueba PoC_FUN_008 Exitosa");	
	}
	
	//@Test
	void ingresar_Recepcion_Poliza_Sin_Datos() throws Exception {
		/*
		 * Implementación: PoC_FUN_009
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Sin_Datos");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Sin_Datos");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Sin_Datos");
		Thread.sleep(3000);
		
		// Presionar directamente el botón GUARDAR
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		
		// Aceptar alerta
	    String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Formulario Incompleto", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Sin_Datos");
	    Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_009 Exitosa");	
	}
	
	//@Test
	void ingresar_Recepcion_Poliza_Datos_Obl_Incompletos() throws Exception {
		/*
		 * Implementación: PoC_FUN_010
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incompletos");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incompletos");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incompletos");
		Thread.sleep(2000);
		
		// Ingresar RUT ASEGURADO y Presionar el botón GUARDAR
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_ASEGURADO);
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incompletos");
		Thread.sleep(2000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		Thread.sleep(2000);
		
		// Aceptar alerta
	    String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Formulario Incompleto", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_010 Exitosa");	
	}
	
	//@Test
	void ingresar_Recepcion_Poliza_Datos_Obl_Incorrectos() throws Exception {
		/*
		 * Implementación: PoC_FUN_011
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incorrectos");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incorrectos");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incorrectos");
		Thread.sleep(2000);
		
		// Ingresar RUT ASEGURADO (INCORRECTO) y Presionar el botón GUARDAR
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_INCORRECTO);
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Datos_Obl_Incorrectos");
		Thread.sleep(2000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		Thread.sleep(2000);
		
		
		// Aceptar alerta
	    String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Rut Incorrecto!", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(2000);
		
		System.out.println("Prueba PoC_FUN_011 Exitosa");	
	}
	
	@Test
	void ingresar_Recepcion_Poliza_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_012
		 */
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Exitoso");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Exitoso");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Exitoso");
		Thread.sleep(2000);
		
		// Ingresar Datos y Presionar el botón GUARDAR
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		campo_Rut_Agente.sendKeys(Configuration.RUT_ASEGURADO);
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Exitoso");
		Thread.sleep(2000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion ingresar_Recepcion_Poliza_Exitoso");
		Thread.sleep(2000);
		
		// Validación de Número de Póliza generado
		String numero_Poliza = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("01-11-"))).getText();
		String glosa_Numero_Poliza = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("resultadoRececpcion"))).getText();
		assertEquals("El nuevo número de póliza es : "+numero_Poliza, glosa_Numero_Poliza, "Valida Glosa con el Número de la Póliza");
		
		System.out.println("Prueba PoC_FUN_012 Exitosa");	
	}
	
	//@Test
	void clic_Hip_Poliza_Generada() throws Exception {
		/*
		 * Implementación: PoC_FUN_013
		 */
		
		takeScreenShotTest(getDriver(), "Produccion_Recepcion clic_Hip_Poliza_Generada");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion clic_Hip_Poliza_Generada");
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion clic_Hip_Poliza_Generada");
		Thread.sleep(2000);
		
		// Ingresar Datos y Presionar el botón GUARDAR
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		campo_Rut_Agente.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		takeScreenShotTest(getDriver(), "Produccion_Recepcion clic_Hip_Poliza_Generada");
		Thread.sleep(2000);
		
		// Clic en Número de Póliza generado
		WebElement numero_Poliza = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("01-11-")));
		numero_Poliza.click();
		
		//Validación despliegue de la página Digitación de Póliza
		String tituloPagina_DigitacionPoliza = this.getDriver().getTitle();
		assertEquals("Digitación De Póliza", tituloPagina_DigitacionPoliza, "Valida título de página");
		takeScreenShotTest(getDriver(), "Produccion_Recepcion clic_Hip_Poliza_Generada");
		Thread.sleep(2000);
		
		System.out.println("Prueba PoC_FUN_013 Exitosa");
	}
	

}