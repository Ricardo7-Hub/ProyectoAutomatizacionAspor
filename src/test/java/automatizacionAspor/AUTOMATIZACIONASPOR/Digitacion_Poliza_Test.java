package automatizacionAspor.AUTOMATIZACIONASPOR;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;




class Digitacion_Poliza_Test extends BaseClass{

	/*
	 * Scenario: Validar PRODUCCIÓN - DATOS DE LA PÓLIZA
	 *  - Despliegue página "Digitación de Póliza".
	 *  - Ingresar los datos datos básicos de la póliza.
	 *  	- EXITOSO
	 *  	- FLUJO ALTERNATIVO
	 *  		- Sin datos (todos los campos vacíos), a excepción de los pre-cargados.
	 *  		- Ingresar Fechas Inicio Vegencia y Término Vigencia, presionar Botón VER y luego presionar Botón GUARDAR.
	 *  		- 
	 *  		- 
	 *  		- 
	 */	
	
	public String numero_Poliza_global;

	
	@BeforeEach
	public void generacion_Numero_Poliza() throws Exception {

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
	}
	
	@Test
	void despliegue_Digitacion_Poliza() throws Exception {
		/*
		 * Implementación: PoC_FUN_014
		 */
		
		String tituloPagina_DigitacionPoliza = this.getDriver().getTitle();
		assertEquals("Digitación De Póliza", tituloPagina_DigitacionPoliza, "Valida título de página");
		
		// Sección POLIZA
		WebElement textoSeccion_Poliza = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'POLIZA')]")));
		assertEquals("POLIZA", textoSeccion_Poliza.getText(), "Valida texto Sección");
		
		WebElement campo_Moneda = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("moneda")));
		assertTrue(campo_Moneda.isEnabled());
		assertEquals("01", campo_Moneda.getAttribute("value"), "Valida texto campo Moneda");
		WebElement campo_Ramo = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ramo")));
		assertTrue(campo_Ramo.isEnabled());
		assertEquals("11", campo_Ramo.getAttribute("value"), "Valida texto campo Ramo");
		WebElement campo_Poliza = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("poliza")));
		assertTrue(campo_Poliza.isEnabled());
		assertEquals(numero_Poliza_global.substring(6,12), campo_Poliza.getAttribute("value"), "Valida texto campo Póliza");
		WebElement Btn_EDITAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("editar")));
		assertEquals("Editar", Btn_EDITAR.getAttribute("value"), "Valida botón EDITAR");
		assertTrue(Btn_EDITAR.isEnabled());
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		assertEquals("Guardar", Btn_GUARDAR.getAttribute("value"), "Valida botón GUARDAR");
		assertTrue(Btn_GUARDAR.isEnabled());
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		assertEquals("Validar", Btn_VALIDAR.getAttribute("value"), "Valida botón VALIDAR");
		assertTrue(Btn_VALIDAR.isEnabled());
		WebElement Btn_APROBAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("aprobar")));
		assertEquals("Aprobar", Btn_APROBAR.getAttribute("value"), "Valida botón APROBAR");
		assertTrue(Btn_APROBAR.isEnabled());
		WebElement Btn_RECHAZAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rechazar")));
		assertEquals("Rechazar", Btn_RECHAZAR.getAttribute("value"), "Valida botón RECHAZAR");
		assertTrue(Btn_RECHAZAR.isEnabled());
		WebElement Btn_BORRADOR_PDF = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("Borrador")));
		assertEquals("Borrador PDF", Btn_BORRADOR_PDF.getAttribute("value"), "Valida botón BORRADOR PDF");
		assertTrue(Btn_BORRADOR_PDF.isEnabled());
		
		// Sección DATOS POLIZA
		WebElement textoSeccion_Datos_Poliza = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'DATOS POLIZA')]")));
		assertEquals("DATOS POLIZA", textoSeccion_Datos_Poliza.getText(), "Valida texto Sección");
		
		WebElement campo_Moneda2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("monedaTXT")));
		assertEquals("UF", campo_Moneda2.getText(), "Valida texto campo Moneda");
		WebElement campo_Ramo2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ramoTXT")));
		assertEquals("Accidentes Personales", campo_Ramo2.getText(), "Valida texto campo Ramo");
		WebElement campo_Convenio = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("convenio")));
		assertEquals("100", campo_Convenio.getAttribute("value"), "Valida texto campo Convenio");
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		assertEquals("99999-7", campo_Rut_Asegurado.getAttribute("value"), "Valida texto campo Rut Asegurado");
		WebElement campo_Nombre_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("nombreAseg")));
		assertEquals("Directo  ", campo_Nombre_Asegurado.getAttribute("value"), "Valida texto campo Nombre Asegurado");
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		assertEquals("99999-7", campo_Rut_Agente.getAttribute("value"), "Valida texto campo Rut Agente");
		WebElement campo_Nombre_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("nombreAgente")));
		assertEquals("Directo  ", campo_Nombre_Agente.getAttribute("value"), "Valida texto campo Nombre Agente");
		
		WebElement campo_Propuesta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ppta")));
		assertTrue(campo_Propuesta.isEnabled());
		WebElement campo_Cant_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numeroItemes")));
		assertTrue(campo_Cant_Items.isEnabled());
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		assertEquals("VER", Btn_VER.getAttribute("value"), "Valida botón VER");
		assertTrue(Btn_VER.isEnabled());
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		assertTrue(campo_Inicio_Vigencia.isEnabled());
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		assertTrue(campo_Termino_Vigencia.isEnabled());
		WebElement list_Sucursal = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sucursalBase")));
		assertTrue(list_Sucursal.isEnabled());
		WebElement check_Renovacion_Aut = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("renaut")));
		assertTrue(check_Renovacion_Aut.isEnabled());
		
		// Sección ITEMS
		WebElement textoSeccion_Items = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'ITEMS')]")));
		assertEquals("ITEMS", textoSeccion_Items.getText(), "Valida texto Sección");
		
		// Sección GLOSA GENERAL
		WebElement textoSeccion_Glosa_General= getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'GLOSA GENERAL')]")));
		assertEquals("GLOSA GENERAL", textoSeccion_Glosa_General.getText(), "Valida texto Sección");
		
		// Sección RESUMEN PRIMA
		WebElement textoSeccion_Resumen_Prima = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'RESUMEN PRIMA')]")));
		assertEquals("RESUMEN PRIMA", textoSeccion_Resumen_Prima.getText(), "Valida texto Sección");
		
		WebElement texto_Prima_Afecta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Prima Afecta')]")));
		assertEquals("Prima Afecta", texto_Prima_Afecta.getText(), "Valida texto campo Prima Afecta");
		WebElement campo_Prima_Afecta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaAfecta")));
		assertEquals("0", campo_Prima_Afecta.getText(), "Valida campo Prima Afecta");
		
		WebElement texto_Prima_Exenta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Prima Exenta')]")));
		assertEquals("Prima Exenta", texto_Prima_Exenta.getText(), "Valida texto campo Prima Exenta");
		WebElement campo_Prima_Exenta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaExenta")));
		assertEquals("0", campo_Prima_Exenta.getText(), "Valida campo Prima Exenta");
		
		WebElement texto_Prima_Neta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Prima Neta')]")));
		assertEquals("Prima Neta", texto_Prima_Neta.getText(), "Valida texto campo Prima Neta");
		WebElement campo_Prima_Neta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaNeta")));
		assertEquals("0", campo_Prima_Neta.getText(), "Valida campo Prima Neta");
		
		WebElement texto_IVA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'IVA')]")));
		assertEquals("IVA", texto_IVA.getText(), "Valida texto campo IVA");
		WebElement campo_IVA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totIVA")));
		assertEquals("0", campo_IVA.getText(), "Valida campo IVA");
		
		WebElement texto_Prima_Bruta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Prima Bruta')]")));
		assertEquals("Prima Bruta", texto_Prima_Bruta.getText(), "Valida texto campo Prima Bruta");
		WebElement campo_Prima_Bruta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaBruta")));
		assertEquals("0", campo_Prima_Bruta.getText(), "Valida campo Prima Bruta");
		
		
		// Sección PLAN DE PAGO
		WebElement textoSeccion_Plan_Pagos = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'PLAN DE PAGO')]")));
		assertEquals("PLAN DE PAGO", textoSeccion_Plan_Pagos.getText(), "Valida texto Sección");
		
		WebElement texto_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Rut Contratante:')]")));
		assertEquals("Rut Contratante:", texto_Rut_Contratante.getText(), "Valida texto campo Rut Contratante");
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		assertTrue(campo_Rut_Contratante.isEnabled());
		
		WebElement texto_Rut_Sujeto_Factura = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Rut Sujeto Factura (opc):')]")));
		assertEquals("Rut Sujeto Factura (opc):", texto_Rut_Sujeto_Factura.getText(), "Valida texto campo Rut Sujeto Factura");
		WebElement campo_Rut_Sujeto_Factura = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutSujetoFactura")));
		assertTrue(campo_Rut_Sujeto_Factura.isEnabled());
		
		WebElement texto_Medio_Pago = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Medio de pago:')]")));
		assertEquals("Medio de pago:", texto_Medio_Pago.getText(), "Valida texto campo Medio de pago");
		WebElement list_Medio_Pago = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("FormadePago")));
		assertTrue(list_Medio_Pago.isEnabled());
		
		WebElement texto_Mandato = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Mandato:')]")));
		assertEquals("Mandato:", texto_Mandato.getText(), "Valida texto campo Mandato");
		WebElement list_Mandato = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("mandato")));
		assertTrue(list_Mandato.isEnabled());
		
		WebElement texto_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Número de Cuotas:')]")));
		assertEquals("Número de Cuotas:", texto_Numero_Cuotas.getText(), "Valida texto campo Número de Cuotas");
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		assertTrue(campo_Numero_Cuotas.isEnabled());
		
		WebElement texto_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), '1er Vencimiento al día')]")));
		assertEquals("1er Vencimiento al día", texto_Primer_Vencimiento.getText(), "Valida texto campo 1er Vencimiento al día");
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		assertTrue(campo_Primer_Vencimiento.isEnabled());
		
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		assertEquals("Crear Plan de Pago", Btn_CREAR_PLAN_PAGO.getAttribute("value"), "Valida botón CREAR PLAN PAGO");
		assertTrue(Btn_CREAR_PLAN_PAGO.isEnabled());
		
		WebElement texto_Cuota = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Cuota')]")));
		assertEquals("Cuota", texto_Cuota.getText(), "Valida texto campo Cuota");
		
		WebElement texto_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Vencimiento')]")));
		assertEquals("Vencimiento", texto_Vencimiento.getText(), "Valida texto campo Vencimiento");
		
		WebElement texto_Valor = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Valor')]")));
		assertEquals("Valor", texto_Valor.getText(), "Valida texto campo Valor");
	
		System.out.println("Prueba PoC_FUN_014 Exitosa");	
		
		
		/*
		 * PENDIENTE: 
		 * - Add Validación de nombre de campos  (algunos si lo tienen, se encontró sulución)
		 * - validar listas desplegables
		 * 
		 */
		
		/*
		 * Implementación: PoC_FUN_015
		 * Ingresar los datos básicos de la póliza y presionar Botón VER.
		 */
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		Btn_VER.click();
		
		/*
		 * Validación de despliegue de nuevos campos
		 * sub-secciones "DETALLE ÍTEM 1" y "COBERTURAS ÍTEM 1"
		 */
		
		// Sub-sección DETALLE ITEM 1
		WebElement textoSeccion_Detalle_Item1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'DETALLE ITEM 1')]")));
		assertEquals("DETALLE ITEM 1", textoSeccion_Detalle_Item1.getText(), "Valida texto Sección");
		
		WebElement texto_Tipo_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Tipo de Item:')]")));
		assertEquals("Tipo de Item:", texto_Tipo_Item.getText(), "Valida texto campo Tipo ítem");
		WebElement list_Tipo_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("selectTipoItem_1")));
		assertTrue(list_Tipo_Item.isEnabled());
		
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		assertEquals("Copiar Vigencias", Btn_COPIAR_VIGENCIAS.getAttribute("value"), "Valida botón COPIAR VIGENCIAS");
		assertTrue(Btn_COPIAR_VIGENCIAS.isEnabled());
		
		WebElement texto_Inicio_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Inicio Vigencia:')]")));
		assertEquals("Inicio Vigencia:", texto_Inicio_Vigencia2.getText(), "Valida texto campo Inicio Vigencia");
		WebElement campo_Inicio_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaItem_1")));
		assertTrue(campo_Inicio_Vigencia2.isEnabled());
		
		WebElement texto_Termino_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Término Vigencia:')]")));
		assertEquals("Término Vigencia:", texto_Termino_Vigencia2.getText(), "Valida texto campo Término Vigencia");
		WebElement campo_Termino_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaItem_1")));
		assertTrue(campo_Termino_Vigencia2.isEnabled());
		
		//WebElement texto_Rut_Asegurado2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Rut Asegurado:')]")));
		//assertEquals("Rut Asegurado:\r\n" + "Nomina:  ", texto_Rut_Asegurado2.getText(), "Valida texto campo Rut Asegurado");
		WebElement campo_Rut_Asegurado2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1002_1")));
		assertTrue(campo_Rut_Asegurado2.isEnabled());
		
		WebElement campo_Nomina = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1003_1")));
		assertTrue(campo_Nomina.isEnabled());
		
		//WebElement texto_Rut_Beneficiario_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Rut Beneficiario del Item (opcional):')]")));
		//assertEquals("Rut Beneficiario del Item (opcional):", texto_Rut_Beneficiario_Item.getText(), "Valida texto campo Rut Beneficiario Item");
		WebElement campo_Beneficiario_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutBeneficiarioItem_1")));
		assertTrue(campo_Beneficiario_Item.isEnabled());
		
		WebElement Btn_ELIMINA_BENEFICIARIO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("eliminaBeneficiario_1")));
		assertEquals("Elimina Beneficiario", Btn_ELIMINA_BENEFICIARIO.getAttribute("value"), "Valida botón ELIMINA BENEFICIARIO)");
		assertTrue(Btn_ELIMINA_BENEFICIARIO.isEnabled());

		// Sub-sección COBERTURAS ITEM 1
		WebElement textoSeccion_Coberturas_Item1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'COBERTURAS ITEM 1')]")));
		assertEquals("COBERTURAS ITEM 1", textoSeccion_Coberturas_Item1.getText(), "Valida texto Sección");
		
		WebElement texto_Cobertura = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Cobertura')]")));
		assertEquals("Cobertura", texto_Cobertura.getText(), "Valida texto campo Cobertura");
		WebElement list_Cobertura = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_1")));
		assertTrue(list_Cobertura.isEnabled());
		
		WebElement texto_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Monto')]")));
		assertEquals("Monto", texto_Monto.getText(), "Valida texto campo Monto");
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		assertTrue(campo_Monto.isEnabled());
		
		WebElement texto_Glosa_MA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Glosa MA(opc)')]")));
		assertEquals("Glosa MA(opc)", texto_Glosa_MA.getText(), "Valida texto campo Glosa MA(opc)");
		WebElement campo_Glosa_MA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("glosaMontoItemCobertura_1_1")));
		assertTrue(campo_Glosa_MA.isEnabled());
		
		WebElement texto_Deducible = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Deducible')]")));
		assertEquals("Deducible", texto_Deducible.getText(), "Valida texto campo Deducible");
		WebElement campo_Deducible = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("deducibleItemCobertura_1_1")));
		assertTrue(campo_Deducible.isEnabled());
		
		WebElement texto_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//th[contains(text(), 'Prima Neta')]")));
		assertEquals("Prima Neta", texto_Prima_Neta2.getText(), "Valida texto campo Prima Neta");
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		assertTrue(campo_Prima_Neta2.isEnabled());
		
		WebElement Btn_PLUS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("agregaCobertura_1_1")));
		assertEquals("+", Btn_PLUS.getAttribute("value"), "Valida botón PLUS)");
		assertTrue(Btn_PLUS.isEnabled());
		
		WebElement texto_Prima_Neta_Total = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Prima Neta Total:')]")));
		assertEquals("Prima Neta Total:", texto_Prima_Neta_Total.getText(), "Valida texto campo Prima Neta Total:");
		WebElement campo_Prima_Neta_Total = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sumaItem_1")));
		assertTrue(campo_Prima_Neta_Total.isEnabled());
		
		WebElement Btn_DIST_PRIMA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botondistribuirPrima_1")));
		assertEquals("Dist. Prima", Btn_DIST_PRIMA.getAttribute("value"), "Valida botón DIST PRIMA)");
		assertTrue(Btn_DIST_PRIMA.isEnabled());
		
		WebElement texto_Glosa_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Glosa Item')]")));
		assertEquals("Glosa Item", texto_Glosa_Item.getText(), "Valida texto campo Glosa Item:");
		WebElement campo_Glosa_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("glosaItem_1")));
		assertTrue(campo_Glosa_Item.isEnabled());
		
		System.out.println("Prueba PoC_FUN_015 Exitosa");	
		
		/*
		 * PENDIENTE: 
		 * - Add Validación de nombre de campos  (algunos si lo tienen, se encontró sulución)
		 * - validar listas desplegables
		 * 
		 */
	}
	
	
	@Test
	void alternativo_Sin_Datos_Crear_Plan_Y_Guardar() throws Exception {
		/*
		 * Implementación: PoC_FUN_016
		 * 
		 * NO ingresar los datos básicos de la póliza y Presionar Botón CREAR PLAN DE PAGO.
		 */
		
		// Presionar directamente el botón CREAR PLAN PAGO
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
				
		// Aceptar alerta
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Falta completar vigencias de la póliza", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	
		System.out.println("Prueba PoC_FUN_016 Exitosa");	
		
		
		
		/*
		 * Implementación: PoC_FUN_017
		 * 
		 * Ingresar Fechas Inicio Vegencia y Término Vigencia, presionar Botón VER y luego presionar Botón GUARDAR.
		 */
		
		Thread.sleep(3000);
		
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		Btn_GUARDAR.click();
		
		// Aceptar alerta
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Error en Vigencia de item 1", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	
		System.out.println("Prueba PoC_FUN_017 Exitosa");
	}
	
	@Test
	void copiar_Vigencias_Y_alternativo_Crear_Plan_Pago() throws Exception {
		/*
		 * Implementación: PoC_FUN_018
		 * 
		 * Ingresar los datos básicos del ítem de la póliza y Presionar Botón COPIAR VIGENCIAS
		 */
		
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		
		//Validación de Datos cargados automáticamente.
		WebElement campo_Inicio_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaItem_1")));
		assertEquals(Configuration.FECHA_INICIO_VIGENCIA, campo_Inicio_Vigencia2.getAttribute("value"), "Validación de datos campo Inicio Vigencia");
		WebElement campo_Termino_Vigencia2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaItem_1")));
		assertEquals(Configuration.FECHA_TERMINO_VIGENCIA, campo_Termino_Vigencia2.getAttribute("value"), "Validación de datos campo Término Vigencia");
		
		System.out.println("Prueba PoC_FUN_018 Exitosa");
		
		
		
		/*
		 * Implementación: PoC_FUN_019
		 * 
		 * NO ingresar los datos básicos del ítem de la póliza, Presionar Botón COPIAR VIGENCIAS y CREAR PLAN DE PAGO
		 */
	    
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
	    Thread.sleep(3000);
	
		System.out.println("Prueba PoC_FUN_019 Exitosa");
	}
	
	@Test
	void copiar_Vigencias_Y_alternativos_Guardar() throws Exception {
		/*
		 * Implementación: PoC_FUN_020
		 * 
		 * Ingreso de Datos sólo hasta la sección ÍTEM 1 (solo datos pre-cargados) y luego presionar Botón GUARDAR.
		 */
		
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		Thread.sleep(3000);
		
	    // Presionar directamente el botón GUARDAR
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		Btn_GUARDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alertas
		String mensaje_Alerta3 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta3, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	   
		System.out.println("Prueba PoC_FUN_020 Exitosa");	
	}
	
	
	@Test
	void alternativo_Coberturas_Item1() throws Exception {
		/*
		 * Implementación: PoC_FUN_022
		 * 
		 * NO ingresar los datos básicos de la cobertura del ítem de la póliza Y Presionar Botón CREAR PLAN DE PAGO
		 */
		
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
	    Thread.sleep(3000);
		
		System.out.println("Prueba PoC_FUN_022 Exitosa");
		
		
		
		/*
		 * Implementación: PoC_FUN_023
		 * 
		 * Ingresar los datos básicos de la cobertura del ítem de la póliza Y Presionar Botón GUARDAR
		 */
		
		Thread.sleep(3000);
		
		// Validar Despliegue de Datos en sección "RESUMEN PRIMA"
		WebElement campo_Prima_Afecta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaAfecta")));
		assertEquals("0.0000", campo_Prima_Afecta.getText(), "Valida campo Prima Afecta");
		WebElement campo_Prima_Exenta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaExenta")));
		assertEquals("0.0000", campo_Prima_Exenta.getText(), "Valida campo Prima Exenta");
		WebElement campo_Prima_Neta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaNeta")));
		assertEquals("0.0000", campo_Prima_Neta.getText(), "Valida campo Prima Neta");
		WebElement campo_IVA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totIVA")));
		assertEquals("0.0000", campo_IVA.getText(), "Valida campo IVA");
		WebElement campo_Prima_Bruta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaBruta")));
		assertEquals("0.0000", campo_Prima_Bruta.getText(), "Valida campo Prima Bruta");
		
		// Presionar directamente el botón GUARDAR
		WebElement Btn_GUARDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar")));
		Btn_GUARDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta4 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta4, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	
		System.out.println("Prueba PoC_FUN_023 Exitosa");
	}
	
	@Test
	void coberturas_Item1_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_021
		 * 
		 * Ingresar los datos básicos de la cobertura del ítem de la póliza
		 */
		
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
		
		// Ingreso de Datos en sección COBERTURAS ITEM 1
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA);
		campo_Monto.click();
		Thread.sleep(3000);
		
		// Validar Despliegue de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Prima_Neta_Total = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sumaItem_1")));
		assertEquals("3.0000", campo_Prima_Neta_Total.getAttribute("value"), "Valida campo Prima Neta Total");
		
		// Validar Despliegue de Datos en sección "RESUMEN PRIMA"
		WebElement campo_Prima_Afecta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaAfecta")));
		assertEquals("3.0000", campo_Prima_Afecta.getText(), "Valida campo Prima Afecta");
		WebElement campo_Prima_Exenta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaExenta")));
		assertEquals("0.0000", campo_Prima_Exenta.getText(), "Valida campo Prima Exenta");
		WebElement campo_Prima_Neta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaNeta")));
		assertEquals("3.0000", campo_Prima_Neta.getText(), "Valida campo Prima Neta");
		WebElement campo_IVA = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totIVA")));
		assertEquals("0.5700", campo_IVA.getText(), "Valida campo IVA");
		WebElement campo_Prima_Bruta = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("totPrimaBruta")));
		assertEquals("3.5700", campo_Prima_Bruta.getText(), "Valida campo Prima Bruta");
		
		System.out.println("Prueba PoC_FUN_021 Exitosa");
	}
	
}

