package com.example.textmesh.ui.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.textmesh.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_fason_add.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class FasonAddActivity : AppCompatActivity() {
    private lateinit var urunKodu: EditText
    private lateinit var urunAdi: EditText
    private lateinit var urunRengi: EditText
    private lateinit var urunAtolyesi: EditText
    private lateinit var urunUretimNo: EditText
    private lateinit var urunTalimatAdeti: EditText
    private lateinit var addBtn: Button
    private lateinit var db: FirebaseFirestore
    private lateinit var finis: MutableMap<Any, Any>
    var selectedPhotoUri: Uri? = null
    private lateinit var terminDate: TextView
    private lateinit var fasonTermin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fason_add)
        db = FirebaseFirestore.getInstance()
        urunKodu = findViewById(R.id.fasonUrunKodu)
        urunAdi = findViewById(R.id.fasonUrunAdı)
        urunRengi = findViewById(R.id.fasonUrunRengi)
        urunAtolyesi = findViewById(R.id.fasonUrunAtolyesi)
        addBtn = findViewById(R.id.fasonButton)
        urunUretimNo = findViewById(R.id.fasonUretimNo)
        urunTalimatAdeti = findViewById(R.id.fasonUrunTalimatAdeti)
        terminDate = findViewById(R.id.fasonTerminDate)
        fasonTermin = findViewById(R.id.fasonTermin)
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }
        fasonTermin.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        fasonAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        addBtn.setOnClickListener {
            uploadImage()
        }

    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd/MM/yy"
        val sdf = SimpleDateFormat(myFormat)
        terminDate.setText(sdf.format(myCalendar.time))
    }

    override fun onBackPressed() {
        val intent = Intent(this, FasonActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitmapDrawable = BitmapDrawable(bitmap)
            fasonAddImage.setBackgroundDrawable(bitmapDrawable)
            fasonAddImage.text = ""
        }
    }

    private fun addProduct(imageUrl: String) {
        val kod: String = urunKodu.text.toString()
        val ad: String = urunAdi.text.toString()
        val renk: String = urunRengi.text.toString()
        val atolye: String = urunAtolyesi.text.toString()
        val termin: String = terminDate.text.toString()
        val uretimNo: String = urunUretimNo.text.toString()
        val talimatAdeti = urunTalimatAdeti.text.toString()
        finis = HashMap()
        finis["modelKodu"] = kod
        finis["model"] = ad
        finis["renk"] = renk
        finis["atolye"] = atolye
        finis["sonDurum"] = "Beklemede"
        finis["imageUrl"] = imageUrl
        finis["talimatAdeti"] = talimatAdeti.toInt()
        finis["uretimNo"] = uretimNo
        finis["termin"] = termin
        finis["beden"] = arrayListOf(true, false, true)

        db.collection("fasonUrunler")
            .add(finis)
            .addOnSuccessListener {
                Toast.makeText(this, "Urun Emri Başarılı", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Urun Emri Başarısız", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImage() {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    addProduct(it.toString())
                }
            }
    }
}