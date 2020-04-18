package com.example.bou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_camisa.*


class Camisa : AppCompatActivity() {

    var uid: String = ""
    var Descripcion: String = ""
    var ID: String = ""
    var Pagado: String = ""
    var Precio: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camisa)

        val b = intent.extras

        if (b != null) {

            uid = b.getString("uid").toString()
            ID = b.getString("ID").toString()
            Descripcion = b.getString("Descripcion").toString()
            Pagado = b.getString("Pagado").toString()
            Precio = b.getString("Precio").toString()
        }

        descripcionTxt.text = Descripcion
        precioTxt.text = Precio;
    }


    fun comprar(view: View){
        val camisas = arrayListOf<CamisaData>()
        val ref = FirebaseDatabase.getInstance().getReference("Codigos")
        val childUpdates = HashMap<String, Any>()

        val post = CamisaData(Descripcion,ID,"\"1\"",Precio)
        val postValues = post.toMap()

        childUpdates["/$uid"] = postValues
        ref.updateChildren(childUpdates)

        val intent = Intent(this, compraExitosa::class.java)
        startActivity(intent)
    }





}
