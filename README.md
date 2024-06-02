PROYECTO
He creado una aplicación web con angular conectada a una base de datos con spring en la que se almacena información del centro de estudio Cardenal Ciseneros. 
Se puede agregar, editar o eliminar carreras o másters además de tener una visión de las "Top Carreras" y otra en la que se ve todo.
Además hay una sección de mensajes en la que se ve todo lo que se va haciendo.

QUE SE HA IMPLEMENTADO
-Aplicación Angular que hace peticiones REST a un servidor Spring que se conecta a una basede datos, 
para manejar datos de alguna entidad relacionada con la feria Aula.
-Consulta, modificación, eliminación.
-Mínimo dos pantallas capaz de listar todos los elementos y añadir nuevos.
-API REST que responda mínimo a verbo POST para crear elementos y verbo GET para recuperar todos los elementos de una BBDD MySQL con una tabla de 5 campos.
-Verbo GET para recuperar un elemento de la BBDD por id.
-Modificación ½ Formulario de modificación de los elementos.
-Verbo PUT para actualizar un elemento en la BBDD.
-Eliminación: Botón de eliminación de un elemento.
-Verbo DELETE para eliminar un elemento de la BBDD.
-Búsqueda 2 Paginación y ordenación y criterio de búsqueda.
-Verbo GET con paginación, ordenación y filtrado.
-10 tests unitarios que funcionan.

ANOTACIONES
Tras mucho intenetarlo el buscar carreras no funciona, otra de las funciones que no tiene una función tan buena como me gustaría es el añadir carreras ya que
como el id es primary key no puedes remplazarla.
