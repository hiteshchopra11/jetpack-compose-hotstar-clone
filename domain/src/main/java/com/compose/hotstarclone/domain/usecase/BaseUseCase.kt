package com.compose.hotstarclone.domain.usecase

interface BaseUseCase<out Result, in ExecutableParam> {

  /**
   * Perform an operation with no input parameters.
   * Will throw an exception by default, if not implemented but invoked.
   *
   * @return
   */
  suspend fun performAsync(): Result = throw NotImplementedError()

  /**
   * Perform an operation.
   *  Will throw an exception by default, if not implemented but invoked.
   *
   * @param params
   * @return
   */
  suspend fun performAsync(params: ExecutableParam): Result = throw NotImplementedError()

  /**
   * Perform an operation with no input parameters.
   * Will throw an exception by default, if not implemented but invoked.
   *
   * @return
   */
  fun perform(): Result = throw NotImplementedError()

  /**
   * Perform an operation.
   *  Will throw an exception by default, if not implemented but invoked.
   *
   * @param params
   * @return
   */
  fun perform(params: ExecutableParam): Result = throw NotImplementedError()
}
