package com.duktiv.gameku

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.duktiv.gameku.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedGame =intent.getParcelableExtra<Game>(GameAdapter.KEY_GAME)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = receivedGame?.nama
        binding.gambarDetail.setImageResource(receivedGame?.poto ?: 0)
        binding.namaJudul.text =receivedGame?.nama
        binding.isiDetail.text = receivedGame?.desc
        binding.isiSpek.text = Html.fromHtml(receivedGame?.spek, Html.FROM_HTML_MODE_LEGACY)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.action_share -> {
                val receivedGame = intent.getParcelableExtra<Game>(GameAdapter.KEY_GAME)
                val tvDetailIsi = findViewById<TextView>(R.id.isi_detail)
                tvDetailIsi.text = receivedGame?.desc
                binding.isiSpek.text = Html.fromHtml(receivedGame?.spek, Html.FROM_HTML_MODE_LEGACY)

                val sharing = Intent(Intent.ACTION_SEND)
                sharing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                sharing.type = "text/plain"
                sharing.putExtra(Intent.EXTRA_TEXT, tvDetailIsi.text)

                startActivity(Intent.createChooser(sharing, "Share menggunakan"))

            }
        }
        return super.onOptionsItemSelected(item)
    }
}