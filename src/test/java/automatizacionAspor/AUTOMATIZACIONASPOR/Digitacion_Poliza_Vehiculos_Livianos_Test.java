package automatizacionAspor.AUTOMATIZACIONASPOR;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



class Digitacion_Poliza_Vehiculos_Livianos_Test extends BaseClass{

	/*
	 * Scenario 1: Validar PRODUCCIÓN - DATOS DE LA PÓLIZA PARA VEHÍCULOS LIVIANOS
	 *  - Despliegue página "Digitación de Póliza".
	 *  
	 * Scenario 2: Autorizar la emisión de la póliza VEHÍCULOS LIVIANOS - PRODUCCIÓN - GENERAR PÓLIZA
	 *  - Ingresar los datos datos básicos de la póliza.
	 *  	- EXITOSO
	 *  
	 * Scenario 3: Validación de póliza VEHÍCULOS LIVIANOS (Datos Incorrectos) - PRODUCCIÓN - VALIDACIÓN DE DATOS
	 *  	- FLUJO ALTERNATIVO
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
		
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos flujo_Previo");
		
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("esPoliza"))).click();
		Thread.sleep(2000);
		
		// Ingresar Datos y Presionar el botón GUARDAR
		Select select_Ramo_Vehiculos = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("ramo")))); 
		select_Ramo_Vehiculos.selectByVisibleText(Configuration.RAMO_VEHICULOS_LIVIANOS);
		String ramoSeleccionado = select_Ramo_Vehiculos.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.RAMO_VEHICULOS_LIVIANOS, ramoSeleccionado, "Verificando Ramo seleccionado");
		
		WebElement campo_Rut_Asegurado = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAseg")));
		campo_Rut_Asegurado.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		
		WebElement campo_Rut_Agente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutAgente")));
		campo_Rut_Agente.sendKeys(Configuration.RUT_ASEGURADO);
		Thread.sleep(2000);
		
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos flujo_Previo");
		this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("guardar"))).click();
		Thread.sleep(2000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos flujo_Previo");
		
		// Clic en Número de Póliza generado
		WebElement numero_Poliza = this.getWait().until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("01-72-")));
		numero_Poliza_global = numero_Poliza.getText();
		numero_Poliza.click();
		Thread.sleep(3000);
	}
	
	@Test
	void despliegue_Digitacion_Poliza_Vehiculos_Livianos() throws Exception {
		/*
		 * Implementación: PoC_FUN_041
		 * 
		 * Scenario 1: Validar PRODUCCIÓN - DATOS DE LA PÓLIZA PARA VEHÍCULOS LIVIANOS
		 *  - Despliegue página "Digitación de Póliza".
		 */
		
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos despliegue_Digitacion_Poliza_Vehiculos_Livianos");
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
		assertEquals("72", campo_Ramo.getAttribute("value"), "Valida texto campo Ramo");
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
		assertEquals("Vehiculos Livianos", campo_Ramo2.getText(), "Valida texto campo Ramo");
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
		Select select_Sucursal = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sucursalBase")))); 
		String sucursalSeleccionado = select_Sucursal.getFirstSelectedOption().getText(); 
		assertEquals(Configuration.SUCURSAL, sucursalSeleccionado, "Verificando Sucursal seleccionada");
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
	
		System.out.println("Prueba PoC_FUN_041 Exitosa");	
		

		
		/*
		 * Implementación: PoC_FUN_041 - SEGUNDA ITERACIÓN
		 * Ingresar los datos básicos de la póliza y presionar Botón VER.
		 */
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos despliegue_Digitacion_Poliza_Vehiculos_Livianos");
		Btn_VER.click();
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos despliegue_Digitacion_Poliza_Vehiculos_Livianos");
		
		/*
		 * Validación de despliegue de nuevos campos
		 * sub-secciones "DETALLE ÍTEM 1" y "COBERTURAS ÍTEM 1"
		 */
		
		// Sub-sección DETALLE ITEM 1
		WebElement textoSeccion_Detalle_Item1 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//legend[contains(text(), 'DETALLE ITEM 1')]")));
		assertEquals("DETALLE ITEM 1", textoSeccion_Detalle_Item1.getText(), "Valida texto Sección");
		
