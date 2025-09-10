package com.el_aouthmanie.nticapp.modules.guides

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // Perform background task
        Log.d("MyWorker", "Background task is executing")

        // Simulating some background task
        try {
            Thread.sleep(2000) // Simulate a task that takes time
        } catch (e: InterruptedException) {
            return Result.failure()
        }

        return Result.success()
    }



}