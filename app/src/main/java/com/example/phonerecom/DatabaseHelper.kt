// app/src/main/java/com/example/phonerecom/DatabaseHelper.kt
package com.example.phonerecom


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_PHONE_TABLE)
        db.execSQL(CREATE_USER_TABLE)
        db.execSQL("INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD, $COLUMN_ROLE) VALUES ('a', 'a', 'admin')")
        db.execSQL("INSERT INTO $TABLE_USER ($COLUMN_USERNAME, $COLUMN_PASSWORD, $COLUMN_ROLE) VALUES ('b', 'b', 'user')")

        // Insert initial phone data
        val initialPhones = listOf(
            /*
            Phone(0, "Phone A", mapOf(
                "Software" to PhoneAttribute("Android 11", 8.5f),
                "Screen" to PhoneAttribute("6.5 inches", 9.0f),
                "Camera" to PhoneAttribute("12 MP", 8.0f),
                "Battery" to PhoneAttribute("4000 mAh", 7.5f),
                "Build_Quality" to PhoneAttribute("Metal", 8.0f),
                "Speaker" to PhoneAttribute("Stereo", 7.0f),
                "Microphone" to PhoneAttribute("Dual", 7.5f),
                "RAM" to PhoneAttribute("6 GB", 8.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                "CPU" to PhoneAttribute("Snapdragon 720G", 8.0f),
                "GPU" to PhoneAttribute("Adreno 618", 7.5f),
                "Size" to PhoneAttribute("160 x 75 x 8 mm", 7.0f),
                "Reviews" to PhoneAttribute("Good", 8.0f),
                "User_Opinions" to PhoneAttribute("Positive", 8.5f),
                "Popularity" to PhoneAttribute("High", 9.0f),
                "Price" to PhoneAttribute("$300", 8.0f)
            )),
            Phone(0, "Phone B", mapOf(
                "Software" to PhoneAttribute("iOS 14", 9.0f),
                "Screen" to PhoneAttribute("5.8 inches", 8.5f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("3000 mAh", 7.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Mono", 6.5f),
                "Microphone" to PhoneAttribute("Single", 7.0f),
                "RAM" to PhoneAttribute("4 GB", 7.5f),
                "Internal_Memory" to PhoneAttribute("64 GB", 7.0f),
                "CPU" to PhoneAttribute("A13 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.0f),
                "Size" to PhoneAttribute("150 x 70 x 7 mm", 8.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$700", 7.5f)
            )),
            Phone(0, "Phone C", mapOf(
                "Software" to PhoneAttribute("Android 10", 7.5f),
                "Screen" to PhoneAttribute("6.1 inches", 8.0f),
                "Camera" to PhoneAttribute("16 MP", 8.5f),
                "Battery" to PhoneAttribute("3500 mAh", 7.0f),
                "Build_Quality" to PhoneAttribute("Plastic", 6.5f),
                "Speaker" to PhoneAttribute("Mono", 6.0f),
                "Microphone" to PhoneAttribute("Single", 6.5f),
                "RAM" to PhoneAttribute("4 GB", 7.0f),
                "Internal_Memory" to PhoneAttribute("64 GB", 7.5f),
                "CPU" to PhoneAttribute("Snapdragon 665", 7.0f),
                "GPU" to PhoneAttribute("Adreno 610", 6.5f),
                "Size" to PhoneAttribute("155 x 75 x 8 mm", 7.0f),
                "Reviews" to PhoneAttribute("Average", 7.0f),
                "User_Opinions" to PhoneAttribute("Mixed", 6.5f),
                "Popularity" to PhoneAttribute("Medium", 7.0f),
                "Price" to PhoneAttribute("$200", 7.5f)
            )),
            Phone(0, "Phone D", mapOf(
                "Software" to PhoneAttribute("iOS 13", 8.5f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("3700 mAh", 7.5f),
                "Build_Quality" to PhoneAttribute("Glass", 8.5f),
                "Speaker" to PhoneAttribute("Stereo", 8.0f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("6 GB", 8.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                "CPU" to PhoneAttribute("A12 Bionic", 9.0f),
                "GPU" to PhoneAttribute("Apple GPU", 8.5f),
                "Size" to PhoneAttribute("160 x 75 x 7 mm", 8.0f),
                "Reviews" to PhoneAttribute("Good", 8.5f),
                "User_Opinions" to PhoneAttribute("Positive", 8.5f),
                "Popularity" to PhoneAttribute("High", 8.5f),
                "Price" to PhoneAttribute("$600", 8.0f)
            )),
            Phone(0, "Phone E", mapOf(
                "Software" to PhoneAttribute("Android 9", 6.5f),
                "Screen" to PhoneAttribute("5.5 inches", 7.0f),
                "Camera" to PhoneAttribute("8 MP", 6.5f),
                "Battery" to PhoneAttribute("3000 mAh", 6.0f),
                "Build_Quality" to PhoneAttribute("Plastic", 5.5f),
                "Speaker" to PhoneAttribute("Mono", 5.0f),
                "Microphone" to PhoneAttribute("Single", 5.5f),
                "RAM" to PhoneAttribute("3 GB", 6.0f),
                "Internal_Memory" to PhoneAttribute("32 GB", 6.5f),
                "CPU" to PhoneAttribute("Snapdragon 450", 6.0f),
                "GPU" to PhoneAttribute("Adreno 506", 5.5f),
                "Size" to PhoneAttribute("150 x 70 x 8 mm", 6.0f),
                "Reviews" to PhoneAttribute("Average", 6.0f),
                "User_Opinions" to PhoneAttribute("Mixed", 5.5f),
                "Popularity" to PhoneAttribute("Low", 6.0f),
                "Price" to PhoneAttribute("$150", 6.5f)
            )),
            Phone(0, "Phone F", mapOf(
                "Software" to PhoneAttribute("iOS 12", 7.5f),
                "Screen" to PhoneAttribute("5.8 inches", 8.0f),
                "Camera" to PhoneAttribute("12 MP", 8.5f),
                "Battery" to PhoneAttribute("3200 mAh", 7.0f),
                "Build_Quality" to PhoneAttribute("Glass", 8.0f),
                "Speaker" to PhoneAttribute("Stereo", 7.5f),
                "Microphone" to PhoneAttribute("Dual", 8.0f),
                "RAM" to PhoneAttribute("4 GB", 7.5f),
                "Internal_Memory" to PhoneAttribute("64 GB", 7.5f),
                "CPU" to PhoneAttribute("A11 Bionic", 8.0f),
                "GPU" to PhoneAttribute("Apple GPU", 7.5f),
                "Size" to PhoneAttribute("145 x 70 x 7 mm", 7.5f),
                "Reviews" to PhoneAttribute("Good", 8.0f),
                "User_Opinions" to PhoneAttribute("Positive", 8.0f),
                "Popularity" to PhoneAttribute("High", 8.0f),
                "Price" to PhoneAttribute("$500", 7.5f)
            )),
            Phone(0, "Phone G", mapOf(
                "Software" to PhoneAttribute("Android 11", 8.0f),
                "Screen" to PhoneAttribute("6.4 inches", 8.5f),
                "Camera" to PhoneAttribute("48 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.0f),
                "Build_Quality" to PhoneAttribute("Metal", 8.5f),
                "Speaker" to PhoneAttribute("Stereo", 8.0f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("160 x 75 x 8 mm", 8.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$800", 8.5f)
            )),
            Phone(0, "Phone H", mapOf(
                "Software" to PhoneAttribute("iOS 15", 9.0f),
                "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("3300 mAh", 7.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("6 GB", 8.5f),
                "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                "CPU" to PhoneAttribute("A14 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.0f),
                "Size" to PhoneAttribute("150 x 70 x 7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$900", 8.5f)
            )),
            Phone(0, "Phone I", mapOf(
                "Software" to PhoneAttribute("Android 12", 8.5f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("64 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Metal", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.5f),
                "GPU" to PhoneAttribute("Adreno 660", 9.0f),
                "Size" to PhoneAttribute("165 x 75 x 8 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1000", 9.0f)
            )),
            Phone(0, "Phone J", mapOf(
                "Software" to PhoneAttribute("iOS 16", 9.5f),
                "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                "Camera" to PhoneAttribute("12 MP", 9.5f),
                "Battery" to PhoneAttribute("3500 mAh", 8.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("A15 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("155 x 75 x 7 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1100", 9.0f)
            )),
            Phone(0, "Phone K", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.0f),
                "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("6000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Metal", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("1 TB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 1", 9.5f),
                "GPU" to PhoneAttribute("Adreno 730", 9.5f),
                "Size" to PhoneAttribute("170 x 80 x 8 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1200", 9.0f)
            )),
            Phone(0, "Phone L", mapOf(
                "Software" to PhoneAttribute("iOS 17", 9.5f),
                "Screen" to PhoneAttribute("6.9 inches", 9.5f),
                "Camera" to PhoneAttribute("12 MP", 9.5f),
                "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("A16 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("160 x 75 x 7 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1300", 9.0f)
            )),
            Phone(0, "Phone M", mapOf(
                "Software" to PhoneAttribute("Android 11", 8.0f),
                "Screen" to PhoneAttribute("6.3 inches", 8.5f),
                "Camera" to PhoneAttribute("48 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.0f),
                "Build_Quality" to PhoneAttribute("Metal", 8.5f),
                "Speaker" to PhoneAttribute("Stereo", 8.0f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("160 x 75 x 8 mm", 8.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$800", 8.5f)
            )),
            Phone(0, "Samsung Galaxy S24", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.2 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("4200 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("147 x 71 x 7.5 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$899", 9.0f)
            )),
            */
            Phone(0, "iPhone 16", mapOf(
                "Software" to PhoneAttribute("iOS 18", 9.5f),
                "Screen" to PhoneAttribute("6.2 inches", 9.5f),
                "Camera" to PhoneAttribute("48 MP", 9.5f),
                "Battery" to PhoneAttribute("3400 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                "CPU" to PhoneAttribute("A17 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("147 x 72 x 7.8 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 9.0f)
            )),
            Phone(0, "Google Pixel 8", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.4 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4500 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                "CPU" to PhoneAttribute("Google Tensor G3", 9.0f),
                "GPU" to PhoneAttribute("Mali-G710 MP8", 8.5f),
                "Size" to PhoneAttribute("156 x 74 x 8.5 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$699", 8.5f)
            )),
            Phone(0, "OnePlus 12", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5200 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("164 x 75 x 8.5 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$899", 9.0f)
            )),
            Phone(0, "Xiaomi 14 Pro", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.75 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("163 x 75 x 8.4 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 9.0f)
            )),
            Phone(0, "Sony Xperia 1 VI", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                "Camera" to PhoneAttribute("48 MP", 9.5f),
                "Battery" to PhoneAttribute("5100 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("166 x 72 x 8.2 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1299", 9.0f)
            )),
            Phone(0, "Oppo Find X6 Pro", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5200 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("164 x 75 x 8.5 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1199", 9.0f)
            )),
            Phone(0, "Huawei P70 Pro", mapOf(
                "Software" to PhoneAttribute("HarmonyOS 4.0", 9.0f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Kirin 9000S", 9.5f),
                "GPU" to PhoneAttribute("Mali-G78 MP24", 9.0f),
                "Size" to PhoneAttribute("162 x 75 x 8.3 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1099", 9.0f)
            )),
            Phone(0, "Asus ROG Phone 8", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.5f),
                "Screen" to PhoneAttribute("6.8 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("6000 mAh", 9.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.5f),
                "Microphone" to PhoneAttribute("Dual", 9.5f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("1 TB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 3", 9.5f),
                "GPU" to PhoneAttribute("Adreno 750", 9.5f),
                "Size" to PhoneAttribute("174 x 78 x 10.3 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1099", 9.0f)
            )),
            Phone(0, "Nokia X60", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.0f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 8.5f),
                "GPU" to PhoneAttribute("Adreno 740", 8.5f),
                "Size" to PhoneAttribute("165 x 76 x 8.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 8.5f)
            )),
            Phone(0, "Motorola Edge 3", mapOf(
                "Software" to PhoneAttribute("Android 14", 9.0f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 8.5f),
                "GPU" to PhoneAttribute("Adreno 740", 8.5f),
                "Size" to PhoneAttribute("165 x 76 x 8.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 8.5f)
            )),
            Phone(0, "Samsung Galaxy S23", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("3900 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("146.3 x 70.9 x 7.6 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 9.0f)
            )),
            Phone(0, "iPhone 15", mapOf(
                "Software" to PhoneAttribute("iOS 17", 9.5f),
                "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                "Camera" to PhoneAttribute("48 MP", 9.5f),
                "Battery" to PhoneAttribute("3279 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("6 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("A16 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("146.7 x 71.5 x 7.8 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$899", 9.0f)
            )),
            Phone(0, "Google Pixel 7", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.3 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4355 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Google Tensor G2", 9.0f),
                "GPU" to PhoneAttribute("Mali-G710 MP7", 8.5f),
                "Size" to PhoneAttribute("155.6 x 73.2 x 8.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$599", 8.5f)
            )),
            Phone(0, "OnePlus 11", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("163.1 x 74.1 x 8.5 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 9.0f)
            )),
            Phone(0, "Xiaomi 13 Pro", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.73 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4820 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("162.9 x 74.6 x 8.4 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 9.0f)
            )),
            Phone(0, "Sony Xperia 1 V", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                "Camera" to PhoneAttribute("48 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("165 x 71 x 8.2 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1299", 9.0f)
            )),
            Phone(0, "Oppo Find X5 Pro", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("163.7 x 73.9 x 8.5 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1199", 9.0f)
            )),
            Phone(0, "Huawei P60 Pro", mapOf(
                "Software" to PhoneAttribute("HarmonyOS 3.0", 9.0f),
                "Screen" to PhoneAttribute("6.6 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4815 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8+ Gen 1", 9.5f),
                "GPU" to PhoneAttribute("Adreno 730", 9.0f),
                "Size" to PhoneAttribute("161 x 74.5 x 8.3 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1099", 9.0f)
            )),
            Phone(0, "Asus ROG Phone 7", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.5f),
                "Screen" to PhoneAttribute("6.78 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("6000 mAh", 9.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.5f),
                "Microphone" to PhoneAttribute("Dual", 9.5f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("512 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 8 Gen 2", 9.5f),
                "GPU" to PhoneAttribute("Adreno 740", 9.5f),
                "Size" to PhoneAttribute("173 x 77 x 10.3 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 9.0f)
            )),
            Phone(0, "Nokia X50", mapOf(
                "Software" to PhoneAttribute("Android 13", 9.0f),
                "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 778G", 8.5f),
                "GPU" to PhoneAttribute("Adreno 642L", 8.5f),
                "Size" to PhoneAttribute("164.9 x 76.2 x 8.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$699", 8.5f)
            )),
            Phone(0, "Samsung Galaxy Note 20", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.7 inches", 9.0f),
                "Camera" to PhoneAttribute("64 MP", 9.0f),
                "Battery" to PhoneAttribute("4300 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                "CPU" to PhoneAttribute("Exynos 990", 9.0f),
                "GPU" to PhoneAttribute("Mali-G77 MP11", 8.5f),
                "Size" to PhoneAttribute("161.6 x 75.2 x 8.3 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 8.5f)
            )),
            Phone(0, "iPhone 12", mapOf(
                "Software" to PhoneAttribute("iOS 14", 9.0f),
                "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("2815 mAh", 7.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("4 GB", 8.5f),
                "Internal_Memory" to PhoneAttribute("64 GB", 8.5f),
                "CPU" to PhoneAttribute("A14 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("146.7 x 71.5 x 7.4 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 8.5f)
            )),
            Phone(0, "Google Pixel 5", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.0 inches", 8.5f),
                "Camera" to PhoneAttribute("12.2 MP", 9.0f),
                "Battery" to PhoneAttribute("4080 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Aluminum", 8.5f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 765G", 8.5f),
                "GPU" to PhoneAttribute("Adreno 620", 8.0f),
                "Size" to PhoneAttribute("144.7 x 70.4 x 8 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$699", 8.5f)
            )),
            Phone(0, "OnePlus 8T", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.55 inches", 9.0f),
                "Camera" to PhoneAttribute("48 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("160.7 x 74.1 x 8.4 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$749", 8.5f)
            )),
            Phone(0, "Xiaomi Mi 10", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("4780 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("162.5 x 74.8 x 9 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$699", 8.5f)
            )),
            Phone(0, "Sony Xperia 5 II", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.1 inches", 9.0f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 865", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("158 x 68 x 8 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$949", 8.5f)
            )),
            Phone(0, "Oppo Reno 4 Pro", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.5 inches", 9.0f),
                "Camera" to PhoneAttribute("48 MP", 9.0f),
                "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 720G", 8.5f),
                "GPU" to PhoneAttribute("Adreno 618", 8.0f),
                "Size" to PhoneAttribute("160.2 x 73.2 x 7.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$599", 8.5f)
            )),
            Phone(0, "Huawei Mate 40 Pro", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.76 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4400 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Kirin 9000", 9.0f),
                "GPU" to PhoneAttribute("Mali-G78 MP24", 8.5f),
                "Size" to PhoneAttribute("162.9 x 75.5 x 9.1 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1099", 8.5f)
            )),
            Phone(0, "Asus Zenfone 7 Pro", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.67 inches", 9.0f),
                "Camera" to PhoneAttribute("64 MP", 9.0f),
                "Battery" to PhoneAttribute("5000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 865+", 9.0f),
                "GPU" to PhoneAttribute("Adreno 650", 8.5f),
                "Size" to PhoneAttribute("165.1 x 77.3 x 9.6 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 8.5f)
            )),
            Phone(0, "Nokia 9 PureView", mapOf(
                "Software" to PhoneAttribute("Android 9", 8.0f),
                "Screen" to PhoneAttribute("5.99 inches", 8.5f),
                "Camera" to PhoneAttribute("12 MP", 8.5f),
                "Battery" to PhoneAttribute("3320 mAh", 7.5f),
                "Build_Quality" to PhoneAttribute("Glass", 8.5f),
                "Speaker" to PhoneAttribute("Mono", 7.5f),
                "Microphone" to PhoneAttribute("Dual", 8.0f),
                "RAM" to PhoneAttribute("6 GB", 8.5f),
                "Internal_Memory" to PhoneAttribute("128 GB", 8.5f),
                "CPU" to PhoneAttribute("Snapdragon 845", 8.5f),
                "GPU" to PhoneAttribute("Adreno 630", 8.0f),
                "Size" to PhoneAttribute("155 x 75 x 8 mm", 8.0f),
                "Reviews" to PhoneAttribute("Good", 8.5f),
                "User_Opinions" to PhoneAttribute("Positive", 8.5f),
                "Popularity" to PhoneAttribute("High", 8.5f),
                "Price" to PhoneAttribute("$699", 8.0f)
            )),
            Phone(0, "Samsung Galaxy S21", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.2 inches", 9.0f),
                "Camera" to PhoneAttribute("64 MP", 9.5f),
                "Battery" to PhoneAttribute("4000 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Exynos 2100", 9.0f),
                "GPU" to PhoneAttribute("Mali-G78 MP14", 8.5f),
                "Size" to PhoneAttribute("151.7 x 71.2 x 7.9 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 8.5f)
            )),
            Phone(0, "iPhone 13", mapOf(
                "Software" to PhoneAttribute("iOS 15", 9.5f),
                "Screen" to PhoneAttribute("6.1 inches", 9.5f),
                "Camera" to PhoneAttribute("12 MP", 9.5f),
                "Battery" to PhoneAttribute("3240 mAh", 8.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.5f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("4 GB", 8.5f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("A15 Bionic", 9.5f),
                "GPU" to PhoneAttribute("Apple GPU", 9.5f),
                "Size" to PhoneAttribute("146.7 x 71.5 x 7.7 mm", 9.0f),
                "Reviews" to PhoneAttribute("Excellent", 9.5f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$799", 9.0f)
            )),
            Phone(0, "Google Pixel 6", mapOf(
                "Software" to PhoneAttribute("Android 12", 9.0f),
                "Screen" to PhoneAttribute("6.4 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4614 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Google Tensor", 9.0f),
                "GPU" to PhoneAttribute("Mali-G78 MP20", 8.5f),
                "Size" to PhoneAttribute("158.6 x 74.8 x 8.9 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$599", 8.5f)
            )),
            Phone(0, "OnePlus 9", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.55 inches", 9.0f),
                "Camera" to PhoneAttribute("48 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                "Size" to PhoneAttribute("160 x 74.2 x 8.7 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$729", 8.5f)
            )),
            Phone(0, "Xiaomi Mi 11", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.81 inches", 9.5f),
                "Camera" to PhoneAttribute("108 MP", 9.5f),
                "Battery" to PhoneAttribute("4600 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                "Size" to PhoneAttribute("164.3 x 74.6 x 8.1 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$749", 8.5f)
            )),
            Phone(0, "Sony Xperia 1 III", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.5 inches", 9.5f),
                "Camera" to PhoneAttribute("12 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                "Size" to PhoneAttribute("165 x 71 x 8.2 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1299", 8.5f)
            )),
            Phone(0, "Oppo Find X3 Pro", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.7 inches", 9.5f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("12 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                "Size" to PhoneAttribute("163.6 x 74 x 8.3 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$1149", 8.5f)
            )),
            Phone(0, "Huawei P40 Pro", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.58 inches", 9.0f),
                "Camera" to PhoneAttribute("50 MP", 9.5f),
                "Battery" to PhoneAttribute("4200 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.0f),
                "CPU" to PhoneAttribute("Kirin 990 5G", 9.0f),
                "GPU" to PhoneAttribute("Mali-G76 MP16", 8.5f),
                "Size" to PhoneAttribute("158.2 x 72.6 x 9 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 8.5f)
            )),
            Phone(0, "Asus ROG Phone 5", mapOf(
                "Software" to PhoneAttribute("Android 11", 9.0f),
                "Screen" to PhoneAttribute("6.78 inches", 9.5f),
                "Camera" to PhoneAttribute("64 MP", 9.0f),
                "Battery" to PhoneAttribute("6000 mAh", 9.0f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 9.0f),
                "Microphone" to PhoneAttribute("Dual", 9.0f),
                "RAM" to PhoneAttribute("16 GB", 9.5f),
                "Internal_Memory" to PhoneAttribute("256 GB", 9.5f),
                "CPU" to PhoneAttribute("Snapdragon 888", 9.0f),
                "GPU" to PhoneAttribute("Adreno 660", 8.5f),
                "Size" to PhoneAttribute("172.8 x 77.3 x 10.3 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$999", 8.5f)
            )),
            Phone(0, "Nokia 8.3 5G", mapOf(
                "Software" to PhoneAttribute("Android 10", 8.5f),
                "Screen" to PhoneAttribute("6.81 inches", 9.0f),
                "Camera" to PhoneAttribute("64 MP", 9.0f),
                "Battery" to PhoneAttribute("4500 mAh", 8.5f),
                "Build_Quality" to PhoneAttribute("Glass", 9.0f),
                "Speaker" to PhoneAttribute("Stereo", 8.5f),
                "Microphone" to PhoneAttribute("Dual", 8.5f),
                "RAM" to PhoneAttribute("8 GB", 9.0f),
                "Internal_Memory" to PhoneAttribute("128 GB", 9.0f),
                "CPU" to PhoneAttribute("Snapdragon 765G", 8.5f),
                "GPU" to PhoneAttribute("Adreno 620", 8.0f),
                "Size" to PhoneAttribute("171.9 x 78.6 x 9 mm", 8.5f),
                "Reviews" to PhoneAttribute("Excellent", 9.0f),
                "User_Opinions" to PhoneAttribute("Very Positive", 9.5f),
                "Popularity" to PhoneAttribute("Very High", 9.5f),
                "Price" to PhoneAttribute("$699", 8.5f)
            ))

        )

        initialPhones.forEach { phone ->
            val values = ContentValues().apply {
                put(COLUMN_PHONE_NAME, phone.nombre)
                phone.attributes.forEach { (key, attribute) ->
                    put(key, attribute.specification)
                    put("${key}_score", attribute.score)
                }
            }
            db.insert(TABLE_PHONE, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PHONE")
        onCreate(db)
    }

    fun addUser(username: String, password: String, role: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_ROLE, role)
        }
        return db.insert(TABLE_USER, null, values)
    }

    fun deleteUser(username: String): Int {
        val db = writableDatabase
        return db.delete(TABLE_USER, "$COLUMN_USERNAME = ?", arrayOf(username))
    }

    fun updateUser(oldUsername: String, newUsername: String, newPassword: String, newRole: String): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, newUsername)
            put(COLUMN_PASSWORD, newPassword)
            put(COLUMN_ROLE, newRole)
        }
        return db.update(TABLE_USER, values, "$COLUMN_USERNAME = ?", arrayOf(oldUsername))
    }

    fun getAllUsers(): List<User> {
        val db = readableDatabase
        val cursor = db.query(TABLE_USER, null, null, null, null, null, null)
        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(COLUMN_USERNAME))
                val password = getString(getColumnIndexOrThrow(COLUMN_PASSWORD))
                val role = getString(getColumnIndexOrThrow(COLUMN_ROLE))
                users.add(User(username, password, role))
            }
        }
        cursor.close()
        return users
    }

    fun userExists(username: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USER,
            arrayOf(COLUMN_USERNAME),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun addPhone(phone: Phone): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PHONE_NAME, phone.nombre)
            phone.attributes.forEach { (key, attribute) ->
                this.put(key, attribute.specification)
                this.put("${key}_score", attribute.score)
            }
        }
        return db.insert(TABLE_PHONE, null, values)
    }

    fun deletePhone(phoneId: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_PHONE, "$COLUMN_ID = ?", arrayOf(phoneId.toString()))
    }

    fun getAllPhones(): List<Phone> {
        val db = readableDatabase
        val cursor = db.query(TABLE_PHONE, null, null, null, null, null, null)
        val phones = mutableListOf<Phone>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val nombre = getString(getColumnIndexOrThrow(COLUMN_PHONE_NAME))
                val attributes = mutableMapOf<String, PhoneAttribute>()
                cursor.columnNames.forEach { columnName ->
                    if (columnName != COLUMN_ID && columnName != COLUMN_PHONE_NAME && !columnName.endsWith("_score")) {
                        val specification = getString(getColumnIndexOrThrow(columnName))
                        val score = getFloat(getColumnIndexOrThrow("${columnName}_score"))
                        attributes[columnName] = PhoneAttribute(specification, score)
                    }
                }
                phones.add(Phone(id, nombre, attributes))
            }
        }
        cursor.close()
        return phones
    }
    /*
    fun getPhonesByPriceRange(minPrice: Float, maxPrice: Float): List<Phone> {
        val db = readableDatabase
        val cursor = db.query(TABLE_PHONE, null, null, null, null, null, null)
        val phones = mutableListOf<Phone>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val nombre = getString(getColumnIndexOrThrow(COLUMN_PHONE_NAME))
                val attributes = mutableMapOf<String, PhoneAttribute>()
                var price: Float? = null
                cursor.columnNames.forEach { columnName ->
                    if (columnName != COLUMN_ID && columnName != COLUMN_PHONE_NAME && !columnName.endsWith("_score")) {
                        val specification = getString(getColumnIndexOrThrow(columnName))
                        val score = getFloat(getColumnIndexOrThrow("${columnName}_score"))
                        attributes[columnName] = PhoneAttribute(specification, score)
                        if (columnName == "Price") {
                            price = specification.replace(Regex("[^\\d.]"), "").toFloatOrNull()
                        }
                    }
                }
                val currentPrice = price
                if (currentPrice != null && currentPrice in minPrice..maxPrice) {
                    phones.add(Phone(id, nombre, attributes))
                }
            }
        }
        cursor.close()
        return phones
    }
*/
    companion object {
        private const val DATABASE_NAME = "phonerecom.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_USER = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_ROLE = "role"

        private const val CREATE_USER_TABLE = "CREATE TABLE $TABLE_USER (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT, " +
                "$COLUMN_ROLE TEXT)"

        const val TABLE_PHONE = "phone"
        const val COLUMN_PHONE_NAME = "name"

        private const val CREATE_PHONE_TABLE = "CREATE TABLE $TABLE_PHONE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PHONE_NAME TEXT, " +
                "Software TEXT, Software_score REAL, " +
                "Screen TEXT, Screen_score REAL, " +
                "Camera TEXT, Camera_score REAL, " +
                "Battery TEXT, Battery_score REAL, " +
                "Build_Quality TEXT, Build_Quality_score REAL, " +
                "Speaker TEXT, Speaker_score REAL, " +
                "Microphone TEXT, Microphone_score REAL, " +
                "RAM TEXT, RAM_score REAL, " +
                "Internal_Memory TEXT, Internal_Memory_score REAL, " +
                "CPU TEXT, CPU_score REAL, " +
                "GPU TEXT, GPU_score REAL, " +
                "Size TEXT, Size_score REAL, " +
                "Reviews TEXT, Reviews_score REAL, " +
                "User_Opinions TEXT, User_Opinions_score REAL, " +
                "Popularity TEXT, Popularity_score REAL, " +
                "Price TEXT, Price_score REAL)"
    }
}

data class User(val username: String, val password: String, val role: String)
data class Phone(val id: Int, val nombre: String, val attributes: Map<String, PhoneAttribute>)
data class PhoneAttribute(val specification: String, val score: Float)