package com.example.bou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_nike.*

class Nike : AppCompatActivity() {
    private val REQUEST_CODE_QR_SCAN = 101;
    var scannedResult: String = ""
    val uid =arrayListOf<String?>();
    val camisas = arrayListOf<CamisaData>()
    val codigos = arrayListOf<CamisaData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nike)
        scan.setOnClickListener {
            run {
                IntentIntegrator(this@Nike).initiateScan();
            }
        }
        firebase();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            if(result.contents != null){
                scannedResult = result.contents

                if(camisas.any{it.ID == scannedResult})
                {
                    val intent = Intent(this, Camisa::class.java)
                    for (i in camisas.indices) {
                        if(camisas[i].ID == scannedResult)
                        {
                            val b = Bundle()
                            b.putString("uid", uid[i])
                            b.putString("ID", camisas[i].ID)
                            b.putString("Descripcion", camisas[i].Descripcion)
                            b.putString("Pagado", camisas[i].Pagado)
                            b.putString("Precio", camisas[i].Precio)
                            intent.putExtras(b) //Put your id to your next Intent
                        }

                    }
                    startActivity(intent)
                }
                else
                {
                    textOut.text = "Clothe not found."
                }
            } else {
                textOut.text = "Scan failed"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    fun firebase(){
        val ref = FirebaseDatabase.getInstance().getReference("Codigos")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (camisaSnapshot in dataSnapshot.children) {
                    uid.add(camisaSnapshot.key)
                    val camisa = camisaSnapshot.getValue(CamisaData::class.java)
                    camisas.add(camisa!!)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })

    }

}
