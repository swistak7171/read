package pl.kamilszustak.read.work.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import pl.kamilszustak.read.work.ListenableWorkerFactory
import timber.log.Timber

class ReadingGoalNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        Timber.i("THIS IS NOTIFICATION")
        return Result.success()
    }

    companion object {
        const val NAME: String = "reading_goal_notification_worker"
    }

    @AssistedInject.Factory
    interface Factory : ListenableWorkerFactory
}