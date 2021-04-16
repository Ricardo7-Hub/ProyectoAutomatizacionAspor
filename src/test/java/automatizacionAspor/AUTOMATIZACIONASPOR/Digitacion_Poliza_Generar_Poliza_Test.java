package automatizacionAspor.AUTOMATIZACIONASPOR;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


class Digitacion_Poliza_Generar_Poliza_Test extends BaseClass{

	/*
	 * Scenario: Validar PRODUCCIÓN - GENERAR PÓLIZA
	 *  - Autorizar la emisión de la póliza (Presionar Botón APROBAR) - EXITOSO
	 *  - Intentar autorizar la emisión de la póliza (Presionar Botón APROBAR) - Prima Neta negativo
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
		Thread.sleep(3000);
		
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
	void generar_Poliza_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_038
		 * 
		 * Autorizar la emisión de la póliza Y (Presionar Botón APROBAR)
		 */
		
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
	    WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
	    Btn_SUBIR.click();
	    takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
	    Thread.sleep(3000);
	    
	    // Presionar directamente el botón APROBAR
	    WebElement Btn_APROBAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("aprobar")));
	    Btn_APROBAR.click();
	    takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
	    Thread.sleep(8000);
	    
	    // Aceptar alerta
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Póliza Activa", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(8000);
	    
	    // Validar despliegue de página con PDF
	    String mainWin = this.getDriver().getWindowHandle();
	    
	    for(String handle: this.getDriver().getWindowHandles()){
			if(!handle.equals(mainWin)){
				//cambiar de ventana
				this.getDriver().switchTo().window(handle);
				takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
			}
		}
	    
	    String tituloPagina_PDF = this.getDriver().getTitle();
		assertEquals("Aspor Intranet", tituloPagina_PDF, "Valida título de página");
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_Exitoso");
		
		System.out.println("Prueba PoC_FUN_038 Exitosa");
	}
	

	@Test
	void generar_Poliza_No_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_039
		 * 
		 * Intentar autorizar la emisión de la póliza (Prima Neta negativo) Y (Presionar Botón APROBAR)
		 */
		
		// Ingresar los datos INCORRECTOS (VALORES NEGATIVOS EN COBERTURA) -> MODIFICACIÓN
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_No_Exitoso");
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_No_Exitoso");
		Thread.sleep(3000);
		
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_No_Exitoso");
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
		
	    WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
	    Btn_SUBIR.click();
	    Thread.sleep(3000);
	    
	    // Presionar directamente el botón APROBAR
	    WebElement Btn_APROBAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("aprobar")));
	    Btn_APROBAR.click();
	    Thread.sleep(8000);
	    
	    // Aceptar alerta
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Póliza NO fue Aprobada", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    takeScreenShotTest(getDriver(), "Generar_Poliza generar_Poliza_No_Exitoso");
	    
		System.out.println("Prueba PoC_FUN_039 Exitosa");
	}

}