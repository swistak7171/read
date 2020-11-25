package pl.kamilszustak.read.work.util

import androidx.work.Data
import androidx.work.ListenableWorker

fun <T> Result<T>.toWorkResult(outputData: Data = Data.EMPTY): ListenableWorker.Result =
    when {
        this.isSuccess -> ListenableWorker.Result.success(outputData)
        else -> ListenableWorker.Result.failure(outputData)
    }