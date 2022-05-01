# Proyecto con anotaciones Java para la asignatura de Sistemas de Información para Internet

Esta asignatura se imparte en 3er curso del Grado en Ingeniería Informmática de la Universidad de Málaga.

Las anotaciones se utilizan en las prácticas de la asignatura. Las anotaciones definidas por el momento son:

* `@Requisitos`: para indicar con qué requisitos están relacionados los métodos o clases de un proyecto. Se utilizará para anotar los métodos de prueba de JUnit. Admite un array de String que puede fijarse sin nombre, porque el elemento utilizado es `value`. Por ejemplo:
```
@Requisitos({"RF1", "RF7"}) 
@Test
public void testInsertarAlumno() {
    ...
}
```

## Instalación del paquete en el reposositorio local

1. Clonar este repositorio con `git clone`
2. Dentro de la carpeta del proyecto ejecutar `mvn install`

## Uso del paquete

Para usar la anotación en un proyecto hay que incluir la dependencia (una vez instalada en el respositorio local) en el `pom.xml` de proyecto. Las coordenadas maven son:

```
<dependency>
    <groupId>es.uma.informatica.sii</groupId>
    <artifactId>anotaciones</artifactId>
    <version>1.0.0</version>
</dependency>
```
