# examenAndroid2
 
link al repositorio: 

## Descripción 

En este programa se nos pedía crear una lista de la compra donde se mostrara el precio y los productos finales. 

Para esto se ha hecho uso de una data class de Producto con todos sus atributos y de una clase donde se implementará toda la lógica. 

### Main Activity

Esta activity contiene toda la lógica del programa:

Clase MainActivity
    Variables
        textoDatos: TextView
        lista: ListView
        txtProducto: EditText
        txtCantidad: EditText
        txtPrecio: EditText
        btnAñadir: Button
        adapter: ArrayAdapter
        productosLista: Lista mutable de Producto
        productoConPrecio: Lista mutable de Producto

    Método onCreate()
        Inicializar la vista con layout "activity_main"
        Asignar referencias a las variables de vista
        Inicializar productosLista y productoConPrecio como listas vacías
        Establecer texto de textoDatos como "Lista de la compra vacía"
        Inicializar adapter con una lista vacía
        Asignar adapter a lista
        Establecer click listener en btnAñadir que llama a agregarProducto()

    Método agregarProducto()
        Leer nombre del producto desde txtProducto
        Leer cantidad desde txtCantidad y convertir a entero (si no es válido, usar 1)
        Leer precio desde txtPrecio y convertir a entero (si no es válido, usar 0)

        Si producto está vacío
            Mostrar mensaje "Debe ingresar un nombre de producto"

        Crear un nuevo Producto con nombre, cantidad y precio
        Agregar productoNuevo a productosLista

        Si productoNuevo.precio no es 0
            Agregar productoNuevo a productoConPrecio

        Limpiar txtProducto, txtCantidad y txtPrecio

        Llamar a actualizarLista()

    Método actualizarLista()
        Limpiar adapter
        total = 0

        Para cada producto en productosLista
            Crear itemString con formato "${producto.nombre} (${producto.cantidad}) = ${producto.precio}€"
            Agregar itemString a adapter
            Notificar cambios en el adapter
            total = total + (producto.precio * producto.cantidad)

        Establecer texto de textoDatos como "Lista de la compra: ${productoConPrecio.size} = $total €"


Esta clase está formada por varios TextEdits con un botón para añadir productos y un ListView para que estos se muestren por pantalla. 

Ademas tendrá un texto que muestra el precio total de la compra y todos los productos que lleva, este texto se va actualizando.
