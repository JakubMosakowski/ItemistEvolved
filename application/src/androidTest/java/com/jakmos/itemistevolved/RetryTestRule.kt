package com.jakmos.itemistevolved

import com.jakmos.itemistevolved.utility.log.ILogger
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Retry test rule used to retry test that failed.
 * Retry failed test 3 times.
 * Thanks to: https://andresand.medium.com/retry-testrule-for-android-espresso-tests-74683ee3b845
 */
class RetryTestRule(val retryCount: Int = 3) : TestRule {

  override fun apply(base: Statement, description: Description): Statement {
    return statement(base, description)
  }

  private fun statement(base: Statement, description: Description): Statement {
    return object : Statement() {

      override fun evaluate() {
        var caughtThrowable: Throwable? = null

        // implement retry logic here
        for (i in 0 until retryCount) {
          try {
            base.evaluate()
            return
          } catch (t: Throwable) {
            caughtThrowable = t
            ILogger.e(description.displayName + ": run " + (i + 1) + " failed")
          }
        }

        ILogger.e(description.displayName + ": giving up after " + retryCount + " failures")

        throw caughtThrowable!!
      }
    }
  }
}
