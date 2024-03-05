package com.duktiv.gameku

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duktiv.gameku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvGame: RecyclerView
    private val listGame= ArrayList<Game>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvGame = binding.rvGame
        rvGame.setHasFixedSize(true)

        listGame.addAll(getListGame())
        showRl()
    }

    private fun showRl() {
        rvGame.layoutManager = LinearLayoutManager(this)
        val gameAdapter = GameAdapter(listGame)
        rvGame.adapter = gameAdapter
    }

    private fun getListGame(): Collection<Game> {
        val dataPoto = resources.obtainTypedArray(R.array.data_photo)
        val dataNama = resources.getStringArray(R.array.data_nama)
        val dataIsi = resources.getStringArray(R.array.data_isi)
        val spek = resources.getStringArray(R.array.data_spek)
        val listGamee = ArrayList<Game>()

        for (i in dataNama.indices) {
            val game = Game(dataPoto.getResourceId(i, -1), dataNama[i], dataIsi[i], spek[i])
            listGamee.add(game)
        }

        return listGamee
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about){
            val moveIntent = Intent(this@MainActivity, About::class.java)
            startActivity(moveIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}