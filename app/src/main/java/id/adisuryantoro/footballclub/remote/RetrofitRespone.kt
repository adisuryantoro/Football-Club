package id.adisuryantoro.footballclub.remote

data class RetrofitResponse(
    var isSuccess: Boolean,
    var message: String,
    var responseBody: Any?
)