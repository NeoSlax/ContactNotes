package ru.eltech.contactnotes.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import ru.eltech.contactnotes.databinding.ActivityMainBinding
import ru.eltech.contactnotes.di.DaggerApplicationComponent
import ru.eltech.contactnotes.presentation.adapters.NoteListAdapter
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as ContactNotesApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel.contactNoteList.observe(this) {
            adapter.submitList(it)
        }
            readContacts()

    }

    private fun readContacts() {
        val readContactPermission = ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_CONTACTS)
        if (readContactPermission == PackageManager.PERMISSION_GRANTED) {
            viewModel.syncContact()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUIRE_CONTACTS_RC
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUIRE_CONTACTS_RC && permissions.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setupRecyclerView() {
        adapter = NoteListAdapter()
        binding.rvMain.adapter = adapter

        adapter.onItemClickListener = {
            val intent = EditItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
    }

    companion object {
        private val REQUIRE_CONTACTS_RC = 100
    }

}