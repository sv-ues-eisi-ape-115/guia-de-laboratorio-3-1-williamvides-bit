[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/hUGGdU9O)
# Laboratorio G0301: Paneles, Bordes y Contenedores Internos
**Asignatura:** Aplicaciones de Escritorio (APE 115) | **Ciclo:** III/2026  
**Institución:** Universidad de El Salvador - Facultad de Ingeniería y Arquitectura  

## Descripción
Este repositorio contiene la implementación del **Laboratorio 3.1**, enfocado en el diseño profesional de interfaces gráficas de escritorio con **Java Swing**. Se abordan los seis administradores de disposición (`Layout Managers`) principales, la aplicación estratégica de bordes mediante `BorderFactory`, y el uso de contenedores especializados como `JSplitPane`, `JTabbedPane` y la arquitectura MDI (`JDesktopPane` + `JInternalFrame`).

## Objetivos
- Organizar interfaces usando `BorderLayout`, `FlowLayout`, `GridLayout`, `GridBagLayout`, `BoxLayout` y `CardLayout`.
- Construir formularios complejos anidando `JPanel` y combinando múltiples Layout Managers.
- Aplicar bordes (`LineBorder`, `TitledBorder`, `EtchedBorder`, `CompoundBorder`, `EmptyBorder`) para estructurar visualmente las secciones.
- Implementar vistas maestro-detalle y navegación multi-sección con `JSplitPane` y `JTabbedPane`.

## Estructura del Proyecto
El proyecto sigue estrictamente el patrón **MVC** y la jerarquía de paquetes definida en la guía:
```
SistemaInventarioAPE115/
├── src/main/java/sv/edu/ues/ape115/layouts/
│   ├── app/              # Punto de entrada (Main.java)
│   ├── ui/               # Vistas Swing (VentanaProductos, ConfigView, etc.)
│   ├── controller/       # Controladores MVC
│   ├── model/            # Entidades (Producto, Empleado, AppConfig, etc.)
│   ├── dao/              # Acceso a datos
│   └── util/             # Utilidades (Validador, SwingUtil, AppConstants)
└── pom.xml               # Configuración Maven + maven-shade-plugin
```

## Instalación y Ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/sv-ues-eisi-ape-115/g0301.git
   cd g0301
   ```
2. Compila el proyecto con Maven:
   ```bash
   mvn clean compile
   ```
3. Ejecuta la aplicación desde tu IDE o línea de comandos:
   ```bash
   mvn exec:java -Dexec.mainClass="sv.edu.ues.ape115.layouts.app.Main"
   ```

## Entregables y Ejercicios
| Módulo | Clase Principal | Descripción |
|--------|----------------|-------------|
| **Ejemplo** | `VentanaProductos.java` | Ventana de gestión con los 6 layouts, `JSplitPane`, `JTabbedPane` y bordes compuestos. |
| **R01** | `VitrinaLayouts.java` | Muestra los 6 Layout Managers en un `GridLayout(2,3)` con `TitledBorder` por celda y `CardLayout` navegable. |
| **R02** | `FormularioCliente.java` | Formulario con `JTabbedPane` (2 pestañas), `GridBagLayout` y validación de campos obligatorios. |
| **R03** | `AppMenuLateral.java` | Menú lateral (`BoxLayout.Y_AXIS`) que navega vistas en `CardLayout`. Botón activo con cambio de color. |
| **R04** | `VistaMaestroDetalle.java` | `JSplitPane` horizontal: `JList` de productos (maestro) + formulario `GridBagLayout` (detalle). |
| **R05** | `DashboardIntegrado.java` | Dashboard completo combinando todos los layouts, `JTabbedPane` (3 tabs) y bordes variados. |

## Estándares de Desarrollo Obligatorios
**Prohibido:** Uso de `null layout` (rompe accesibilidad y redimensionamiento).  
**Bordes:** Cada panel de sección debe tener al menos un `BorderFactory`.  
**UI Helper:** Los botones se generan mediante `crearBoton(label, color)`.  
**Arquitectura:** Patrón MVC estricto. Cada componente Swing declarado como atributo de clase.  
**Validación:** Validación en tiempo real de entradas y principios de seguridad informática.  
**Redimensionamiento:** Todos los layouts deben adaptarse correctamente al cambiar el tamaño de la ventana.

## Envío y Evaluación Automática
La evaluación se ejecuta automáticamente mediante **GitHub Actions** al hacer push. Asegúrate de que el código compile localmente antes de enviar.
```bash
git add --all
git commit -m "Completo G0301 Layout Managers y Bordes"
git push origin main
```
Verifica el progreso en la pestaña **Actions** de tu repositorio. Puedes hacer múltiples pushes; cada uno desencadena una nueva evaluación y actualiza la calificación.

## Rúbrica de Evaluación (Automática)
| Criterio | Puntos | Verificación Clave |
|----------|--------|-------------------|
| Ejemplo `VentanaProductos` | 15 | Extiende `JFrame`, usa `JSplitPane`, `JTabbedPane`, `JList`, `CompoundBorder` |
| R01 `VitrinaLayouts` | 15 | `GridLayout(2,3)`, `CardLayout` funcional, `TitledBorder` en celdas, ≥6 botones |
| R02 `FormularioCliente` | 15 | `JTabbedPane` ≥2 tabs, `JTextArea` en `JScrollPane`, `CompoundBorder` |
| R03 `AppMenuLateral` | 15 | `BoxLayout.Y_AXIS`, `CardLayout`, ≥5 botones, `VerticalGlue` para Salir |
| R04 `VistaMaestroDetalle` | 20 | `JSplitPane` con `oneTouchExpandable` y `resizeWeight(0.30)`, `JList` cargada |
| R05 `DashboardIntegrado` | 15 | Todos los layouts integrados, `JTabbedPane` ≥3 tabs, `setMinimumSize(900,550)` |
| Estándares Generales | 5 | MVC, sin `null layout`, paquetes correctos, `Producto.java` y `Main.java` presentes |
**Total:** 100 pts

---
```

### Notas para el despliegue:
1. Si tu `artifactId` en `pom.xml` es diferente, ajusta el comando de ejecución en la sección.
2. La rúbrica y los estándares reflejan **exactamente** la tabla de evaluación automática (`Checklist del estudiante`) de la guía, por lo que GitHub Actions validará estos puntos.
