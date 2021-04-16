package automatizacionAspor.AUTOMATIZACIONASPOR;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



class Digitacion_Poliza_Plan_Pagos_Test extends BaseClass{

	/*
	 * Scenario: Validar PRODUCCIÓN - DATOS DE LA PÓLIZA
	 *  - Despliegue página "Digitación de Póliza".
	 *  - Ingresar los datos datos básicos de la póliza.
	 *  	- EXITOSO
	 *  	- FLUJO ALTERNATIVO
	 *  		- Sin datos (todos los campos vacíos), a excepción de los pre-cargados.
	 *  		- Datos Incorrectos - Crear Plan
	 *  		- Datos Incorrectos - Fecha Primer Vencimiento 
	 *  		- Datos Incorrectos - Fecha Primer Vencimiento Anterior a Vigencias Y Vigencias Correctos
	 *  		- Datos Incorrectos - Valores Negativos
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
	}
	
	
	@Test
	void alternativo_Sin_Datos_Crear_Plan_Y_Guardar() throws Exception {
		/*
		 * Implementación: PoC_FUN_025
		 * 
		 * NO ingresar los datos básicos correspondientes al plan de pagos Y Presionar Botón CREAR PLAN DE PAGO.
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Sin_Datos_Crear_Plan_Y_Guardar");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
				
		// Aceptar alertas
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("RUT malo!", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	    
	    String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Datos incompletos (contratante, cuotas, fechas y/o prima)", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_025 Exitosa");	
		
		
		
		/*
		 * Implementación: PoC_FUN_026
		 * 
		 * NO ingresar los datos básicos correspondientes al plan de pagos Y Presionar Botón GUARDAR
		 */

		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Sin_Datos_Crear_Plan_Y_Guardar");
		
