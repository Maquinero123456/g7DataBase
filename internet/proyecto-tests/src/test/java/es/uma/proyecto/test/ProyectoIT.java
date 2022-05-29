package es.uma.proyecto.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
	
	@Test
	public void iniciarSesionAdmin() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(670, 734));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		assertThat(driver.findElement(By.cssSelector("h3 > span")).getText(), is("ADMINISTRADORES"));
	}
	@Test
	public void inciarSesionCamposVacios() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:botonLogin")).click();
		assertThat(driver.findElement(By.id("login:userMessage")).getText(), is("Valor obligatorio"));
		assertThat(driver.findElement(By.id("login:passwordMessage")).getText(), is("Valor obligatorio"));
	}
	@Test
	public void iniciarSesionNoAdmin() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("juan");
		driver.findElement(By.id("login:pass")).sendKeys("juan");
		driver.findElement(By.cssSelector("p")).click();
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("p:nth-child(1)")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(1)")).getText(), is("BUENAS TARDES INDIVIDUAL JUAN ¿QUÉ ACCIÓN REALIZARÁ?"));
	}
	@Test
	public void iniciarSesionPasswordMal() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("asdsa");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector(".intro-contr")).click();
		assertThat(driver.findElement(By.id("login:passwordMessage")).getText(), is("La contraseña no coincide"));
	}
	@Test
	public void iniciarSesionUsuarioNoExiste() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("adsfasf");
		driver.findElement(By.id("login:pass")).click();
		driver.findElement(By.id("login:pass")).sendKeys("fdasfa");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector(".intro-usu")).click();
		assertThat(driver.findElement(By.id("login:userMessage")).getText(), is("La cuenta no existe"));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void registro() {
	  driver.get("http://0.0.0.0:8080/proyecto-war/");
	  driver.manage().window().setSize(new Dimension(1920, 1048));
	  driver.findElement(By.linkText("página de registro")).click();
	  driver.findElement(By.id("registro:nombre")).click();
	  driver.findElement(By.id("registro:nombre")).sendKeys("hola");
	  driver.findElement(By.id("registro:pass")).click();
	  driver.findElement(By.id("registro:pass")).sendKeys("Hola1234");
	  driver.findElement(By.id("registro:repass")).click();
	  driver.findElement(By.id("registro:repass")).sendKeys("Hola1234");
	  driver.findElement(By.id("registro:email")).click();
	  driver.findElement(By.id("registro:email")).sendKeys("hola@gmail.com");
	  driver.findElement(By.name("registro:j_idt15")).click();
	  driver.findElement(By.linkText("Ir a la página de login")).click();
	  driver.findElement(By.id("login:user")).click();
	  driver.findElement(By.id("login:user")).sendKeys("hola");
	  driver.findElement(By.id("login:pass")).click();
	  driver.findElement(By.id("login:pass")).sendKeys("Hola1234");
	  driver.findElement(By.id("login:botonLogin")).click();
	  driver.findElement(By.id("inicio")).click();
	  assertThat(driver.findElement(By.cssSelector("p:nth-child(3)")).getText(), is("NO TIENES NADA QUE HACER AQUI. PIDE A ALGUN ADMINISTRADOR QUE AÑADA UNA CUENTA A TU USUARIO."));
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
		assertThat(driver.findElement(By.cssSelector("p:nth-child(4) > i")).getText(), is("BANCOS PABLO SA"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(10) > i")).getText(), is("DOLAR"));
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
		assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > i")).getText(), is("SEGREGADA"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(9) > i")).getText(), is("HN47QUXH11325678769785549996"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(6) > i")).getText(), is("NO TIENE"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(4) > i")).getText(), is("ACTIVE"));
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
		assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > i")).getText(), is("POOLED"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(6) > i")).getText(), is("NO TIENE"));
	}
	
	  @Test
  public void crearEmpresa() {
    driver.get("http://0.0.0.0:8080/proyecto-war/");
    driver.manage().window().setSize(new Dimension(945, 1028));
    driver.findElement(By.id("login:user")).click();
    driver.findElement(By.id("login:user")).sendKeys("ponciano");
    driver.findElement(By.id("login:pass")).sendKeys("ponciano");
    driver.findElement(By.id("login:botonLogin")).click();
    driver.findElement(By.cssSelector("div:nth-child(4) > button:nth-child(7)")).click();
    driver.findElement(By.id("crearEmp:identCrearEmp")).click();
    driver.findElement(By.id("crearEmp:identCrearEmp")).sendKeys("7654321");
    driver.findElement(By.id("crearEmp:nombreCrearEmp")).click();
    driver.findElement(By.id("crearEmp:nombreCrearEmp")).sendKeys("1");
    driver.findElement(By.id("crearEmp:direcCrearEmp")).click();
    driver.findElement(By.id("crearEmp:direcCrearEmp")).sendKeys("1");
    driver.findElement(By.id("crearEmp:ciudadCrearEmp")).click();
    driver.findElement(By.id("crearEmp:ciudadCrearEmp")).sendKeys("1");
    driver.findElement(By.id("crearEmp:codPostCrearEmp")).click();
    driver.findElement(By.id("crearEmp:codPostCrearEmp")).sendKeys("1");
    driver.findElement(By.id("crearEmp:PaisCrearEmp")).click();
    driver.findElement(By.id("crearEmp:PaisCrearEmp")).sendKeys("1");
    driver.findElement(By.id("crearEmp:BajaCrearEmp")).click();
    driver.findElement(By.id("crearEmp:crearEmpresa")).click();
    driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
    driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
    assertThat(driver.findElement(By.cssSelector("#DatosEmpresa > p:nth-child(3) > i")).getText(), is("7654321"));
  }
  @Test
  public void crearIndividual() {
    driver.get("http://0.0.0.0:8080/proyecto-war/");
    driver.manage().window().setSize(new Dimension(945, 1028));
    driver.findElement(By.id("login:user")).click();
    driver.findElement(By.id("login:user")).sendKeys("ponciano");
    driver.findElement(By.id("login:pass")).sendKeys("ponciano");
    driver.findElement(By.id("login:botonLogin")).click();
    driver.findElement(By.cssSelector("div:nth-child(4) > button:nth-child(6)")).click();
    driver.findElement(By.id("fCrear:identCrearInd")).click();
    driver.findElement(By.id("fCrear:identCrearInd")).sendKeys("1234567");
    driver.findElement(By.id("fCrear:identUsuCrearInd")).click();
    driver.findElement(By.id("fCrear:identUsuCrearInd")).sendKeys("1234567");
    driver.findElement(By.id("fCrear:nombreCrearInd")).click();
    driver.findElement(By.id("fCrear:nombreCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:apellidosCrearInd")).click();
    driver.findElement(By.id("fCrear:apellidosCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:fechaNacCrearInd")).click();
    driver.findElement(By.id("fCrear:fechaNacCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:direcCrearInd")).click();
    driver.findElement(By.id("fCrear:ciudadCrearInd")).click();
    driver.findElement(By.id("fCrear:ciudadCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:direcCrearInd")).click();
    driver.findElement(By.id("fCrear:direcCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:codPostCrearInd")).click();
    driver.findElement(By.id("fCrear:codPostCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:PaisCrearInd")).click();
    driver.findElement(By.id("fCrear:PaisCrearInd")).sendKeys("1");
    driver.findElement(By.id("fCrear:BajaCrearInd")).click();
    driver.findElement(By.id("fCrear:crearCliente")).click();
    driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
    driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
    driver.findElement(By.cssSelector("#DatosInd > p:nth-child(3) > i")).click();
    assertThat(driver.findElement(By.cssSelector("#DatosInd > p:nth-child(3) > i")).getText(), is("1234567"));
  }

	@Test @SuppressWarnings("deprecation")
	public void bajaCliente() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(929, 918));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarCl:identMostrar")).click();
		driver.findElement(By.id("mostrarCl:identMostrar")).sendKeys("P3310693A");
		driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(5) > i")).getText(), is("ALTA"));
		assertThat(driver.findElement(By.cssSelector("p:nth-child(7) > i")).getText(), is("NO TIENE"));
		driver.findElement(By.cssSelector("div:nth-child(5) > button:nth-child(5)")).click();
		driver.findElement(By.id("fBaja:identBaja")).click();
		driver.findElement(By.id("fBaja:identBaja")).sendKeys("P3310693A");
		driver.findElement(By.id("fBaja:darBaja")).click();
		driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(5) > i")).getText(), is("BAJA"));
	}

	@Test @SuppressWarnings("deprecation")
	public void altaClliente() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(929, 918));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarCl:identMostrar")).click();
		driver.findElement(By.id("mostrarCl:identMostrar")).sendKeys("63937528N");
		driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(5) > i")).getText(), is("ALTA"));
		driver.findElement(By.cssSelector("div:nth-child(5) > button:nth-child(3)")).click();
		driver.findElement(By.id("fAlta")).click();
		driver.findElement(By.id("fAlta:darAlta")).click();
		driver.findElement(By.cssSelector(".content > div:nth-child(4) > button:nth-child(2)")).click();
		driver.findElement(By.cssSelector("html")).click();
		driver.findElement(By.id("mostrarCl:mostrarCliente")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(5) > i")).getText(), is("ALTA"));
	}
	@Test
	public void addPersonaAutorizadaEmpresa() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.cssSelector(".centrar-elem")).click();
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("div:nth-child(6) > button:nth-child(3)")).click();
		driver.findElement(By.id("fAPA:idEmpAPA")).click();
		driver.findElement(By.id("fAPA:idEmpAPA")).sendKeys("P3310693A");
		driver.findElement(By.id("fAPA:idPerAPA")).click();
		driver.findElement(By.id("fAPA:idPerAPA")).sendKeys("Victor");
		driver.findElement(By.id("fAPA:tipoAPA1")).sendKeys("Victor");
		driver.findElement(By.id("fAPA:tipoAPA2")).sendKeys("Baki");
		driver.findElement(By.id("fAPA:tipoAPA3")).sendKeys("Calle falsa");
		driver.findElement(By.id("fAPA:tipoAPA7")).click();
		driver.findElement(By.id("fAPA:tipoAPA")).click();
		driver.findElement(By.id("fAPA:tipoAPA")).sendKeys("Ninguna");
		driver.findElement(By.id("fAPA:addAutor")).click();
		driver.findElement(By.cssSelector("div:nth-child(6) > button:nth-child(2)")).click();
		driver.findElement(By.id("mostrarPersona:persMostrar")).click();
		driver.findElement(By.id("mostrarPersona:persMostrar")).sendKeys("victor");
		driver.findElement(By.id("mostrarPersona:mostrarCuentas")).click();
		assertThat(driver.findElement(By.cssSelector("#DatosPersona > p:nth-child(3) > i")).getText(), is("VICTOR"));
		driver.findElement(By.cssSelector("p:nth-child(11)")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(11) > i")).getText(), is("{PABLONOTOQUES S.A.(P3310693A)}"));
	}
	@Test
	public void addPersonaAutorizadaEmpresaYaExiste() {
		driver.get("http://0.0.0.0:8080/proyecto-war/");
		driver.manage().window().setSize(new Dimension(790, 866));
		driver.findElement(By.id("login:user")).click();
		driver.findElement(By.id("login:user")).sendKeys("ponciano");
		driver.findElement(By.id("login:pass")).sendKeys("ponciano");
		driver.findElement(By.id("login:botonLogin")).click();
		driver.findElement(By.cssSelector("div:nth-child(6) > button:nth-child(4)")).click();
		driver.findElement(By.id("cAPA:idEmpCPA")).click();
		driver.findElement(By.id("cAPA:idEmpCPA")).click();
		driver.findElement(By.id("cAPA:idEmpCPA")).sendKeys("P3310693A");
		driver.findElement(By.id("cAPA:idPerCPA")).click();
		driver.findElement(By.id("cAPA:idPerCPA")).click();
		driver.findElement(By.id("cAPA:idPerCPA")).sendKeys("PATest");
		driver.findElement(By.id("cAPA:tipoCPA")).sendKeys("Ninguna");
		driver.findElement(By.id("cAPA:addAutorC")).click();
		driver.findElement(By.cssSelector("div:nth-child(6) > button:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#mostrarPersona > p")).click();
		driver.findElement(By.id("mostrarPersona:persMostrar")).click();
		driver.findElement(By.id("mostrarPersona:persMostrar")).sendKeys("PATest");
		driver.findElement(By.id("mostrarPersona:persMostrar")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("#DatosPersona > p:nth-child(3)")).click();
		assertThat(driver.findElement(By.cssSelector("#DatosPersona > p:nth-child(3) > i")).getText(), is("PATEST"));
		driver.findElement(By.cssSelector("p:nth-child(11)")).click();
		assertThat(driver.findElement(By.cssSelector("p:nth-child(11) > i")).getText(), is("{PABLONOTOQUES S.A.(P3310693A)}"));
	}
}