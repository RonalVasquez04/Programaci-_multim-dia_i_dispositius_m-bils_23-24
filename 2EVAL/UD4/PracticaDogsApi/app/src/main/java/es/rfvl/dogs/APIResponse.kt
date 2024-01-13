package es.rfvl.dogs

import com.google.gson.annotations.SerializedName

data class APIResponse (
    @SerializedName("status") var status:String,
    @SerializedName("message") var images:List<String>
)