		WebElement texto_Tipo_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(), 'Tipo de Item:')]")));
		assertEquals("Tipo de Item:", texto_Tipo_Item.getText(), "Valida texto campo Tipo ítem");
		Select select_Tipo_Item = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("selectTipoItem_1")))); 
		String tipo_Item_Seleccionado = select_Tipo_Item.getFirstSelectedOption().getText(); 
		assertEquals("Vehículo Liviano", tipo_Item_Seleccionado, "Verificando Color seleccionado");
		
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
		
		WebElement campo_Rut_Propietario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1038_1")));
		assertTrue(campo_Rut_Propietario.isEnabled());
		
		WebElement campo_Patente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("patente_r_1039_1")));
		assertTrue(campo_Patente.isEnabled());
		
		Select select_Tipo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("tipoveh_r_1039_1")))); 
		String tipoSeleccionado = select_Tipo.getFirstSelectedOption().getText(); 
		assertEquals("Automóvil", tipoSeleccionado, "Verificando Tipo seleccionado");
		
		Select select_Marca = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("marca_r_1039_1")))); 
		String marcaSeleccionado = select_Marca.getFirstSelectedOption().getText(); 
		assertEquals("Elija un tipo", marcaSeleccionado, "Verificando marca seleccionado");
		
		Select select_Modelo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("modelo_r_1039_1")))); 
		String modeloSeleccionado = select_Modelo.getFirstSelectedOption().getText(); 
		assertEquals("Elija una marca", modeloSeleccionado, "Verificando marca seleccionado");
		
		WebElement campo_Motor = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("motor_r_1039_1")));
		assertTrue(!campo_Motor.isEnabled());
		
		WebElement campo_Chasis = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("chasis_r_1039_1")));
		assertTrue(!campo_Chasis.isEnabled());
		
		Select select_Color = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("color_r_1039_1")))); 
		String colorSeleccionado = select_Color.getFirstSelectedOption().getText(); 
		assertEquals("Elija un color", colorSeleccionado, "Verificando Color seleccionado");
		
		Select select_Anio = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("year_r_1039_1")))); 
		String anioSeleccionado = select_Anio.getFirstSelectedOption().getText(); 
		assertEquals("Elija Año", anioSeleccionado, "Verificando Año seleccionado");
		
		WebElement campo_Id = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1039_1")));
		assertTrue(campo_Id.isEnabled());
		
		Select select_Uso = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1040_1")))); 
		String usoSeleccionado = select_Uso.getFirstSelectedOption().getText(); 
		assertEquals("Comercial", usoSeleccionado, "Verificando Uso seleccionado");
		
		WebElement campo_Rut_Beneficiario_Item = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutBeneficiarioItem_1")));
		assertTrue(campo_Rut_Beneficiario_Item.isEnabled());
		
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
		Select select_Cobertura = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("itemCoberturas_1_1")))); 
		String coberturaSeleccionado = select_Cobertura.getFirstSelectedOption().getText(); 
		assertEquals("Daños Materiales", coberturaSeleccionado, "Verificando Uso seleccionado");
		
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
		
		System.out.println("Prueba PoC_FUN_041 (Segunda iteración) Exitosa");	
	}
	
	
	@Test
	void generar_Poliza_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_043
		 * 
		 * Scenario 2: Autorizar la emisión de la póliza VEHÍCULOS LIVIANOS - PRODUCCIÓN - GENERAR PÓLIZA
		 *  - Ingresar los datos datos básicos de la póliza.
		 *  	- EXITOSO
		 */
		
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
		//Ingreso de Datos en la sección "DATOS POLIZA"
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		Btn_VER.click();
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
		// Ingreso de Datos en sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		WebElement campo_Rut_Propietario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1038_1")));
		campo_Rut_Propietario.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Patente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("patente_r_1039_1")));
		campo_Patente.sendKeys(Configuration.PATENTE);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		// Validación de Datos autocompletados
		campo_Rut_Propietario.click(); //para sacar foco
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
		Select select_Tipo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("tipoveh_r_1039_1")))); 
		String tipoSeleccionado = select_Tipo.getFirstSelectedOption().getText(); 
		assertEquals("Camioneta", tipoSeleccionado, "Verificando Tipo seleccionado");
		
		Select select_Marca = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("marca_r_1039_1")))); 
		String marcaSeleccionado = select_Marca.getFirstSelectedOption().getText(); 
		assertEquals("NISSAN", marcaSeleccionado, "Verificando marca seleccionado");
		
		Select select_Modelo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("modelo_r_1039_1")))); 
		String modeloSeleccionado = select_Modelo.getFirstSelectedOption().getText(); 
		assertEquals("TERRANO", modeloSeleccionado, "Verificando marca seleccionado");
		
		WebElement campo_Motor = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("motor_r_1039_1")));
		assertTrue(!campo_Motor.isEnabled());
		
		WebElement campo_Chasis = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("chasis_r_1039_1")));
		assertTrue(!campo_Chasis.isEnabled());
		
		Select select_Color = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("color_r_1039_1")))); 
		String colorSeleccionado = select_Color.getFirstSelectedOption().getText(); 
		assertEquals("-", colorSeleccionado, "Verificando Color seleccionado");
		
		Select select_Anio = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("year_r_1039_1")))); 
		String anioSeleccionado = select_Anio.getFirstSelectedOption().getText(); 
		assertEquals("2008", anioSeleccionado, "Verificando Año seleccionado");
		
		WebElement campo_Id = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1039_1")));
		assertEquals("900113", campo_Id.getAttribute("value"), "Verificando Campo ID");
		
		// Seleccionar USO
		Select select_Uso = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1040_1")))); 
		select_Uso.selectByVisibleText(Configuration.USO_VEHICULO);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto.clear();
		campo_Monto.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		// Ingresar los datos en sección "PLAN DE PAGO"
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		
		// Presionar directamente los botones CREAR PLAN PAGO y VALIDAR
		WebElement Btn_CREAR_PLAN_PAGO = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("calculaCuotas")));
		Btn_CREAR_PLAN_PAGO.click();
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		Thread.sleep(3000);
		
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	    takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
	    WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
	    Btn_SUBIR.click();
	    Thread.sleep(3000);
	    takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
	    
	    // Presionar directamente el botón APROBAR
	    WebElement Btn_APROBAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("aprobar")));
	    Btn_APROBAR.click();
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
				takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
			}
		}
	    
	    String tituloPagina_PDF = this.getDriver().getTitle();
		assertEquals("Aspor Intranet", tituloPagina_PDF, "Valida título de página");
		
		System.out.println("Prueba PoC_FUN_042 Exitosa");
	}
	
	
	@Test
	void generar_Poliza_No_Exitoso() throws Exception {
		/*
		 * Implementación: PoC_FUN_042
		 * 
		 * Scenario 3: Validación de póliza VEHÍCULOS LIVIANOS (Datos Incorrectos) - PRODUCCIÓN - VALIDACIÓN DE DATOS
		 *  	- FLUJO ALTERNATIVO
		 */
		
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		//Ingreso de Datos en la sección "DATOS POLIZA"
		WebElement campo_Inicio_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("iniVigenciaPol")));
		campo_Inicio_Vigencia.sendKeys(Configuration.FECHA_INICIO_VIGENCIA);
		WebElement campo_Termino_Vigencia = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("terVigenciaPol")));
		campo_Termino_Vigencia.sendKeys(Configuration.FECHA_TERMINO_VIGENCIA);
		WebElement Btn_VER = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("creaMultis")));
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		Btn_VER.click();
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		// Ingreso de Datos en sección "DETALLE ITEM 1"
		WebElement Btn_COPIAR_VIGENCIAS = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("botonCopiaVigencias_1")));
		Btn_COPIAR_VIGENCIAS.click();
		WebElement campo_Rut_Propietario = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rut_r_1038_1")));
		campo_Rut_Propietario.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Patente = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("patente_r_1039_1")));
		campo_Patente.sendKeys(Configuration.PATENTE);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		// Validación de Datos autocompletados
		campo_Rut_Propietario.click(); //para sacar foco
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		Select select_Tipo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("tipoveh_r_1039_1")))); 
		String tipoSeleccionado = select_Tipo.getFirstSelectedOption().getText(); 
		assertEquals("Camioneta", tipoSeleccionado, "Verificando Tipo seleccionado");
		
		Select select_Marca = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("marca_r_1039_1")))); 
		String marcaSeleccionado = select_Marca.getFirstSelectedOption().getText(); 
		assertEquals("NISSAN", marcaSeleccionado, "Verificando marca seleccionado");
		
		Select select_Modelo = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("modelo_r_1039_1")))); 
		String modeloSeleccionado = select_Modelo.getFirstSelectedOption().getText(); 
		assertEquals("TERRANO", modeloSeleccionado, "Verificando marca seleccionado");
		
		WebElement campo_Motor = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("motor_r_1039_1")));
		assertTrue(!campo_Motor.isEnabled());
		
		WebElement campo_Chasis = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("chasis_r_1039_1")));
		assertTrue(!campo_Chasis.isEnabled());
		
		Select select_Color = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("color_r_1039_1")))); 
		String colorSeleccionado = select_Color.getFirstSelectedOption().getText(); 
		assertEquals("-", colorSeleccionado, "Verificando Color seleccionado");
		
		Select select_Anio = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("year_r_1039_1")))); 
		String anioSeleccionado = select_Anio.getFirstSelectedOption().getText(); 
		assertEquals("2008", anioSeleccionado, "Verificando Año seleccionado");
		
		WebElement campo_Id = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1039_1")));
		assertEquals("900113", campo_Id.getAttribute("value"), "Verificando Campo ID");
		
		// Seleccionar USO
		Select select_Uso = new Select(getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("r_1040_1")))); 
		select_Uso.selectByVisibleText(Configuration.USO_VEHICULO);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		// Ingreso de Datos en sección "COBERTURAS ITEM 1"
		WebElement campo_Monto = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("montoItemCobertura_1_1")));
		campo_Monto.clear();
		campo_Monto.sendKeys(Configuration.MONTO);
		WebElement campo_Prima_Neta2 = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("pnetaItemCobertura_1_1")));
		campo_Prima_Neta2.clear();
		campo_Prima_Neta2.sendKeys(Configuration.PRIMA_NETA_NEGATIVO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		// Ingresar los datos en sección "PLAN DE PAGO"
		WebElement campo_Rut_Contratante = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rutContratante")));
		campo_Rut_Contratante.sendKeys(Configuration.RUT_ASEGURADO);
		WebElement campo_Numero_Cuotas = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("numCuotas")));
		campo_Numero_Cuotas.clear();
		campo_Numero_Cuotas.sendKeys(Configuration.NUMERO_CUOTAS);
		WebElement campo_Primer_Vencimiento = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("primeraCuota")));
		campo_Primer_Vencimiento.sendKeys(Configuration.FECHA_PRIMER_VENCIMIENTO);
		Thread.sleep(3000);
		takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
		
		
		// Presionar directamente el botón VALIDAR
		WebElement Btn_VALIDAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("validar")));
		Btn_VALIDAR.click();
		Thread.sleep(3000);
		
		// Aceptar alerta
		String mensaje_Alerta = this.getDriver().switchTo().alert().getText();
	    assertEquals("Borrador Actualizado", mensaje_Alerta, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
	    Thread.sleep(3000);
	    takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_Exitoso");
		
	    WebElement Btn_SUBIR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Subir'")));
	    Btn_SUBIR.click();
	    Thread.sleep(3000);
	    takeScreenShotTest(getDriver(), "Vehiculos_Livianos generar_Poliza_No_Exitoso");
	    
	    // Presionar directamente el botón APROBAR
	    WebElement Btn_APROBAR = getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("aprobar")));
	    Btn_APROBAR.click();
	    Thread.sleep(8000);
	    
	    // Aceptar alerta
		String mensaje_Alerta2 = this.getDriver().switchTo().alert().getText();
	    assertEquals("Póliza NO fue Aprobada", mensaje_Alerta2, "Valida mensaje de Alerta");
	    this.getDriver().switchTo().alert().accept();
		
		System.out.println("Prueba PoC_FUN_042 Exitosa");
	}

}