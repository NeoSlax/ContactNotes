package ru.eltech.contactnotes.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.eltech.contactnotes.R
import ru.eltech.contactnotes.databinding.ActivityEditItemBinding
import ru.eltech.contactnotes.databinding.ActivityMainBinding
import javax.inject.Inject

class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding

    private val component by lazy {
        (application as ContactNotesApp).component.editItemComponentFactory()
            .create(parseIntentId())
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EditItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupVM()
    }

    private fun setupVM() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setupListener()
        viewModel.isFinished.observe(this) {
            finish()
        }

    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener {
            val note = binding.etNote.text.toString()
            viewModel.addNoteItem(note)
        }
    }

    private fun parseIntentId(): Int {
        if (intent.hasExtra(ID)) {
            return intent.getIntExtra(ID, UNDEF_ID)
        } else {
            throw RuntimeException("EditItemActivity started with id == null")
        }
    }

    companion object {
        fun newIntentEditItem(context: Context, id: Int): Intent {
            return Intent(context, EditItemActivity::class.java).apply {
                putExtra(ID, id)
            }
        }

        private const val ID = "id"
        private const val UNDEF_ID = -1
    }
}