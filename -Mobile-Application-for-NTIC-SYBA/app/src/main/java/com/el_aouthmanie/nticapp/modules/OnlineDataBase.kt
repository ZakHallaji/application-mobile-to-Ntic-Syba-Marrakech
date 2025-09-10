package com.el_aouthmanie.nticapp.modules

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.el_aouthmanie.nticapp.controllers.UserAuthRole
import com.el_aouthmanie.nticapp.controllers.toUserAuthRole
import com.el_aouthmanie.nticapp.modules.intities.Notification
import com.el_aouthmanie.nticapp.modules.intities.Seance
import com.el_aouthmanie.nticapp.modules.intities.User
import com.el_aouthmanie.nticapp.ui.screens.scheduleScreen.dayIndex
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
val NORMALE_END_TIME = LocalTime.parse("18:30")

//todo handle firebase and online data
object OnlineDataBase {
    private val client by lazy {
        OkHttpClient()
    }
    private val API_URL = "http://eplanner-syba.somee.com/Service1.asmx/ListeSeancesGrp"
    private val REQUEST_BODY = "application/json; charset=utf-8"

    private val PREF_NAME = "loginInfo"

    private val KEY_IS_LOGGED_IN = "isLoged"
    private val KEY_NAME = "name"
    private val KEY_GROUP = "group"
    private val KEY_ROLE = "role"

    private val daysInApi = dayIndex.map { it.key.lowercase() }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNextClass(realm: Realm, scope: CoroutineScope): Seance? {


        val currentTime = LocalTime.now()
        val todayName = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale("fr"))

        val allSeances = realm.query<Seance>().find().toList()

        val onGoing = allSeances.firstOrNull {
            it.dayName.equals(todayName, true) &&
                    LocalTime.parse(it.startingTime) < currentTime &&
                    LocalTime.parse(it.endingTime) > currentTime
        }
        if (onGoing is Seance) {
            return onGoing
        }

        val nextClass = allSeances.filter {
            it.dayName.equals(todayName, true) &&
                    LocalTime.parse(it.startingTime) > currentTime
        }.minByOrNull {
            LocalTime.parse(it.startingTime)
        }

        if (nextClass is Seance) {
            return nextClass
        }

        // get the class in the next day

        val nextDayClass = allSeances
            .groupBy { it.dayName }
            .entries
            .firstOrNull {
                Log.d("Debug", "Day in API: ${it.key}, Today: $todayName")
                daysInApi.indexOf(it.key) > daysInApi.indexOf(todayName)
            }
            ?.value
            ?.sortedBy {
                LocalTime.parse(it.startingTime)
            }?.first()

        return nextDayClass
    }


    suspend fun syncClasses(
        grp: String, periode: String,
        realm: Realm, scope: CoroutineScope,
        onFailureResponse: () -> Unit = {},
        onComplete: () -> Unit
    ) {

        val json = """
    {
        "user": "$grp",
        "pass": "$grp",
        "groupe": "$grp",
        "periode": "$periode"
    }
    """.trimIndent()

        val request = Request.Builder()
            .url(API_URL)
            .post(json.toRequestBody(REQUEST_BODY.toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailureResponse()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let {
                    val jsonResponse = it.string()
                    scope.launch {
                        parseResponse(jsonResponse, realm)
                        onComplete()
                    }
                }
            }
        })
    }

    suspend fun parseResponse(jsonString: String, realm: Realm) {
        Log.d("onDb", "fetched data : $jsonString")
        val jsonObject = JSONObject(jsonString)
        val dataArray = JSONArray(jsonObject.getString("d"))

        realm.write {
            // Delete all existing Seance objects before inserting new data
            delete(Seance::class)

            for (i in 0 until dataArray.length()) {
                val seanceJson = dataArray.getJSONObject(i)

                val seance = Seance().apply {
                    id = seanceJson.optString("CodeSeance", "")
                    dayName = seanceJson.optString("Jour", "")
                    codeSeance = seanceJson.optString("CodeSeance", "")
                    nomMode = seanceJson.optString("Nom_mode", "")
                    classRoom = seanceJson.optString("NumSalle", "N/A")
                    teacher = seanceJson.optString("Formateur", "")
                    intitule = seanceJson.optString("Intitule", "")
                    moduleDetails = seanceJson.optString("Detail_module", "")
                    startingTime = seanceJson.optString("Heure_debut", "")
                    endingTime = seanceJson.optString("Heure_fin", "")
                }

                copyToRealm(
                    seance,
                    updatePolicy = UpdatePolicy.ALL
                ) // **Upsert to avoid duplicates**
            }
        }
    }


    fun saveLoginState(
        context: Context,
        isLoggedIn: Boolean,
        authRole: UserAuthRole = UserAuthRole.Guest,
        name: String,
        group: String
    ) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.putString(KEY_ROLE, authRole.toString())
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_GROUP, group)
        editor.apply()
    }

    fun getGroup(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return sharedPreferences.getString(KEY_GROUP, "N/A") ?: "N/A" // Default to false if not set
    }

    // Check login state from SharedPreferences
    fun isUserLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false) // Default to false if not set
    }

    fun getUserLoginState(context: Context): User {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        return User(
            name = sharedPreferences.getString(KEY_NAME, "Guest") ?: "Guest",
            group = sharedPreferences.getString(KEY_GROUP, " - ") ?: " - ",
            authRole = sharedPreferences.getString(KEY_ROLE, "Guest")?.toUserAuthRole()
                ?: UserAuthRole.Guest
        )
    }

    suspend fun addAnnouncmentToHistory(
        realm: Realm,
        notification: Notification
    ){
        realm.write {
            copyToRealm(notification)
        }
    }
}
