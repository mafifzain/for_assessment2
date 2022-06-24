package org.d3if0060.assessment2.data

import android.app.Application

class TokoApplication : Application() {
    val database: TokoDb by lazy { TokoDb.getDataDb(this) }
}