package com.example.alejandro.postit.network.interceptor

import okhttp3.Interceptor
import java.io.IOException
import java.net.SocketTimeoutException

class NonSecurityInterceptor : Interceptor {

    /**
     * Intercepts the chain and calls onOnIntercept defined method
     * @param chain to be intercepted
     * @return the result specified in onOnIntercept method
     * @throws IOException thrown
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response? {
        return onOnIntercept(chain)
    }

    /**
     * Definition of an interceptor in order to handle Time Out Exceptions
     */
    @Throws(IOException::class)
    private fun onOnIntercept(chain: Interceptor.Chain): okhttp3.Response? {
        try {
            return chain.proceed(chain.request())
        } catch (exception: SocketTimeoutException) {
            return null
        }

    }
}