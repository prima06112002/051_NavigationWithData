package com.example.estehpam

import androidx.lifecycle.ViewModel
import com.example.estehpam.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 3000
class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI:StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setJumlah(jmEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmEsJumbo,
                harga = hitungHarga(jumlah = jmEsJumbo)
            )
        }
    }
    fun setRasa(rasaPilihan: String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(rasa = rasaPilihan)
        }
    }
    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ) : String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
    fun setContact(ListData: MutableList<String>){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = ListData[0],
                alamat = ListData[1],
                telpon = ListData[2]

            )
        }
    }
}