package es.uma.proyecto.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.proyecto.test.BaseDatos;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
// Generated by Selenium IDE
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ProyectoIT {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	private static String baseURL;
	private static Map<String,String> propiedadesExtra = new HashMap<>();

	@BeforeClass
	public static void setupClass() {
		String server="localhost";
		try (InputStream is = ProyectoIT.class.getClassLoader().getResourceAsStream("pom.properties")) {
			Properties pomProperties = new Properties();
			pomProperties.load(is);
			server=pomProperties.getProperty("server.host");
			String databaseURL = "jdbc:mysql://"+server+":3306/sii";
			propiedadesExtra.put("javax.persistence.jdbc.url", databaseURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		baseURL="http://"+server+":8080/proyecto-war/";
	}

	@Before
	public void setUp() throws ParseException {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	@Requisitos({"RF10"})
	@Test
	/**
	 * Test para comprobar un correcto inicio de sesión por parte de un usuario normal 
	 * En este caso, un cliente individual
	 */
	public void iniciarSesion() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		assertThat(driver.findElement(By.cssSelector("i")).getText(), is("Ponciano"));
	}
	
	@SuppressWarnings("deprecation")
	@Requisitos({"RF10"})
	@Test
	/**
	 * Test para comprobar un incorrecto inicio de sesion con la contraseña que no es
	 * En este caso, un cliente individual
	 */
	public void iniciarSesionPasswordMal() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("poncianoNoEs");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.id("login:passwordMessage")).click();
		driver.findElement(By.cssSelector(".mensajes:nth-child(4)")).click();
		assertThat(driver.findElement(By.id("login:passwordMessage")).getText(), is("La contraseña no coincide"));
	}

	@SuppressWarnings("deprecation")
	@Requisitos({"RF10"})
	@Test
	/**
	 * Test para comprobar un incorrecto inicio de sesion 
	 * Cuando no se colocan los valores obligatorios
	 */
	public void iniciarSesionCamposVacios() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:botonLogin")).click();
		assertThat(driver.findElement(By.id("login:userMessage")).getText(), is("Valor obligatorio"));
		assertThat(driver.findElement(By.id("login:passwordMessage")).getText(), is("Valor obligatorio"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void iniciarSesionNoExisteUsuario() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("Inventado");
		driver.findElement(By.id("login:pass")).sendKeys("asdasd");
		driver.findElement(By.id("login:botonLogin")).click();
		assertThat(driver.findElement(By.id("login:userMessage")).getText(), is("La cuenta indicada no existe"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void registro() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("registro:nombre")).click();
		driver.findElement(By.id("registro:nombre")).sendKeys("David");
		driver.findElement(By.id("registro:pass")).sendKeys("David123");
		driver.findElement(By.id("registro:repass")).sendKeys("David123");
		driver.findElement(By.id("registro:email")).sendKeys("david@david.com");
		driver.findElement(By.name("registro:j_idt15")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(1)")).getText(), is("El registro se ha realizado con éxito."));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void registroCamposVacios() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.name("registro:j_idt15")).click();
		driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(3)")).click();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(3)")).getText(), is("Valor obligatorio"));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("Valor obligatorio"));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(3)")).getText(), is("Valor obligatorio"));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(3)")).getText(), is("Valor obligatorio"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void registroEmailNoValido() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("registro:nombre")).click();
		driver.findElement(By.id("registro:nombre")).sendKeys("Juanito");
		driver.findElement(By.id("registro:pass")).sendKeys("Juanito123");
		driver.findElement(By.id("registro:repass")).sendKeys("Juanito123");
		driver.findElement(By.id("registro:email")).sendKeys("juanasdas");
		driver.findElement(By.name("registro:j_idt15")).click();
		driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(3)")).click();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(3)")).getText(), is("El email debe ser valido"));
	}

	@SuppressWarnings("deprecation")
	@Test
  	public void registroPasswordNoCoinciden() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("registro:nombre")).click();
		driver.findElement(By.id("registro:nombre")).sendKeys("Juanito");
		driver.findElement(By.id("registro:pass")).sendKeys("Juanito123");
		driver.findElement(By.id("registro:repass")).sendKeys("asdasd");
		driver.findElement(By.id("registro:email")).sendKeys("juan@juan.com");
		driver.findElement(By.name("registro:j_idt15")).click();
		driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(3)")).click();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(3)")).getText(), is("Las contraseñas deben coincidir."));
	}

	@Test @SuppressWarnings("deprecation")
	public void registroPasswordNoValida() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("registro:nombre")).click();
		driver.findElement(By.id("registro:nombre")).sendKeys("Juanito");
		driver.findElement(By.id("registro:pass")).sendKeys("asdadd");
		driver.findElement(By.id("registro:repass")).sendKeys("asdadd");
		driver.findElement(By.id("registro:email")).sendKeys("juanito@juanito.com");
		driver.findElement(By.name("registro:j_idt15")).click();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("La contraseña debe ser valida"));
	}

  	@Test @SuppressWarnings("deprecation")
  	public void registroUsuarioRepetido() {
		driver.get("http://0.0.0.0:8080/proyecto-war/registro.xhtml");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.cssSelector("table")).click();
		driver.findElement(By.id("registro:nombre")).click();
		driver.findElement(By.id("registro:nombre")).sendKeys("ponciano");
		driver.findElement(By.id("registro:pass")).sendKeys("Ponciano123");
		driver.findElement(By.id("registro:repass")).sendKeys("Ponciano123");
		driver.findElement(By.id("registro:email")).sendKeys("ponciano@ponciano.com");
		driver.findElement(By.name("registro:j_idt15")).click();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(3)")).getText(), is("Existe un usuario con la misma cuenta."));
  	}

	@Test @SuppressWarnings("deprecation")
  	public void mostrarCuentaReferenciaTest() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(929, 918));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("div:nth-child(5) > button:nth-child(3)")).click();
		driver.findElement(By.id("mostrarCuentasRef:iban")).click();
		driver.findElement(By.id("mostrarCuentasRef:iban")).sendKeys("VG57DDVS5173214964983931");
		driver.findElement(By.id("mostrarCuentasRef:mostrarCuentas")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(4) > i")).getText(), is("Bancos Pablo SA"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(10) > i")).getText(), is("Dolar"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(2) > i")).getText(), is("VG57DDVS5173214964983931"));
  	}

	@Test @SuppressWarnings("deprecation")
	public void mostrarCuentaSegregadaTest() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(929, 918));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("div:nth-child(5) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarCuentas:iban")).click();
		driver.findElement(By.id("mostrarCuentas:iban")).sendKeys("FR5514508000502273293129K55");
		driver.findElement(By.id("mostrarCuentas:mostrarCuentas")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > i")).getText(), is("Segregada"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(9) > i")).getText(), is("HN47QUXH11325678769785549996"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(6) > i")).getText(), is("No Tiene"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(4) > i")).getText(), is("Active"));
	}

	@Test @SuppressWarnings("deprecation")
	public void mostrarCuentaPooledTest() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(929, 918));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("div:nth-child(5) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarCuentas:iban")).click();
		driver.findElement(By.id("mostrarCuentas:iban")).sendKeys("ES8400817251647192321264");
		driver.findElement(By.id("mostrarCuentas:mostrarCuentas")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(2) > i")).getText(), is("ES8400817251647192321264"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(8) > i")).getText(), is("{ES7121007487367264321882(Euro), GB79BARC20040134265953(Libra), VG88HBIJ4257959912673134(Dolar)}"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > i")).getText(), is("Pooled"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(6) > i")).getText(), is("No Tiene"));
	}
	
}