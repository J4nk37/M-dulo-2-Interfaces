📘 GestorUsuariosApp

Aplicación de escritorio en Java Swing desarrollada con IntelliJ GUI Designer, que implementa un gestor de usuarios con navegación lateral, un panel central con distintas vistas y una zona de previsualización con Resumen y Logs.

✨ Características principales

Diseño profesional con BorderLayout
Cabecera, menú lateral, centro dinámico, previsualización y barra inferior.

Menú de navegación (izquierda)

Formulario

Dashboard

Usuarios

Informes

Ajustes

Ayuda

Panel central (CardLayout)
El contenido cambia según el botón de navegación:

Formulario: campos Nombre, Email, Rol, Activo, Notas.

Dashboard: métricas generales.

Usuarios: tabla editable + formulario para añadir usuarios.

Informes: lista de informes con botón para generar.

Ajustes: checkboxes de configuración.

Ayuda: FAQs y botón de abrir manual.

Panel de previsualización (derecha)

Pestaña Resumen (solo lectura).

Pestaña Logs (registro de acciones).

Barra inferior (derecha)
Botones Cancelar, Limpiar Logs y Guardar.

Redimensionable

Los campos del formulario crecen en horizontal.

El área de Notas crece en ambas direcciones.

El JTabbedPane se ajusta al alto de la ventana.