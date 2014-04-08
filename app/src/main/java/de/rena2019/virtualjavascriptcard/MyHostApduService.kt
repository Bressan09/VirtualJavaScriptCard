package de.rena2019.virtualjavascriptcard

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class MyHostApduService: HostApduService() {
    private var countCommand = -1;
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        Log.e("TESTE", commandApdu!!.toHexString())

        countCommand++
        when (countCommand) {
            0 -> {
                return "6F23840E325041592E5359532E4444463031A511BF0C0E610C4F07A00000000410108701019000".decodeHex()
            }
            1 -> {
                return "6f1a8407a0000000041010a50f500d4d4348495020426173652030319000".decodeHex()
            }
            2 -> {
                return "771a82025980941408010100100101011801010018020200200102009000".decodeHex()
            }
        }
        return "6A82".decodeHex()
    }

    fun String.decodeHex(): ByteArray {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
                .map { it.toInt(16).toByte() }
                .toByteArray()
    }

    private fun ByteArray.toHexString() : String {
        return this.joinToString("") {
            java.lang.String.format("%02x", it)
        }
    }

    override fun onDeactivated(reason: Int) {
        //TODO("Not yet implemented")
    }

}