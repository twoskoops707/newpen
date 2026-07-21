package com.twoskoops707.newpen

import android.content.Context
import android.graphics.Color

enum class AppTheme(
    val id: String,
    val displayName: String,
    val desc: String,
    val accentHex: String,
    val styleRes: Int
) {
    TERMINAL("terminal", "TERMINAL", "classic green on black", "#22C55E", R.style.Theme_PenThis_Terminal),
    PHOSPHOR("phosphor", "PHOSPHOR", "amber warm glow", "#F97316", R.style.Theme_PenThis_Phosphor),
    ARCTIC("arctic", "ARCTIC", "cyan — sharp outdoors", "#06B6D4", R.style.Theme_PenThis_Arctic),
    MIDNIGHT("midnight", "MIDNIGHT", "purple for night use", "#A855F7", R.style.Theme_PenThis_Midnight),
    SOLAR("solar", "SOLAR", "yellow — max sun readability", "#FBBF24", R.style.Theme_PenThis_Solar),
    GHOST("ghost", "GHOST", "near-white high contrast", "#CBD5E1", R.style.Theme_PenThis_Ghost);

    fun accentColor(): Int = Color.parseColor(accentHex)
}

object ThemeManager {
    private const val PREF_FILE = "penthis_prefs"
    private const val PREF_THEME = "theme_id"

    fun getCurrent(context: Context): AppTheme {
        val id = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
            .getString(PREF_THEME, AppTheme.TERMINAL.id)
        return AppTheme.values().find { it.id == id } ?: AppTheme.TERMINAL
    }

    fun save(context: Context, theme: AppTheme) {
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
            .edit().putString(PREF_THEME, theme.id).apply()
    }

    fun apply(context: Context) {
        context.setTheme(getCurrent(context).styleRes)
    }
}
