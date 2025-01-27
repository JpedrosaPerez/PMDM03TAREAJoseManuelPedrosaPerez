# Pokémon App

## Introducción

La aplicación **Pokémon App** es una plataforma que permite a los usuarios explorar y gestionar su colección de Pokémon. Los usuarios pueden ver la **Pokédex** para descubrir los Pokémon disponibles, gestionar los Pokémon que han capturado y ajustar preferencias como el idioma y la configuración de eliminación de Pokémon.

## Características principales

- **Autenticación con Firebase**: Los usuarios pueden iniciar sesión y cerrar sesión utilizando Firebase Authentication.
- **Pokédex**: Los usuarios pueden acceder a la Pokédex, donde se muestra información sobre los Pokémon disponibles, como su nombre, imagen, peso y altura.
- **Pokémon Capturados**: Los usuarios pueden ver la lista de los Pokémon que han capturado, con la opción de eliminarlos.
- **Ajustes**: En el menú de configuración, los usuarios pueden:
  - Cambiar el idioma de la aplicación entre **Español** e **Inglés**.
  - Activar o desactivar la eliminación de Pokémon.
  - Cerrar sesión.

## Tecnologías utilizadas

- **Firebase Authentication**: Para manejar el inicio y cierre de sesión de los usuarios.
- **Retrofit**: Para realizar las llamadas a la API de PokeAPI y obtener la información de los Pokémon.
- **RecyclerView**: Para mostrar las listas de Pokémon capturados y la Pokédex de manera eficiente.
- **SharedPreferences**: Para almacenar la configuración de la aplicación, como el idioma y el estado de las preferencias.

## Conclusiones del desarrollador

Este proyecto ha sido una excelente oportunidad para profundizar en el uso de **Firebase Authentication** y la integración de APIs externas como **PokeAPI**. Algunos de los desafíos que enfrenté incluyeron la gestión del estado de la sesión y la configuración de Firebase en el proyecto, lo que me permitió aprender mucho sobre la autenticación y cómo manejar la información de los usuarios de forma segura.

El uso de **RecyclerView** y **Retrofit** también fue esencial para optimizar la carga de datos de la Pokédex y la visualización de los Pokémon capturados, mejorando la experiencia del usuario.

## Instrucciones de uso

1. **Clonar el repositorio**:
   Abre una terminal y ejecuta el siguiente comando para clonar el repositorio:

   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
