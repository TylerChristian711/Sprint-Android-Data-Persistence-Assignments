package com.lambdaschool.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import com.lambdaschool.sharedprefs.model.JournalEntry
import java.lang.StringBuilder

// TODO: 15. A Shared Preferences helper class
class Prefs(context:Context) {
    // TODO: 16. KEYS for Shared Preferences can be defined as Constants here
    companion object {
        private const val JOURTNAL_PREFERENCES = "JourtnalPrefrences"
        private const val  ID_LIST_KEY = "id_List"
        private const val NEXT_ID_KEY = "next_id"
        private const val ENTRY_ID_KEY_PREFIX= "entry"
    }

    val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(JOURTNAL_PREFERENCES, Context.MODE_PRIVATE)


    // TODO: 17. Each Journal Entry will be its own entry in shared preferences
       fun createEntry(entry: JournalEntry){

        val ids = getListOfIds()

        if (entry.id == JournalEntry.INVALID_ID && !ids.contains(entry.id.toString())) {
            val editor = sharedPrefs.edit()

            var nextId = sharedPrefs.getInt(NEXT_ID_KEY, 0)
            entry.id = nextId

            editor.putInt(NEXT_ID_KEY,nextId++)

            ids.add(entry.id.toString())
            val newIdList = StringBuilder()
            for (id in ids) {
                newIdList.append(id).append(",")
            }
            editor.putString(ID_LIST_KEY, newIdList.toString())

            editor.putString(ENTRY_ID_KEY_PREFIX + entry.id.toString(), entry.toCsvString())
            editor.commit()
        }else{
            updateEntry(entry)
        }

    }

    // TODO: 18. IDs are all stored as a CSV list in one SharedPreferences entry
       private fun getListOfIds():ArrayList<String>{
        val idList = sharedPrefs.getString(ID_LIST_KEY,"")?:""
        val oldList = idList?.split(",")

        val ids = ArrayList<String>(oldList.size)
        if (idList.isNotBlank()){
            idList.addAll(oldList)
        }
        return ids
    }

    fun readEntry(id:Int):JournalEntry?{
        val entryAsCsv = sharedPrefs.getString(ENTRY_ID_KEY_PREFIX + id, "invalid")
        return entryAsCsv?.let {
            (entryAsCsv)}

    }



    // TODO: 19. This collects all known entries in Shared Preferences, with the help of the ID List
       fun readAllEnTries():MutableList<JournalEntry>{
        val ListOFIds = getListOfIds()

        val enttyList = mutableListOf<JournalEntry>()
        for (id in ListOFIds){
            readEntry(id.toInt())?.let {
                enttyList.add(it)
            }
            return enttyList
        }

        return mutableListOf()
    }

    // TODO: 20. This is another way to define a SharedPreferences item
    // In Activity, can simply use: prefs.bgColor (to get and set)

    // TODO: 21. Update an entry - use CSV technique to "serialize" a Journal Entry
    // edit an existing entry
    fun updateEntry(entry: JournalEntry){
        val editor = sharedPrefs.edit()
        editor.putString(ENTRY_ID_KEY_PREFIX + entry.id,entry.toCsvString())
        editor.apply()
    }
}