package cl.stephanielazo.stephanielazo1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cantidadPastelDeChoclo = findViewById<EditText>(R.id.editCantidadPastelDeChoclo)
        val subtotalPastelDeChoclo = findViewById<TextView>(R.id.textSubtotalPastelDeChoclo)

        val cantidadCazuela = findViewById<EditText>(R.id.editCantidadCazuela)
        val subtotalCazuela = findViewById<TextView>(R.id.textSubtotalCazuela)

        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        val textTotalSinPropina = findViewById<TextView>(R.id.textTotalSinPropina)
        val textMontoPropina = findViewById<TextView>(R.id.textMontoPropina)
        val textTotalConPropina = findViewById<TextView>(R.id.textTotalConPropina)
        val buttonLimpiar = findViewById<Button>(R.id.buttonLimpiar)


        val pastelDeChocloPrecio = 12000
        val cazuelaPrecio = 10000


        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("es", "CL"))


        val calcularTotales = {
            val pastelDeChocloCantidad = cantidadPastelDeChoclo.text.toString().toIntOrNull() ?: 0
            val cazuelaCantidad = cantidadCazuela.text.toString().toIntOrNull() ?: 0

            val subtotalPastel = pastelDeChocloCantidad * pastelDeChocloPrecio
            val subtotalCazuelaValor = cazuelaCantidad * cazuelaPrecio

            val totalSinPropina = subtotalPastel + subtotalCazuelaValor
            val propina = if (switchPropina.isChecked) totalSinPropina * 0.1 else 0.0
            val totalConPropina = totalSinPropina + propina


            subtotalPastelDeChoclo.text = "Subtotal: ${currencyFormatter.format(subtotalPastel)}"
            subtotalCazuela.text = "Subtotal: ${currencyFormatter.format(subtotalCazuelaValor)}"


            textTotalSinPropina.text = "Comida: ${currencyFormatter.format(totalSinPropina)}"
            textMontoPropina.text = "Propina: ${currencyFormatter.format(propina)}"
            textTotalConPropina.text = "TOTAL: ${currencyFormatter.format(totalConPropina)}"
        }


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calcularTotales()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        cantidadPastelDeChoclo.addTextChangedListener(textWatcher)
        cantidadCazuela.addTextChangedListener(textWatcher)
        switchPropina.setOnCheckedChangeListener { _, _ -> calcularTotales() }

       
        buttonLimpiar.setOnClickListener {
            cantidadPastelDeChoclo.text.clear()
            cantidadCazuela.text.clear()
            switchPropina.isChecked = false
            subtotalPastelDeChoclo.text = "Subtotal: ${currencyFormatter.format(0)}"
            subtotalCazuela.text = "Subtotal: ${currencyFormatter.format(0)}"
            textTotalSinPropina.text = "Comida: ${currencyFormatter.format(0)}"
            textMontoPropina.text = "Propina: ${currencyFormatter.format(0)}"
            textTotalConPropina.text = "TOTAL: ${currencyFormatter.format(0)}"
        }
    }
}
