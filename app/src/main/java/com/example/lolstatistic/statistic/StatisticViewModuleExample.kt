package com.example.lolstatistic.statistic

import android.nfc.tech.IsoDep.get
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.appcompat.widget.ResourceManagerInternal.get
import androidx.lifecycle.ProcessLifecycleOwner.get
import com.squareup.picasso.Picasso.get
import okhttp3.internal.platform.Platform.get
import okhttp3.internal.publicsuffix.PublicSuffixDatabase.get
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

class StatisticViewModuleExample {
    val statisticModule = module {
        viewModel { (StatisticViewModel()) }
    }
}