		// Presionar directamente el botón GUARDAR
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		Btn_GUARDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta3 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta3, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_026 Exitosa");
	}
	
	
	@Test
	void alternativo_Datos_Incorrectos_Crear_Plan() throws Exception {
		/*
		 * Implementación: PoC_FUN_027
		 * 
		 * Ingresar los datos INCORRECTOS (RUT) correspondientes al plan de pagos Y Presionar Botón CREAR PLAN DE PAGO
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Crear_Plan");
		
		// Ingresar los datos INCORRECTOS (RUT)
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_INCORRECTO);
		campo_Primer_Vencimiento.click();
		
		// Aceptar alerta validación de RUT
		Thread.sleep(3000);
		
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error en rut", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		
		// Aceptar alerta
	    String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Datos incompletos (contratante, cuotas, fechas y/o prima)", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_027 Exitosa");	
	}
	
	@Test
	void alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento() throws Exception {
		/*
		 * Implementación: PoC_FUN_028
		 * 
		 * Ingresar los datos INCORRECTOS (1er Vencimiento al día) correspondientes al plan de pagos
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento");
		
		// Ingresar los datos INCORRECTOS (1er Vencimiento al día)
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO_INCORRECTA);
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta3 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Día de pago debe ser 5, 15 o 25", mensaje_Alerta3, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_028 Exitosa");
	}
	
	
	@Test
	void alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas() throws Exception {
		/*
		 * Implementación: PoC_FUN_029
		 * 
		 * Ingresar los datos INCORRECTOS (FECHA 1er Vencimiento al día ANTERIOR A VIGENCIA) Y Presionar Botón CREAR PLAN DE PAGO
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas");
		
		// Ingresar los datos INCORRECTOS (FECHA 1er Vencimiento al día ANTERIOR A VIGENCIA)
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO_ANTERIOR);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta3 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error al crear plan de pago", mensaje_Alerta3, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_029 Exitosa");
		
		
		/*
		 * Implementación: PoC_FUN_033
		 * 
		 * Ingresar los datos INCORRECTOS (FECHA INICIO VIGENCIA > FECHA TÉRMINO VIGENCIA) Y (Presionar Botón CREAR PLAN DE PAGO)
		 */
		
		
		// Ingresar los datos INCORRECTOS (FECHA INICIO VIGENCIA > FECHA TÉRMINO VIGENCIA) -> MODIFICACIÓN
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.clear();
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.clear();
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas");
		
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas");
		
		// Ingresar los datos CORRECTOS
		campo_Primer_Vencimiento.clear();
		Thread.sleep(3000);
		// Aceptar alerta
		String mensaje_Alerta4 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Día de pago debe ser 5, 15 o 25", mensaje_Alerta4, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
		
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Fecha_Primer_Vencimiento_Anterior_Y_Vigencias_Incorrectas");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta5 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error al crear plan de pago", mensaje_Alerta5, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_033 Exitosa");
		
		
		/*
		 * Implementación: PoC_FUN_034
		 * 
		 * Ingresar los datos INCORRECTOS (FECHA INICIO VIGENCIA > FECHA TÉRMINO VIGENCIA) Y (Presionar Botón GUARDAR)
		 */
		
		
		// Presionar directamente el botón GUARDAR
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		Btn_GUARDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta6 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error en Vigencia de Póliza", mensaje_Alerta6, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_034 Exitosa");
	}
	
	
	@Test
	void alternativo_Datos_Incorrectos_Valores_Negativos() throws Exception {
		/*
		 * Implementación: PoC_FUN_030
		 * 
		 * Ingresar los datos INCORRECTOS (VALORES NEGATIVOS EN COBERTURA) Y (Presionar Botón CREAR PLAN DE PAGO)
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Valores_Negativos");
		
		// Ingresar los datos INCORRECTOS (VALORES NEGATIVOS EN COBERTURA) -> MODIFICACIÓN
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto.clear();
		campo_Monto.sendKeys(Configuration.MONTO_NEGATIVO);
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Valores_Negativos");
		
		// Ingresar los datos CORRECTOS
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Valores_Negativos");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta3 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Datos incompletos (contratante, cuotas, fechas y/o prima)", mensaje_Alerta3, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_030 Exitosa");
		
		
		
		/*
		 * Implementación: PoC_FUN_031
		 * 
		 * Ingresar los datos INCORRECTOS (NÚMERO DE CUOTA NEGATIVO) Y (Presionar Botón CREAR PLAN DE PAGO)
		 */
		
		//  (VALORES NEGATIVOS EN COBERTURA) -> MODIFICACIÓN
		campo_Monto.clear();
		campo_Monto.sendKeys(Configuration.MONTO);
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Valores_Negativos");
		
		// Ingresar los datos INCORRECTOS (NÚMERO DE CUOTA NEGATIVO)
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS_NEGATIVO);
		takeScreenShotTest(getDriver(), "Plan_Pagos alternativo_Datos_Incorrectos_Valores_Negativos");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta4 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Datos incompletos (contratante, cuotas, fechas y/o prima)", mensaje_Alerta4, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	
		System.out.println("Prueba PoC_FUN_031 Exitosa");	
	}
	
	
	@Test
	void plan_Pagos_Exitosos() throws Exception {
		/*
		 * Implementación: PoC_FUN_024
		 * 
		 * Ingresar los datos básicos correspondientes al plan de pagos Y (Presionar Botón CREAR PLAN DE PAGO)
		 */
		
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
		
		// Ingresar los datos CORRECTOS (FECHA 1er Vencimiento al día ANTERIOR A VIGENCIA)
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
		
		// Verificar Despliegue de cuotas
		WebElement cuota1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 1')]")));
		assertEquals("Cuota 1", cuota1.getText(), "Valida datos");
		WebElement cuota2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 2')]")));
		assertEquals("Cuota 2", cuota2.getText(), "Valida datos");
		WebElement cuota3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 3')]")));
		assertEquals("Cuota 3", cuota3.getText(), "Valida datos");
		WebElement vencimiento1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-01-2021')]")));
		assertEquals("25-01-2021", vencimiento1.getText(), "Valida datos");
		WebElement vencimiento2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-02-2021')]")));
		assertEquals("25-02-2021", vencimiento2.getText(), "Valida datos");
		WebElement vencimiento3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-03-2021')]")));
		assertEquals("25-03-2021", vencimiento3.getText(), "Valida datos");
		WebElement valor1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '1.2115')]")));
		assertEquals("1.2115", valor1.getText(), "Valida datos");
		WebElement valor2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-02-2021')]/following-sibling::td")));
		assertEquals("1.2115", valor2.getText(), "Valida datos");
		WebElement valor3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-03-2021')]/following-sibling::td")));
		assertEquals("1.2115", valor3.getText(), "Valida datos");
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
	
		System.out.println("Prueba PoC_FUN_024 Exitosa");
		
		
		
		/*
		 * Implementación: PoC_FUN_032
		 * 
		 * Ingresar los datos básicos correspondientes al plan de pagos (NÚMERO DE CUOTAS ALTO) Y (Presionar Botón CREAR PLAN DE PAGO)
		 */
		
		//  (NÚMERO DE CUOTA NEGATIVO) -> MODIFICACIÓN
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS_ALTO);
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
		
		// Presionar directamente el botón CREAR PLAN PAGO
		Btn_CREAR_PLAN_PAGO.click();
		Thread.sleep(4000);
		takeScreenShotTest(getDriver(), "Plan_Pagos plan_Pagos_Exitosos");
		
		// Verificar Despliegue de cuotas

		WebElement cuota2_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 1')]")));
		assertEquals("Cuota 1", cuota2_1.getText(), "Valida datos");
		WebElement cuota2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 2')]")));
		assertEquals("Cuota 2", cuota2_2.getText(), "Valida datos");
		WebElement cuota2_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 3')]")));
		assertEquals("Cuota 3", cuota2_3.getText(), "Valida datos");
		WebElement cuota2_4 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Cuota 4')]")));
		assertEquals("Cuota 4", cuota2_4.getText(), "Valida datos");
		
		WebElement vencimiento2_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-01-2021')]")));
		assertEquals("25-01-2021", vencimiento2_1.getText(), "Valida datos");
		WebElement vencimiento2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-02-2021')]")));
		assertEquals("25-02-2021", vencimiento2_2.getText(), "Valida datos");
		WebElement vencimiento2_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-03-2021')]")));
		assertEquals("25-03-2021", vencimiento2_3.getText(), "Valida datos");
		WebElement vencimiento2_4 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-04-2021')]")));
		assertEquals("25-04-2021", vencimiento2_4.getText(), "Valida datos");
		
		WebElement valor2_1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-01-2021')]/following-sibling::td")));
		assertEquals("0.9127", valor2_1.getText(), "Valida datos");
		WebElement valor2_2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-02-2021')]/following-sibling::td")));
		assertEquals("0.9127", valor2_2.getText(), "Valida datos");
		WebElement valor2_3 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-03-2021')]/following-sibling::td")));
		assertEquals("0.9127", valor2_3.getText(), "Valida datos");
		WebElement valor2_4 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '25-04-2021')]/following-sibling::td")));
		assertEquals("0.9127", valor2_4.getText(), "Valida datos");
		
		System.out.println("Prueba PoC_FUN_032 Exitosa");
	}

}
