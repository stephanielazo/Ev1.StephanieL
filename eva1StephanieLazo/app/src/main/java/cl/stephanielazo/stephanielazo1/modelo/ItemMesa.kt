package cl.stephanielao.stephanielazo1.modelo

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {
    fun calcularSubtotal(): Int {
        return cantidad * itemMenu.precio
    }
}
