package automatizacionAspor.AUTOMATIZACIONASPOR;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;




class Digitacion_Poliza_Validacion_Datos_Test extends BaseClass{

	/*
	 * Scenario: Validar PRODUCCIÓN - VALIDACIÓN DATOS
	 *  - Validar que los datos ingresados de forma automática sean correctos (Presionar Botón VALIDAR).
	 *  	- EXITOSO
	 *  	- FLUJO ALTERNATIVO
	 *  		- DATOS INCORRECTOS - VALIDACIÓN FALLA.
	 *  		- FLUJO ALTERNATIVO - FECHAS VIGENCIA IGUALES
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DATOS INCORRECTOS
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS COBERTURAS - EXITOSO
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS COBERTURAS - DATOS INCORRECTOS
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS ITEMS - EXITOSO
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS ITEMS - DATOS INCORRECTOS
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS ITEMS DOS COBERTURAS - EXITOSO
	 *  		- FLUJO ALTERNATIVO - VALIDACIÓN DOS ITEMS DOS COBERTURAS - DATOS INCORRECTOS
	 */	
	
	public String numero_Poliza_global;

	
	@BeforeEach
	public void flujo_Previo() throws Exception {

		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("menu-item-32"))).click();
		
		WebElement usuario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usuario.sendKeys(Configuration.USER);
		WebElement contraseña = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		contraseña.sendKeys(Configuration.PASS);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();
		Thread.sleep(3000);	
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Recepción"))).click();
		Thread.sleep(3000);
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		Thread.sleep(2000);
		
		// Ingresar Datos y Presionar el botón GUARDAR
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		campo_Rut_Agente.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
	
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		Thread.sleep(2000);
		
		// Clic en Número de Póliza generado
		WebElement numero_Poliza = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("01-11-")));
		numero_Poliza_global = numero_Poliza.getText();
		numero_Poliza.click();
		Thread.sleep(3000);
		
		//Ingreso de Datos en la sección "DETALLE ITEM 1"
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		WebElement campo_Rut_Asegurado2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		campo_Rut_Asegurado2.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto.clear();
		campo_Monto.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		
		// Ingresar los datos en sección "PLAN DE PAGO"
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
	}
	
	
	@Test
	void validacion_Datos_Exitosos() throws Exception {
		/*
		 * Implementación: PoC_FUN_035
		 * 
		 * Validar que los datos ingresados de forma automática sean correctos Y (Presionar Botón VALIDAR)
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_Exitosos");
		
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_Exitosos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
	
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_EXITOSA, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals(Configuration.INFO_GLOSA_VALIDACION_EXITOSO, glosa_Conclusion.getText(), "Validación");
		
		WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
		assertEquals("Subir", Btn_SUBIR.getAttribute("value"), "Valida botón SUBIR");
		assertTrue(Btn_SUBIR.isEnabled());
		
		System.out.println("Prueba PoC_FUN_035 Exitosa");
	}
	
	
	@Test
	void alternativo_validacion_Datos_Incorrectos() throws Exception {
		/*
		 * Implementación: PoC_FUN_036
		 * 
		 * Validar que los datos ingresados de forma automática NO sean correctos Y (Presionar Botón VALIDAR).
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos alternativo_validacion_Datos_Incorrectos");
		
		// Ingresar los datos INCORRECTOS (VALORES NEGATIVOS EN COBERTURA) -> MODIFICACIÓN
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos alternativo_validacion_Datos_Incorrectos");
		
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos alternativo_validacion_Datos_Incorrectos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
	
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_1, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals("", glosa_Conclusion.getText(), "Validación");
		assertTrue(!glosa_Conclusion.isDisplayed());
		
		System.out.println("Prueba PoC_FUN_036 Exitosa");
		

		
		/*
		 * Implementación: PoC_FUN_037
		 * 
		 * Ingresar Fechas Inicio Vegencia y Término Vigencia iguales Y (Presionar Botón VALIDAR).
		 */


		// Fechas Inicio Vegencia y Término Vigencia iguales -> MODIFICACIÓN
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.clear();
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.clear();
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		takeScreenShotTest(getDriver(), "Validacion_Datos alternativo_validacion_Datos_Incorrectos");

		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		
		// Presionar directamente el botón VALIDAR
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error en Vigencia de item 1", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_037 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_COBERTURAS_Exitosos() throws Exception {
		/*
		 * Implementación: PoC_FUN_047
		 * 
		 * Validar los datos de la póliza con DOS COBERTURAS
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Exitosos");
		
		// Agregar SEGUNDA COBERTURA
		WebElement Btn_PLUS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("agregaCobertura_1_1")));
		Btn_PLUS.click();
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Exitosos");
		
		//Ingresar Datos en SEGUNDA COBERTURA
		Select select_Cobertura_2 = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_2")))); 
		select_Cobertura_2.selectByVisibleText(Configuration.SEGUNDA_COBERTURA);
		String coberturaSeleccionada = select_Cobertura_2.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SEGUNDA_COBERTURA, coberturaSeleccionada, "Verificando cobertura seleccionada");
		
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_2")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_2")));
		campo_Prima_Neta2_2.clear();
		campo_Prima_Neta2_2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Exitosos");
				
		
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Exitosos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
	
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_EXITOSA, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals(Configuration.INFO_GLOSA_VALIDACION_EXITOSO, glosa_Conclusion.getText(), "Validación");
		
		WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
		assertEquals("Subir", Btn_SUBIR.getAttribute("value"), "Valida botón SUBIR");
		assertTrue(Btn_SUBIR.isEnabled());
		
		System.out.println("Prueba PoC_FUN_047 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos() throws Exception {
		/*
		 * Implementación: PoC_FUN_048
		 * 
		 * Validar los datos de la póliza con DOS COBERTURAS con datos incorrectos.
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");
		
		// Agregar SEGUNDA COBERTURA
		WebElement Btn_PLUS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("agregaCobertura_1_1")));
		Btn_PLUS.click();
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");
		
		//Ingresar Datos (INCORRECTOS) en SEGUNDA COBERTURA
		Select select_Cobertura_2 = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_2")))); 
		select_Cobertura_2.selectByVisibleText(Configuration.SEGUNDA_COBERTURA);
		String coberturaSeleccionada = select_Cobertura_2.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SEGUNDA_COBERTURA, coberturaSeleccionada, "Verificando cobertura seleccionada");
		
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_2")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_2")));
		campo_Prima_Neta2_2.clear();
		campo_Prima_Neta2_2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");
				
		
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
 		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
 		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
 		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");
 		
 		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
 		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
 	
 		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
 		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_1, detalle_Validacion.getText(), "Validación");
 		
 		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
 		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
 		
 		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
 		assertEquals("", glosa_Conclusion.getText(), "Validación");
 		assertTrue(!glosa_Conclusion.isDisplayed());
		
		System.out.println("Prueba PoC_FUN_048 Exitosa");
		
		
		/*
		 * Implementación: PoC_FUN_049
		 * 
		 * Validar los datos de la póliza con DOS COBERTURAS IGUALES (Repetidas)
		 */
		
		//Ingresar Datos (INCORRECTOS) en SEGUNDA COBERTURA -> MODIFICACIÓN
		select_Cobertura_2.selectByVisibleText(Configuration.PRIMERA_COBERTURA);
		String coberturaSeleccionada2 = select_Cobertura_2.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.PRIMERA_COBERTURA, coberturaSeleccionada2, "Verificando cobertura seleccionada");
		
		campo_Prima_Neta2_2.clear();
		campo_Prima_Neta2_2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");		
		
		// Presionar directamente el botón VALIDAR
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
 		WebElement textoSeccion_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
 		assertEquals("VALIDACION", textoSeccion_Validacion_2.getText(), "Valida texto Sección");
 		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_COBERTURAS_Datos_Incorrectos");
 		
 		WebElement texto_Inicio_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
 		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion_2.getText().substring(0, 15), "Validación");
 	
 		WebElement detalle_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
 		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_DOS_COBERTURAS_IGUALES, detalle_Validacion_2.getText(), "Validación");
 		
 		WebElement texto_Final_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
 		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion_2.getText().substring(0, 14), "Validación");
 		
 		WebElement glosa_Conclusion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
 		assertEquals("", glosa_Conclusion_2.getText(), "Validación");
 		assertTrue(!glosa_Conclusion_2.isDisplayed());
		
		System.out.println("Prueba PoC_FUN_049 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_ITEMS_Exitosos() throws Exception {
		/*
		 * Implementación: PoC_FUN_044
		 * 
		 * Validar los datos de la póliza con DOS ÍTEMS - EXITOSO
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		// Agregar Segundo ÍTEM
		WebElement campo_Cant_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numeroItemes")));
		campo_Cant_Items.clear();
		campo_Cant_Items.sendKeys("2");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		Thread.sleep(3000);
		
		//Ingreso de Datos en la sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS_1.click();
		WebElement campo_Rut_Asegurado_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		campo_Rut_Asegurado_1.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto_1.clear();
		campo_Monto_1.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta_1.clear();
		campo_Prima_Neta_1.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		//Ingreso de Datos en la sección "DETALLE ITEM 2"
		WebElement Btn_COPIAR_VIGENCIAS_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_2")));
		Btn_COPIAR_VIGENCIAS_2.click();
		WebElement campo_Rut_Asegurado_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_2")));
		campo_Rut_Asegurado_2.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 2"
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_2_1")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_2_1")));
		campo_Prima_Neta_2.clear();
		campo_Prima_Neta_2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
						
		
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Exitosos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
	
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_EXITOSA, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals(Configuration.INFO_GLOSA_VALIDACION_EXITOSO, glosa_Conclusion.getText(), "Validación");
		
		WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
		assertEquals("Subir", Btn_SUBIR.getAttribute("value"), "Valida botón RECHAZAR");
		assertTrue(Btn_SUBIR.isEnabled());
		
		System.out.println("Prueba PoC_FUN_044 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_ITEMS_Datos_Incorrectos() throws Exception {
		/*
		 * Implementación: PoC_FUN_045
		 * 
		 * Validar los datos de la póliza con DOS ÍTEMS - DATOS INCORRECTOS (ITEM 1)
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		// Agregar Segundo ÍTEM
		WebElement campo_Cant_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numeroItemes")));
		campo_Cant_Items.clear();
		campo_Cant_Items.sendKeys("2");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		Thread.sleep(3000);
		
		//Ingreso de Datos en la sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS_1.click();
		WebElement campo_Rut_Asegurado_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		campo_Rut_Asegurado_1.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto_1.clear();
		campo_Monto_1.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta_1.clear();
		campo_Prima_Neta_1.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		//Ingreso de Datos en la sección "DETALLE ITEM 2"
		WebElement Btn_COPIAR_VIGENCIAS_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_2")));
		Btn_COPIAR_VIGENCIAS_2.click();
		WebElement campo_Rut_Asegurado_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_2")));
		campo_Rut_Asegurado_2.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 2"
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_2_1")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_2_1")));
		campo_Prima_Neta_2.clear();
		campo_Prima_Neta_2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
						
		
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
		
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_1, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals("", glosa_Conclusion.getText(), "Validación");
		assertTrue(!glosa_Conclusion.isDisplayed());
		
		System.out.println("Prueba PoC_FUN_045 Exitosa");
		
		
		/*
		 * Implementación: PoC_FUN_046
		 * 
		 * Validar los datos de la póliza con DOS ÍTEMS - DATOS INCORRECTOS (ITEM 2)
		 */
		
		//Ingreso de Datos en la sección "COBERTURAS ITEM 1" -> MODIFICACIÓN
		campo_Prima_Neta_1.clear();
		campo_Prima_Neta_1.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		//Ingreso de Datos en la sección "COBERTURAS ITEM 2" -> MODIFICACIÓN
		campo_Prima_Neta_2.clear();
		campo_Prima_Neta_2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
						
		
		// Presionar directamente el botón VALIDAR
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion_2.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_Datos_Incorrectos");
		
		WebElement texto_Inicio_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion_2.getText().substring(0, 15), "Validación");
		
		WebElement detalle_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_1, detalle_Validacion_2.getText(), "Validación");
		
		WebElement texto_Final_Validacion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion_2.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals("", glosa_Conclusion_2.getText(), "Validación");
		assertTrue(!glosa_Conclusion_2.isDisplayed());
		
		System.out.println("Prueba PoC_FUN_046 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_050
		 * 
		 * Validar los datos de la póliza con DOS ÍTEMS y DOS COBERTURAS - EXITOSO
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		// Agregar Segundo ÍTEM
		WebElement campo_Cant_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numeroItemes")));
		campo_Cant_Items.clear();
		campo_Cant_Items.sendKeys("2");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		Thread.sleep(3000);
		
		//Ingreso de Datos en la sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS_1.click();
		WebElement campo_Rut_Asegurado_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		campo_Rut_Asegurado_1.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto_1.clear();
		campo_Monto_1.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta_1.clear();
		campo_Prima_Neta_1.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		// Agregar SEGUNDA COBERTURA
		WebElement Btn_PLUS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("agregaCobertura_1_1")));
		Btn_PLUS.click();
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		//Ingresar Datos en SEGUNDA COBERTURA
		Select select_Cobertura_2 = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_2")))); 
		select_Cobertura_2.selectByVisibleText(Configuration.SEGUNDA_COBERTURA);
		String coberturaSeleccionada = select_Cobertura_2.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SEGUNDA_COBERTURA, coberturaSeleccionada, "Verificando cobertura sdeleccionada");
		
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_2")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_2")));
		campo_Prima_Neta2_2.clear();
		campo_Prima_Neta2_2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		//Ingreso de Datos en la sección "DETALLE ITEM 2"
		WebElement Btn_COPIAR_VIGENCIAS_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_2")));
		Btn_COPIAR_VIGENCIAS_2.click();
		WebElement campo_Rut_Asegurado_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_2")));
		campo_Rut_Asegurado_2.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 2"
		WebElement campo_Monto_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_2_1")));
		campo_Monto_3.clear();
		campo_Monto_3.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_2_1")));
		campo_Prima_Neta_3.clear();
		campo_Prima_Neta_3.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
						
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
		WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Exitoso");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
	
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_EXITOSA, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals(Configuration.INFO_GLOSA_VALIDACION_EXITOSO, glosa_Conclusion.getText(), "Validación");
		
		WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
		assertEquals("Subir", Btn_SUBIR.getAttribute("value"), "Valida botón RECHAZAR");
		assertTrue(Btn_SUBIR.isEnabled());
		
		System.out.println("Prueba PoC_FUN_050 Exitosa");
	}
	
	
	@Test
	void validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos() throws Exception {
		/*
		 * Implementación: PoC_FUN_050
		 * 
		 * Validar que los datos ingresados de forma automática NO sean correctos.
		 */
		
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		// Agregar Segundo ÍTEM
		WebElement campo_Cant_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numeroItemes")));
		campo_Cant_Items.clear();
		campo_Cant_Items.sendKeys("2");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		Thread.sleep(3000);
		
		//Ingreso de Datos en la sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS_1.click();
		WebElement campo_Rut_Asegurado_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		campo_Rut_Asegurado_1.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto_1.clear();
		campo_Monto_1.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta_1.clear();
		campo_Prima_Neta_1.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		// Agregar SEGUNDA COBERTURA
		WebElement Btn_PLUS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("agregaCobertura_1_1")));
		Btn_PLUS.click();
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		//Ingresar Datos en SEGUNDA COBERTURA
		Select select_Cobertura_2 = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_2")))); 
		select_Cobertura_2.selectByVisibleText(Configuration.SEGUNDA_COBERTURA);
		String coberturaSeleccionada = select_Cobertura_2.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SEGUNDA_COBERTURA, coberturaSeleccionada, "Verificando cobertura sdeleccionada");
		
		WebElement campo_Monto_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_2")));
		campo_Monto_2.clear();
		campo_Monto_2.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_2")));
		campo_Prima_Neta2_2.clear();
		campo_Prima_Neta2_2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		//Ingreso de Datos en la sección "DETALLE ITEM 2"
		WebElement Btn_COPIAR_VIGENCIAS_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_2")));
		Btn_COPIAR_VIGENCIAS_2.click();
		WebElement campo_Rut_Asegurado_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_2")));
		campo_Rut_Asegurado_2.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 2"
		WebElement campo_Monto_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_2_1")));
		campo_Monto_3.clear();
		campo_Monto_3.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_2_1")));
		campo_Prima_Neta_3.clear();
		campo_Prima_Neta_3.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
						
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Verificar Despliegue de sección "VALIDACION"
	    WebElement textoSeccion_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//fieldset/legend[contains(text(), 'VALIDACION')]")));
		assertEquals("VALIDACION", textoSeccion_Validacion.getText(), "Valida texto Sección");
		takeScreenShotTest(getDriver(), "Validacion_Datos validacion_Datos_DOS_ITEMS_DOS_COBERTURAS_Datos_Incorrectos");
		
		WebElement texto_Inicio_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInicio")));
		
		WebElement detalle_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valInfo")));
		assertEquals(Configuration.INFO_VALIDACION_NO_EXITOSA_1, detalle_Validacion.getText(), "Validación");
		
		WebElement texto_Final_Validacion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valFinal")));
		assertEquals(Configuration.TEXTO_FINAL_VALIDACION, texto_Final_Validacion.getText().substring(0, 14), "Validación");
		
		WebElement glosa_Conclusion = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("valConclusion")));
		assertEquals("", glosa_Conclusion.getText(), "Validación");
		assertTrue(!glosa_Conclusion.isDisplayed());
		assertEquals(Configuration.TEXTO_INICIO_VALIDACION, texto_Inicio_Validacion.getText().substring(0, 15), "Validación");
		
		System.out.println("Prueba PoC_FUN_051 Exitosa");
	}
}
