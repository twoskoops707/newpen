package com.twoskoops707.newpen

import android.content.Context
import com.twoskoops707.newpen.data.models.WifiBoard

object DeviceProfileManager {
    private const val PREF_FILE = "penthis_prefs"
    private const val PREF_WIFI_BOARD = "wifi_board"

    fun getBoard(context: Context): WifiBoard {
        val id = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
            .getString(PREF_WIFI_BOARD, WifiBoard.AWOK_DUAL_MINI_V3.id)
        return WifiBoard.entries.find { it.id == id } ?: WifiBoard.AWOK_DUAL_MINI_V3
    }

    fun saveBoard(context: Context, board: WifiBoard) {
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
            .edit().putString(PREF_WIFI_BOARD, board.id).apply()
    }
}
