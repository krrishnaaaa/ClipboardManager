package com.pcsalt.example.clipboardmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pcsalt.example.clipboardmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

    binding.btnCopy.setOnClickListener {
      val text = binding.etInput.text.toString().trim()
      if (text.isEmpty()) {
        binding.etInput.error = "Enter text to copy"
        return@setOnClickListener
      }
      val clip = ClipData.newPlainText("copied_text", text)
      clipboard.setPrimaryClip(clip)
      Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    binding.btnPaste.setOnClickListener {
      val clip = clipboard.primaryClip
      if (clip != null && clip.itemCount > 0) {
        val pastedText = clip.getItemAt(0).text.toString()
        binding.tvPastedText.text = pastedText
      } else {
        Toast.makeText(this, "Clipboard is empty", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
